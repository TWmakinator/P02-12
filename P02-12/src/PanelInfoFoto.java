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
		/*setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelCentro.add(panel);
		
		ImageIcon im = new ImageIcon(ruta);
		im.setDescription(ruta);
		JLabel lblfoto = new JLabel(im);
		panel.add(lblfoto);
		
		JPanel panelInformacion = new JPanel();
		panelCentro.add(panelInformacion);
		panelInformacion.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelColor = new JPanel();
		panelInformacion.add(panelColor);
		
		JLabel lblColor = new JLabel(BD.obtenerColorProducto(ruta));
		lblColor.setBackground(Color.WHITE);
		panelColor.add(lblColor);
		
		JPanel panelTalla = new JPanel();
		panelInformacion.add(panelTalla);
		
		JLabel lblTalla = new JLabel(BD.obtenerTallaProducto(ruta));
		panelTalla.add(lblTalla);
		
		JPanel panelPrecio = new JPanel();
		panelInformacion.add(panelPrecio);
		
		JLabel lblPrecio = new JLabel(String.valueOf(BD.obtenerPrecioProducto(ruta)));
		panelPrecio.add(lblPrecio);
		setVisible(true);
		
		
		*/
		
		setLayout(new GridBagLayout());
		ImageIcon im = new ImageIcon(ruta);
		im.setDescription(ruta);
		JLabel lblfoto = new JLabel(im);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 2;  //En qué columna empieza
		constraints.gridy = 0;  //En qué fila empieza
		constraints.gridwidth = 5; //Cuántas columnas ocupa
		constraints.gridheight = 6; //Cuántas filas ocupa

		add(lblfoto,constraints);
		
		JLabel lblTalla = new JLabel(BD.obtenerNombreCarta(ruta));
		constraints.gridx = 4;  //En qué columna empieza
		constraints.gridy = 6;  //En qué fila empieza
		constraints.gridwidth = 3; //Cuántas columnas ocupa
		constraints.gridheight = 1; //Cuántas filas ocupa
		add(lblTalla,constraints);
		
		JLabel lblColor = new JLabel(BD.obtenerRarezaCarta(ruta));
		constraints.gridx = 4;  //En qué columna empieza
		constraints.gridy = 7;  //En qué fila empieza
		constraints.gridwidth = 3; //Cuántas columnas ocupa
		constraints.gridheight = 1; //Cuántas filas ocupa
		add(lblColor,constraints);
		
		JLabel lblPrecio = new JLabel(String.valueOf(BD.obtenerPrecioCarta(ruta)));
		constraints.gridx = 4;  //En qué columna empieza
		constraints.gridy = 8;  //En qué fila empieza
		constraints.gridwidth = 3; //Cuántas columnas ocupa
		constraints.gridheight = 1; //Cuántas filas ocupa
		add(lblPrecio,constraints);
		
		
		  Border etchedRaised = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.YELLOW);
		  setBorder(etchedRaised);
		
		

	}

	
}
