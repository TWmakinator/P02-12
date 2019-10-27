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
	private JTextField textField;
	private JTextField textField_1;

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
		
		JPanel pAbajo = new JPanel();
		contentPane.add(pAbajo, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Iniciar Sesion");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// comprobara los datos nick y pass, 
				//si son correctos abre la ventana principal  si no muestra un show dialog de error a la hora de inicia sesion.
			}
		});
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setBackground(Color.WHITE);
		pAbajo.add(btnAceptar);
		
		JFrame ventana = this;
		JButton btnR = new JButton("Registrarse");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ventana.setVisible(false);
			VentanaRegistro v2 =  new VentanaRegistro();
			v2.setVisible(true);
			
			}
		});
		pAbajo.add(btnR);
		
		JPanel pArriba = new JPanel();
		contentPane.add(pArriba, BorderLayout.NORTH);
		
		JLabel lblDeustogames = new JLabel("DeustoGames");
		lblDeustogames.setFont(new Font("Plantagenet Cherokee", Font.BOLD | Font.ITALIC, 20));
		pArriba.add(lblDeustogames);
		
		JPanel pCentro = new JPanel();
		contentPane.add(pCentro, BorderLayout.CENTER);
		pCentro.setLayout(new MigLayout("", "[50][110][170,grow][50]", "[30][30][30][30]"));
		
		JLabel lblNick = new JLabel("NICK :");
		lblNick.setHorizontalAlignment(SwingConstants.LEFT);
		pCentro.add(lblNick, "cell 1 1,alignx center");
		
		textField = new JTextField();
		pCentro.add(textField, "cell 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("CONTRASEÑA :");
		pCentro.add(lblContrasea, "cell 1 2,alignx center");
		
		textField_1 = new JTextField();
		pCentro.add(textField_1, "cell 2 2,growx");
		textField_1.setColumns(10);
	}
}
