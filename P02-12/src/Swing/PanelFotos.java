package Swing;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BaseDeDatos.BD;

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

	private void cargarFotos() {

		ArrayList<String> rutas = BD.obtenerTodasRutasFotos();

		for (int i = 0; i < rutas.size(); i++) {
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
		cargarFotos();
		setVisible(true);
	}

}
