package tec.inf.javaEE.lab2023.dto;


public class ViajeDTO {

	private Integer id;
	private String ci;
	private String estado;
	private String origen;
	private String destino;


	public ViajeDTO() {

	}
	public ViajeDTO(Integer id, String ci, String estado, String origen, String destino) {
		super();
		this.id = id;
		this.ci = ci;
		this.estado = estado;
		this.origen = origen;
		this.destino = destino;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getHasta() {
		return destino;
	}
	public void setHasta(String hasta) {
		this.destino = hasta;
	}
	public String getDesde() {
		return origen;
	}
	public void setDesde(String desde) {
		this.origen = desde;
	}



}
