package tec.inf.javaEE.lab2023.dao.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

import tec.inf.javaEE.lab2023.dao.GuiaDaoLocal;
import tec.inf.javaEE.lab2023.dao.UsuarioDaoLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.dao.ViajeDaoLocal;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.InfoViajesDTO;
import tec.inf.javaEE.lab2023.dto.ViajeDTO;
import tec.inf.javaEE.lab2023.entity.Guia;
import tec.inf.javaEE.lab2023.entity.Usuario;
import tec.inf.javaEE.lab2023.entity.Conductor;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Vehiculo;
import tec.inf.javaEE.lab2023.entity.Viaje;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;

@Stateless
public class ViajeDao implements ViajeDaoLocal{

	@JAVAEE2023
	@Inject
	public EntityManager em;

	@EJB
	GuiaDaoLocal guiaDao;
	@EJB
	VehiculoDaoLocal vehiculoDao;
	@EJB
	UsuarioDaoLocal usuarioDao;
	@EJB
	ViajeDaoLocal viajeDao;

	@Override
	public boolean updateEstadoViaje(int id) throws NoResultException {
		Query q = em.createNativeQuery("select * from viaje where id = " + id, Viaje.class);
		
		try {
			Viaje result = (Viaje) q.getSingleResult();
			String estadoActual = result.getEstado();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			if(estadoActual.equals("Pendiente")) {
				Date fechaInicio = new Date();
				String fechaString = dateFormat.format(fechaInicio);
				result.setInicioViaje(fechaString);
				result.setEstado("EnProgreso");
				em.persist(result);
				return false;
			}else {
				Date fechaFin = new Date();
				String fechaString = dateFormat.format(fechaFin);
				result.setFinViaje(fechaString);
				result.setEstado("Finalizado");
				em.persist(result);
				return true;
			}

		} catch (NoResultException e) {
			throw e;
		}
	}
	
	 @Override
	 public void altaViaje(int idGuia, String matricula, String ciConductor) throws NoResultException {
		 
		 Guia g = guiaDao.obtenerGuia(idGuia);
		 Vehiculo v = vehiculoDao.obtenerVehiculo(matricula);
		 Usuario c = usuarioDao.obtenerUsuario(ciConductor);
		 Conductor conductor = (Conductor) c;
		 
		 if(g == null) {
			 throw new NoResultException("Guia invalida");
		 }else if(v == null) {
			 throw new NoResultException("Vehiculo invalido");
		 }else if(conductor == null) {
			 throw new NoResultException("Conductor invalido");
		 }else {
			 Viaje nuevo = new Viaje();
			 nuevo.setConductor(conductor);
			 nuevo.setGuia(g);
			 nuevo.setVehiculo(v);
			 nuevo.setEstado("Pendiente");
			 
			 em.persist(nuevo);
			 
			 g.setViaje(nuevo);
			 em.persist(g);
			 
			 List<Viaje> viajes = v.getViajes();
			 viajes.add(nuevo);
			 v.setViajes(viajes);
			 em.persist(v);
			 
			 viajes = conductor.getViajes();
			 viajes.add(nuevo);
			 conductor.setViajes(viajes);
			 em.persist(conductor);
		 }
	 }
	 
