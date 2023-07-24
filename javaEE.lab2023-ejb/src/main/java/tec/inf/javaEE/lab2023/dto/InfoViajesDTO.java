package tec.inf.javaEE.lab2023.dto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

@Entity
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "InfoViajesDTO",
        classes = @ConstructorResult(
            targetClass = InfoViajesDTO.class,
            columns = {
                @ColumnResult(name = "idViaje", type = int.class),
                @ColumnResult(name = "estado", type = String.class),
                @ColumnResult(name = "inicio", type = String.class),
                @ColumnResult(name = "fin", type = String.class),
                @ColumnResult(name = "conductor", type = String.class),
                @ColumnResult(name = "vehiculo", type = String.class),
                @ColumnResult(name = "idGuia", type = int.class),
                @ColumnResult(name = "TdeCarga", type = String.class),
            }
        )
    )
})
public class InfoViajesDTO {

	@Id
	private int idViaje;
	private String estado;
	private String inicio;
	private String fin;
	private String conductor;
	private String vehiculo;
	private int idGuia;
	private String TdeCarga;
	
	
	public InfoViajesDTO() {}
	
	public InfoViajesDTO(int idViaje, String estado, String inicio, String fin, String conductor, String vehiculo,
			int idGuia, String tdeCarga) {
		super();
		this.idViaje = idViaje;
		this.estado = estado;
		this.inicio = inicio;
		this.fin = fin;
		this.conductor = conductor;
		this.vehiculo = vehiculo;
		this.idGuia = idGuia;
		TdeCarga = tdeCarga;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getIdGuia() {
		return idGuia;
	}

	public void setIdGuia(int idGuia) {
		this.idGuia = idGuia;
	}

	public String getTdeCarga() {
		return TdeCarga;
	}

	public void setTdeCarga(String tdeCarga) {
		TdeCarga = tdeCarga;
	}
	
	
	
}
