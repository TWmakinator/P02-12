
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/** Clase de gestión de base de datos del examen
 * Funciona con un hilo propio para no interrumpir el tiempo de ejecución
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class BD {
	

	private static Exception lastError = null;  // Información de último error SQL ocurrido
	
	/** Inicializa una BD SQLITE y devuelve una conexión con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	
	/** Devuelve statement para usar la base de datos
	 * @param con	Conexión ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	public static Statement usarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	
	/** Crea las tablas de la base de datos. Si ya existen, las deja tal cual
	 * @param con	Conexión ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	public static Statement usarCrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			try {
				statement.executeUpdate("create table usuario " +
					"(nick string, clave string, email string, NumeroCuenta int)");
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			return statement;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	
	/** Reinicia en blanco las tablas de la base de datos. 
	 * UTILIZAR ESTE MËTODO CON PRECAUCIÓN. Borra todos los datos que hubiera ya en las tablas
	 * @param con	Conexión ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se borra correctamente, null si hay cualquier error
	 */
	public static Statement reiniciarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			statement.executeUpdate("drop table if exists usuario");
			return usarCrearTablasBD( con );
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	
	/** Cierra la base de datos abierta y cierra el hilo de proceso de base de datos
	 * @param con	Conexión abierta de la BD
	 * @param st	Sentencia abierta de la BD
	 */
	public static void cerrarBD( final Connection con, final Statement st ) {
			try {
				if (st!=null) st.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
			}
	}
	
	/** Devuelve la información de excepción del último error producido por cualquiera 
	 * de los métodos de gestión de base de datos
	 */
	public static Exception getLastError() {
		return lastError;
	}
	
	/*MÉTODOS DEL PROYECTO*/
	
	/**
	 * Devuelve información sobre la existencia del usuario en la bbdd
	 * @param nick
	 * @param clave
	 * @return  0: Si el nick es incorrecto
	 *           1: Si el nick es correcto pero la clave no
	 *           2: Si el nick y la clave son correctos
	 
	 */
	
	public static int buscarUsuario(String nick, String clave) {
		int resultado = 0;
		String query = "SELECT * FROM usuario WHERE Nick='"+nick+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				String cl = rs.getString(3);
				if(cl.equals(clave)){
					resultado = 2;
				}else {
					resultado = 1;
				}
			}/*else {
				resultado = 0;
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		System.err.println(resultado);
		return resultado;

	}
	
	public static void registrarUsuario(String nick, String email, String contraseña, String numeroCuenta ) {
		String sql = "INSERT INTO usuario VALUES('"+nick+"','"+email+"','"+contraseña+"','"+numeroCuenta+"')";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}
	
	
	public static ArrayList<String> obtenerTodasRutasFotos(){
		ArrayList<String> rutas = new ArrayList<String>();
		String sql = "SELECT Ruta FROM cartas";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String r = rs.getString("Ruta");
				System.out.println(r);
				rutas.add(r);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return rutas;
		
	}
	
	public static float obtenerPrecioCarta(String ruta) {
		String sql = "SELECT Precio FROM cartas WHERE Ruta='"+ruta+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		float precio = 0;
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				precio = rs.getFloat("Precio");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return precio;
	}
	
	public static String obtenerRutaFoto(int referencia) {
		String sql = "SELECT Ruta FROM cartas WHERE Referencia="+referencia;
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String ruta="";
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
				ruta = rs.getString("Ruta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return ruta;
	}
	
	public static String obtenerNombreCarta(String ruta) {
		String sql = "SELECT Nombre FROM cartas WHERE Ruta='"+ruta+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String nombre="";
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				nombre = rs.getString("Nombre");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return nombre;
	}
	
	public static String obtenerRarezaCarta(String ruta) {
		String sql = "SELECT Rareza FROM cartas WHERE Ruta='"+ruta+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String rareza="";
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				rareza = rs.getString("Rareza");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return rareza;
	}
	
}
