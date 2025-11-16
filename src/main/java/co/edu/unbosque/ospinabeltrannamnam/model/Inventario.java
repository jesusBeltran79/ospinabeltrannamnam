package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

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

    
    @ManyToOne
    @JoinColumn(name = "codigo_ingrediente")
    private Ingrediente ingrediente;

    
    @Column(name = "stock_minimo", precision = 20, scale = 4)
    private BigDecimal stockMinimo = BigDecimal.ZERO;

    public Inventario() { }

    public Inventario(Integer cantidad, Administrador administrador, Ingrediente ingrediente, BigDecimal stockMinimo) {
        this.cantidad = cantidad;
        this.administrador = administrador;
        this.ingrediente = ingrediente;
        this.stockMinimo = stockMinimo;
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

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public BigDecimal getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(BigDecimal stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	@Override
    public String toString() {
        return "Inventario [inventarioId=" + inventarioId + ", cantidad=" + cantidad + ", administrador=" + administrador
                + ", ingrediente=" + (ingrediente != null ? ingrediente.getCodigo() : null) + ", stockMinimo=" + stockMinimo + "]";
    }
}
