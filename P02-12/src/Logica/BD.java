package Logica;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;

public class BD {
	static Logger log;
	private static Exception lastError = null; // Información de último error
												// SQL ocurrido
	
	/**
	 * Inicializa una BD SQLITE y devuelve una conexión con ella
	 * 
	 * @param nombreBD
	 *            Nombre de fichero de la base de datos
	 * @return Conexión con la base de datos indicada. Si hay algún error, se
	 *         devuelve null
	 */
	public static Connection initBD(String nombreBD) {
		log = Logger.getLogger("LOG");
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Devuelve statement para usar la base de datos
	 * 
	 * @param con
	 *            Conexión ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay
	 *         cualquier error
	 */
	public static Statement usarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Crea las tablas de la base de datos. Si ya existen, las deja tal cual
	 * 
	 * @param con
	 *            Conexión ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay
	 *         cualquier error
	 */
	public static Statement usarCrearTablasBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			try {
				statement.executeUpdate(
						"create table usuario " + "(nick string, contraseña string, email string, NumeroCuenta int)");
			} catch (SQLException e) {
			} // Tabla ya existe. Nada que hacer
			return statement;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Reinicia en blanco las tablas de la base de datos. UTILIZAR ESTE MËTODO
	 * CON PRECAUCIÓN. Borra todos los datos que hubiera ya en las tablas
	 * 
	 * @param con
	 *            Conexión ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se borra correctamente, null si hay
	 *         cualquier error
	 */
	public static Statement reiniciarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			statement.executeUpdate("drop table if exists usuario");
			return usarCrearTablasBD(con);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Cierra la base de datos abierta y cierra el hilo de proceso de base de
	 * datos
	 * 
	 * @param con
	 *            Conexión abierta de la BD
	 * @param st
	 *            Sentencia abierta de la BD
	 */
	public static void cerrarBD(final Connection con, final Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve la información de excepción del último error producido por
	 * cualquiera de los métodos de gestión de base de datos
	 */
	public static Exception getLastError() {
		return lastError;
	}

	/* MÉTODOS DEL PROYECTO */

	/**
	 * Devuelve información sobre la existencia del usuario en la bd
	 * 
	 * @param nick
	 * @param contraseña
	 * @return 0: Si el nick es incorrecto 1: Si el nick es correcto pero la
	 *         contraseña no 2: Si el nick y la contraseña son correctos
	 * 
	 */

	public static int buscarUsuario(String nick, String contrasenya) {
		int resultado = 0;
		String query = "SELECT * FROM usuario WHERE Nick='" + nick + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				String cl = rs.getString(2);
				if (cl.equals(contrasenya)) {
					resultado = 2;
				} else {
					resultado = 1;
				}
			} else {
				resultado = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		System.err.println(resultado);
		return resultado;

	}

	public static int buscarNombreUsuario(String nick) {
		int resultado = 0;
		String query = "SELECT * FROM usuario WHERE Nick='" + nick + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				String cl = rs.getString(1);
				if (cl.equals(nick)) {
					resultado = 1; // nombres iguales
				}
			} else {
				resultado = 0; // no encontrado
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		System.err.println(resultado);
		return resultado;

	}

	public static void registrarUsuario(String nick, String email, String contrasenya) {
		log.log(Level.INFO, " Nuevo usuario registrado en BD: " + (new Date()));
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String sql = "INSERT INTO usuario VALUES('" + nick + "','" + email + "','" + contrasenya + "')";
		try {
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void registrarCarta(String nombre, String edicion, String rareza, String precio, String ruta,
			String referencia, String stock) {
		log.log(Level.INFO, " Carta añadida a BD: " + (new Date()));
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String sql = "INSERT INTO Cartas VALUES('" + nombre + "','" + edicion + "','" + rareza + "','" + precio + "','"
				+ ruta + "','" + referencia + "','" + stock + "')";
		try {
			st.executeUpdate(sql);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void eliminarCarta(String nombre) {
		log.log(Level.INFO, " Carta eliminada de BD: " + (new Date()));
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String sql = "DELETE FROM Cartas WHERE Nombre ='" + nombre + "'";
		try {
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void cambiarNombreUsuario(String nombre, String nuevoNombre) {
		log.log(Level.INFO, " Nombre de usuario actualizado: " + (new Date()));
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String sql = "UPDATE usuario SET Nick ='" + nuevoNombre + "' WHERE Nick ='" + nombre + "'";
		try {
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void cambiarContrasenyaUsuario(String nombre, String nuevaContrasenya) {		
		log.log(Level.INFO, " Contrasenya cargada: " + (new Date()));
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String sql = "UPDATE usuario SET Clave ='" + nuevaContrasenya + "' WHERE Nick ='" + nombre + "'";
		try {
			st.executeUpdate(sql);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void anyadirStock(String nombre, String edicion, String stock) {	
		log.log(Level.INFO, " Stock añadido: " + (new Date()));
		String sql = "UPDATE Cartas SET (Stock = ?) WHERE (Nombre = ?, Edicion = ?)";
		Connection con = BD.initBD("BaseDeDatos.db");

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			// las interrogaciones (la primera mete el stmt 1), etc
			stmt.setString(1, stock);
			stmt.setString(2, nombre);
			stmt.setString(3, edicion);

			stmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int buscarCarta(String nombre) {
		int resultado = 0;
		String query = "SELECT * FROM Cartas WHERE Nombre='" + nombre + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				String non = rs.getString(1);
				if (non.equals(nombre)) {
					resultado = 2;
				} else {
					resultado = 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		System.err.println(resultado);
		return resultado;

	}

	public static ArrayList<String> obtenerTodasRutasFotos() {
		
		ArrayList<String> rutas = new ArrayList<String>();
		String sql = "SELECT Ruta FROM Cartas";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		log.log(Level.INFO, "Todas las rutas obtenidas :" + (new Date()));
		
		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String r = rs.getString("Ruta");
			//	System.out.println(r);
		
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

	public static ArrayList<String> obtenerRutasFotos(Carta c){
		log.log(Level.INFO, " Todas las rutas obtenidas: " + (new Date()));
		ArrayList<String> rutas = new ArrayList<String>();
		String sql = "SELECT ruta FROM Cartas WHERE Nombre='"+c.getNombreCarta()+"' AND Rareza='"+c.getRareza()+"' AND Precio='"+c.getPrecio()+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String r = rs.getString("ruta");
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
	public static ArrayList<String> obtenerRutasFotos1(Carta c){

		ArrayList<String> rutas = new ArrayList<String>();
		String sql = "SELECT ruta FROM Cartas WHERE Nombre='"+c.getNombreCarta()+"' AND Edicion='"+c.getEdicion()+"' AND Precio='"+c.getPrecio()+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String r = rs.getString("ruta");
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
	public static ArrayList<String> obtenerRutasFotos2(Carta c) { 
		ArrayList<String> rutas = new ArrayList<String>();
		String sql = "";
		if (c.getNombreCarta().equals("") && c.getEdicion().equals("") && c.getRareza().equals("") && c.getPrecio()==0) //cast
			sql = "SELECT ruta FROM producto WHERE Nombre='" + c.getNombreCarta() + "' AND Edicion='" + c.getEdicion() + "' AND Rareza='"
					+ c.getEdicion() + "'" + "' AND Precio='" + c.getPrecio();
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);

		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String r = rs.getString("ruta");
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
	
	public static Carta obtenerDatosCarta(String ruta) {
		log.log(Level.INFO, " Datos de la carta obtenidos desde BD: " + (new Date()));
		String sql = "SELECT * FROM Cartas WHERE Ruta='"+ruta+"'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		ResultSet rs;
		Carta c=null;
		try {
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				c = new Carta(rs.getString("Nombre"), rs.getString("Rareza"), rs.getString("Edicion"), Float.parseFloat(rs.getString("Precio")));
			}
			st.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	
	public static float obtenerPrecioCarta(String ruta) {
		log.log(Level.INFO, " Precio de carta obtenido: " + (new Date()));
		String sql = "SELECT Precio FROM Cartas WHERE Ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		float precio = 0;
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
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
		log.log(Level.INFO, " Ruta de carta obtenida desde BD: " + (new Date()));
		String sql = "SELECT Ruta FROM Cartas WHERE Referencia=" + referencia;
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String ruta = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				ruta = rs.getString("Ruta");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return ruta;
	}

	public static String obtenerNombreCarta(String ruta) {
		log.log(Level.INFO, " Obtenido nombre de carta desde BD: " + (new Date()));
		String sql = "SELECT Nombre FROM Cartas WHERE Ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String nombre = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
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
		log.log(Level.INFO, " Obtenida rareza de carta desde BD: " + (new Date()));
		String sql = "SELECT Rareza FROM Cartas WHERE Ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String rareza = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
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
