package Swing;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.BD;

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
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JTextField txtClave;
	private String nick,con;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void vaciarCampos() {
		txtNick.setText("");	
		txtClave.setText("");

	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel pArriba = new JPanel();
		contentPane.add(pArriba, BorderLayout.NORTH);
		
		JLabel lblDeustogames = new JLabel("DeustoGames");
		lblDeustogames.setFont(new Font("Plantagenet Cherokee", Font.BOLD | Font.ITALIC, 20));
		pArriba.add(lblDeustogames);
		
		JPanel pCentro = new JPanel();
		contentPane.add(pCentro, BorderLayout.CENTER);
		pCentro.setLayout(new MigLayout("", "[50][110][170,grow][50]", "[30][30][30][30]"));
		
		JPanel pAbajo = new JPanel();
		contentPane.add(pAbajo, BorderLayout.SOUTH);
		JLabel lblNick = new JLabel("NICK :");
		lblNick.setHorizontalAlignment(SwingConstants.LEFT);
		pCentro.add(lblNick, "cell 1 1,alignx center");
		
		txtNick = new JTextField();
		pCentro.add(txtNick, "cell 2 1,growx");
		txtNick.setColumns(10);
		
		JLabel lblContrasea = new JLabel("CONTRASEÑA :");
		pCentro.add(lblContrasea, "cell 1 2,alignx center");
		
		txtClave = new JTextField();
		pCentro.add(txtClave, "cell 2 2,growx");
		txtClave.setColumns(10);
	
		JButton btnAceptar = new JButton("Iniciar Sesion");
		
		JFrame ventana=this;
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// comprobara los datos nick y pass, 
				//si son correctos abre la ventana principal  si no muestra un show dialog de error a la hora de inicia sesion.
			
				int resultado = BD.buscarUsuario(txtNick.getText(), txtClave.getText());
				if(resultado == 2) {
					JOptionPane.showMessageDialog(null, "Inicio de sesión correcto", "INICIO SESIÓN", JOptionPane.INFORMATION_MESSAGE);
					nick = txtNick.getText();
					con = txtClave.getText();
					
						ventana.setVisible(false);
						if(nick.equals("admin") && con.equals("admin")) {
							VentanaPrincipalAdmin vpa =  new VentanaPrincipalAdmin();
							vpa.setVisible(true);
						}else {
							VentanaPrincipal vpc= new VentanaPrincipal();
							vpc.setVisible(true);
						}
						
				}else if (resultado == 1){
					JOptionPane.showMessageDialog(null, "Clave incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario desconocido", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				vaciarCampos();
			}
		});
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setBackground(Color.WHITE);
		pAbajo.add(btnAceptar);
		
		
		JButton btnR = new JButton("Registrarse");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ventana.setVisible(false);
			VentanaRegistro v2 =  new VentanaRegistro();
			v2.setVisible(true);
			
			}
		});
		pAbajo.add(btnR);

		

	}
}
