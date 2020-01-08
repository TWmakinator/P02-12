package Logica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;

public class PanelFotos extends JPanel {

	private void cargarFotos(Carta c) {
		System.out.println(c.getNombreCarta()+" "+c.getRareza()+" "+c.getEdicion()+" "+c.getPrecio());
		ArrayList<String> rutas;
		if(c.getNombreCarta().equals("") && c.getRareza().equals("") && c.getEdicion().equals("") && c.getPrecio().equals("")) { //casteo Double String
			rutas = BD.obtenerTodasRutasFotos();
			System.out.println("Tamaño del array "+rutas.size());
		}
		else if(c.getEdicion().equals("")) {
			rutas = BD.obtenerRutasFotos(c.getNombreCarta(), c.getRareza(), c.getPrecio());
			
		}
		else if(c.getRareza().equals("")) {
			rutas = BD.obtenerRutasFotos1(c.getNombreCarta(), c.getEdicion(), c.getPrecio());
			
		}else {
			rutas = BD.obtenerRutasFotos2(c.getNombreCarta(), c.getRareza(), c.getPrecio(), c.getEdicion());
		
		}
		for(int i=0;i<rutas.size();i++) {
			String ruta = rutas.get(i);
			PanelInfoFoto p = new PanelInfoFoto(ruta);
			add(p);
		}
	}

	/**
	 * Crea el panel.
	 */
	public PanelFotos() {
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 5, 0, 0));
		cargarFotos(null);
		setVisible(true);
	}

}
