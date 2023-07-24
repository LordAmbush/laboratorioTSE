package tec.inf.javaEE.lab2023.business.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.PermisoVehicularBusinessLocal;
import tec.inf.javaEE.lab2023.dao.PermisoNacCirculacionLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.entity.Vehiculo;

@Stateless
@Named("permisoVehicularBusiness")
public class PermisoVehicularBusiness implements PermisoVehicularBusinessLocal{

	@EJB
	PermisoNacCirculacionLocal permisoNacCirculacionLocal;

	@EJB
	VehiculoDaoLocal vehiculoDao;
	

	
	@Override
	public void altaPermiso(String fechaDesde, String fechaHasta, String matricula) throws Exception {
		permisoNacCirculacionLocal.altaPermiso(fechaDesde, fechaHasta, matricula);
	}
}
