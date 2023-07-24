package tec.inf.javaEE.lab2023.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.ItvDaoLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Itv;
import tec.inf.javaEE.lab2023.entity.Vehiculo;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
@Stateless
public class ItvDao implements ItvDaoLocal{
	@JAVAEE2023
	@Inject
	public EntityManager em;
	
	@EJB
	VehiculoDaoLocal vehiculoDao;

	public Itv obtenerItv(int nroItv) throws NoResultException {
		String queryString = "SELECT * FROM Itv WHERE numero = '"+nroItv+"' AND status = 'true'";
	    try {
			Query query = em.createNativeQuery(queryString, Itv.class);
	    	Itv result = (Itv) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	public void altaItv(String fechaDesde, String fechaHasta, String matricula) throws NoResultException {

		Vehiculo v = vehiculoDao.obtenerVehiculo(matricula);
		if(v == null) {
			throw new NoResultException("Vehiculo invalido");
		}else {
			Itv nuevo = new Itv();
			nuevo.setFechaDesde(fechaDesde);
			nuevo.setFechaHasta(fechaHasta);
			nuevo.setVehiculo(v);
			List<Itv> aux = v.getItv();
			aux.add(nuevo);
			v.setItv(aux);
			em.persist(nuevo);
			em.persist(v);
		}
	}
		

}
