package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Logica.Carta;
import Logica.PanelFotos;
import Logica.PanelInfoFoto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import net.miginfocom.swing.MigLayout;

public class VentanaCarta extends JFrame {

	private JPanel contentPane;
	private JFrame va;
	private VentanaPrincipal vp;

	/**
	 * Create the frame.
	 */
	public VentanaCarta(String ruta, JFrame va) {
		this.va = va;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNombreCarta = new JLabel("Nombre Carta");
		panel.add(lblNombreCarta);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				dispose();

			}
		});
		panel_2.add(btnVolver);
		
		JButton btnAnyadirCarrito = new JButton("Anyadir al Carrito");
		btnAnyadirCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.aniadirProductoAlCarrito(VentanaLogin.getNick(), ruta, BD.obtenerNombreCarta(ruta), BD.obtenerEdicionCarta(ruta), BD.obtenerRarezaCarta(ruta), BD.obtenerPrecioCarta(ruta) );
				JOptionPane.showMessageDialog(null, "La Carta se ha añadido al carro", "Añadido al Carro", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		panel_2.add(btnAnyadirCarrito);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new MigLayout("", "[53.00px][105.00]", "[10][][25][pref!][25][pref!][25][pref!]"));
		
		JLabel lblNombre = new JLabel("Nombre:");
		panel_3.add(lblNombre, "cell 0 1,alignx left,aligny top");
		
		String nombreCarta = BD.obtenerNombreCarta(ruta);
		JLabel lblNomc = new JLabel(nombreCarta);
		panel_3.add(lblNomc, "cell 1 1,alignx center");
		
		JLabel label_2 = new JLabel(" ");
		panel_3.add(label_2, "cell 0 2");
		
		
		JLabel lblEdicion = new JLabel("Edicion:");
		panel_3.add(lblEdicion, "cell 0 3,alignx left,aligny top");
		
		String edicionCarta = BD.obtenerEdicionCarta(ruta); 
		JLabel lblEdic = new JLabel(edicionCarta);
		panel_3.add(lblEdic, "cell 1 3,alignx center");
		
		JLabel label_1 = new JLabel(" ");
		panel_3.add(label_1, "cell 0 4");
		
		JLabel lblRareza = new JLabel("Rareza:");
		panel_3.add(lblRareza, "cell 0 5");
		
		String RarezaCarta = BD.obtenerRarezaCarta(ruta);
		JLabel lblRarec = new JLabel(RarezaCarta);
		panel_3.add(lblRarec, "cell 1 5,alignx center");
		
		JLabel label = new JLabel(" ");
		panel_3.add(label, "cell 0 6");
		
		JLabel lblPrecio = new JLabel("Precio:");
		panel_3.add(lblPrecio, "cell 0 7");
		
		float precioCarta = BD.obtenerPrecioCarta(ruta);
		String precioCartaS = String.valueOf(precioCarta);
		JLabel lblPrec = new JLabel(precioCartaS+"€");
		panel_3.add(lblPrec, "cell 1 7,alignx center");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ImageIcon im = new ImageIcon(ruta);
		JLabel lblFotoCarta = new JLabel(im);
		panel_1.add(lblFotoCarta);
	
	}

}
