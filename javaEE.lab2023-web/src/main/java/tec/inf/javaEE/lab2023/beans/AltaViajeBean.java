package tec.inf.javaEE.lab2023.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.GuiaBusinessLocal;
import tec.inf.javaEE.lab2023.business.ViajeBusinessLocal;

@Named("altaViajeBean")
@RequestScoped
public class AltaViajeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	ViajeBusinessLocal viajeBusiness;	
	@EJB
	GuiaBusinessLocal guiaBusiness;
	
	private int idViaje;
	private int idGuia;
	private String matricula;
	private String ciConductor;
	
	@PostConstruct
	public void init() {
		idGuia = guiaBusiness.obtenerUltimaGuia();
	}
	
	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public int getIdGuia() {
		return idGuia;
	}

	public void setIdGuia(int idGuia) {
		this.idGuia = idGuia;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCiConductor() {
		return ciConductor;
	}

	public void setCiConductor(String ciConductor) {
		this.ciConductor = ciConductor;
	}

	public void submit() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			viajeBusiness.altaViaje(idGuia,matricula,ciConductor);
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/frontoffice.xhtml");
		}catch(Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}


}
