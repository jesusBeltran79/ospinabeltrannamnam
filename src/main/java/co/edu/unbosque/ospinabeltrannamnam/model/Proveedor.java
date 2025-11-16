package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
public class Proveedor {
	@Id
	@Column(name = "proveedor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer proveedorId;

	@ManyToOne
	@JoinColumn(name = "nit_empresa", nullable = false)
	private Empresa empresa;

	@Column(name = "primer_nombre", length = 80, nullable = false)
	private String primerNombre;

	@Column(name = "segundo_nombre", length = 80)
	private String segundoNombre;

	@Column(name = "primer_apellido", length = 80, nullable = false)
	private String primerApellido;

	@Column(name = "segundo_apellido", length = 80)
	private String segundoApellido;

	@Column(name = "email", length = 150)
	private String email;

	@Column(name = "telefono")
	private Long telefono;

	@Column(name = "cargo", length = 80)
	private String cargo;

	public Proveedor() {
	}

	public Proveedor(Empresa empresa, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String email, Long telefono, String cargo) {
		super();
		this.empresa = empresa;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.email = email;
		this.telefono = telefono;
		this.cargo = cargo;
	}

	public Integer getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Proveedor [proveedorId=" + proveedorId + ", empresa=" + empresa + ", primerNombre=" + primerNombre
				+ ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", email=" + email + ", telefono=" + telefono + ", cargo=" + cargo + "]";
	}

}
