package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;


@Entity
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String matricula;
	private String marca;
	private String modelo;
	private int peso;
	private String capacidad;
	private boolean status;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    @OneToOne(mappedBy = "vehiculo")
    private PermisoNacCirculacion permisoNacCirculacion;
    @OneToMany(mappedBy = "vehiculo")
    private List<Itv> itv;
    
    @OneToMany(mappedBy = "vehiculo")
    private List<Viaje> viajes;

	public Vehiculo() {
		super();
		this.status = true;
	}   
	public String getMatricula() {
		return this.matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}   
	public String getMarca() {
		return this.marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}   
	public String getModelo() {
		return this.modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}   
	public int getPeso() {
		return this.peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}   
	public String getCapacidad() {
		return this.capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}	
	public PermisoNacCirculacion getPermisoNacCirculacion() {
		return permisoNacCirculacion;
	}
	public void setPermisoNacCirculacion(PermisoNacCirculacion permisoNacCirculacion) {
		this.permisoNacCirculacion = permisoNacCirculacion;
	}
	public List<Itv> getItv() {
		return itv;
	}
	public void setItv(List<Itv> itv) {
		this.itv = itv;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
   
}
