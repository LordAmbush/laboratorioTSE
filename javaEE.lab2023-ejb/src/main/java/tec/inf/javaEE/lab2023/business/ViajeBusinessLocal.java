package tec.inf.javaEE.lab2023.business;

import java.util.concurrent.ExecutionException;

import tec.inf.javaEE.lab2023.dto.ViajeDTO;

public interface ViajeBusinessLocal {

	ViajeDTO consultarEstadoViaje(String ci) throws InterruptedException, ExecutionException;

	public void altaViaje(int idGuia, String matricula, String ciConductor)  throws Exception ;
	
	void deleteViaje(int id) throws Exception;

	boolean updateEstadoViaje(int id);

}
