package co.edu.unbosque.ospinabeltrannamnam.dto;

public class InventarioDTO {
    private Integer inventarioId;
    private Integer cantidad;
    private Integer administradorId;
	public InventarioDTO() {
		super();
	}
	
	public InventarioDTO(Integer cantidad, Integer administradorId) {
		super();
		this.cantidad = cantidad;
		this.administradorId = administradorId;
	}

	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getAdministradorId() {
		return administradorId;
	}
	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}
	@Override
	public String toString() {
		return "InventarioDTO [inventarioId=" + inventarioId + ", cantidad=" + cantidad + ", administradorId="
				+ administradorId + "]";
	}
   
}
