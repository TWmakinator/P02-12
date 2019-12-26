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
	BD.registrarUsuario("test","test@gmail.com", "test","1234567890987654");
	int enc = BD.buscarUsuario("test", "test");
	assertEquals(2, enc);
}


		
	}


