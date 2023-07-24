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

import tec.inf.javaEE.lab2023.business.RubroBusinessLocal;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.RubroDTO;

@Named("altaRubro")
@RequestScoped
public class AltaRubroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	RubroBusinessLocal rubroBusiness;
	
	private String nombre;
	private String rubroSeleccionado;
	private List<String> tiposRubro;
	
	@PostConstruct
	public void init() {
		List<String> tiposDeRubro = new ArrayList<>(); 
		List<RubroDTO> rubrosDTO = rubroBusiness.getRubro();
		for (RubroDTO rubro : rubrosDTO) {
			tiposDeRubro.add(rubro.getNombre());
		}
		this.tiposRubro = tiposDeRubro;
	}
	
	public List<String> getTiposRubro() {
		return tiposRubro;
	}

	public void setTiposRubro(List<String> tiposRubro) {
		this.tiposRubro = tiposRubro;
	}


	public String getRubroSeleccionado() {
		return rubroSeleccionado;
	}
	public void setRubroSeleccionado(String rubroSeleccionado) {
		this.rubroSeleccionado = rubroSeleccionado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public void altaRubro() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			rubroBusiness.altaRubro(nombre);
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/administrador.xhtml");
		}catch(Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	
	public void buttonClickedDelete(String rubro) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		try {
			rubroBusiness.deleteRubro(rubro);
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/administrador.xhtml");
		}catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	

}

