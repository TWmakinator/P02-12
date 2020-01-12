package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.InterfaceReloj;
import Logica.PanelFotos;
import Logica.PanelInfoFoto;

import java.awt.GridBagLayout;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	static Logger log;
	private JPanel contentPane;
	private InterfaceReloj ireloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			log = Logger.getLogger("LoggerEjecucion");
		} catch (Exception e) {
		}
		log.log(Level.INFO, " Inicio de la ventana principal" + (new Date()));

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		ireloj = new InterfaceReloj();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JFrame ventana = this;

		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(Color.LIGHT_GRAY);
		panelNorte.setForeground(Color.DARK_GRAY);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[] { 76, 104, 666, 202, 119, 0 };
		gbl_panelNorte.rowHeights = new int[] { 37, 0 };
		gbl_panelNorte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelNorte.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelNorte.setLayout(gbl_panelNorte);

		JLabel lblUsuarioX = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuarioX = new GridBagConstraints();
		gbc_lblUsuarioX.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsuarioX.gridx = 0;
		gbc_lblUsuarioX.gridy = 0;
		panelNorte.add(lblUsuarioX, gbc_lblUsuarioX);

		JLabel lblNewLabel = new JLabel(VentanaLogin.getNick());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panelNorte.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblVentanaCliente = new JLabel("Tienda Magic");
		lblVentanaCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblVentanaCliente = new GridBagConstraints();
		gbc_lblVentanaCliente.insets = new Insets(0, 0, 0, 5);
		gbc_lblVentanaCliente.gridx = 2;
		gbc_lblVentanaCliente.gridy = 0;
		panelNorte.add(lblVentanaCliente, gbc_lblVentanaCliente);

		JButton btnCarrito = new JButton("Carrito");
		btnCarrito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnCarrito.setBackground(Color.WHITE);
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaCarrito();
			}
		});

		GridBagConstraints gbc_lblReloj = new GridBagConstraints();
		gbc_lblReloj.insets = new Insets(0, 0, 0, 5);
		gbc_lblReloj.gridx = 3;
		gbc_lblReloj.gridy = 0;
		panelNorte.add(ireloj, gbc_lblReloj);
		btnCarrito.setIcon(new ImageIcon("FotosExtra/Carrito.png"));
		GridBagConstraints gbc_btnCarrito = new GridBagConstraints();
		gbc_btnCarrito.gridx = 4;
		gbc_btnCarrito.gridy = 0;
		panelNorte.add(btnCarrito, gbc_btnCarrito);

		JPanel panelCentro = new PanelFotos();
		JScrollPane scroll = new JScrollPane(panelCentro);

		contentPane.add(scroll, BorderLayout.CENTER);
		panelCentro.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelFotos pf = (PanelFotos) e.getComponent();
				int x = e.getX();
				int y = e.getY();
				PanelInfoFoto pi = (PanelInfoFoto) pf.getComponentAt(x, y);
				VentanaCarta vc = new VentanaCarta(pi.getRuta(), ventana);
				vc.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
				log.log(Level.INFO, "Cerrando la sesion actual" + (new Date()));
			}
		});
		panelSur.add(btnCerrarSesion);
	}

}
