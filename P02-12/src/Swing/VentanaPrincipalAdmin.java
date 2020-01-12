package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.InterfaceReloj;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaPrincipalAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalAdmin frame = new VentanaPrincipalAdmin();
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
	public VentanaPrincipalAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(5, 5, 424, 24);
		contentPane.add(panel);
		panel.setBackground(new Color(0, 0, 0, 0));

		JLabel lblVentanaadmin = new JLabel("VentanaAdmin");
		lblVentanaadmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentanaadmin.setForeground(Color.WHITE);
		lblVentanaadmin.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		panel.add(lblVentanaadmin);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 223, 424, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.setBackground(new Color(0, 0, 0, 0));

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

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 29, 424, 194);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(0, 0, 0, 0));
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 200, 0, 202, 0 };
		gbl_panel_2.rowHeights = new int[] { 30, 30, 29, 29, 29, 30, 50 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel_2.setLayout(gbl_panel_2);

		JButton btnNewButton_1 = new JButton("Anyadir Carta BD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAnyadirCarta vn = new VentanaAnyadirCarta();
				vn.setVisible(true);
				ventana.setVisible(false);

			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Eliminar Carta BD");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEliminarCarta vn = new VentanaEliminarCarta();
				vn.setVisible(true);
				ventana.setVisible(false);

			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		panel_2.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton = new JButton("Cambiar Nombre Usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCambiarNombreUsuario vn = new VentanaCambiarNombreUsuario();
				vn.setVisible(true);
				ventana.setVisible(false);

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		panel_2.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_3 = new JButton("Cambiar Clave Usuario");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCambiarContrasenya vc = new VentanaCambiarContrasenya();
				vc.setVisible(true);
				ventana.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 5;
		panel_2.add(btnNewButton_3, gbc_btnNewButton_3);

		JLabel lblNewLabel = new JLabel(
				new ImageIcon(VentanaPrincipalAdmin.class.getResource("/FotosFondo/Fondo4.jpg")));
		lblNewLabel.setBounds(5, 5, 424, 251);
		contentPane.add(lblNewLabel);
	}

}
