package tec.inf.javaEE.lab2023.entity;


import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Tracking implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private float lon;
	
	private float lat;
	
    @ManyToOne
    @JoinColumn(name = "empresa_id")
	private Viaje viaje;
	
	public Tracking() {
		super();
	}

	public Tracking(float lon, float lat, Viaje viaje) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.viaje = viaje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	
	
}
