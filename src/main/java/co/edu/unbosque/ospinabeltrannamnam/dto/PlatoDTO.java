package co.edu.unbosque.ospinabeltrannamnam.dto;

import java.math.BigDecimal;

public class PlatoDTO {
	private Integer platoId;
	private String nombre;
	private BigDecimal precioVenta;
	private BigDecimal margenGanancia;
	private BigDecimal costo;
	private String imagen;

	public PlatoDTO() {
		super();
	}

	

	public PlatoDTO(String nombre, BigDecimal precioVenta, BigDecimal margenGanancia, BigDecimal costo, String imagen) {
		super();
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.margenGanancia = margenGanancia;
		this.costo = costo;
		this.imagen = imagen;
	}



	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getMargenGanancia() {
		return margenGanancia;
	}

	public void setMargenGanancia(BigDecimal margenGanancia) {
		this.margenGanancia = margenGanancia;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "PlatoDTO [platoId=" + platoId + ", nombre=" + nombre + ", precioVenta=" + precioVenta
				+ ", margenGanancia=" + margenGanancia + ", costo=" + costo + ", imagen=" + imagen + "]";
	}

}
