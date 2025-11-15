package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	@Column(name = "imagen", length = 255)
	private String imagen;

	@ManyToMany
	@JoinTable(name = "emplear", joinColumns = @JoinColumn(name = "receta_id"), inverseJoinColumns = @JoinColumn(name = "codigo_ingrediente"))
	private List<Ingrediente> ingredientes = new ArrayList<>();

	public Receta() {
	}

	public Receta(String nombre, String descripcion, Integer tiempoPreparacion, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tiempoPreparacion = tiempoPreparacion;
		this.imagen = imagen;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public void agregarIngrediente(Ingrediente ingrediente) {
		if (ingrediente == null)
			return;
		if (!this.ingredientes.contains(ingrediente)) {
			this.ingredientes.add(ingrediente);
		}
		if (!ingrediente.getRecetas().contains(this)) {
			ingrediente.getRecetas().add(this);
		}
	}

	public void removerIngrediente(Ingrediente ingrediente) {
		if (ingrediente == null)
			return;
		this.ingredientes.remove(ingrediente);
		ingrediente.getRecetas().remove(this);
	}

	@Override
	public String toString() {
		return "Receta [recetaId=" + recetaId + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", tiempoPreparacion=" + tiempoPreparacion + ", imagen=" + imagen + "]";
	}
}
