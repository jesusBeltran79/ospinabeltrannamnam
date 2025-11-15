package co.edu.unbosque.ospinabeltrannamnam.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {
    @Id
    @Column(name = "codigo", length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer codigo;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "costo_unitario", precision = 18, scale = 4, nullable = false)
    private Integer costoUnitario;

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado = "disponible";

   
    @ManyToMany(mappedBy = "ingredientes")
    private List<Receta> recetas = new ArrayList<>();

    public Ingrediente() { }

    public Ingrediente(String nombre, Integer costoUnitario, LocalDate fechaCaducidad, String estado) {
        this.nombre = nombre;
        this.costoUnitario = costoUnitario;
        this.fechaCaducidad = fechaCaducidad;
        this.estado = estado;
    }

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(Integer costoUnitario) { this.costoUnitario = costoUnitario; }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<Receta> getRecetas() { return recetas; }
    public void setRecetas(List<Receta> recetas) { this.recetas = recetas; }

    
    public void agregarReceta(Receta receta) {
        if (receta == null) return;
        if (!this.recetas.contains(receta)) {
            this.recetas.add(receta);
        }
        if (!receta.getIngredientes().contains(this)) {
            receta.getIngredientes().add(this);
        }
    }

    public void removerReceta(Receta receta) {
        if (receta == null) return;
        this.recetas.remove(receta);
        receta.getIngredientes().remove(this);
    }

    @Override
    public String toString() {
        return "Ingrediente [codigo=" + codigo + ", nombre=" + nombre + ", costoUnitario=" + costoUnitario
                + ", fechaCaducidad=" + fechaCaducidad + ", estado=" + estado + "]";
    }
}
