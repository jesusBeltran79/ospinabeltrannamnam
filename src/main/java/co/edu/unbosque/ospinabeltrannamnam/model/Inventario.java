package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {
	@Id
	@Column(name = "inventario_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inventarioId;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@OneToOne
	@JoinColumn(name = "administrador_id", unique = true)
	private Administrador administrador;

	public Inventario() {
	}

	public Inventario(Integer cantidad, Administrador administrador) {
		super();
		this.cantidad = cantidad;
		this.administrador = administrador;
	}

	public Integer getInventarioId() {
		return inventarioId;
	}

	public void setInventarioId(Integer inventarioId) {
		this.inventarioId = inventarioId;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "Inventario [inventarioId=" + inventarioId + ", cantidad=" + cantidad + ", administrador="
				+ administrador + "]";
	}

}
