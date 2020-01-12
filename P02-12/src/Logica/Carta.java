package Logica;

public class Carta {

	private String nombreCarta;
	private String rareza;
	private String edicion;
	private double precio;

	public Carta(String nombreCarta, String rareza, String edicion, double precio) {
		this.nombreCarta = nombreCarta;
		this.rareza = rareza;
		this.edicion = edicion;
		this.precio = precio;

	}

	public String getNombreCarta() {
		return nombreCarta;
	}

	public void setNombreCarta(String nombreCarta) {
		this.nombreCarta = nombreCarta;
	}

	public String getRareza() {
		return rareza;
	}

	public void setRareza(String rareza) {
		this.rareza = rareza;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
