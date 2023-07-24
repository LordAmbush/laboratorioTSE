package tec.inf.javaEE.lab2023.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import jakarta.annotation.PostConstruct;
import tec.inf.javaEE.lab2023.business.VehiculoBusinessLocal;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.gubuy.oauth2.client.Credenciales;

@Named("altaVehiculoBean")
@SessionScoped
public class AltaVehiculoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	VehiculoBusinessLocal vehiculoBusiness;
	
	@Inject
    private UsuarioLogueadoBean userBean;
	
	private String matricula;
	private String capacidad;
	private String marca;
	private String modelo;
	private int peso;
	private int empresa;
	private String vehiculoSeleccionado;
	private List<VehiculoDTO> listarVehiculos;
	
	
	public void listarVehiculos() {
		try {
			List<VehiculoDTO> v = vehiculoBusiness.getVehiculos();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> listarVehiculosITVVENCIDOS() {
		List<String> vehiculos = new ArrayList<>(); 
		List<VehiculoDTO> vehiculosITVVENCIDOS = vehiculoBusiness.getVehiculosVencidosITV(Credenciales.getEmpresaUserLogged());
		for (VehiculoDTO v : vehiculosITVVENCIDOS) {
			vehiculos.add(v.getMatricula());
		}
		return vehiculos;
	}
	
	public List<String> listarVehiculosPERMISOSVENCIDOS() {
		List<String> vehiculos = new ArrayList<>(); 
		List<VehiculoDTO> vehiculosITVVENCIDOS = vehiculoBusiness.getVehiculosVencidosPNC(Credenciales.getEmpresaUserLogged());
		for (VehiculoDTO v : vehiculosITVVENCIDOS) {
			vehiculos.add(v.getMatricula());
		}
		return vehiculos;
	}
	
	public List<String> getListarVehiculos() {
		List<String> vehiculos = new ArrayList<>(); 
		List<VehiculoDTO> vehiculosDTO = vehiculoBusiness.getVehiculos();
		for (VehiculoDTO v : vehiculosDTO) {
			vehiculos.add(v.getMatricula());
		}
		return vehiculos;
    }
	
	public List<String> getListarVehiculosAlDiaEmpresa() {
		List<String> vehiculos = new ArrayList<>(); 
		List<VehiculoDTO> vehiculosDTO = vehiculoBusiness.getVehiculosAlDia(Credenciales.getEmpresaUserLogged());
		for (VehiculoDTO v : vehiculosDTO) {
			vehiculos.add(v.getMatricula());
		}
		return vehiculos;
    }
	
	public List<VehiculoDTO> getPopulateVehiculos() {
		List<VehiculoDTO> vehiculosDTO = vehiculoBusiness.getVehiculos();
		return vehiculosDTO;
    }
	
	public List<VehiculoDTO> getPopulateVehiculosEmpresa() {
		List<VehiculoDTO> vehiculosDTO = vehiculoBusiness.getVehiculosEmpresa(Credenciales.getEmpresaUserLogged());
		return vehiculosDTO;
    }
	
	
	public String getMatricula() {
		return matricula;
	}

	

	public String getVehiculoSeleccionado() {
		return vehiculoSeleccionado;
	}

	public void setVehiculoSeleccionado(String vehiculoSeleccionado) {
		this.vehiculoSeleccionado = vehiculoSeleccionado;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}



	public String getCapacidad() {
		return capacidad;
	}



	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public int getPeso() {
		return peso;
	}



	public void setPeso(int peso) {
		this.peso = peso;
	}



	public int getEmpresa() {
		return empresa;
	}



	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public void submit() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			vehiculoBusiness.altaVehiculo(matricula,capacidad,marca,modelo,peso,Credenciales.getEmpresaUserLogged());
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/frontoffice.xhtml");
		}catch(Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	
	public void borrarVehiculo(VehiculoDTO v) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		try {
			vehiculoBusiness.deleteVehiculo(v.getMatricula());
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/frontoffice.xhtml");
		}catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	
	public void editarVehiculo(VehiculoDTO v) {
		try {
	    	this.matricula = v.getMatricula();
	    	this.capacidad = v.getCapacidad();
	    	this.marca = v.getMarca();
	    	this.modelo = v.getModelo();
	    	this.peso = v.getPeso();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        externalContext.redirect(externalContext.getRequestContextPath() + "/modificarVehiculo.xhtml");
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
	
	public void updateVehiculo() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		try {
			vehiculoBusiness.updateVehiculo(matricula,capacidad,marca,modelo,peso,Credenciales.getEmpresaUserLogged());
	    	this.matricula = null;
	    	this.capacidad = null;
	    	this.marca = null;
	    	this.modelo = null;
	    	this.peso = 0;
			Flash flash = facesContext.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Exito en la operacion!"));
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/frontoffice.xhtml");
		}catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
	}
	
	
}

