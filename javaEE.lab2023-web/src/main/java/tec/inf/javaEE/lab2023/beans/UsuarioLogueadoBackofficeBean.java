package tec.inf.javaEE.lab2023.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.UsuarioBusinessLocal;
import tec.inf.javaEE.lab2023.dto.BackofficeDTO;

@Named("BackofficeBean")
@SessionScoped
public class UsuarioLogueadoBackofficeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuarioBusinessLocal usuarioBusiness;
	
	private String loginCI;
	private String loginPassword;
	private String rol;
	
	private String ci;
	private String email;
	private String fechaNac;
	private String username;
	
	
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginCI() {
		return loginCI;
	}
	public void setLoginCI(String loginCI) {
		this.loginCI = loginCI;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public void verificarUser() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		BackofficeDTO user = usuarioBusiness.login(loginCI, loginPassword);
		if(user != null) {
			ci = user.getCi();
			email = user.getEmail();
			fechaNac = user.getFechaNac();
			username = user.getUsername();
			rol = user.getRol();
			//redireccionar
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Login exitoso!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			if(rol == "Administrador") {
				externalContext.redirect(externalContext.getRequestContextPath() + "/administrador.xhtml");

			}else {
				externalContext.redirect(externalContext.getRequestContextPath() + "/autoridad.xhtml");
			}
		}else {
			System.out.println("ELSE");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos!"));
		}
	}
	
	public void logout() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		loginCI = null;
		loginPassword = null;
		rol = null;
		ci = null;
		email = null;
		fechaNac = null;
		username = null;
		Flash flash = facesContext.getExternalContext().getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sesion cerrada con exito!"));
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath() + "/dashboard.xhtml");
	}

}
