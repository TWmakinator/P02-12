package Logica;
import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class BDJUnit extends TestCase {
	
	@Test
	public void RegistrarUsuario() {
	Connection con= BD.initBD("BaseDeDatos");
	Statement st= BD.usarBD(con);
	BD.registrarUsuario("test","test", "test@gmail.com","1234567890987654");
	int enc = BD.buscarUsuario("test", "test");
	assertEquals(2, enc);
}


		
	}

