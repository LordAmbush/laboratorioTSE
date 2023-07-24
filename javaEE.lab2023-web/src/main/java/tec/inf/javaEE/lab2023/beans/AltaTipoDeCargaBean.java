package tec.inf.javaEE.lab2023.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.TipoDeCargaBusinessLocal;
import tec.inf.javaEE.lab2023.dto.RubroDTO;
import tec.inf.javaEE.lab2023.dto.TipoDeCargaDTO;



@Named("altaTipoDeCarga")
@RequestScoped
public class AltaTipoDeCargaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	TipoDeCargaBusinessLocal tipoDeCargaBusiness;
	
	private String nombre;
	private String tipoCargaSeleccionado;
	private List<String> tiposDeCarga;
	
	@PostConstruct
	public void init() {
		List<String> tiposDeCarga = new ArrayList<>(); 
		List<TipoDeCargaDTO> tiposDeCargaDTO = tipoDeCargaBusiness.getTipoDeCarga();
		for (TipoDeCargaDTO tdCarga : tiposDeCargaDTO) {
			tiposDeCarga.add(tdCarga.getNombre());
		}
		this.tiposDeCarga = tiposDeCarga;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipoCargaSeleccionado() {
		return tipoCargaSeleccionado;
	}

	public void setTipoCargaSeleccionado(String tipoCargaSeleccionado) {
		this.tipoCargaSeleccionado = tipoCargaSeleccionado;
	}

	public List<String> getTiposDeCarga() {
		return tiposDeCarga;
	}
	
	public void altaTipoDeCarga() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			tipoDeCargaBusiness.altaTipoDeCarga(nombre);
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/administrador.xhtml");
		}catch(Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	
	public void buttonClickedDelete(String tCarga) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		try {
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/administrador.xhtml");
			tipoDeCargaBusiness.deleteTipoCarga(tCarga);
		}catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	

}

