package tec.inf.javaEE.lab2023.dto;

public class PesadasDTO {

	private int id;
	private int viaje;
	private String peso;
	
	
	public PesadasDTO() {}
	
	public PesadasDTO(int viaje, String peso) {
		super();
		this.viaje = viaje;
		this.peso = peso;
	}
	public int getViaje() {
		return viaje;
	}
	public void setViaje(int viaje) {
		this.viaje = viaje;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
