package tec.inf.javaEE.lab2023.dao;

import java.sql.Date;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.entity.PermisoNacCirculacion;

public interface PermisoNacCirculacionLocal {
	PermisoNacCirculacion obtenerPermiso(int nroPermiso) throws NoResultException;
	void eliminarPermiso(int nroPermiso) throws NoResultException;
	void altaPermiso(String fechaDesde, String fechaHasta, String matricula) throws NoResultException;
}
