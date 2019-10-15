import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JTextField txtClave;
	private JTextField txtNickR;
	private JTextField txtClaveR;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pAbajo = new JPanel();
		contentPane.add(pAbajo, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAceptar.setForeground(Color.RED);
		btnAceptar.setBackground(Color.WHITE);
		pAbajo.add(btnAceptar);
		
		JPanel pArriba = new JPanel();
		contentPane.add(pArriba, BorderLayout.NORTH);
		
		JLabel lblDeustogames = new JLabel("DeustoGames");
		pArriba.add(lblDeustogames);
		
		JPanel pCentro = new JPanel();
		contentPane.add(pCentro, BorderLayout.CENTER);
		pCentro.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel1 = new JPanel();
		pCentro.add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JButton btnInicioSesin = new JButton("INICIO SESIÓN");
		btnInicioSesin.setForeground(Color.BLACK);
		btnInicioSesin.setBackground(Color.WHITE);
		btnInicioSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resultado = BD.buscarUsuario(txtNick.getText(), txtClave.getText());
				if(resultado == 2) {
					JOptionPane.showMessageDialog(null, "Inicio de sesión correcto", "INICIO SESIÓN", JOptionPane.INFORMATION_MESSAGE);
				}else if (resultado == 1){
					JOptionPane.showMessageDialog(null, "Clave incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario desconocido", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel1.add(btnInicioSesin, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel1.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNick = new JLabel("NICK:");
		lblNick.setBounds(10, 32, 46, 14);
		panel.add(lblNick);
		
		txtNick = new JTextField();
		txtNick.setBounds(62, 29, 128, 20);
		panel.add(txtNick);
		txtNick.setColumns(10);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setBounds(10, 85, 46, 14);
		panel.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setBounds(62, 82, 128, 20);
		panel.add(txtClave);
		txtClave.setColumns(10);
		
		JPanel panel2 = new JPanel();
		pCentro.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JButton btnRegistro = new JButton("REGISTRO");
		btnRegistro.setBackground(Color.WHITE);
		btnRegistro.setForeground(Color.BLACK);
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				String correo="newton.marian@gmail.com";
				int pos1 = correo.indexOf("@");
				int pos2 = correo.lastIndexOf("@");
				if(pos1==pos2 && pos1!=-1) {
					//tiene una Ãºnica arroba
				}*/
				
				if(txtNickR.getText().equals("") || txtClaveR.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						
							int resultado = BD.buscarUsuario(txtNickR.getText(), txtClaveR.getText());
							if(resultado != 0) {
								JOptionPane.showMessageDialog(null, "Ese usuario ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
							}else {
								BD.registrarUsuario(txtNickR.getText(), txtClaveR.getText());
								JOptionPane.showMessageDialog(null, "Registro realizado con Ã©xito", "REGISTRO", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "La edad tiene que ser numÃ©rica", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel2.add(btnRegistro, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel2.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNickR = new JLabel("NICK:");
		lblNickR.setBounds(10, 33, 46, 14);
		panel_1.add(lblNickR);
		
		txtNickR = new JTextField();
		txtNickR.setColumns(10);
		txtNickR.setBounds(62, 30, 128, 20);
		panel_1.add(txtNickR);
		
		JLabel lblClaveR = new JLabel("CLAVE");
		lblClaveR.setBounds(10, 86, 46, 14);
		panel_1.add(lblClaveR);
		
		txtClaveR = new JTextField();
		txtClaveR.setColumns(10);
		txtClaveR.setBounds(62, 83, 128, 20);
		panel_1.add(txtClaveR);
	}
}
