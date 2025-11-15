package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plato")
public class Plato {
	@Id
	@Column(name = "plato_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer platoId;

	@Column(name = "nombre", length = 150, nullable = false)
	private String nombre;

	@Column(name = "precio_venta", precision = 18, scale = 4, nullable = false)
	private BigDecimal precioVenta = BigDecimal.ZERO;

	@Column(name = "margen_ganancia", precision = 10, scale = 6)
	private BigDecimal margenGanancia = BigDecimal.ZERO;

	@Column(name = "costo", precision = 18, scale = 4)
	private BigDecimal costo = BigDecimal.ZERO;

	@Column(name = "imagen", length = 255)
	private String imagen;

	public Plato() {
	}

	public Plato(String nombre, BigDecimal precioVenta, BigDecimal margenGanancia, BigDecimal costo, String imagen) {
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.margenGanancia = margenGanancia;
		this.costo = costo;
		this.imagen = imagen;
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
		return "Plato [platoId=" + platoId + ", nombre=" + nombre + ", precioVenta=" + precioVenta + ", margenGanancia="
				+ margenGanancia + ", costo=" + costo + ", imagen=" + imagen + "]";
	}
}
