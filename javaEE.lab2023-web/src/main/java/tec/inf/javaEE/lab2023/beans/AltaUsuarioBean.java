package tec.inf.javaEE.lab2023.beans;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.UsuarioBusinessLocal;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.gubuy.oauth2.client.Credenciales;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDI;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIService;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceLocator;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub;
import uy.edu.fing.tse.demo2023.services.soap.Persona;

@Named("altaUsuarioBean")
@RequestScoped
public class AltaUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBusinessLocal usuarioBusiness;
	
	private String ci;
	private String email;
	private Date fechaNac;
	private String username;
	private String tipoUser;
	private String pass;
	private String empresa;
	private String empresaSeleccionada;
	private List<UsuarioDTO> listUsers;
	private UsuarioDTO user;
	
	

	
	public void obtenerUser() {
		try {
			setUser(usuarioBusiness.obtenerUsuario(ci));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getListarConductores() {
		List<String> conductores = new ArrayList<>(); 
		//mandarle empresa del admin logueado
		List<UsuarioDTO> usuariosDTO = usuarioBusiness.getConductores(Credenciales.getEmpresaUserLogged());
		for (UsuarioDTO u : usuariosDTO) {
			conductores.add(u.getCi());
		}
		return conductores;
    }
	
	public void getUsers() {
		try {
			setListUsers(usuarioBusiness.getUsuarios());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<UsuarioDTO> getPopulateUsuarios() {
		List<UsuarioDTO> usuariosDTO = usuarioBusiness.getUsuarios();
		return usuariosDTO;
    }
	
	public void altaUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			
			//obtener url a donde me voy a conectar
			NodoPDIService servicio = new NodoPDIServiceLocator();
			
			//Creo el objeto que me permite conectar a las operaciones
			NodoPDI ws = new NodoPDIServiceSoapBindingStub(new URL(servicio.getNodoPDIPortAddress()), servicio);
			
			Persona p = ws.obtenerDatosPersona(ci);
			
			if(p != null) {
				usuarioBusiness.altaUsuario(ci, p.getNombre()+p.getApellido(), email, p.getFechaNac(), tipoUser, pass, empresaSeleccionada);
				Flash flash = facesContext.getExternalContext().getFlash();
				flash.setKeepMessages(true);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect(externalContext.getRequestContextPath() + "/administrador.xhtml");
			}else {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La persona ingresada no existe en la PDI!"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	
	public void buttonClickedDelete(UsuarioDTO usuario) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		try {
			usuarioBusiness.deleteUsuario(usuario.getCi());
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
	
	
	public void updateUser() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
			String fecha = dateFormat.format(fechaNac);
			usuarioBusiness.updateUsuario(ci, username, email, fecha);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTipoUser() {
		return tipoUser;
	}
	public void setTipoUser(String tipoUser) {
		this.tipoUser = tipoUser;
	}
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
	    String salt = BCrypt.gensalt(); // Generate a random salt
	    String hashedPass = BCrypt.hashpw(pass, salt); // Hash the password with the salt
	    this.pass = hashedPass;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(String empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<UsuarioDTO> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<UsuarioDTO> listUsers) {
		this.listUsers = listUsers;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}
}
