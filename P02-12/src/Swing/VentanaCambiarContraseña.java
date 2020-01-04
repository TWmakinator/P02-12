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

public class VentanaCambiarContraseña extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNuevaContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCambiarContraseña frame = new VentanaCambiarContraseña();
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
	public VentanaCambiarContraseña() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JFrame ventana = this;
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblCambiarContraseñaUsuario = new JLabel("Cambiar Contraseña Usuario");
		panel.add(lblCambiarContraseñaUsuario);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		GridBagConstraints gbc_lblNombreUsuario = new GridBagConstraints();
		gbc_lblNombreUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblNombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreUsuario.gridx = 3;
		gbc_lblNombreUsuario.gridy = 1;
		panel_1.add(lblNombreUsuario, gbc_lblNombreUsuario);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 4;
		gbc_txtNombre.gridy = 1;
		panel_1.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contraseña:");
		GridBagConstraints gbc_lblNuevaContrasea = new GridBagConstraints();
		gbc_lblNuevaContrasea.insets = new Insets(0, 0, 0, 5);
		gbc_lblNuevaContrasea.anchor = GridBagConstraints.WEST;
		gbc_lblNuevaContrasea.gridx = 3;
		gbc_lblNuevaContrasea.gridy = 3;
		panel_1.add(lblNuevaContrasea, gbc_lblNuevaContrasea);
		
		txtNuevaContraseña = new JTextField();
		GridBagConstraints gbc_txtNuevaContraseña = new GridBagConstraints();
		gbc_txtNuevaContraseña.insets = new Insets(0, 0, 0, 5);
		gbc_txtNuevaContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNuevaContraseña.gridx = 4;
		gbc_txtNuevaContraseña.gridy = 3;
		panel_1.add(txtNuevaContraseña, gbc_txtNuevaContraseña);
		txtNuevaContraseña.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNombre.getText().equals("")|| txtNuevaContraseña.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				}else {
					try {
						
						int resultado = BD.buscarNombreUsuario(txtNombre.getText()); 						
						if(resultado != 0) {
						BD.cambiarContraseñaUsuario(txtNombre.getText(), txtNuevaContraseña.getText());	
						JOptionPane.showMessageDialog(null, "La Contraseña del Usuario ya ha sido modificada", "Perfecto", JOptionPane.ERROR_MESSAGE);
						ventana.setVisible(false);
						VentanaPrincipalAdmin vp = new VentanaPrincipalAdmin();
						vp.setVisible(true);
							
						}else {				
							JOptionPane.showMessageDialog(null, "El Nombre del Usuario No Existe", "ERROR", JOptionPane.ERROR_MESSAGE);

						}
					}catch(NumberFormatException e1) {
						
					}
				}
			}
		});
		panel_2.add(btnCambiar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				VentanaPrincipalAdmin va = new VentanaPrincipalAdmin();
				va.setVisible(true);
			}
		});
		panel_2.add(btnVolver);
		
	}

}
