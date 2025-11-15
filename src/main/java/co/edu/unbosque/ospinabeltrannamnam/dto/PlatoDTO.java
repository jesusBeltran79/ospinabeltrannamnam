package co.edu.unbosque.ospinabeltrannamnam.dto;

import java.math.BigDecimal;

public class PlatoDTO {
	private Integer platoId;
	private String nombre;
	private Integer precioVenta;
	private Integer margenGanancia;
	private Integer costo;

	public PlatoDTO() {
		super();
	}

	public PlatoDTO(Integer platoId, String nombre, Integer precioVenta, Integer margenGanancia, Integer costo) {
		super();
		this.platoId = platoId;
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.margenGanancia = margenGanancia;
		this.costo = costo;
	}

	public Integer getPlatoId() {
		return platoId;
	}

	public void setPlatoId(Integer platoId) {
		this.platoId = platoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Integer precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getMargenGanancia() {
		return margenGanancia;
	}

	public void setMargenGanancia(Integer margenGanancia) {
		this.margenGanancia = margenGanancia;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "PlatoDTO [platoId=" + platoId + ", nombre=" + nombre + ", precioVenta=" + precioVenta
				+ ", margenGanancia=" + margenGanancia + ", costo=" + costo + "]";
	}

}
