package tec.inf.javaEE.lab2023.business;

import java.util.Date;

public interface PermisoVehicularBusinessLocal {
	void altaPermiso(String fechaDesde, String fechaHasta, String matricula) throws Exception;
}
