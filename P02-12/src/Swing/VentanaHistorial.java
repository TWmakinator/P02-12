package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaHistorial extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	File f = new File("Historial.txt");

	/**
	 * Create the frame.
	 */
	public VentanaHistorial() {
		setForeground(Color.LIGHT_GRAY);
		setResizable(false);

		setBackground(Color.WHITE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(Color.WHITE);

		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);

		sacarHistorial();

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);
		scrollPane.setBounds(18, 0, 404, 198);
		panelCentro.add(scrollPane);

		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(Color.WHITE);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Ultima Compra");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
		panelNorte.add(lblNewLabel);

		JPanel panelSur = new JPanel();
		panelSur.setBackground(Color.WHITE);
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaHistorial.this.setVisible(false);
				VentanaPrincipalAdmin va = new VentanaPrincipalAdmin();
				va.setVisible(true);

			}
		});
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnVolver.setFont(new Font("Arial", Font.PLAIN, 11));
		panelSur.add(btnVolver);

	}

	private void sacarHistorial() {

		try {

			BufferedReader leer = new BufferedReader(new FileReader(f));

			String linea = leer.readLine();
			while (linea != null) {
				textArea.append(linea + "\n");
				linea = leer.readLine();

			}
			leer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}

	}

}
