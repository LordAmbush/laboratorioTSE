package tec.inf.javaEE.lab2023.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.EmpresaBusinessLocal;
import tec.inf.javaEE.lab2023.dao.EmpresaDaoLocal;
import tec.inf.javaEE.lab2023.dao.UsuarioDaoLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.entity.AdminEmpresa;
import tec.inf.javaEE.lab2023.entity.Conductor;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Usuario;
import tec.inf.javaEE.lab2023.entity.Vehiculo;


/**
 * Session Bean implementation class ViajeBuisness
 */
@Stateless
@Named("empresaBusiness")
public class EmpresaBusiness implements EmpresaBusinessLocal {

	@EJB
	EmpresaDaoLocal empresaDao;
	@EJB
	VehiculoDaoLocal vehiculoDao;
	@EJB
	UsuarioDaoLocal usuarioDao;
	
	public EmpresaBusiness() {
		
	}
	
    public void altaEmpresa(int numero,String direccion,String nombre,String rsocial) throws Exception {
		empresaDao.altaEmpresa(numero,direccion,nombre,rsocial);
    }

	@Override
	public void updateEmpresa(int numero, String direccion, String nombre, String rsocial) throws Exception {
    	try {	
    		empresaDao.updateEmpresa(numero,direccion,nombre,rsocial);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmpresa(String nombre) throws Exception {
    	try {	
    		empresaDao.deleteEmpresa(nombre);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Empresa obtenerEmpresa(String nombre) {
	 	try {	
	 		return empresaDao.obtenerEmpresa(nombre);
    	} catch (Exception e) {
    		System.out.println("Error al obtener la empresa. msj: EmpresaBusiness");
    		return null;
		}
		
	}

	@Override
	public List<EmpresaDTO> getEmpresas() {
	 	try {
	 		List<EmpresaDTO> empresas = empresaDao.getEmpresas();
	 		return empresas;
    	} catch (Exception e) {
    		System.out.println("Error al listar las empresas. msj: EmpresaBusiness");
    		return null;
		}
	}


	@Override
	public List<VehiculoDTO> getVehiculos(String nombreEmpresa) {
		try {
			Empresa e = empresaDao.obtenerEmpresa(nombreEmpresa);
			List<VehiculoDTO> res = new ArrayList<>();
			List<Vehiculo> listVehiculos = e.getVehiculos();
			for(Vehiculo vehiculo : listVehiculos) {
				if (vehiculo.getEmpresa().getNombreEmpresa().equals(nombreEmpresa)) {
			  		VehiculoDTO vehiculoDTO = new VehiculoDTO();
			    	vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
			    	vehiculoDTO.setMarca(vehiculo.getMarca());
			    	vehiculoDTO.setMatricula(vehiculo.getMatricula());
			    	vehiculoDTO.setModelo(vehiculo.getModelo());
			    	vehiculoDTO.setPeso(vehiculo.getPeso());
			    	vehiculoDTO.setEmpresa(vehiculo.getEmpresa().getNombreEmpresa());
			        
			    	res.add(vehiculoDTO);
				}
	    	}
			return res;	
    	} catch (Exception e) {
    		System.out.println("Error al listar los vehiculos de una empresa. msj: EmpresaBusiness");
		}
		return null;		
	}	
	
}

