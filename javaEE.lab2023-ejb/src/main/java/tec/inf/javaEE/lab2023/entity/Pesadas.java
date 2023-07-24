package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pesadas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    @ManyToOne
    @JoinColumn(name = "viaje_id")
	private Viaje viaje;
	private String peso;
	
	
	public Pesadas() {}
	
	public Pesadas(Viaje viaje, String peso) {
		super();
		this.viaje = viaje;
		this.peso = peso;
	}
	public int getId() {
		return id;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	
}
