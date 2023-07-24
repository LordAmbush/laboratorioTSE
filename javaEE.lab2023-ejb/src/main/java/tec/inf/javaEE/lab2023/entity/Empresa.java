package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	private int nroEmpresa;
	private String nombreEmpresa;
	private String razonSocial;
	private String direccion;
	private boolean status;
	
	@OneToOne(mappedBy = "empresa")
    private AdminEmpresa adminEmpresa;
	@OneToMany(mappedBy = "empresa")
    private List<Vehiculo> vehiculos;
    @OneToMany(mappedBy = "empresa")
    private List<Conductor> conductores;

	
	public Empresa() {
		super();
		this.status = true;
		this.adminEmpresa = null;
		this.vehiculos = null;
		this.conductores = null;
	}   
	public int getNroEmpresa() {
		return this.nroEmpresa;
	}

	public void setNroEmpresa(int nroEmpresa) {
		this.nroEmpresa = nroEmpresa;
	}  

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setnombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}   

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}   
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public AdminEmpresa getAdminEmpresa() {
		return adminEmpresa;
	}
	public void setAdminEmpresa(AdminEmpresa adminEmpresa) {
		this.adminEmpresa = adminEmpresa;
	}
	
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	public List<Conductor> getConductores() {
		return conductores;
	}
	public void setConductores(List<Conductor> conductores) {
		this.conductores = conductores;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	@Override
	public String toString() {
		return "Empresa [nroEmpresa=" + nroEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", razonSocial=" + razonSocial
				+ ", direccion=" + direccion + ", adminEmpresa=" + adminEmpresa + ", vehiculos=" + vehiculos
				+ ", conductores=" + conductores + "]";
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
   
}
