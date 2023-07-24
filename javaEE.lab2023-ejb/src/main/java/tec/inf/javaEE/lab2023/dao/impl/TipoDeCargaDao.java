package tec.inf.javaEE.lab2023.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.TipoDeCargaDaoLocal;
import tec.inf.javaEE.lab2023.dto.RubroDTO;
import tec.inf.javaEE.lab2023.dto.TipoDeCargaDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Rubro;
import tec.inf.javaEE.lab2023.entity.TipoDeCarga;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
@Stateless
public class TipoDeCargaDao implements TipoDeCargaDaoLocal{
	
	@JAVAEE2023
	@Inject
	public EntityManager em;

	@Override
	public void altaTipoDeCarga(String nombre) throws NoResultException {
		
		TipoDeCarga tdc = this.obtenerTipoDeCarga(nombre);
		if (tdc == null) {
			TipoDeCarga nuevo = new TipoDeCarga();
			nuevo.setNombre(nombre);			
			em.persist(nuevo);
		}else {
			throw new NoResultException("Ya existe un tipo de carga con esos datos");
		} 		
	}

	@Override
	public TipoDeCarga obtenerTipoDeCarga(String nombre) throws NoResultException {
	    String queryString = "SELECT * FROM TipoDeCarga WHERE nombre = '"+nombre+"' AND status = 'true'";
	    try {
		    Query query = em.createNativeQuery(queryString, TipoDeCarga.class);
	    	TipoDeCarga result = (TipoDeCarga) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	@Override
	public void deleteTipoDeCarga(String nombre) throws NoResultException {
		TipoDeCarga t = obtenerTipoDeCarga(nombre);
		if (t != null) {
			t.setStatus(false);
			em.persist(t);
		}
  	}
	
	@Override
	public List<TipoDeCargaDTO> getTipoDeCarga() {
		String queryString = "SELECT t FROM TipoDeCarga t WHERE t.status = 'true'";
		TypedQuery<TipoDeCarga> query = em.createQuery(queryString, TipoDeCarga.class);
		List<TipoDeCarga> tiposDeCarga = query.getResultList();
	    
	    List<TipoDeCargaDTO> tiposDeCargaDTO = new ArrayList<>();
	    
	    for (TipoDeCarga rubro : tiposDeCarga) {
	    	TipoDeCargaDTO tipoDeCargaDTO = new TipoDeCargaDTO();
	    	tipoDeCargaDTO.setNombre(rubro.getNombre());
	    	tiposDeCargaDTO.add(tipoDeCargaDTO);
	    }
	    return tiposDeCargaDTO;
	}

}
