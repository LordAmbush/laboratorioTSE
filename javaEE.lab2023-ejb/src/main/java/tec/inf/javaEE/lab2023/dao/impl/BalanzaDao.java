package tec.inf.javaEE.lab2023.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.BalanzaDaoLocal;
import tec.inf.javaEE.lab2023.dao.ViajeDaoLocal;
import tec.inf.javaEE.lab2023.entity.Viaje;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.dto.PesadasDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Pesadas;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;

@Stateless
public class BalanzaDao implements BalanzaDaoLocal{

	@JAVAEE2023
	@Inject
	public EntityManager em;
	
	@EJB
	ViajeDaoLocal viajeDao;
	
	@Override
	public void agregarPesada(Viaje v, String peso){
		Pesadas pesada = new Pesadas(v,peso);
		List<Pesadas> aux = v.getPesadas();
		aux.add(pesada);
		v.setPesadas(aux);
		try {
			em.persist(pesada);
			em.persist(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<PesadasDTO> listarPesadas(){
		String queryString = "SELECT p FROM Pesadas p";
		List<PesadasDTO> pesadasDTO = new ArrayList<>();
		try {
			TypedQuery<Pesadas> query = em.createQuery(queryString, Pesadas.class);
			List<Pesadas> pesadas = query.getResultList();
		    for (Pesadas p : pesadas) {
		    	PesadasDTO dto = new PesadasDTO();
		    	dto.setId(p.getId());
		    	dto.setPeso(p.getPeso());
		    	dto.setViaje(p.getViaje().getId());
		    	pesadasDTO.add(dto);
		    }
		    return pesadasDTO;
		}catch(NoResultException e) {
			return pesadasDTO;
		}
	}
	
}
