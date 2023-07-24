package tec.inf.javaEE.lab2023.dto;

public class VehiculoDTO {

	private String matricula;
	private String marca;
	private String modelo;
	private int peso;
	private String capacidad;
	private String empresa;
	private String itv;
	private String permiso;

	
	
	public VehiculoDTO() {
		
	}

	public VehiculoDTO(String matricula, String marca, String modelo, int peso, String capacidad, String empresa) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.peso = peso;
		this.capacidad = capacidad;
		this.empresa = empresa;

	}

	
	
	public String getItv() {
		return itv;
	}

	public void setItv(String itv) {
		this.itv = itv;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getPeso() {
		return peso;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}


	public String getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

}
