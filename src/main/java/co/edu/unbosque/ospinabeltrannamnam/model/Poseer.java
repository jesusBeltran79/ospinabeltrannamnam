package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "poseer", uniqueConstraints = @UniqueConstraint(columnNames = { "codigo_ingrediente", "inventario_id" }))
public class Poseer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "poseer_id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "codigo_ingrediente", referencedColumnName = "codigo", nullable = false)
	private Ingrediente ingrediente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "inventario_id", referencedColumnName = "inventario_id", nullable = false)
	private Inventario inventario;

	@Column(name = "stock_minimo")
	private BigDecimal stockMinimo = BigDecimal.ZERO;

	public Poseer() {
	}

	public Poseer(Ingrediente ingrediente, Inventario inventario, BigDecimal stockMinimo) {
		this.ingrediente = ingrediente;
		this.inventario = inventario;
		this.stockMinimo = stockMinimo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public BigDecimal getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(BigDecimal stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	
}
