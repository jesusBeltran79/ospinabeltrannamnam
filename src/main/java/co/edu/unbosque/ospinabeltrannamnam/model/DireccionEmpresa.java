package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "direccion_empresa")
public class DireccionEmpresa {
	@Id
	@Column(name = "direccion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer direccionId;

	@Column(name = "direccion_text", length = 250, nullable = false)
	private String direccionText;

	@Column(name = "ciudad", length = 100)
	private String ciudad;

	@Column(name = "departamento", length = 100)
	private String departamento;

	@Column(name = "pais", length = 100)
	private String pais;

	public DireccionEmpresa() {
	}

	public DireccionEmpresa(String direccionText, String ciudad, String departamento, String pais) {
		super();
		this.direccionText = direccionText;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.pais = pais;
	}

	public Integer getDireccionId() {
		return direccionId;
	}

	public void setDireccionId(Integer direccionId) {
		this.direccionId = direccionId;
	}

	public String getDireccionText() {
		return direccionText;
	}

	public void setDireccionText(String direccionText) {
		this.direccionText = direccionText;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "DireccionEmpresa [direccionId=" + direccionId + ", direccionText=" + direccionText + ", ciudad="
				+ ciudad + ", departamento=" + departamento + ", pais=" + pais + "]";
	}

}
