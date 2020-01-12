package BaseDeDatos;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;

import Logica.Carrito;
import Logica.Carta;

public class BD {
	static Logger log;
	private static Exception lastError = null;

	/**
	 * Inicializa una BD SQLITE y devuelve una conexión con ella
	 * 
	 * @param nombreBD Nombre de fichero de la base de datos
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
	 * @param con Conexión ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier
	 *         error
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
	 * @param con Conexión ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier
	 *         error
	 */
	public static Statement usarCrearTablasBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			try {
				statement.executeUpdate("create table usuario " + "(nick string, contrasenya string, email string)");
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
	 * Reinicia en blanco las tablas de la base de datos. UTILIZAR ESTE MËTODO CON
	 * PRECAUCIÓN. Borra todos los datos que hubiera ya en las tablas
	 * 
	 * @param con Conexión ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se borra correctamente, null si hay cualquier
	 *         error
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
	 * Cierra la base de datos abierta y cierra el hilo de proceso de base de datos
	 * 
	 * @param con Conexión abierta de la BD
	 * @param st  Sentencia abierta de la BD
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
		String query = "SELECT * FROM usuario WHERE nick='" + nick + "'";
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
		return resultado;

	}

	public static int buscarNombreUsuario(String nick) {
		int resultado = 0;
		String query = "SELECT * FROM usuario WHERE nick='" + nick + "'";
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
		return resultado;

	}

	public static void registrarUsuario(String nick, String contrasenya, String email) {
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Nuevo usuario registrado en BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		String sql = "INSERT INTO usuario VALUES('" + nick + "','" + contrasenya + "','" + email + "')";
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
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Carta añadida a BD: " + (new Date()));

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
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Carta eliminada de BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		String sql = "DELETE FROM Cartas WHERE nombre ='" + nombre + "'";
		try {
			st.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void cambiarNombreUsuario(String nombre, String nuevoNombre) {
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Nombre de usuario actualizado: " + (new Date()));

		Statement st = BD.usarBD(con);
		String sql = "UPDATE usuario SET nick ='" + nuevoNombre + "' WHERE nick ='" + nombre + "'";
		try {
			st.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static void cambiarContrasenyaUsuario(String nombre, String nuevaContrasenya) {
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Contrasenya cargada: " + (new Date()));

		Statement st = BD.usarBD(con);
		String sql = "UPDATE usuario SET clave ='" + nuevaContrasenya + "' WHERE nick ='" + nombre + "'";
		try {
			st.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
	}

	public static int buscarCarta(String nombre) {
		int resultado = 0;
		String query = "SELECT * FROM Cartas WHERE nombre='" + nombre + "'";
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
		String sql = "SELECT ruta FROM Cartas";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		log.log(Level.INFO, "Todas las rutas obtenidas :" + (new Date()));

		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String r = rs.getString("ruta");
				// System.out.println(r);

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

	public static ArrayList<String> obtenerRutasFotos(Carta c) {
		ArrayList<String> rutas = new ArrayList<String>();
		String sql = "SELECT ruta FROM Cartas WHERE nombre='" + c.getNombreCarta() + "' AND rareza='" + c.getRareza()
				+ "' AND precio='" + c.getPrecio() + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Todas las rutas obtenidas: " + (new Date()));

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
		String sql = "SELECT * FROM Cartas WHERE ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Datos de la carta obtenidos desde BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		ResultSet rs;
		Carta c = null;
		try {
			rs = st.executeQuery(sql);

			if (rs.next()) {
				c = new Carta(rs.getString("nombre"), rs.getString("rareza"), rs.getString("edicion"),
						Float.parseFloat(rs.getString("precio")));
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
		String sql = "SELECT precio FROM Cartas WHERE ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Precio de carta obtenido: " + (new Date()));

		Statement st = BD.usarBD(con);
		float precio = 0;
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				precio = rs.getFloat("precio");
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
		String sql = "SELECT ruta FROM Cartas WHERE referencia=" + referencia;
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Ruta de carta obtenida desde BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		String ruta = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				ruta = rs.getString("ruta");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return ruta;
	}

	public static String obtenerNombreCarta(String ruta) {
		String sql = "SELECT nombre FROM Cartas WHERE ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Obtenido nombre de carta desde BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		String nombre = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				nombre = rs.getString("nombre");

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return nombre;
	}

	public static String obtenerEdicionCarta(String ruta) {
		String sql = "SELECT edicion FROM Cartas WHERE ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Obtenida la Edicion de la carta desde BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		String nombre = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				nombre = rs.getString("edicion");

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
		String sql = "SELECT rareza FROM Cartas WHERE ruta='" + ruta + "'";
		Connection con = BD.initBD("BaseDeDatos.db");
		log.log(Level.INFO, " Obtenida rareza de carta desde BD: " + (new Date()));

		Statement st = BD.usarBD(con);
		String rareza = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				rareza = rs.getString("rareza");

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return rareza;
	}

	public static void aniadirProductoAlCarrito(String nick, String ruta, String nombre, String edicion, String rareza,
			float precio) {

		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		Date d = new Date(System.currentTimeMillis());
		int unidades = 0;
		int stock = 0;
		String sql = "SELECT referencia FROM Cartas WHERE ruta='" + ruta + "' AND nombre='" + nombre + "' AND rareza='"
				+ rareza + "' AND edicion='" + edicion + "'";
		try {

			ResultSet rs = st.executeQuery(sql);
			int ref = rs.getInt("referencia");
			rs.close();
			sql = "SELECT * FROM Carrito WHERE referencia =" + ref;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				unidades = unidades + 1;
				sql = "UPDATE Carrito SET unidades =" + unidades + " WHERE referencia = " + ref;
				st.executeUpdate(sql);
			} else {

				String fecha = d.toString();
				sql = "INSERT INTO Carrito VALUES('" + nick + "'," + ref + ",'" + fecha + "',1," + precio + ")";
				st.executeUpdate(sql);
			}
			stock = obtenerUnidadesProducto(ref) - 1;
			sql = "UPDATE Cartas SET stock =" + stock + " WHERE referencia = " + ref;
			st.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cerrarBD(con, st);

	}

	public static ArrayList<Carrito> obtenerDatosCarrito() {
		String sql = "SELECT * FROM Carrito";
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		ArrayList<Carrito> carrito = new ArrayList<Carrito>();

		try {

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int ref = rs.getInt("referencia");
				float precio = rs.getFloat("precio");
				String nick = rs.getString("nick");
				int unidades = rs.getInt("unidades");
				String fecha = rs.getString("fecha");
				carrito.add(new Carrito(ref, nick, unidades, precio, fecha));

			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cerrarBD(con, st);
		return carrito;
	}

	public static int obtenerUnidadesProducto(int referencia) {
		String sql = "SELECT stock FROM Cartas WHERE referencia=" + referencia;
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		int unidades = 0;
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				unidades = rs.getInt("stock");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cerrarBD(con, st);
		return unidades;
	}

	public static void modificarUnidadesEnElCarrito(int referencia, int unidades) {
		String sql = "UPDATE Cartas SET stock = stock-" + unidades + " WHERE referencia =" + referencia;
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		try {
			st.executeUpdate(sql);
			con = BD.initBD("BaseDeDatos.db");
			st = BD.usarBD(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		cerrarBD(con, st);
	}

	public static void LimpiarCarrito() {
		String sql = "DELETE FROM Carrito";
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
}
