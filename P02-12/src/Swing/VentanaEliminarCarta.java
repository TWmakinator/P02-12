package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaEliminarCarta extends JFrame {

	private JPanel contentPane;
	private JTextField txtEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEliminarCarta frame = new VentanaEliminarCarta();
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
	public VentanaEliminarCarta() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblEliminarCarta = new JLabel("Eliminar Carta");
		panel.add(lblEliminarCarta);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		JFrame ventana = this;

		JButton btnEliminar = new JButton("Eliminar ");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtEliminar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Tienes que escribir el nombre de la carta a borrar", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int resultado = BD.buscarCarta(txtEliminar.getText());
						if (resultado == 2) {
							BD.eliminarCarta(txtEliminar.getText());
							JOptionPane.showMessageDialog(null, "Carta Eliminada", "Exito", JOptionPane.ERROR_MESSAGE);

							ventana.setVisible(false);
							VentanaPrincipalAdmin vl = new VentanaPrincipalAdmin();
							vl.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(null, "La Carta que deseas eliminar no esta en el stock",
									"Error", JOptionPane.INFORMATION_MESSAGE);

						}
					} catch (NumberFormatException e1) {

					}
				}

			}
		});
		panel_1.add(btnEliminar);

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
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 165, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNombreCarta = new JLabel("Nombre de la Carta :");
		GridBagConstraints gbc_lblNombreCarta = new GridBagConstraints();
		gbc_lblNombreCarta.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreCarta.gridx = 2;
		gbc_lblNombreCarta.gridy = 2;
		panel_2.add(lblNombreCarta, gbc_lblNombreCarta);

		txtEliminar = new JTextField();
		GridBagConstraints gbc_txtEliminar = new GridBagConstraints();
		gbc_txtEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_txtEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEliminar.gridx = 3;
		gbc_txtEliminar.gridy = 2;
		panel_2.add(txtEliminar, gbc_txtEliminar);
		txtEliminar.setColumns(10);
	}

}
