package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.entity.Vehiculo;

public interface VehiculoDaoLocal {
	void altaVehiculo(String matricula, String marca, String modelo, int peso,String capacidad, String nombreEmpresa) throws NoResultException;
	Vehiculo obtenerVehiculo(String matricula) throws NoResultException;
	void deleteVehiculo(String matricula) throws Exception,NoResultException;
	List<VehiculoDTO> getVehiculos()throws NoResultException;
	List<VehiculoDTO> getVehiculosEmpresa(String empresa);
	List<VehiculoDTO> getVehiculosAlDia(String empresa);
	List<VehiculoDTO> getVehiculosVencidos(String empresa);
	List<VehiculoDTO> getVehiculosVencidosITV(String empresa);
	List<VehiculoDTO> getVehiculosVencidosPNC(String empresa);
	void updateVehiculo(String matricula, String marca, String modelo, int peso, String capacidad, String nombreEmpresa)
			throws NoResultException;
}
