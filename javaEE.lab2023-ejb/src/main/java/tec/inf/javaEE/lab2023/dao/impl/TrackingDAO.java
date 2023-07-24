package tec.inf.javaEE.lab2023.dao.impl;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.TrackingDaoLocal;
import tec.inf.javaEE.lab2023.dao.ViajeDaoLocal;
import tec.inf.javaEE.lab2023.dto.PesadasDTO;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
import tec.inf.javaEE.lab2023.entity.Pesadas;
import tec.inf.javaEE.lab2023.entity.Tracking;
import tec.inf.javaEE.lab2023.dto.TrackingDTO;
import tec.inf.javaEE.lab2023.entity.Viaje;

@Stateless
public class TrackingDAO implements TrackingDaoLocal {
	
	@JAVAEE2023
	@Inject
	public EntityManager em;
	
	@EJB
	ViajeDaoLocal viajeDao;
	
	@Override
    public void location(int viaje, float lat, float lon) {
		Viaje v = viajeDao.objeterViaje(viaje);
		if (v != null ) {
			Tracking t = new Tracking(lon,lat,v);
			List<Tracking> aux = v.getTracking();
			aux.add(t);
			v.setTracking(aux);
			em.persist(t);
			em.persist(v);
		}else {
			System.out.println("no existe un viaje activo aun");
		}
	}

	@Override
	public List<TrackingDTO> obtenerTracking(){
		String queryString = "SELECT t FROM Tracking t";
		List<TrackingDTO> listTracking = new ArrayList<>();
		try {
			TypedQuery<Tracking> query = em.createQuery(queryString, Tracking.class);
			List<Tracking> tracking = query.getResultList();
		    for (Tracking p : tracking) {
		    	TrackingDTO dto = new TrackingDTO(p.getId(),p.getLat(),p.getLon(),p.getViaje().getId());
		    	listTracking.add(dto);
		    }
		    return listTracking;
		}catch(NoResultException e) {
			return listTracking;
		}
	}
}
