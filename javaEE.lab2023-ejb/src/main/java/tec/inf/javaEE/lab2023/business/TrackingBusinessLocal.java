package tec.inf.javaEE.lab2023.business;

import java.util.List;

import javax.ejb.Local;

import tec.inf.javaEE.lab2023.dto.TrackingDTO;

@Local
public interface TrackingBusinessLocal {
	public void location(int viaje, float lat, float lon);

	public List<TrackingDTO> listarTracking();
}
