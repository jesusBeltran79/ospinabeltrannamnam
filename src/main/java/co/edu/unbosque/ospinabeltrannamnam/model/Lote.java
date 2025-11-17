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

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad; // nuevo

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio")
    private Integer precio; // nuevo: precio total del lote

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = true)
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "codigo_ingrediente", nullable = true)
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    public Lote() {}

    public Lote(LocalDate fechaRecepcion, LocalDate fechaCaducidad, Integer cantidad, Integer precio,
                OrdenCompra ordenCompra, Ingrediente ingrediente, Proveedor proveedor) {
        this.fechaRecepcion = fechaRecepcion;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ordenCompra = ordenCompra;
        this.ingrediente = ingrediente;
        this.proveedor = proveedor;
    }

    public Integer getLoteId() { return loteId; }
    public void setLoteId(Integer loteId) { this.loteId = loteId; }

    public LocalDate getFechaRecepcion() { return fechaRecepcion; }
    public void setFechaRecepcion(LocalDate fechaRecepcion) { this.fechaRecepcion = fechaRecepcion; }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }

    public OrdenCompra getOrdenCompra() { return ordenCompra; }
    public void setOrdenCompra(OrdenCompra ordenCompra) { this.ordenCompra = ordenCompra; }

    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

    @Override
    public String toString() {
        return "Lote [loteId=" + loteId + ", fechaRecepcion=" + fechaRecepcion + ", fechaCaducidad=" + fechaCaducidad
                + ", cantidad=" + cantidad + ", precio=" + precio + ", ordenCompra=" + ordenCompra
                + ", ingrediente=" + ingrediente + ", proveedor=" + proveedor + "]";
    }
}
