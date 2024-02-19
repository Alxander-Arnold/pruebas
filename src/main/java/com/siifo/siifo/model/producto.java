package com.siifo.siifo.model;





public class producto {

	
	private String nombreProducto;
	private int cantidadProducto;
	private int precioProducto;
	private String categoria;
	private String fecha;
	



	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public int getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public int getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "producto [nombreProducto=" + nombreProducto + ", cantidadProducto=" + cantidadProducto
				+ ", precioProducto=" + precioProducto + ", categoria=" + categoria + ", fecha=" + fecha + "]";
	}
	
	
	

}
