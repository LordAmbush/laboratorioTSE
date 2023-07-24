package tec.inf.javaEE.lab2023.dto;

public class TrackingDTO {
	private int id;
	private float lat;	
	private float lon;	
	private int viaje;
	
	
	
	public TrackingDTO(int id, float lat, float lon, int viaje) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.viaje = viaje;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public int getViaje() {
		return viaje;
	}
	public void setViaje(int viaje) {
		this.viaje = viaje;
	}
	
	
}
