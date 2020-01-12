package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Logica.Carrito;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCarrito extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCarrito frame = new VentanaCarrito();
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
	public VentanaCarrito() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JFrame ventana = this;

		JPanel pArriba = new JPanel();
		contentPane.add(pArriba, BorderLayout.NORTH);

		JPanel pAbajo = new JPanel();
		contentPane.add(pAbajo, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana.setVisible(false);

			}
		});
		pAbajo.add(btnVolver);

		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				VentanComprar vc = new VentanComprar();
				vc.setVisible(true);
			}
		});

		pAbajo.add(btnComprar);

		JPanel pCentral = new JPanel();
		pCentral.setLayout(new GridLayout(0, 1, 0, 0));
		ArrayList<Carrito> carrito = new ArrayList<Carrito>();
		carrito = BD.obtenerDatosCarrito();

		for (Carrito c : carrito) {
			String ruta = BD.obtenerRutaFoto(c.getReferencia());
			PanelCarrito pc = new PanelCarrito(ruta, c.getUnidades(), c.getPrecio(), c.getReferencia());
			pCentral.add(pc);

		}
		JScrollPane scroll = new JScrollPane(pCentral);
		contentPane.add(scroll, BorderLayout.CENTER);
		setVisible(true);
	}

}