	@Override
	public Viaje objeterViaje(int id) {
		String queryString = "SELECT * FROM Viaje WHERE id = '"+id+"'";
	    try {
			Query query = em.createNativeQuery(queryString, Viaje.class);
	    	Viaje result = (Viaje) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	@Override
	public void deleteViaje(int id) throws NoResultException {
		Viaje viaje = this.objeterViaje(id);
		if (viaje != null) {
			Guia g = viaje.getGuia();
			g.setStatus(false);
			viaje.setStatus(false);
			em.persist(viaje);
			em.persist(g);
		}else {
			 throw new NoResultException("No se encontró ningún viaje de id: " + id);
		}
			
	
	}
	/*
	@Override
	public List<ViajeDTO> getViajes() {
		String queryString = "SELECT v FROM Viaje";
		TypedQuery<Viaje> query = em.createQuery(queryString, Viaje.class);
		List<Viaje> viajes = query.getResultList();
	    
	    List<ViajeDTO> viajesDTO = new ArrayList<>();
	    
	    for (Viaje viaje : viajes) {
	        ViajeDTO viajeDTO = new ViajeDTO();
	        viajeDTO.setEstado(viaje.getEstado());
	        viajeDTO.setInicioViaje(new Date(viaje.getInicioViaje().getTime()));
	        viajeDTO.setFinViaje(new Date(viaje.getFinViaje().getTime()));
	        
	        viajesDTO.add(viajeDTO);
	    }
	    
	    return viajesDTO;
	}
	@Override
	public ViajeDTO getViaje(int id) {
	    String queryString = "SELECT v FROM viaje v WHERE v.id = :id";
	    TypedQuery<Viaje> query = em.createQuery(queryString, Viaje.class);
	    query.setParameter("id", id);
	    try {
	    	Viaje result = query.getSingleResult();
		    ViajeDTO viajeDTO = new ViajeDTO(result.getEstado(),(new Date(result.getInicioViaje().getTime())),(new Date(result.getFinViaje().getTime())));
	        return viajeDTO;
	    } catch (NoResultException e) {
	        throw e;
	    }
	}
	*/
	@Override
	public ViajeDTO consultarEstadoViaje(String ci) throws NoResultException {
		String queryString = "SELECT * FROM viaje WHERE conductor_id = '"+ci+"' ORDER BY id DESC LIMIT 1";
		Query query = em.createNativeQuery(queryString, Viaje.class);
		try {
			Viaje result = (Viaje) query.getSingleResult();
			//agregar datos de la guia pertinentes para el componente movil
			Guia g = result.getGuia();
			ViajeDTO ret = new ViajeDTO(result.getId(),result.getConductor().getCi(),result.getEstado(),g.getOrigen(),g.getDestino());
			return ret;

		} catch (NoResultException e) {
			throw e;
		}
	}

	@Override
	public List<GraficasDTO> ViajeRubro() {
		List<GraficasDTO> ret = new ArrayList<>();
	    String queryString = "SELECT r.nombre AS data, COUNT(v.id) AS cantidad "
	            + "FROM viaje v "
	            + "JOIN guia g ON v.guia_id = g.id "
	            + "JOIN rubro r ON g.rubro_id = r.id "
	            + "GROUP BY r.nombre "
	            + "ORDER BY cantidad DESC "
	            + "LIMIT 10";

	    try {
	        Query query = em.createNativeQuery(queryString, "GraficasDTO");
	        ret = query.getResultList();
	        return ret;
	    } catch (NoResultException e) {
	    	return ret;
	    }
	}
	
	@Override
	public List<GraficasDTO> ViajeTipoCarga() {
		 List<GraficasDTO> ret = new ArrayList<>();
	    String queryString = "SELECT r.nombre AS data, COUNT(v.id) AS cantidad "
	            + "FROM viaje v "
	            + "JOIN guia g ON v.guia_id = g.id "
	            + "JOIN tipodecarga r ON g.tipocarga_id = r.id "
	            + "GROUP BY r.nombre "
	            + "ORDER BY cantidad DESC "
	            + "LIMIT 10";

	    try {
	        Query query = em.createNativeQuery(queryString, "GraficasDTO");
	        ret = query.getResultList();
	        return ret;
	    } catch (NoResultException e) {
	    	return ret;
	    }
	}
	
	@Override
	public List<InfoViajesDTO> infoViajes() {
		  List<InfoViajesDTO> ret = new ArrayList<>();
	    String queryString = "SELECT v.id AS idViaje, v.estado AS estado, v.inicioviaje AS inicio, v.finviaje AS fin, v.conductor_id AS conductor, v.vehiculo_id AS vehiculo, g.id AS idGuia, t.nombre AS TdeCarga "
	            + "FROM viaje v "
	            + "JOIN guia g ON v.guia_id = g.id "
	            + "JOIN tipodecarga t ON g.tipocarga_id = t.id";
	    try {
	        Query query = em.createNativeQuery(queryString, "InfoViajesDTO");
	        ret = query.getResultList();
	        return ret;
	    } catch (NoResultException e) {
	    	return  ret;
	    }
	}
	
	
	
}
