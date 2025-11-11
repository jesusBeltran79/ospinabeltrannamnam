package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lote")
public class Lote {
    @Id
    @Column(name = "lote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loteId;

    @Column(name = "fecha_recepcion")
    private LocalDate fechaRecepcion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "codigo_ingrediente", nullable = false)
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    public Lote() {}

	public Lote(LocalDate fechaRecepcion, Integer cantidad, OrdenCompra ordenCompra, Ingrediente ingrediente,
			Proveedor proveedor) {
		super();
		this.fechaRecepcion = fechaRecepcion;
		this.cantidad = cantidad;
		this.ordenCompra = ordenCompra;
		this.ingrediente = ingrediente;
		this.proveedor = proveedor;
	}

	public Integer getLoteId() {
		return loteId;
	}

	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}

	public LocalDate getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(LocalDate fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString() {
		return "Lote [loteId=" + loteId + ", fechaRecepcion=" + fechaRecepcion + ", cantidad=" + cantidad
				+ ", ordenCompra=" + ordenCompra + ", ingrediente=" + ingrediente + ", proveedor=" + proveedor + "]";
	}

}
