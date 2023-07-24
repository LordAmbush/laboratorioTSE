package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
public class Rubro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	private boolean status;
    @OneToMany(mappedBy = "rubro")
    private List<Guia> guias;

	public Rubro() {
		super();
		this.status = true;
	}   
	
	
	public Rubro(String nombre) {
		super();
		this.status = true;
		this.nombre = nombre;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public List<Guia> getGuias() {
		return guias;
	}


	public void setGuias(List<Guia> guias) {
		this.guias = guias;
	}
   
}
