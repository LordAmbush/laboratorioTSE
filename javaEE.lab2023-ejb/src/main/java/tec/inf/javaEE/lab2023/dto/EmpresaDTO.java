package tec.inf.javaEE.lab2023.dto;

public class EmpresaDTO {

	private int nroEmpresa;
	private String nombreEmpresa;
	private String razonSocial;
	private String direccion;
	
	
	public EmpresaDTO() {
		
	}
	

	public EmpresaDTO(int nroEmpresa, String nombreEmpresa, String razonSocial, String direccion) {
		super();
		this.nroEmpresa = nroEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
	}


	public int getNroEmpresa() {
		return nroEmpresa;
	}


	public void setNroEmpresa(int nroEmpresa) {
		this.nroEmpresa = nroEmpresa;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	@Override
	public String toString() {
		return "EmpresaDTO [nroEmpresa=" + nroEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", razonSocial="
				+ razonSocial + ", direccion=" + direccion + "]";
	}
	
	
	
}
