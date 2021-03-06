package Logica;

public class Carrito {

	private int referencia;
	private String nick;
	private String carta;
	private int unidades;
	private float precio;
	private String fecha;

	public String getCarta() {
		return carta;
	}

	public void setCarta(String carta) {
		this.carta = carta;
	}
	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Carrito() {

	}

	public Carrito(int referencia, String nick, String carta, int unidades, float precio, String fecha) {
		this.referencia = referencia;
		this.nick = nick;
		this.carta = carta;
		this.unidades = unidades;
		this.precio = precio;
		this.fecha = fecha;
		
	}

}
