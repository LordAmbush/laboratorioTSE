package tec.inf.javaEE.lab2023.dao;



import java.util.List;

import javax.ejb.Local;

import tec.inf.javaEE.lab2023.dto.TrackingDTO;
import tec.inf.javaEE.lab2023.entity.Tracking;

@Local
public interface TrackingDaoLocal {
    public void location(int viaje, float lat, float lon);

	List<TrackingDTO> obtenerTracking();
}
