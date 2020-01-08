package Logica;

public class Carta {

	private String NombreCarta;
	private String Rareza;
	private String Edicion;
	private double Precio;
	
	
	
	public Carta(String nombreCarta, String rareza, String Edicion, double precio) {
		this.NombreCarta = nombreCarta;
		this.Rareza = rareza;
		this.Edicion = Edicion;
		this.Precio = precio;
		
	}


	public String getNombreCarta() {
		return NombreCarta;
	}


	public void setNombreCarta(String nombreCarta) {
		NombreCarta = nombreCarta;
	}


	public String getRareza() {
		return Rareza;
	}


	public void setRareza(String rareza) {
		Rareza = rareza;
	}
	
	public String getEdicion() {
		return Edicion;
	}


	public void setEdicion(String edicion) {
		Edicion = edicion;
	}
	

	public double getPrecio() {
		return Precio;
	}


	public void setPrecio(double precio) {
		Precio = precio;
	}
	
	
	
	
	
}
