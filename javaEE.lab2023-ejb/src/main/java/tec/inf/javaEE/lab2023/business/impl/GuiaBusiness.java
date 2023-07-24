package tec.inf.javaEE.lab2023.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.GuiaBusinessLocal;
import tec.inf.javaEE.lab2023.dao.GuiaDaoLocal;
import tec.inf.javaEE.lab2023.entity.Guia;

@Stateless
@Named("guiaBusiness")
public class GuiaBusiness implements GuiaBusinessLocal{
	
	@EJB
	GuiaDaoLocal guiaDaoLocal;

	@Override
	public void altaGuia(int volumenDeCarga, String origen, String destino, String rubro,String tipoDeCarga) throws Exception {
		guiaDaoLocal.altaGuia(volumenDeCarga, origen, destino, tipoDeCarga, rubro);	
	}
	@Override
	public int obtenerUltimaGuia() {
		return guiaDaoLocal.obtenerUltimaGuia();
		
	}
}
