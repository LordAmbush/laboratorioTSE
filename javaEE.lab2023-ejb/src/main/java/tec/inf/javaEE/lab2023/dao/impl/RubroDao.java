package tec.inf.javaEE.lab2023.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.RubroDaoLocal;
import tec.inf.javaEE.lab2023.dto.RubroDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Rubro;
import tec.inf.javaEE.lab2023.entity.Usuario;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
@Stateless
public class RubroDao implements RubroDaoLocal{

	@JAVAEE2023
	@Inject
	public EntityManager em;
	
	@Override
	public void altaRubro(String nombre) throws NoResultException {
		Rubro rubro = this.obtenerRubro(nombre);
		if (rubro == null) {
			Rubro nuevo = new Rubro(nombre);
			em.persist(nuevo);
		}else {
			throw new NoResultException("Ya existe un rubro con ese nombre!");
		} 
	}

	@Override
	public Rubro obtenerRubro(String nombre) throws NoResultException {
	    String queryString = "SELECT * FROM Rubro WHERE nombre = '"+nombre+"' AND status = 'true'";
	    try {
		    Query query = em.createNativeQuery(queryString, Rubro.class);
	    	Rubro result = (Rubro) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	    	return null;
	    }
	}
	
	@Override
	public boolean existeRubro(String nombre) {
	    String queryString = "SELECT COUNT(*) FROM Rubro WHERE nombre = :nombre";
	    Query query = em.createQuery(queryString);
	    query.setParameter("nombre", nombre);
	    Long count = (Long) query.getSingleResult();
	    return count > 0;
	}

	@Override
	public void deleteRubro(String nombre) throws NoResultException {
		Rubro r = obtenerRubro(nombre);
		if (r != null) {
			r.setStatus(false);
			em.persist(r);
		}
	}

	@Override
	public List<RubroDTO> getRubros() {
		String queryString = "SELECT r FROM Rubro r WHERE r.status = 'true'";
		TypedQuery<Rubro> query = em.createQuery(queryString, Rubro.class);
		List<Rubro> rubros = query.getResultList();
	    
	    List<RubroDTO> rubrosDTO = new ArrayList<>();
	    
	    for (Rubro rubro : rubros) {
	    	RubroDTO rubroDTO = new RubroDTO();
	    	rubroDTO.setNombre(rubro.getNombre());
	    	rubrosDTO.add(rubroDTO);
	    }
	    return rubrosDTO;
	}

	@Override
	public void modificarRubro(String nombre) throws Exception {
		Rubro r = this.obtenerRubro(nombre);
		r.setNombre(nombre);
		em.persist(r);		
	}




}
