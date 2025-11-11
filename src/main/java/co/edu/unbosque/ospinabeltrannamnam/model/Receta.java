package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receta")
public class Receta {
	@Id
	@Column(name = "receta_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer recetaId;

	@Column(name = "nombre", length = 150, nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 150)
	private String descripcion;

	@Column(name = "tiempo_preparacion", nullable = false)
	private Integer tiempoPreparacion = 0;

	public Receta() {
	}

	public Receta(String nombre, String descripcion, Integer tiempoPreparacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public Integer getRecetaId() {
		return recetaId;
	}

	public void setRecetaId(Integer recetaId) {
		this.recetaId = recetaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Integer tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	@Override
	public String toString() {
		return "Receta [recetaId=" + recetaId + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", tiempoPreparacion=" + tiempoPreparacion + "]";
	}

}
