package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Logica.Carrito;
import Logica.GestionFicheros;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class VentanComprar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public String usuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
					VentanComprar frame = new VentanComprar(" ");
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
	public VentanComprar(String usuario) {
		
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblRealizarCompra = new JLabel("Realizar Compra");
		panel.add(lblRealizarCompra);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[][17.00][101.00][181.00,grow][][]", "[][][][][][]"));

		JLabel lblNewLabel = new JLabel("Titular:");
		panel_1.add(lblNewLabel, "cell 2 1,alignx center");

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char car = e.getKeyChar();
				if (Character.isDigit(car)) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(rootPane, "Solo se puede ingresar Letras");
				}
			}
		});
		panel_1.add(textField, "cell 3 1,growx");
		textField.setColumns(10);

		JLabel lblNumeroDeCuenta = new JLabel("Numero de Cuenta:");
		panel_1.add(lblNumeroDeCuenta, "cell 2 3,alignx trailing");

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				char car = arg0.getKeyChar();
				if ((car < '0' || car > '9'))
					arg0.consume();
				int max = 15;
				int min = 15;
				if (textField_1.getText().length() > max && textField_1.getText().length() > min) {
					arg0.consume();
					JOptionPane.showMessageDialog(rootPane, "Solo se puede ingresar 16 Numeros");
				} else if (Character.isLetter(car)) {
					getToolkit().beep();
					arg0.consume();
					JOptionPane.showMessageDialog(rootPane, "Solo se puede ingresar Numeros");
				}
			}
		});
		panel_1.add(textField_1, "cell 3 3,growx");
		textField_1.setColumns(10);

		JLabel lblCodigoCvc = new JLabel("Codigo CVC2:");
		panel_1.add(lblCodigoCvc, "cell 2 5,alignx center");

		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char car = arg0.getKeyChar();
				if ((car < '0' || car > '9'))
					arg0.consume();
				int max = 2;
				int min = 2;
				if (textField_2.getText().length() > max && textField_2.getText().length() > min) {
					arg0.consume();
					JOptionPane.showMessageDialog(rootPane, "Solo se puede ingresar 3 Numeros");
				} else if (Character.isLetter(car)) {
					getToolkit().beep();
					arg0.consume();
					JOptionPane.showMessageDialog(rootPane, "Solo se puede ingresar Numeros");
				}
			}
		});
		panel_1.add(textField_2, "cell 3 5,growx");
		textField_2.setColumns(10);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JFrame ventana = this;
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana.setVisible(false);
				VentanaCarrito vc = new VentanaCarrito(usuario);
				vc.setVisible(true);
			}
		});
		panel_2.add(btnVolver);

		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "Compra Realizada Correctamente", "Comprado",
							JOptionPane.INFORMATION_MESSAGE);
					
					GestionFicheros.Historial(BD.obtenerDatosCarrito(), usuario);
					
					BD.LimpiarCarrito();
					ventana.setVisible(false);
				}
			}
		});
		panel_2.add(btnComprar);
	}

}
