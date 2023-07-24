package tec.inf.javaEE.lab2023.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.business.VehiculoBusinessLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.entity.Vehiculo;

@Stateless
@Named("vehiculoBusiness")
public class VehiculoBusiness implements VehiculoBusinessLocal {

	@EJB
	VehiculoDaoLocal vehiculoDao;

	public VehiculoBusiness() {
		
	}
	
    public void altaVehiculo(String matricula, String capacidad, String marca, String modelo,int peso, String empresa) throws Exception {
    	vehiculoDao.altaVehiculo(matricula,marca,modelo,peso, capacidad,empresa);
    }

    @Override
    public void updateVehiculo(String matricula, String capacidad, String marca, String modelo,int peso, String empresa) throws Exception {
    	vehiculoDao.updateVehiculo(matricula,marca,modelo,peso, capacidad,empresa);
    }
	@Override
	public Vehiculo obtenerVehiculo(String matricula) throws NoResultException {
		Vehiculo res = vehiculoDao.obtenerVehiculo(matricula);
		return res;
	}

	@Override
	public ArrayList<VehiculoDTO> getVehiculos() throws NoResultException {
		ArrayList<VehiculoDTO> res = (ArrayList<VehiculoDTO>) vehiculoDao.getVehiculos();
		return res;
	}
	
	@Override
	public List<VehiculoDTO> getVehiculosEmpresa(String empresa) throws NoResultException {
		return vehiculoDao.getVehiculosEmpresa(empresa);
	}

	@Override
	public void deleteVehiculo(String matricula) throws Exception {
		vehiculoDao.deleteVehiculo(matricula);
	}
	
	@Override
	public List<VehiculoDTO> getVehiculosAlDia(String matricula) throws NoResultException{
		return vehiculoDao.getVehiculosAlDia(matricula);
		
	}
	@Override
	public List<VehiculoDTO> getVehiculosVencidos(String matricula) throws NoResultException{
		return vehiculoDao.getVehiculosVencidos(matricula);
	}
	@Override
	public List<VehiculoDTO> getVehiculosVencidosITV(String matricula) throws NoResultException{
		return vehiculoDao.getVehiculosVencidosITV(matricula);
	}
	@Override
	public List<VehiculoDTO> getVehiculosVencidosPNC(String matricula) throws NoResultException{
		return vehiculoDao.getVehiculosVencidosPNC(matricula);
	}
	
	

}

