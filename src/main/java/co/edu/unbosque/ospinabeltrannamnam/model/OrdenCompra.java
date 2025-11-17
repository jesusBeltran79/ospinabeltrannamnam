package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orden_compra")
public class OrdenCompra {
    @Id
    @Column(name = "orden_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordenId;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision = LocalDate.now();

    @Column(name = "estado", length = 30, nullable = false)
    private String estado = "activo"; // activo/inactivo/cancelado

    // Relación con administrador (persistente)
    @ManyToOne
    @JoinColumn(name = "administrador_id")
    private Administrador administrador;

    // Empresa destino (NECESARIO según tu petición) — relaciona por NIT
    @ManyToOne
    @JoinColumn(name = "nit_empresa", nullable = false)
    private Empresa empresa;

    // Relación ManyToMany simplificada con ingredientes
    @ManyToMany
    @JoinTable(
        name = "orden_ingrediente",
        joinColumns = @JoinColumn(name = "orden_id"),
        inverseJoinColumns = @JoinColumn(name = "codigo_ingrediente")
    )
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public OrdenCompra() { }

    public OrdenCompra(LocalDate fechaEmision, String estado, Administrador administrador, Empresa empresa) {
        this.fechaEmision = fechaEmision;
        this.estado = estado;
        this.administrador = administrador;
        this.empresa = empresa;
    }

    public Integer getOrdenId() { return ordenId; }
    public void setOrdenId(Integer ordenId) { this.ordenId = ordenId; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Administrador getAdministrador() { return administrador; }
    public void setAdministrador(Administrador administrador) { this.administrador = administrador; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public List<Ingrediente> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<Ingrediente> ingredientes) { this.ingredientes = ingredientes; }

    @Override
    public String toString() {
        return "OrdenCompra [ordenId=" + ordenId + ", fechaEmision=" + fechaEmision + ", estado=" + estado +
               ", empresa=" + (empresa != null ? empresa.getNit() : null) +
               ", administrador=" + (administrador != null ? administrador.getAdministradorId() : null) +
               ", ingredientes=" + ingredientes + "]";
    }
}
