package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.InfoViajesDTO;
import tec.inf.javaEE.lab2023.dto.ViajeDTO;
import tec.inf.javaEE.lab2023.entity.Viaje;

public interface ViajeDaoLocal {

	//ViajeDTO obtenerUltimoViaje(String ci) throws NoResultException;
	boolean updateEstadoViaje(int id) throws NoResultException;
	
	public void altaViaje(int idGuia, String matricula, String ciConductor) throws NoResultException;
	Viaje objeterViaje(int id) throws NoResultException;
	void deleteViaje(int id) throws NoResultException; 
	//List<ViajeDTO> getViajes()throws NoResultException;

	//ViajeDTO getViaje(int ci) throws NoResultException;

	ViajeDTO consultarEstadoViaje(String ci);

	List<GraficasDTO> ViajeRubro();

	List<GraficasDTO> ViajeTipoCarga();

	List<InfoViajesDTO> infoViajes();;
}
