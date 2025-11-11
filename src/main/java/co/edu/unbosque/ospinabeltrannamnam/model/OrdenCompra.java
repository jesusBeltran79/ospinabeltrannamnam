package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orden_compra")
public class OrdenCompra {
	@Id
	@Column(name = "orden_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ordenId;

	@Column(name = "fecha_emision", nullable = false)
	private LocalDate fechaEmision = LocalDate.now();

	@Column(name = "fecha_recepcion")
	private LocalDate fechaRecepcion;

	@Column(name = "estado", length = 30, nullable = false)
	private String estado = "pendiente";

	@Column(name = "total", precision = 20, scale = 4)
	private Integer total;
	@ManyToOne
	@JoinColumn(name = "nit_empresa", nullable = false)
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "administrador_id")
	private Administrador administrador;

	public OrdenCompra() {
	}

	public OrdenCompra(LocalDate fechaEmision, LocalDate fechaRecepcion, String estado, Integer total,
			Empresa empresa, Administrador administrador) {
		super();
		this.fechaEmision = fechaEmision;
		this.fechaRecepcion = fechaRecepcion;
		this.estado = estado;
		this.total = total;
		this.empresa = empresa;
		this.administrador = administrador;
	}

	public Integer getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(Integer ordenId) {
		this.ordenId = ordenId;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public LocalDate getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(LocalDate fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "OrdenCompra [ordenId=" + ordenId + ", fechaEmision=" + fechaEmision + ", fechaRecepcion="
				+ fechaRecepcion + ", estado=" + estado + ", total=" + total + ", empresa=" + empresa
				+ ", administrador=" + administrador + "]";
	}

}
