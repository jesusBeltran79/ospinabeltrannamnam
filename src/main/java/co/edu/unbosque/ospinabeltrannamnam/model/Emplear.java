package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "emplear")
public class Emplear {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emplear_id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "receta_id", referencedColumnName = "receta_id", nullable = false)
	private Receta receta;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "codigo_ingrediente", referencedColumnName = "codigo", nullable = false)
	private Ingrediente ingrediente;

	public Emplear() {
	}

	public Emplear(Receta receta, Ingrediente ingrediente) {
		super();
		this.receta = receta;
		this.ingrediente = ingrediente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public String toString() {
		return "Emplear [id=" + id + ", receta=" + receta + ", ingrediente=" + ingrediente + "]";
	}

}
