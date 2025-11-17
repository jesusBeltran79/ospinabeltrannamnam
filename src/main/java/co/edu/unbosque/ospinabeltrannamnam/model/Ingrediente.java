package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {
    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    // cantidad: según tu decisión puede representar stock o cantidad solicitada cuando se usa en una orden
    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado = "disponible";

    @Column(name = "costo_unitario", precision = 18, scale = 4)
    private Integer costoUnitario;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Receta> recetas = new ArrayList<>();

    @ManyToMany(mappedBy = "ingredientes")
    private List<OrdenCompra> ordenes = new ArrayList<>();

    public Ingrediente() { }

    public Ingrediente(String nombre, Integer cantidad, String estado, Integer costoUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estado = estado;
        this.costoUnitario = costoUnitario;
    }

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(Integer costoUnitario) { this.costoUnitario = costoUnitario; }

    public List<Receta> getRecetas() { return recetas; }
    public void setRecetas(List<Receta> recetas) { this.recetas = recetas; }

    public List<OrdenCompra> getOrdenes() { return ordenes; }
    public void setOrdenes(List<OrdenCompra> ordenes) { this.ordenes = ordenes; }

    @Override
    public String toString() {
        return "Ingrediente [codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad +
               ", estado=" + estado + ", costoUnitario=" + costoUnitario + "]";
    }
}
