package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {
	@Id
	@Column(name = "administrador_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer administradorId;

	@Column(name = "nombre_administrador", length = 150, nullable = false)
	private String nombreAdministrador;

	@Column(name = "clave", length = 255, nullable = false)
	private String clave;

	public Administrador() {
	}

	public Administrador(String nombreAdministrador, String clave) {
		super();
		this.nombreAdministrador = nombreAdministrador;
		this.clave = clave;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Administrador [administradorId=" + administradorId + ", nombreAdministrador=" + nombreAdministrador
				+ ", clave=" + clave + "]";
	}

}
