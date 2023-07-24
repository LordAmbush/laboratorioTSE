package tec.inf.javaEE.lab2023.business;

import java.util.List;

import tec.inf.javaEE.lab2023.dto.TipoDeCargaDTO;

public interface TipoDeCargaBusinessLocal {
	
	void altaTipoDeCarga(String nombre) throws Exception;
	//void updateEmpresa(int numero, String direccion, String nombre, String rsocial) throws Exception;
	//void deleteEmpresa(int numero) throws Exception;
	
	//Empresa obtenerEmpresa(int numero);
	//List<EmpresaDTO> getEmpresas();
	
	//FUNCIONALIDADES DE LOS CASOS DE USO
	List<TipoDeCargaDTO> getTipoDeCarga();

	void deleteTipoCarga(String nombre);


}
