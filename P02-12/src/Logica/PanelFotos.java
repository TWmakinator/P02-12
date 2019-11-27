package Logica;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelFotos extends JPanel{

	
	
	
	private void cargarFotos(String nombre, String rareza, String edicion,String precio) {
		ArrayList<String> rutas;
			System.out.println(nombre+" "+rareza+"  "+edicion+"  "+precio);
		if(nombre.equals("") && rareza.equals("") && edicion.equals("") && precio.equals("")) {
			rutas = BD.obtenerTodasRutasFotos();
			System.out.println("Tamaño del array "+rutas.size());
		}else {
		rutas=BD.obtenerRutasFotos2(nombre, edicion, rareza, precio);
		}
		for(int i=0;i<rutas.size();i++) {
			
			ImageIcon im = new ImageIcon(rutas.get(i));
			im.setDescription(rutas.get(i));
			JLabel lblFoto = new JLabel(im);
			JPanel pFoto = new JPanel();
			pFoto.add(lblFoto);
			add(pFoto);
		}
		}
	
	/**
	 * Create the panel.
	 */
	public PanelFotos(String nombre, String rareza, String edicion,String precio) {
		setLayout(new GridLayout(0, 5, 0, 0));
		cargarFotos(nombre, rareza, edicion, precio);
		setVisible(true);
	}
	
}
