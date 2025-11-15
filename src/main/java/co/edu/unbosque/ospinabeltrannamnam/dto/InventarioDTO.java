package co.edu.unbosque.ospinabeltrannamnam.dto;

public class InventarioDTO {
    private Integer inventarioId;
    private Integer cantidad;
    private Integer administradorId;
	public InventarioDTO() {
		super();
	}
	public InventarioDTO(Integer inventarioId, Integer cantidad, Integer administradorId) {
		super();
		this.inventarioId = inventarioId;
		this.cantidad = cantidad;
		this.administradorId = administradorId;
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
