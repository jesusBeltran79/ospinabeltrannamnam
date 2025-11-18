package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

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
	private Integer precioVenta;

	@Column(name = "margen_ganancia", precision = 10, scale = 6)
	private Integer margenGanancia;

	@Column(name = "costo", precision = 18, scale = 4)
	private Integer costo;

	@Column(name = "imagen", length = 255)
	private String imagen;

	@Column(name = "receta_id")
	private Integer recetaId;

	public Plato() {
	}

	public Plato(String nombre, Integer precioVenta, Integer margenGanancia, Integer costo, String imagen,
			Integer recetaId) {
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.margenGanancia = margenGanancia;
		this.costo = costo;
		this.imagen = imagen;
		this.recetaId = recetaId;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Integer getRecetaId() {
		return recetaId;
	}

	public void setRecetaId(Integer recetaId) {
		this.recetaId = recetaId;
	}

	@Override
	public String toString() {
		return "Plato [platoId=" + platoId + ", nombre=" + nombre + ", precioVenta=" + precioVenta + ", margenGanancia="
				+ margenGanancia + ", costo=" + costo + ", recetaId=" + recetaId + ", imagen=" + imagen + "]";
	}
}
