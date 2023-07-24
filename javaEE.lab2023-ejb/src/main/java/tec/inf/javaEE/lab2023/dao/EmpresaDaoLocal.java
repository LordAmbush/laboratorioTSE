package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.entity.AdminEmpresa;
import tec.inf.javaEE.lab2023.entity.Conductor;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Vehiculo;

public interface EmpresaDaoLocal {
	void altaEmpresa(int numero, String direccion, String nombre, String rsocial) throws NoResultException;
	Empresa obtenerEmpresa(String nombre) throws NoResultException;
	List<EmpresaDTO> getEmpresas();
	void updateEmpresa(int nroEmpresa,String direccion,String nombre,String rsocial);
	void deleteEmpresa(String nombre) throws NoResultException;
	

	void addVehiculo(Vehiculo vehiculo, Empresa empresa) throws Exception;
	List<GraficasDTO> EmpresasViajes();
	List<GraficasDTO> EmpresasVehiculos();
	GraficasDTO cantConductores(String empresa);
	GraficasDTO cantViajes(String empresa);
	GraficasDTO cantVehiculos(String empresa);
}
