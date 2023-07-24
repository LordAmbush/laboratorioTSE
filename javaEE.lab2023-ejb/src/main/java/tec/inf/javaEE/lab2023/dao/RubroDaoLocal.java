package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.RubroDTO;
import tec.inf.javaEE.lab2023.entity.Rubro;

public interface RubroDaoLocal {
	void altaRubro(String nombre) throws NoResultException;
	void modificarRubro(String nombre) throws Exception;
	void deleteRubro(String nombre) throws NoResultException;
	
	Rubro obtenerRubro(String nombre) throws NoResultException;
	List<RubroDTO> getRubros();
	
	boolean existeRubro(String nombre);
	
	

	

}
