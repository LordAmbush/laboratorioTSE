package tec.inf.javaEE.lab2023.dao;

import java.sql.Date;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.entity.Itv;

public interface ItvDaoLocal {
	
	void altaItv(String fechaDesde, String fechaHasta, String matricula) throws NoResultException;
	Itv obtenerItv(int nroItv) throws NoResultException;
}
