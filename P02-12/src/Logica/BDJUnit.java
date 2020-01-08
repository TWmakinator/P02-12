package Logica;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class BDJUnit {

	@Test
	public void RegistrarUsuario() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		BD.registrarUsuario("test", "test", "test@gmail.com");
		int enc = BD.buscarUsuario("test", "test");
		assertEquals(2, enc);
	}

	@Test
	public void BuscarUsuario() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		int enc = BD.buscarUsuario("admin", "admin1");
		assertEquals(2, enc);

	}

	@Test
	public void BuscarNombreUsuario() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		int enc = BD.buscarNombreUsuario("alvaro");
		assertEquals(0, enc);
	}

	@Test
	public void BuscarCarta() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		int enc = BD.buscarCarta("Ensartar a los críticos");
		assertEquals(2, enc);

	}

	@Test
	public void ObtenerPrecioCarta() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		float precio = BD.obtenerPrecioCarta("CartasMagic/Carta1.jpg");
		assertEquals(6, precio, 0.0);
	}

	@Test
	public void ObtenerRutaFoto() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String ruta = BD.obtenerRutaFoto(1);
		assertEquals("CartasMagic/Carta1.jpg", ruta);
	}

	@Test
	public void ObtenerNombreCarta() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String nombre = BD.obtenerNombreCarta("CartasMagic/Carta1.jpg");
		assertEquals("Engendro del caos", nombre);
	}

	@Test
	public void ObtenerRarezaCarta() {
		Connection con = BD.initBD("BaseDeDatos.db");
		Statement st = BD.usarBD(con);
		String rareza = BD.obtenerRarezaCarta("CartasMagic/Carta1.jpg");
		assertEquals("Mítica", rareza);
	}

}
