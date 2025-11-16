package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa {
	@Id
	@Column(name = "nit")
	private Integer nit;

	@Column(name = "nombre", length = 150, nullable = false)
	private String nombre;

	@Column(name = "email", length = 150)
	private String email;

	@Column(name = "telefono")
	private Long telefono;

	@ManyToOne
	@JoinColumn(name = "direccion_id")
	private DireccionEmpresa direccion;

	@Column(name = "estado", length = 20, nullable = false)
	private String estado = "activo";

	public Empresa() {
	}

	

	public Empresa(Integer nit, String nombre, String email, Long telefono, DireccionEmpresa direccion, String estado) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.estado = estado;
	}



	public Integer getNit() {
		return nit;
	}

	public void setNit(Integer nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Long getTelefono() {
		return telefono;
	}



	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}



	public DireccionEmpresa getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionEmpresa direccion) {
		this.direccion = direccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Empresa [nit=" + nit + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", estado=" + estado + "]";
	}

}
