package tec.inf.javaEE.lab2023.business.impl;

import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.ViajeBusinessLocal;
import tec.inf.javaEE.lab2023.dao.ViajeDaoLocal;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.dto.ViajeDTO;

/**
 * Session Bean implementation class ViajeBuisness
 */
@Stateless
@Named("viajeBusiness")
public class ViajeBusiness implements ViajeBusinessLocal {

	@EJB
	ViajeDaoLocal viajeDao;
	
	public ViajeBusiness() {
		
	}
	
    @Override
    public ViajeDTO consultarEstadoViaje(String ci) throws InterruptedException, ExecutionException {    	
    	ViajeDTO v = viajeDao.consultarEstadoViaje(ci);
    	return v;
    }
    
    @Override
    public boolean updateEstadoViaje(int id) {
    	return viajeDao.updateEstadoViaje(id);		
    }
    
    @Override
    public void altaViaje(int idGuia, String matricula, String ciConductor)  throws Exception {
    	viajeDao.altaViaje(idGuia, matricula, ciConductor);
    }

	@Override
	public void deleteViaje(int id) throws Exception {
		viajeDao.deleteViaje(id);
		
	}

}

