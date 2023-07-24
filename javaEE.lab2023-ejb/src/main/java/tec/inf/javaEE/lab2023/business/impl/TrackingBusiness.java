package tec.inf.javaEE.lab2023.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.TrackingBusinessLocal;
import tec.inf.javaEE.lab2023.dao.TrackingDaoLocal;
import tec.inf.javaEE.lab2023.dto.TrackingDTO;

@Stateless
@Named("trackingService")
public class TrackingBusiness implements TrackingBusinessLocal {

	@EJB
	TrackingDaoLocal trackingDAO;
	
	@Override
    public void location(int viaje, float lat, float lon) {
		trackingDAO.location(viaje, lat, lon);
	}

	@Override
	public List<TrackingDTO> listarTracking(){
		return trackingDAO.obtenerTracking();
	}
}
