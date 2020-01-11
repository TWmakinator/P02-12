package Logica;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class PanelInfoFoto extends JPanel {

	private String ruta;

	public String getRuta() {
		return ruta;
	}

	/**
	 * Create the panel.
	 */
	public PanelInfoFoto(String ruta) {
		this.ruta = ruta;
		Carta c = BD.obtenerDatosCarta(ruta);
		setLayout(new GridBagLayout());
		ImageIcon im = new ImageIcon(ruta);
		im.setDescription(ruta);
		JLabel lblfoto = new JLabel(im);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 2; // En qué columna empieza
		constraints.gridy = 0; // En qué fila empieza
		constraints.gridwidth = 5; // Cuántas columnas ocupa
		constraints.gridheight = 6; // Cuántas filas ocupa

		add(lblfoto, constraints);

		JLabel lblNombreCarta = new JLabel(c.getNombreCarta());
		constraints.gridx = 4; // En qué columna empieza
		constraints.gridy = 6; // En qué fila empieza
		constraints.gridwidth = 3; // Cuántas columnas ocupa
		constraints.gridheight = 1; // Cuántas filas ocupa
		add(lblNombreCarta, constraints);

		JLabel lblPrecio = new JLabel(String.valueOf(c.getPrecio()));
		constraints.gridx = 4; // En qué columna empieza
		constraints.gridy = 8; // En qué fila empieza
		constraints.gridwidth = 3; // Cuántas columnas ocupa
		constraints.gridheight = 1; // Cuántas filas ocupa
		add(lblPrecio, constraints);

		Border line = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
		setBorder(line);
	}

}
