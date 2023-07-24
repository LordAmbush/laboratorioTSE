package tec.inf.javaEE.lab2023.dto;

public class RubroDTO {
	private String nombre;
	
	public RubroDTO() {
		super();
	}
	
	public RubroDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "RubroDTO [nombre=" + nombre + "]";
	}
	
	

}
