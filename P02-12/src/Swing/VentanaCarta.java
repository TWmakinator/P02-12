package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import net.miginfocom.swing.MigLayout;

public class VentanaCarta extends JFrame {

	private JPanel contentPane;
	private JFrame va;

	/**
	 * Create the frame.
	 */
	public VentanaCarta(String ruta, JFrame va) {
		this.va = va;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
				va.setVisible(false);
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
			}
		});
		panel_2.add(btnVolver);
		
		JButton btnAnyadirCarrito = new JButton("Anyadir al Carrito");
		btnAnyadirCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnAnyadirCarrito);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new MigLayout("", "[53.00px][105.00]", "[10][][25][pref!][25][pref!][25][pref!]"));
		
		JLabel lblNombre = new JLabel("Nombre");
		panel_3.add(lblNombre, "cell 0 1,alignx left,aligny top");
		
		JLabel lblNomc = new JLabel("NomC");
		panel_3.add(lblNomc, "cell 1 1,alignx center");
		
		JLabel label_2 = new JLabel(" ");
		panel_3.add(label_2, "cell 0 2");
		
		JLabel lblEdicion = new JLabel("Edicion:");
		panel_3.add(lblEdicion, "cell 0 3,alignx left,aligny top");
		
		JLabel lblEdic = new JLabel("EdiC");
		panel_3.add(lblEdic, "cell 1 3,alignx center");
		
		JLabel label_1 = new JLabel(" ");
		panel_3.add(label_1, "cell 0 4");
		
		JLabel lblRareza = new JLabel("Rareza:");
		panel_3.add(lblRareza, "cell 0 5");
		
		JLabel lblRarec = new JLabel("RareC");
		panel_3.add(lblRarec, "cell 1 5,alignx center");
		
		JLabel label = new JLabel(" ");
		panel_3.add(label, "cell 0 6");
		
		JLabel lblPrecio = new JLabel("Precio:");
		panel_3.add(lblPrecio, "cell 0 7");
		
		JLabel lblPrec = new JLabel("PreC");
		panel_3.add(lblPrec, "cell 1 7,alignx center");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panelFotet = new JPanel();
	
	}

}
