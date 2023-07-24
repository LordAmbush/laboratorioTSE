package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PermisoNacCirculacion implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int numero;
    private String fechaDesde;
    private String fechaHasta;
    private boolean status;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    
	public PermisoNacCirculacion() {
		super();
		this.status = true;
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


	public void setNumero(int numero){
        this.numero = numero;
    }

    public int getNumero(){
        return this.numero;
    }

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public boolean alDia() {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateHasta = LocalDate.parse(this.fechaHasta, formatter);
	    LocalDate dateActual = LocalDate.now(); 

	    return dateHasta.isAfter(dateActual) || dateHasta.isEqual(dateActual);
	}
}
