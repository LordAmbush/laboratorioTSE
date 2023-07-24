package tec.inf.javaEE.lab2023.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.TipoDeCargaBusinessLocal;
import tec.inf.javaEE.lab2023.dao.TipoDeCargaDaoLocal;
import tec.inf.javaEE.lab2023.dto.TipoDeCargaDTO;

@Stateless
@Named("tipoDeCargaBusiness")
public class TipoDeCargaBusiness implements TipoDeCargaBusinessLocal {
	@EJB
	TipoDeCargaDaoLocal tipoDeCargaDao;
	
	@Override
	public void altaTipoDeCarga(String nombre) throws Exception {
		tipoDeCargaDao.altaTipoDeCarga(nombre);
		/*try {
			if (tipoDeCargaDao.existeRubro(nombre)) {
				System.out.println("El rubro" + nombre + "ya esta registrado en el sistema.");
			}else {

				tipoDeCargaDao.altaTipoDeCarga(nombre);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}

	@Override
	public List<TipoDeCargaDTO> getTipoDeCarga() {
		
		return tipoDeCargaDao.getTipoDeCarga();
	}
	
	@Override
	public void deleteTipoCarga(String nombre) {
		tipoDeCargaDao.deleteTipoDeCarga(nombre);
	}

}
