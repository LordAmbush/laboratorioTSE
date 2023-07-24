package tec.inf.javaEE.lab2023.business.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.ItvBusinessLocal;
import tec.inf.javaEE.lab2023.dao.ItvDaoLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.entity.Vehiculo;


@Stateless
@Named("itvBusiness")
public class ItvBusiness implements ItvBusinessLocal{
	
	@EJB
	ItvDaoLocal itvDaoLocal;

	@EJB
	VehiculoDaoLocal vehiculoDao;
	
	@Override
	public void altaItv(String fDesde, String fHasta, String matricula) throws Exception{
		itvDaoLocal.altaItv(fDesde, fHasta,matricula);
	}

}
