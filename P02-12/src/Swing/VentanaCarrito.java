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
	private JPanel pCentral = new JPanel();
	public String usuario;

	/**
	 * Create the frame.
	 */

	public void mostrarCarrito(ArrayList<Carrito> car, int n) {

		if (n < car.size()) {
			Carrito c = car.get(n);
			String ruta = BD.obtenerRutaFoto(c.getReferencia());
			PanelCarrito pc = new PanelCarrito(ruta, c.getUnidades(), c.getPrecio(), c.getReferencia());
			pCentral.add(pc);
			mostrarCarrito(car, n + 1);

		}

	}

	public VentanaCarrito(String usuario) {
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
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
				VentanComprar vc = new VentanComprar(usuario);
				vc.setVisible(true);
			}
		});

		pAbajo.add(btnComprar);

		pCentral.setLayout(new GridLayout(0, 1, 0, 0));

		ArrayList<Carrito> car = new ArrayList<Carrito>();
		car = BD.obtenerDatosCarrito();
		mostrarCarrito(car, 0);

		JScrollPane scroll = new JScrollPane(pCentral);
		contentPane.add(scroll, BorderLayout.CENTER);
		setVisible(true);
	}

}
