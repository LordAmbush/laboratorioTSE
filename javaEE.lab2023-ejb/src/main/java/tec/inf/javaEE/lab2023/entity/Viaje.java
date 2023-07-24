package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Viaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String estado;
	private String inicioViaje;
	private String finViaje;
	private boolean status;
    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @OneToOne
    @JoinColumn(name = "guia_id")
    private Guia guia;
	@OneToMany(mappedBy = "viaje")
    private List<Pesadas> pesadas;
	@OneToMany(mappedBy = "viaje")
    private List<Tracking> tracking;
    


	public List<Tracking> getTracking() {
		return tracking;
	}

	public void setTracking(List<Tracking> tracking) {
		this.tracking = tracking;
	}

	public Viaje() {
		super();
		this.status = true;
	}

	public List<Pesadas> getPesadas() {
		return pesadas;
	}

	public void setPesadas(List<Pesadas> pesadas) {
		this.pesadas = pesadas;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getInicioViaje() {
		return inicioViaje;
	}

	public void setInicioViaje(String inicioViaje) {
		this.inicioViaje = inicioViaje;
	}

	public String getFinViaje() {
		return finViaje;
	}

	public void setFinViaje(String finViaje) {
		this.finViaje = finViaje;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	
}