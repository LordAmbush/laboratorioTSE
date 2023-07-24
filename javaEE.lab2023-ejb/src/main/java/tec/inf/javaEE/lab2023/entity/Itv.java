package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Entity
public class Itv implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String fechaDesde;
    private String fechaHasta;
    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    
	public Itv() {
		super();
		this.status = true;
	}   
	
    public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Integer getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Itv(Integer id, String fechaDesde, String fechaHasta, Vehiculo vehiculo) {
		super();
		this.id = id;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.vehiculo = vehiculo;
		this.status = true;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean alDia() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaHasta = LocalDate.parse(this.fechaHasta, formatter);
	    LocalDate fechaActual = LocalDate.now(); 
	    return fechaHasta.isAfter(fechaActual) || fechaHasta.isEqual(fechaActual);

	}
	
}
