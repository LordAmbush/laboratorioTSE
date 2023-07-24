package tec.inf.javaEE.lab2023.services.soap;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import tec.inf.javaEE.lab2023.business.TrackingBusinessLocal;

@WebService
public class NodoTracking {
	
	@EJB
	TrackingBusinessLocal service;
	
    public static void main(String[] args) {
        String url = "http://localhost:8080/nodoTracking";
        Endpoint.publish(url, new NodoTracking());
        System.out.println("-------------Service Tracking started at: " + url);
    }
    
    @WebMethod
    public void location(int viaje, float lat, float lon) {
    	service.location(viaje, lat, lon);
    }
}
