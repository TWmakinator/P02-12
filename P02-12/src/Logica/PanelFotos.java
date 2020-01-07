package Logica;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;

public class PanelFotos extends JPanel{
	
	
	private void cargarFotos() {
			ArrayList<String> rutas;	
			rutas = BD.obtenerTodasRutasFotos();
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
	 * Crea el panel.
	 */
	public PanelFotos(){
		setOpaque(false);	
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 5, 0, 0));
		cargarFotos();
		setVisible(true);
	}
	
}
