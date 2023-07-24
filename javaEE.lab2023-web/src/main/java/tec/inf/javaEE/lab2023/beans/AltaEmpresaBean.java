package tec.inf.javaEE.lab2023.beans;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.EmpresaBusinessLocal;
import tec.inf.javaEE.lab2023.business.GraficasBusinessLocal;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.gubuy.oauth2.client.Credenciales;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDI;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIService;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceLocator;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub;

@Named("altaEmpresaBean")
@SessionScoped
public class AltaEmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	EmpresaBusinessLocal empresaBusiness;
	
	@EJB
	GraficasBusinessLocal grafBusiness;
	
	private int numero;
	private String direccion;
	private int cantVehiculos;
	private int cantViajes;
	private int cantConductores;
	private String nombre;
	private String rsocial;
	private List<EmpresaDTO> listarEmpresas;
	

    @PostConstruct
    public void init() {	
    	cantVehiculos = grafBusiness.cantVehiculos(Credenciales.getEmpresaUserLogged()).getCantidad();
    	cantViajes = grafBusiness.cantViajes(Credenciales.getEmpresaUserLogged()).getCantidad();
    	cantConductores = grafBusiness.cantConductores(Credenciales.getEmpresaUserLogged()).getCantidad();
    }
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRsocial() {
		return rsocial;
	}
	public void setRsocial(String rsocial) {
		this.rsocial = rsocial;
	}
	
	public void submit() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		try {
			//obtener url a donde me voy a conectar
			NodoPDIService servicio = new NodoPDIServiceLocator();
			
			//Creo el objeto que me permite conectar a las operaciones
			NodoPDI ws = new NodoPDIServiceSoapBindingStub(new URL(servicio.getNodoPDIPortAddress()), servicio);
			
			uy.edu.fing.tse.demo2023.services.soap.Empresa e = ws.obtenerDatosEmpresa(String.valueOf(numero));
			
			if(e != null) {
				empresaBusiness.altaEmpresa(numero,e.getDireccion(),e.getNombreFantasia(),e.getNombre());
				Flash flash = facesContext.getExternalContext().getFlash();
				flash.setKeepMessages(true);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect(externalContext.getRequestContextPath() + "/frontoffice.xhtml");
			}else {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La empresa no se encuentra en la PDI"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	public void obtenerEmpresa() {
		
		try {
			Empresa ee = empresaBusiness.obtenerEmpresa(nombre);
			System.out.println(ee);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmpresa() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			empresaBusiness.updateEmpresa(numero, direccion, nombre, rsocial);
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/dashboard.xhtml");
		}catch(Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	public void listarEmpresas() {
		try {
			List<EmpresaDTO> eee = empresaBusiness.getEmpresas();
			System.out.println(eee); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getListarEmpresas() {
		List<String> empresas = new ArrayList<>(); 
		List<EmpresaDTO> empresasDTO = empresaBusiness.getEmpresas();
		for (EmpresaDTO e : empresasDTO) {
			empresas.add(e.getNombreEmpresa());
		}
		return empresas;
    }
	
	public List<EmpresaDTO> getPopulateEmpresas() {
		List<EmpresaDTO> empresasDTO = empresaBusiness.getEmpresas();
		return empresasDTO;
    }
	
	public void buttonClickedDelete(EmpresaDTO empresa) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			empresaBusiness.deleteEmpresa(empresa.getNombreEmpresa());
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

	public int getCantVehiculos() {
		return cantVehiculos;
	}

	public void setCantVehiculos(int cantVehiculos) {
		this.cantVehiculos = cantVehiculos;
	}

	public int getCantViajes() {
		return cantViajes;
	}

	public void setCantViajes(int cantViajes) {
		this.cantViajes = cantViajes;
	}

	public int getCantConductores() {
		return cantConductores;
	}

	public void setCantConductores(int cantConductores) {
		this.cantConductores = cantConductores;
	}
}

