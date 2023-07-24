package tec.inf.javaEE.lab2023.business;

import java.util.List;

import tec.inf.javaEE.lab2023.dto.RubroDTO;
import tec.inf.javaEE.lab2023.entity.Rubro;

public interface RubroBusinessLocal {
	void altaRubro(String nombre) throws Exception;
	void updateRubro(String nombre) throws Exception;
	void deleteRubro(String nombre) throws Exception;
	
	Rubro obtenerRubro(String nombre);
	List<RubroDTO> getRubro();
}
