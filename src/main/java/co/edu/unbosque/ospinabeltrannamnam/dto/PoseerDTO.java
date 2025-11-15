package co.edu.unbosque.ospinabeltrannamnam.dto;

import java.math.BigDecimal;

public class PoseerDTO {
    private Integer id;
    private Integer codigoIngrediente;
    private Integer inventarioId;
    private BigDecimal stockMinimo;
	public PoseerDTO() {
		super();
	}
	public PoseerDTO(Integer id, Integer codigoIngrediente, Integer inventarioId, BigDecimal stockMinimo) {
		super();
		this.id = id;
		this.codigoIngrediente = codigoIngrediente;
		this.inventarioId = inventarioId;
		this.stockMinimo = stockMinimo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigoIngrediente() {
		return codigoIngrediente;
	}
	public void setCodigoIngrediente(Integer codigoIngrediente) {
		this.codigoIngrediente = codigoIngrediente;
	}
	public Integer getInventarioId() {
		return inventarioId;
	}
	public void setInventarioId(Integer inventarioId) {
		this.inventarioId = inventarioId;
	}
	public BigDecimal getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(BigDecimal stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	@Override
	public String toString() {
		return "PoseerDTO [id=" + id + ", codigoIngrediente=" + codigoIngrediente + ", inventarioId=" + inventarioId
				+ ", stockMinimo=" + stockMinimo + "]";
	}
   
}
