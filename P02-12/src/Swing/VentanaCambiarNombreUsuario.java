package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class VentanaCambiarNombreUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNuevoNombre;

	/**
	 * Create the frame.
	 */
	public VentanaCambiarNombreUsuario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JFrame ventana = this;

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblCambiarNombreUsuario = new JLabel("Cambiar Nombre Usuario");
		panel.add(lblCambiarNombreUsuario);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNombre.getText().equals("") || txtNuevoNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {
					try {

						int resultado = BD.buscarNombreUsuario(txtNombre.getText());
						if (resultado != 0) {
							BD.cambiarNombreUsuario(txtNombre.getText(), txtNuevoNombre.getText());
							JOptionPane.showMessageDialog(null, "El Nombre del Usuario ya ha sido Modificado",
									"Perfecto", JOptionPane.INFORMATION_MESSAGE);
							ventana.setVisible(false);
							VentanaPrincipalAdmin vp = new VentanaPrincipalAdmin();
							vp.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(null, "El Nombre del Usuario No Existe", "ERROR",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (NumberFormatException e1) {

					}
				}
			}
		});
		panel_1.add(btnCambiar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				VentanaPrincipalAdmin va = new VentanaPrincipalAdmin();
				va.setVisible(true);
			}

		});
		panel_1.add(btnVolver);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		GridBagConstraints gbc_lblNombreUsuario = new GridBagConstraints();
		gbc_lblNombreUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblNombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreUsuario.gridx = 2;
		gbc_lblNombreUsuario.gridy = 2;
		panel_2.add(lblNombreUsuario, gbc_lblNombreUsuario);

		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 2;
		panel_2.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNuevoNombreUsuario = new JLabel("Nuevo Nombre :");
		GridBagConstraints gbc_lblNuevoNombreUsuario = new GridBagConstraints();
		gbc_lblNuevoNombreUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblNuevoNombreUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_lblNuevoNombreUsuario.gridx = 2;
		gbc_lblNuevoNombreUsuario.gridy = 4;
		panel_2.add(lblNuevoNombreUsuario, gbc_lblNuevoNombreUsuario);

		txtNuevoNombre = new JTextField();
		GridBagConstraints gbc_txtNuevoNombre = new GridBagConstraints();
		gbc_txtNuevoNombre.anchor = GridBagConstraints.NORTH;
		gbc_txtNuevoNombre.insets = new Insets(0, 0, 0, 5);
		gbc_txtNuevoNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNuevoNombre.gridx = 3;
		gbc_txtNuevoNombre.gridy = 4;
		panel_2.add(txtNuevoNombre, gbc_txtNuevoNombre);
		txtNuevoNombre.setColumns(10);
	}

}
