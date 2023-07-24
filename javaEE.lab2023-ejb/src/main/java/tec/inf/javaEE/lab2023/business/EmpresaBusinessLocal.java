package tec.inf.javaEE.lab2023.business;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;

public interface EmpresaBusinessLocal {
	
	void altaEmpresa(int numero, String direccion, String nombre, String rsocial) throws Exception;
	void updateEmpresa(int numero, String direccion, String nombre, String rsocial) throws Exception;
	//void deleteEmpresa(int numero) throws Exception;
	void deleteEmpresa(String nombre) throws Exception;
	
	Empresa obtenerEmpresa(String nombre);
	List<EmpresaDTO> getEmpresas();
	
	//FUNCIONALIDADES DE LOS CASOS DE USO
	
	List<VehiculoDTO> getVehiculos(String nombreEmpresa) throws NoResultException;

}
