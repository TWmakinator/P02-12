package Swing;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class VentanaAñadirCarta extends JFrame {

	private JPanel contentPane;
	private JTextField txtEdicion;
	private JTextField txtNombre;
	private JTextField txtRareza;
	private JTextField txtRuta;
	private JTextField txtStock;
	private JTextField txtReferencia;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAñadirCarta frame = new VentanaAñadirCarta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAñadirCarta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblAadirelimonarCarta = new JLabel("Añadir Carta");
		panel.add(lblAadirelimonarCarta);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {30, 30, 170, 30, 30, 75, 30};
		gbl_panel_2.rowHeights = new int[] {0, 0, 0, 0, 0, 30, 0, 30, 30, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblSpacio1 = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		panel_2.add(lblSpacio1, gbc_label);
		
		JLabel lblSpacio = new JLabel(" ");
		GridBagConstraints gbc_lblSpacio = new GridBagConstraints();
		gbc_lblSpacio.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpacio.gridx = 2;
		gbc_lblSpacio.gridy = 2;
		panel_2.add(lblSpacio, gbc_lblSpacio);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		panel_2.add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 3;
		panel_2.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblReferencia = new JLabel("Referecia");
		GridBagConstraints gbc_lblReferencia = new GridBagConstraints();
		gbc_lblReferencia.anchor = GridBagConstraints.WEST;
		gbc_lblReferencia.insets = new Insets(0, 0, 5, 5);
		gbc_lblReferencia.gridx = 4;
		gbc_lblReferencia.gridy = 3;
		panel_2.add(lblReferencia, gbc_lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setColumns(10);
		GridBagConstraints gbc_txtReferencia = new GridBagConstraints();
		gbc_txtReferencia.insets = new Insets(0, 0, 5, 0);
		gbc_txtReferencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtReferencia.gridx = 5;
		gbc_txtReferencia.gridy = 3;
		panel_2.add(txtReferencia, gbc_txtReferencia);
		
		JLabel lblEdicion = new JLabel("Edicion");
		GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
		gbc_lblEdicion.anchor = GridBagConstraints.WEST;
		gbc_lblEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicion.gridx = 1;
		gbc_lblEdicion.gridy = 4;
		panel_2.add(lblEdicion, gbc_lblEdicion);
		
		txtEdicion = new JTextField();
		GridBagConstraints gbc_txtEdicion = new GridBagConstraints();
		gbc_txtEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_txtEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEdicion.gridx = 2;
		gbc_txtEdicion.gridy = 4;
		panel_2.add(txtEdicion, gbc_txtEdicion);
		txtEdicion.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.anchor = GridBagConstraints.WEST;
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 4;
		gbc_lblPrecio.gridy = 4;
		panel_2.add(lblPrecio, gbc_lblPrecio);
		
		txtPrecio = new JTextField();
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.insets = new Insets(0, 0, 5, 0);
		gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrecio.gridx = 5;
		gbc_txtPrecio.gridy = 4;
		panel_2.add(txtPrecio, gbc_txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblRareza = new JLabel("Rareza");
		GridBagConstraints gbc_lblRareza = new GridBagConstraints();
		gbc_lblRareza.anchor = GridBagConstraints.WEST;
		gbc_lblRareza.insets = new Insets(0, 0, 5, 5);
		gbc_lblRareza.gridx = 1;
		gbc_lblRareza.gridy = 5;
		panel_2.add(lblRareza, gbc_lblRareza);
		
		txtRareza = new JTextField();
		txtRareza.setColumns(10);
		GridBagConstraints gbc_txtRareza = new GridBagConstraints();
		gbc_txtRareza.insets = new Insets(0, 0, 5, 5);
		gbc_txtRareza.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRareza.gridx = 2;
		gbc_txtRareza.gridy = 5;
		panel_2.add(txtRareza, gbc_txtRareza);
		
		JLabel lblStock = new JLabel("Stock");
		GridBagConstraints gbc_lblStock = new GridBagConstraints();
		gbc_lblStock.insets = new Insets(0, 0, 5, 5);
		gbc_lblStock.anchor = GridBagConstraints.WEST;
		gbc_lblStock.gridx = 4;
		gbc_lblStock.gridy = 5;
		panel_2.add(lblStock, gbc_lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		GridBagConstraints gbc_txtStock = new GridBagConstraints();
		gbc_txtStock.insets = new Insets(0, 0, 5, 0);
		gbc_txtStock.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStock.gridx = 5;
		gbc_txtStock.gridy = 5;
		panel_2.add(txtStock, gbc_txtStock);
		
		JLabel lblRuta = new JLabel("Ruta");
		GridBagConstraints gbc_lblRuta = new GridBagConstraints();
		gbc_lblRuta.anchor = GridBagConstraints.WEST;
		gbc_lblRuta.insets = new Insets(0, 0, 5, 5);
		gbc_lblRuta.gridx = 1;
		gbc_lblRuta.gridy = 6;
		panel_2.add(lblRuta, gbc_lblRuta);
		
		txtRuta = new JTextField();
		txtRuta.setColumns(10);
		GridBagConstraints gbc_txtRuta = new GridBagConstraints();
		gbc_txtRuta.insets = new Insets(0, 0, 5, 5);
		gbc_txtRuta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRuta.gridx = 2;
		gbc_txtRuta.gridy = 6;
		panel_2.add(txtRuta, gbc_txtRuta);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		JFrame ventana = this;
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Añade la carta a la bd
				
				if(txtNombre.getText().equals("")|| txtEdicion.getText().equals("") || txtRareza.getText().equals("")|| txtPrecio.getText().equals("")|| txtReferencia.getText().equals("")|| txtRuta.getText().equals("")|| txtStock.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			 	}else {
					try {
						int resultado = BD.buscarCarta(txtNombre.getText());
						if(resultado == 1) {
							JOptionPane.showMessageDialog(null, "Esta Carta ya existe, Se sumara al stock la cantidad indicada", "ERROR", JOptionPane.ERROR_MESSAGE);
							//Suma el stock indicado mas lo que teniamos
								
						}else {	
							BD.registrarCarta(txtNombre.getText(),txtEdicion.getText(),txtRareza.getText(),txtPrecio.getText(),txtReferencia.getText(),txtRuta.getText(),txtStock.getText());
							JOptionPane.showMessageDialog(null, "Carta Añadida a la BD", "Exito", JOptionPane.INFORMATION_MESSAGE);
							ventana.setVisible(false);
							VentanaPrincipalAdmin vl = new VentanaPrincipalAdmin();
							vl.setVisible(true);
						}
					}catch(NumberFormatException e1) {
						
					}
			 	}
			}
		});
		panel_1.add(btnAadir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				VentanaPrincipalAdmin va = new VentanaPrincipalAdmin();
				va.setVisible(true);
			}
		});
		panel_1.add(btnVolver);	

	}

}
