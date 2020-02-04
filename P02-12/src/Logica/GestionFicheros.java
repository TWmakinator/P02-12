package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import BaseDeDatos.BD;

public class GestionFicheros {

	public static void Historial(ArrayList<Carrito> lista, String usuario) {

		int i = 0;
		try {

			ArrayList<Carrito> historial = lista;
			PrintWriter pw = null;
			File f = new File("Historial.txt");

			if (!f.exists())
				pw = new PrintWriter(f);

			else {

				pw = new PrintWriter(new FileWriter(f, true));
				while (i < historial.size()) {
					Carrito c = new Carrito();
					c = historial.get(i);
					pw.println("Usuario: " + "" + usuario + "     " + "Carta : " + c.getCarta());
					i++;

				}
				pw.flush();
				pw.close();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
