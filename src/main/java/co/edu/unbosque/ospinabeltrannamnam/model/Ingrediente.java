package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {
	@Id
	@Column(name = "codigo", length = 50)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(name = "nombre", length = 150, nullable = false)
	private String nombre;

	@Column(name = "costo_unitario", precision = 18, scale = 4, nullable = false)
	private Integer costoUnitario;

	@Column(name = "fecha_caducidad")
	private LocalDate fechaCaducidad;

	@Column(name = "estado", length = 20, nullable = false)
	private String estado = "disponible";

	public Ingrediente() {
	}

	public Ingrediente(String nombre, Integer costoUnitario, LocalDate fechaCaducidad, String estado) {
		super();
		this.nombre = nombre;
		this.costoUnitario = costoUnitario;
		this.fechaCaducidad = fechaCaducidad;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(Integer costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Ingrediente [codigo=" + codigo + ", nombre=" + nombre + ", costoUnitario=" + costoUnitario
				+ ", fechaCaducidad=" + fechaCaducidad + ", estado=" + estado + "]";
	}

}
