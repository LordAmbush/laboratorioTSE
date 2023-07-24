package tec.inf.javaEE.lab2023.dto;

import java.sql.Date;

public class GuiaDTO {
	int id;
	private int volumenCarga;
	private Date fecha;
	private String origen;
	private String destino;
	private String tipoDeCarga;
	private String rubro;
	
	
	public GuiaDTO() {
		super();
	}


	public GuiaDTO(int id, int volumenCarga, Date fecha, String origen, String destino, String tipoDeCarga, String rubro) {
		super();
		this.id = id;
		this.volumenCarga = volumenCarga;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.tipoDeCarga = tipoDeCarga;
		this.rubro = rubro;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getVolumenCarga() {
		return volumenCarga;
	}


	public void setVolumenCarga(int volumenCarga) {
		this.volumenCarga = volumenCarga;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getTipoDeCarga() {
		return tipoDeCarga;
	}


	public void setTipoDeCarga(String tipoDeCarga) {
		this.tipoDeCarga = tipoDeCarga;
	}


	public String getRubro() {
		return rubro;
	}


	public void setRubro(String rubro) {
		this.rubro = rubro;
	}


	@Override
	public String toString() {
		return "GuiaDTO [volumenCarga=" + volumenCarga + ", fecha=" + fecha + ", origen=" + origen + ", destino="
				+ destino + ", tipoDeCarga=" + tipoDeCarga + ", rubro=" + rubro + "]";
	}
	
	
	
}
