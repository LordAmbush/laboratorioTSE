package tec.inf.javaEE.lab2023.dto;

public class TipoDeCargaDTO {
	private String nombre;

	public TipoDeCargaDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public TipoDeCargaDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoDeCargaDTO [nombre=" + nombre + "]";
	}
	
	
}

	
