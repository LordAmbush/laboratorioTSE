package tec.inf.javaEE.lab2023.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.BalanzaBusinessLocal;
import tec.inf.javaEE.lab2023.dao.BalanzaDaoLocal;
import tec.inf.javaEE.lab2023.dao.ViajeDaoLocal;
import tec.inf.javaEE.lab2023.dto.PesadasDTO;
import tec.inf.javaEE.lab2023.entity.Viaje;


@Stateless
@Named("balanzaBusiness")
public class BalanzaBusiness implements BalanzaBusinessLocal{

	@EJB
	ViajeDaoLocal viajeDao;
	@EJB
	BalanzaDaoLocal balanzaDao;
	
	@Override
	public void agregarPesada(int id, String[] pesadas) {
		Viaje v = viajeDao.objeterViaje(id);
		for(int i = 0; i < pesadas.length; i++) {
			balanzaDao.agregarPesada(v,pesadas[i]);
		}
	}
	
	@Override
	public List<PesadasDTO> listarPesadas(){
		return balanzaDao.listarPesadas();
	}
	
}
