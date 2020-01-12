package Swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import BaseDeDatos.BD;
import javax.swing.SwingConstants;

public class PanelCarrito extends JPanel {
	private JTextField txtPrecio;

	public PanelCarrito(String ruta, int unidades, float precio, int referencia) {
		
		setLayout(new BorderLayout(0, 0));

		JPanel pArriba = new JPanel();
		add(pArriba, BorderLayout.NORTH);

		JPanel pAbajo = new JPanel();
		add(pAbajo, BorderLayout.SOUTH);

		JPanel pCentro = new JPanel();
		add(pCentro, BorderLayout.CENTER);
		pCentro.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pFoto = new JPanel();
		pCentro.add(pFoto);

		ImageIcon im = new ImageIcon(ruta);
		JLabel lblFoto = new JLabel(im);
		pFoto.add(lblFoto);

		JPanel pDatosProducto = new JPanel();
		pCentro.add(pDatosProducto);
		pDatosProducto.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pInfo = new JPanel();
		pDatosProducto.add(pInfo);

		JPanel pUnidades = new JPanel();
		pDatosProducto.add(pUnidades);
		pUnidades.setLayout(null);

		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(10, 22, 103, 14);
		pUnidades.add(lblUnidades);

		
		
		int unidadesRestantes = BD.obtenerUnidadesProducto(referencia);
		JSpinner spinnerUnidades = new JSpinner();
		SpinnerModel sp = spinnerUnidades.getModel();
		sp.setValue(new Integer(1));
		spinnerUnidades.setModel(sp);
		spinnerUnidades.setBounds(123, 19, 29, 20);
		
		spinnerUnidades.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				int valor = ((Integer) spinnerUnidades.getValue()).intValue();			
				if (unidadesRestantes >= valor) {
					if(valor > 0) {
					BD.modificarUnidadesEnElCarrito(referencia, unidadesRestantes-valor);	
					txtPrecio.setText(String.valueOf(precio * valor));
					}
				} else {
					
					JOptionPane.showMessageDialog(null, "No hay unidades suficientes");
				}
			}
		});
		pUnidades.add(spinnerUnidades);

		JLabel lblPrecio = new JLabel("Precio total");
		lblPrecio.setBounds(10, 78, 75, 14);
		pUnidades.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(95, 75, 86, 20);
		
		pUnidades.add(txtPrecio);
		txtPrecio.setColumns(10);

	}
}
