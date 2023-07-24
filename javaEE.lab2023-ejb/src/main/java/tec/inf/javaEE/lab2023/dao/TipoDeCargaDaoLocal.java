package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.TipoDeCargaDTO;
import tec.inf.javaEE.lab2023.entity.TipoDeCarga;

public interface TipoDeCargaDaoLocal {
	void altaTipoDeCarga(String nombre) throws NoResultException; 
	TipoDeCarga obtenerTipoDeCarga(String id) throws NoResultException;
	void deleteTipoDeCarga(String nombre) throws NoResultException;
	List<TipoDeCargaDTO> getTipoDeCarga();
}
