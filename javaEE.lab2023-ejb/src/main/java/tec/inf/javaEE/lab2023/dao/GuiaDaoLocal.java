package tec.inf.javaEE.lab2023.dao;


import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.entity.Guia;


public interface GuiaDaoLocal {
	void altaGuia(int volumenCarga, String origen, String destino, String tipoDeCarga, String nombreRubro) throws NoResultException;
	Guia obtenerGuia(int id) throws NoResultException;
	public int obtenerUltimaGuia();
	List<GraficasDTO> VolumenCargaRubro();

}
