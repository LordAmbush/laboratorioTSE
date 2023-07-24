package tec.inf.javaEE.lab2023.dao.impl;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.PermisoNacCirculacionLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Itv;
import tec.inf.javaEE.lab2023.entity.PermisoNacCirculacion;
import tec.inf.javaEE.lab2023.entity.Vehiculo;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
@Stateless
public class PermisoNacCirculacionDao implements  PermisoNacCirculacionLocal{
	
	@JAVAEE2023
	@Inject
	public EntityManager em;
	
	@EJB
	VehiculoDaoLocal vehiculoDao;


	
	public PermisoNacCirculacion obtenerPermiso(int nroPermiso) throws NoResultException {
		String queryString = "SELECT * FROM PermisoNacCirculacion WHERE numero = '"+nroPermiso+"' AND status = 'true'";

	    try {
			Query query = em.createNativeQuery(queryString, PermisoNacCirculacion.class);
	        PermisoNacCirculacion result = (PermisoNacCirculacion) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	        return null;
	    }
	}

	@Override
	public void altaPermiso(String fechaDesde, String fechaHasta, String matricula) throws NoResultException {
		Vehiculo v = vehiculoDao.obtenerVehiculo(matricula);
		if(v == null) {
			throw new NoResultException("No se encontro el vehiculo");
		}else {
			PermisoNacCirculacion nuevo = new PermisoNacCirculacion();
			nuevo.setFechaDesde(fechaDesde);
			nuevo.setFechaHasta(fechaHasta);
			nuevo.setVehiculo(v);
			v.setPermisoNacCirculacion(nuevo);
			em.persist(nuevo);
			em.persist(v);
		}
	}		
	
	public void eliminarPermiso(int nroPermiso) throws NoResultException {
		PermisoNacCirculacion p = obtenerPermiso(nroPermiso);
		if (p != null) {
			 p.setStatus(false);
			 em.persist(p);
		}
	  
	}



}
