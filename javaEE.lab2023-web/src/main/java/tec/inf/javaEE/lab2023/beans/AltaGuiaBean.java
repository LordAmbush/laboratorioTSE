package tec.inf.javaEE.lab2023.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.GuiaBusinessLocal;
import tec.inf.javaEE.lab2023.business.TipoDeCargaBusinessLocal;
import tec.inf.javaEE.lab2023.dto.GuiaDTO;
import tec.inf.javaEE.lab2023.dto.TipoDeCargaDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;



@Named("altaGuia")
@SessionScoped
public class AltaGuiaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	GuiaBusinessLocal guiaBusiness;
	
	private int volumenDeCarga;
	private Date fechaRegistro;
	private String origen;
	private String destino;
	private String tipoCargaSeleccionado;
	private String rubroSeleccionado;
	private int ultGuia;
	
	public int getVolumenDeCarga() {
		return volumenDeCarga;
	}

	public void setVolumenDeCarga(int volumenDeCarga) {
		this.volumenDeCarga = volumenDeCarga;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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

	public String getTipoCargaSeleccionado() {
		return tipoCargaSeleccionado;
	}

	public void setTipoCargaSeleccionado(String tipoCargaSeleccionado) {
		this.tipoCargaSeleccionado = tipoCargaSeleccionado;
	}

	public String getRubroSeleccionado() {
		return rubroSeleccionado;
	}
	public void setRubroSeleccionado(String rubroSeleccionado) {
		this.rubroSeleccionado = rubroSeleccionado;
	}
	
	public int obtenerGuia() {
		return guiaBusiness.obtenerUltimaGuia();
	}

	public void altaGuia() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			guiaBusiness.altaGuia(volumenDeCarga, origen, destino, rubroSeleccionado, tipoCargaSeleccionado);
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/altaViaje.xhtml");
		}catch(Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}

	public int getUltGuia() {
		return ultGuia;
	}

	public void setUltGuia(int ultGuia) {
		this.ultGuia = ultGuia;
	}
	

}

