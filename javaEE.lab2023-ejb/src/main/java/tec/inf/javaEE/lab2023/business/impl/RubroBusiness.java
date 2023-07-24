package tec.inf.javaEE.lab2023.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.business.RubroBusinessLocal;
import tec.inf.javaEE.lab2023.dao.RubroDaoLocal;
import tec.inf.javaEE.lab2023.dto.RubroDTO;
import tec.inf.javaEE.lab2023.entity.Rubro;

@Stateless
@Named("rubroBusiness")
public class RubroBusiness implements RubroBusinessLocal{
	
	@EJB
	RubroDaoLocal rubroDao;
	
	@Override
	public void altaRubro(String nombre) throws Exception {
		rubroDao.altaRubro(nombre);
	}

	@Override
	public void updateRubro(String nombre) throws Exception {
		try {
			Rubro rubro = rubroDao.obtenerRubro(nombre);
			if (rubro.getNombre().equals(nombre)) {
				rubroDao.modificarRubro(nombre);
			}else {
				System.out.println("El rubro" + rubro + "no esta registrado en el sistema.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRubro(String nombre) throws Exception {
		rubroDao.deleteRubro(nombre);
	}

	@Override
	public Rubro obtenerRubro(String nombre) {
		Rubro r = rubroDao.obtenerRubro(nombre);
		System.out.println(r.toString());
		return r;
	}

	@Override
	public List<RubroDTO> getRubro() {
		List<RubroDTO> rubros = rubroDao.getRubros();
		for(RubroDTO rubro : rubros) {
			System.out.println(rubro.toString());
		}
		return rubros;
	}

}
