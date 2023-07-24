package tec.inf.javaEE.lab2023.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

@Entity
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "GraficasDTO",
        classes = @ConstructorResult(
            targetClass = GraficasDTO.class,
            columns = {
                @ColumnResult(name = "data", type = String.class),
                @ColumnResult(name = "cantidad", type = int.class)
            }
        )
    )
})
public class GraficasDTO {
	@Id
	private String data;
	private int cantidad;
	
	public GraficasDTO() {
		super();
	}
	public GraficasDTO(String data, int cantidad) {
		super();
		this.data = data;
		this.cantidad = cantidad;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}




