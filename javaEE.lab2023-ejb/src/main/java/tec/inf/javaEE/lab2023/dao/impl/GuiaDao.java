package tec.inf.javaEE.lab2023.dao.impl;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.GuiaDaoLocal;
import tec.inf.javaEE.lab2023.dao.RubroDaoLocal;
import tec.inf.javaEE.lab2023.dao.TipoDeCargaDaoLocal;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.entity.Guia;
import tec.inf.javaEE.lab2023.entity.Rubro;
import tec.inf.javaEE.lab2023.entity.TipoDeCarga;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
@Stateless
public class GuiaDao implements GuiaDaoLocal{
	@JAVAEE2023
	@Inject
	public EntityManager em;
	@EJB
	RubroDaoLocal rubroDao;
	@EJB
	TipoDeCargaDaoLocal tipoDeCargaDao;
	
	@Override
	public void altaGuia(int volumenCarga, String origen, String destino, String nombreTipoDeCarga, String nombreRubro) throws NoResultException {
			Date fechaRegistro = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fecha = dateFormat.format(fechaRegistro);
			Rubro r = rubroDao.obtenerRubro(nombreRubro);
			TipoDeCarga c = tipoDeCargaDao.obtenerTipoDeCarga(nombreTipoDeCarga);

			if(c == null) {
				throw new NoResultException("No se encontro el tipo de carga");
			}else if(r == null) {
				throw new NoResultException("No se encontro el rubro");
			}else if(volumenCarga < 0) {
				throw new NoResultException("Volumen de carga invalido");
			}else if(origen.equals(destino)) {
				throw new NoResultException("El origen y destino no pueden ser iguales");
			}else {
		        TypedQuery<Guia> guiaQuery = em.createQuery("SELECT g FROM Guia g ORDER BY g.id DESC", Guia.class);
		        guiaQuery.setMaxResults(1);
		        List<Guia> guiasSinViaje = guiaQuery.getResultList();

		        if (!guiasSinViaje.isEmpty()) {
		            Guia previousGuia = guiasSinViaje.get(0);
		            if(previousGuia.getViaje()==null) {
		            	em.remove(previousGuia);
		            }
		        }
				
				Guia nuevo = new Guia();
				nuevo.setVolumenDeCarga(volumenCarga);
				nuevo.setTipoDeCarga(c);
				nuevo.setRubro(r);
				nuevo.setfechaRegistro(fecha);
				nuevo.setOrigen(origen);
				nuevo.setDestino(destino);
				em.persist(nuevo);
			}
	}
	@Override
	public int obtenerUltimaGuia() {
	    String queryString = "SELECT * FROM guia ORDER BY id DESC LIMIT 1;";
	    try {
			Query query = em.createNativeQuery(queryString, Guia.class);
	    	Guia result = (Guia) query.getSingleResult();
	        return result.getId();
	    } catch (NoResultException e) {
	    	return 0;
	    }
	}

	@Override
	public Guia obtenerGuia(int id) throws NoResultException {
	    String queryString = "SELECT * FROM guia g WHERE g.id = "+ id+" AND g.status = 'true'";
		Query query = em.createNativeQuery(queryString, Guia.class);

	    //TypedQuery<Guia> query = em.createQuery(queryString, Guia.class);
	    //query.setParameter("id", id);
	    try {
	    	Guia result = (Guia) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	    	return null;
	    }
	}
	
	


	@Override
	public List<GraficasDTO> VolumenCargaRubro() {
	    String queryString = "SELECT r.nombre AS data, SUM(g.volumendecarga) AS cantidad "
	            + "FROM guia g "
	            + "JOIN rubro r ON g.rubro_id = r.id "
	            + "WHERE g.status = 'true' and r.status = 'true'"
	            + "GROUP BY r.nombre "
	            + "ORDER BY cantidad DESC "
	            + "LIMIT 10";
	    try {
	        Query query = em.createNativeQuery(queryString, "GraficasDTO");
	        List<GraficasDTO> ret = query.getResultList();
	        return ret;
	    } catch (NoResultException e) {
	        throw new NoResultException("No se pudo listar las empresas");
	    }
	}

}
