package tec.inf.javaEE.lab2023.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.PermisoVehicularBusinessLocal;

@Named("altaPNCBean")
@RequestScoped
public class AltaPNCirculacionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Date fechaHasta;
	private Date fechaDesde;
	private String matricula;
	
	@EJB
	PermisoVehicularBusinessLocal PNCBusiness;
	
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public void altaPNC() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
			String f1 = dateFormat.format(fechaDesde);
            String f2 = dateFormat.format(fechaHasta);
            PNCBusiness.altaPermiso(f1,f2,matricula);
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
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
}
