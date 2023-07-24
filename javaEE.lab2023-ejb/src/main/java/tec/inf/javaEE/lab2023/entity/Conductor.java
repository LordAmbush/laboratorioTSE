package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Conductor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    @OneToMany(mappedBy = "conductor")
    private List<Viaje> viajes;
    private boolean status;
    
	@Override
	public String toString() {
		return "Conductor :" + super.toString() + "";
	}
    public Conductor(String ci, String username, String email, String fechaNac, Empresa e) {
    	super(ci, username, email, fechaNac);	
    	this.empresa = e;
	}
	public Conductor() {
		super();    
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
	
	public void addViaje(Viaje v) {
		if (this.viajes.add(v)) {
			System.out.println("El viaje se ha agregado correctamente al conductor"+this.getUsername());
		}
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}  
    
}
