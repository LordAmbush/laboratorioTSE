package tec.inf.javaEE.lab2023.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import tec.inf.javaEE.lab2023.business.UsuarioBusinessLocal;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.gubuy.oauth2.client.Credenciales;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDI;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIService;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceLocator;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub;
import uy.edu.fing.tse.demo2023.services.soap.Persona;


import java.io.Serializable;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


@Named("UsuarioLogueadoBean")
@SessionScoped
public class UsuarioLogueadoBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    String email;
    String nombre;
    String apellido;
    String ci;
    String rol;
    String nombreCompleto;
    String empresa;
    
    @EJB
    UsuarioBusinessLocal usuarioService;
    
    @PostConstruct
    public void init() throws MalformedURLException, RemoteException {
        String respuesta = Credenciales.getCallBackGubUyInfo();
        if(respuesta != null) {
        	JsonReader jsonReader = Json.createReader(new StringReader(respuesta));
        	JsonObject jsonObject = jsonReader.readObject();
        	jsonReader.close();

            ci = jsonObject.getString("numero_documento");
            
            UsuarioDTO usuarioLogueado = usuarioService.obtenerUsuario(ci);
            
            if(usuarioLogueado != null) {
    	        email = usuarioLogueado.getEmail();
    	        rol = usuarioLogueado.getRol();
    	        nombreCompleto = usuarioLogueado.getUsername();
    	        if(rol.equals("AdminEmpresa")) {
        	        empresa = usuarioLogueado.getEmpresa();
        	        Credenciales.setEmpresaUserLogged(empresa);
    	        }
    	        
    	        /*
    	        if(rol == "Funcionario") {
    	        	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Login Exitoso!"));
    				try {
						externalContext.redirect(externalContext.getRequestContextPath() + "/funcionario.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
    	        }else if(rol == "AdminEmpresa"){
    	        	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Login Exitoso!"));
    				try {
						externalContext.redirect(externalContext.getRequestContextPath() + "/administradorEmpresa.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
    	        }else {
    	        	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Login Exitoso!"));
    				try {
						externalContext.redirect(externalContext.getRequestContextPath() + "/publico.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
    	        }
    	        */
            	
            	
            }else {
        		NodoPDIService servicio = new NodoPDIServiceLocator();
        		
        		NodoPDI ws = new NodoPDIServiceSoapBindingStub(new URL(servicio.getNodoPDIPortAddress()), servicio);
        		
        		Persona p = ws.obtenerDatosPersona(ci);
        		rol = "Publico";
        		
        		if(p != null) {
    				ci = p.getCi();
    				nombre = p.getNombre();
        		}else {

        		} 
            }

        } else {
        	ci = null;
	        email = null;
	        nombre = null;
	        apellido = null;
	        rol = null;
	        nombreCompleto = null;
	        empresa = null;
	        Credenciales.setEmpresaUserLogged(null);
        }
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public void logout() {
    	ci = null;
        email = null;
        nombre = null;
        apellido = null;
        rol = null;
        nombreCompleto = null;
        empresa = null;
        
        try {
            // URL del servlet de logout
            URL url = new URL("http://localhost:8080/javaEE.lab2023-web/logout");
            
            // Abrir conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Configurar la solicitud HTTP
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            
            // Enviar la solicitud HTTP
            connection.connect();
            
            // Leer la respuesta del servlet si es necesario
            // ...
            
            // Cerrar la conexión
            connection.disconnect();
            FacesContext facesContext = FacesContext.getCurrentInstance();
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Logout exitoso!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    		externalContext.redirect(externalContext.getRequestContextPath() + "/dashboard.xhtml");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
}
