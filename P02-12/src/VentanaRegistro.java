import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField txtContraseña;
	private JTextField txtEmail;
	private JTextField txtNumeroCuenta;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblRegistro = new JLabel("Registro");
		panel.add(lblRegistro);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JFrame ventana = this;
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
			}
		});
		panel_1.add(btnVolver);
		
		JButton bntRegistrar = new JButton("Registrarse");
		bntRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Añade el user a la bd
				
				if(txtNombre.getText().equals("")|| txtEmail.getText().equals("") || txtContraseña.getText().equals("")|| txtNumeroCuenta.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int resultado = BD.buscarUsuario(txtNombre.getText(), txtContraseña.getText());
						if(resultado != 0) {
							JOptionPane.showMessageDialog(null, "Este usuario ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else {
						
							BD.registrarUsuario(txtNombre.getText(),txtEmail.getText(), txtContraseña.getText(), txtNumeroCuenta.getText());
							JOptionPane.showMessageDialog(null, "Registro realizado con éxito", "REGISTRO", JOptionPane.INFORMATION_MESSAGE);							
						}
					}catch(NumberFormatException e1) {
						
					}
				}
			}
		});
		panel_1.add(bntRegistrar);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {40, 25, 30, 30, 30, 30, 30, 0, 31, 25};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel_2.add(lblNombre, gbc_lblNewLabel);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 2;
		panel_2.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel_2.add(lblEmail, gbc_lblNewLabel_1);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 3;
		gbc_txtEmail.gridy = 3;
		panel_2.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		panel_2.add(lblContraseña, gbc_lblNewLabel_2);
		
		txtContraseña = new JTextField();
		GridBagConstraints gbc_txtContraseña = new GridBagConstraints();
		gbc_txtContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_txtContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContraseña.gridx = 3;
		gbc_txtContraseña.gridy = 4;
		panel_2.add(txtContraseña, gbc_txtContraseña);
		txtContraseña.setColumns(10);
		
		JLabel lblNumCuenta = new JLabel("Numero de Cuenta:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		panel_2.add(lblNumCuenta, gbc_lblNewLabel_3);
		
		txtNumeroCuenta = new JTextField();
		GridBagConstraints gbc_txtNumeroCuenta = new GridBagConstraints();
		gbc_txtNumeroCuenta.insets = new Insets(0, 0, 5, 5);
		gbc_txtNumeroCuenta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumeroCuenta.gridx = 3;
		gbc_txtNumeroCuenta.gridy = 5;
		panel_2.add(txtNumeroCuenta, gbc_txtNumeroCuenta);
		txtNumeroCuenta.setColumns(10);
	}

}
