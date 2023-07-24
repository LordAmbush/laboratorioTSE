package tec.inf.javaEE.lab2023.business;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.entity.Vehiculo;


@Local
public interface VehiculoBusinessLocal {

	void altaVehiculo(String matricula, String capacidad, String marca, String modelo,int peso, String empresa) throws Exception;

	public Vehiculo obtenerVehiculo(String matricula) throws NoResultException;
	
	public void deleteVehiculo(String matricula)throws NoResultException, Exception;

	ArrayList<VehiculoDTO> getVehiculos() throws NoResultException;

	List<VehiculoDTO> getVehiculosEmpresa(String empresa) throws NoResultException;
	
	List<VehiculoDTO> getVehiculosAlDia(String matricula) throws NoResultException;

	List<VehiculoDTO> getVehiculosVencidos(String matricula) throws NoResultException;

	List<VehiculoDTO> getVehiculosVencidosITV(String matricula) throws NoResultException;

	List<VehiculoDTO> getVehiculosVencidosPNC(String matricula) throws NoResultException;

	void updateVehiculo(String matricula, String capacidad, String marca, String modelo, int peso, String empresa)
			throws Exception;
}
