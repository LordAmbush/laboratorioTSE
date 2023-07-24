package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Guia implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int volumenDeCarga;
	private String fechaRegistro;
	private String origen;
	private String destino;
	private boolean status;
	
    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;
    @ManyToOne
    @JoinColumn(name = "tipocarga_id")
    private TipoDeCarga tipoDeCarga;
    
    @OneToOne(mappedBy = "guia")
    private Viaje viaje;

	public Guia() {
		super();
		this.status = true;
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   

	public String getfechaRegistro() {
		return this.fechaRegistro;
	}

	public void setfechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}  
		public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}  
		public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}  

	public int getVolumenDeCarga() {
		return this.volumenDeCarga;
	}

	public void setVolumenDeCarga(int volumenDeCarga) {
		this.volumenDeCarga = volumenDeCarga;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Rubro getRubro() {
		return rubro;
	}
	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	public TipoDeCarga getTipoDeCarga() {
		return tipoDeCarga;
	}
	public void setTipoDeCarga(TipoDeCarga tipoDeCarga) {
		this.tipoDeCarga = tipoDeCarga;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
   
}
