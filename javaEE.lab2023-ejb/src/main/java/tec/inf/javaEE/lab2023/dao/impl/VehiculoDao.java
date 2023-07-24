package tec.inf.javaEE.lab2023.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.EmpresaDaoLocal;
import tec.inf.javaEE.lab2023.dao.ItvDaoLocal;
import tec.inf.javaEE.lab2023.dao.PermisoNacCirculacionLocal;
import tec.inf.javaEE.lab2023.dao.VehiculoDaoLocal;
import tec.inf.javaEE.lab2023.dto.VehiculoDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Itv;
import tec.inf.javaEE.lab2023.entity.Vehiculo;
import tec.inf.javaEE.lab2023.entity.Viaje;
import tec.inf.javaEE.lab2023.entity.PermisoNacCirculacion;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;

@Stateless
public class VehiculoDao implements VehiculoDaoLocal{

	@JAVAEE2023
	@Inject
	public EntityManager em;
	@EJB
	EmpresaDaoLocal empDao;
	@EJB
	PermisoNacCirculacionLocal permisoDao;
	@EJB
	ItvDaoLocal itvDao;
	
	 public void altaVehiculo(String matricula, String marca, String modelo, int peso,String capacidad, String nombreEmpresa) throws NoResultException {
		Vehiculo vehiculo = this.obtenerVehiculo(matricula);
		if (vehiculo == null) {
			Empresa e = empDao.obtenerEmpresa(nombreEmpresa);
			if (e != null) {
				Vehiculo nuevo = new Vehiculo();
				nuevo.setMatricula(matricula);
				nuevo.setMarca(marca);
				nuevo.setModelo(modelo);
				nuevo.setPeso(peso);
				nuevo.setCapacidad(capacidad);
				nuevo.setEmpresa(e);
				nuevo.setViajes(null);
				em.persist(nuevo);
				
				List<Vehiculo> vehiculos = e.getVehiculos();
				vehiculos.add(nuevo);
				e.setVehiculos(vehiculos);
				em.persist(e);				
			}else {
				throw new NoResultException("No existe una empresa con ese nombre!");
			}
		}else {
			throw new NoResultException("Ya existe un vehiculo con esa matricula!");
		} 
	 }
	 
	 @Override
	 public void updateVehiculo(String matricula, String marca, String modelo, int peso,String capacidad, String nombreEmpresa) throws NoResultException {
		Vehiculo vehiculo = this.obtenerVehiculo(matricula);
		vehiculo.setMarca(marca);
		vehiculo.setModelo(modelo);
		vehiculo.setPeso(peso);
		vehiculo.setCapacidad(capacidad);
		vehiculo.setViajes(null);
		em.persist(vehiculo);			
	 }
	 
	 public Vehiculo obtenerVehiculo(String matricula) throws NoResultException {
	    String queryString = "SELECT * FROM Vehiculo WHERE matricula = '"+matricula+"' AND status = 'true'";
	    try {
		    Query query = em.createNativeQuery(queryString, Vehiculo.class);
	    	Vehiculo result = (Vehiculo) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	 		
	 public void deleteVehiculo(String matricula) throws NoResultException {
		Vehiculo vehiculo = this.obtenerVehiculo(matricula);
		if(vehiculo != null) {
	        boolean hasViajes = vehiculo.getViajes().size() > 0;
	        boolean viajesFinalizados = true;
	        List<Viaje> viajes = vehiculo.getViajes();
	        int index = 0;
	        while (index < viajes.size() && !viajesFinalizados) {
	            Viaje v = viajes.get(index);
	            if (v.getEstado().equals("EnProgreso") || v.getEstado().equals("Pendiente")) {
	                viajesFinalizados = false;
	            }
	            index++;
	        }
			if(!hasViajes || !viajesFinalizados) {
				List<Itv> itvs = vehiculo.getItv();
				if (!itvs.isEmpty()) {
					for(Itv itv : itvs) {
						itv.setStatus(false);
						em.persist(itv);
					}
				}
				PermisoNacCirculacion p = vehiculo.getPermisoNacCirculacion();
				if(p != null) {
					p.setStatus(false);
					em.persist(p);
				}
				vehiculo.setStatus(false);
				em.persist(vehiculo);
			}else {
				throw new NoResultException("Vehiculo con viajes no finalizados!");
			}		
		}else {
			throw new NoResultException("No se pudo encontrar el vehiculo!");
		}

	}

	 //retorna una lista de todos los vehiculos para la empresa de nroEmpresa
	 public List<VehiculoDTO> getVehiculos() {

		String queryString = "SELECT v FROM Vehiculo v WHERE status = 'true'";
		TypedQuery<Vehiculo> query = em.createQuery(queryString, Vehiculo.class);
		List<Vehiculo> vehiculos = query.getResultList();
	    
	    List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
	    
	    for (Vehiculo vehiculo : vehiculos) {
    		VehiculoDTO vehiculoDTO = new VehiculoDTO();
	    	vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
	    	vehiculoDTO.setMarca(vehiculo.getMarca());
	    	vehiculoDTO.setMatricula(vehiculo.getMatricula());
	    	vehiculoDTO.setModelo(vehiculo.getModelo());
	    	vehiculoDTO.setPeso(vehiculo.getPeso());
	    	vehiculoDTO.setEmpresa(vehiculo.getEmpresa().getNombreEmpresa());
	    	
	    	boolean hasItv = vehiculo.getItv().size() > 0;
            boolean itvAlDia = hasItv && vehiculo.getItv().get(vehiculo.getItv().size() - 1).alDia();
            boolean permisoAlDia = vehiculo.getPermisoNacCirculacion() != null && vehiculo.getPermisoNacCirculacion().alDia();
            
            if(itvAlDia) {
            	vehiculoDTO.setItv(vehiculo.getItv().get(vehiculo.getItv().size() - 1).getFechaHasta());
            }else {
            	vehiculoDTO.setItv("VENCIDO");
            }
            
            if(permisoAlDia) {
            	vehiculoDTO.setPermiso(vehiculo.getPermisoNacCirculacion().getFechaHasta());
            }else {
            	vehiculoDTO.setPermiso("VENCIDO");
            }
	    	
	        
	        vehiculosDTO.add(vehiculoDTO);
	    	}
	    return vehiculosDTO;
	}

	//retorna una lista de todos los vehiculos para la empresa de nroEmpresa
    @Override
    public List<VehiculoDTO> getVehiculosEmpresa(String empresa) {
        String queryString = "SELECT v FROM Vehiculo v JOIN Empresa e ON v.empresa = e.nroEmpresa  WHERE v.status = 'true' AND e.nombreEmpresa = '"+empresa+"'";
        TypedQuery<Vehiculo> query = em.createQuery(queryString, Vehiculo.class);
        List<Vehiculo> vehiculos = query.getResultList();
	    
	    List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
	   
	    for (Vehiculo vehiculo : vehiculos) {	
	    		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		    	vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
		    	vehiculoDTO.setMarca(vehiculo.getMarca());
		    	vehiculoDTO.setMatricula(vehiculo.getMatricula());
		    	vehiculoDTO.setModelo(vehiculo.getModelo());
		    	vehiculoDTO.setPeso(vehiculo.getPeso());
		    	vehiculoDTO.setEmpresa(empresa);
		        
		        vehiculosDTO.add(vehiculoDTO);
	    	
    	}
	    return vehiculosDTO;
	}

	//retorna una lista de todos los vehiculos para la empresa de nroEmpresa
    @Override
    public List<VehiculoDTO> getVehiculosAlDia(String empresa) {
        String queryString = "SELECT v FROM Vehiculo v JOIN Empresa e ON v.empresa = e.nroEmpresa  WHERE v.status = 'true' AND e.nombreEmpresa = '"+empresa+"'";
        TypedQuery<Vehiculo> query = em.createQuery(queryString, Vehiculo.class);
        List<Vehiculo> vehiculos = query.getResultList();

        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            if ((vehiculo.getItv().size() > 0 || vehiculo.getPermisoNacCirculacion() != null)
                    && ((vehiculo.getItv().size() == 0 || vehiculo.getItv().get(vehiculo.getItv().size() - 1).alDia())
                    && (vehiculo.getPermisoNacCirculacion() == null || vehiculo.getPermisoNacCirculacion().alDia())))
            {
                VehiculoDTO vehiculoDTO = new VehiculoDTO();
                vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
                vehiculoDTO.setMarca(vehiculo.getMarca());
                vehiculoDTO.setMatricula(vehiculo.getMatricula());
                vehiculoDTO.setModelo(vehiculo.getModelo());
                vehiculoDTO.setPeso(vehiculo.getPeso());
                vehiculoDTO.setEmpresa(empresa);
                System.out.println(vehiculoDTO);
                vehiculosDTO.add(vehiculoDTO);
            }
        }
        return vehiculosDTO;
    }
    
    
    @Override
    public List<VehiculoDTO> getVehiculosVencidos(String empresa) {
        String queryString = "SELECT v FROM Vehiculo v JOIN Empresa e ON v.empresa = e.nroEmpresa WHERE v.status = 'true' AND e.nombreEmpresa = '"+empresa+"'";
        TypedQuery<Vehiculo> query = em.createQuery(queryString, Vehiculo.class);
        List<Vehiculo> vehiculos = query.getResultList();

        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            boolean hasItv = vehiculo.getItv().size() > 0;
            boolean itvAlDia = hasItv && vehiculo.getItv().get(vehiculo.getItv().size() - 1).alDia();
            boolean permisoAlDia = vehiculo.getPermisoNacCirculacion() != null && vehiculo.getPermisoNacCirculacion().alDia();

            if (!itvAlDia || !permisoAlDia || !hasItv || vehiculo.getPermisoNacCirculacion() == null) {
                VehiculoDTO vehiculoDTO = new VehiculoDTO();
                vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
                vehiculoDTO.setMarca(vehiculo.getMarca());
                vehiculoDTO.setMatricula(vehiculo.getMatricula());
                vehiculoDTO.setModelo(vehiculo.getModelo());
                vehiculoDTO.setPeso(vehiculo.getPeso());
                vehiculoDTO.setEmpresa(empresa);
                System.out.println(vehiculoDTO);
                vehiculosDTO.add(vehiculoDTO);
            }
        }

        return vehiculosDTO;
	}
    
    @Override
    public List<VehiculoDTO> getVehiculosVencidosITV(String empresa) {
        String queryString = "SELECT v FROM Vehiculo v JOIN Empresa e ON v.empresa = e.nroEmpresa WHERE v.status = 'true' AND e.nombreEmpresa = '"+empresa+"'";
        TypedQuery<Vehiculo> query = em.createQuery(queryString, Vehiculo.class);
        List<Vehiculo> vehiculos = query.getResultList();

        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            boolean hasItv = vehiculo.getItv().size() > 0;
            boolean itvAlDia = hasItv && vehiculo.getItv().get(vehiculo.getItv().size() - 1).alDia();

            if (!itvAlDia || !hasItv) {
                VehiculoDTO vehiculoDTO = new VehiculoDTO();
                vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
                vehiculoDTO.setMarca(vehiculo.getMarca());
                vehiculoDTO.setMatricula(vehiculo.getMatricula());
                vehiculoDTO.setModelo(vehiculo.getModelo());
                vehiculoDTO.setPeso(vehiculo.getPeso());
                vehiculoDTO.setEmpresa(empresa);
                System.out.println(vehiculoDTO);
                vehiculosDTO.add(vehiculoDTO);
            }
        }

        return vehiculosDTO;
    }
    
    @Override
    public List<VehiculoDTO> getVehiculosVencidosPNC(String empresa) {
        String queryString = "SELECT v FROM Vehiculo v JOIN Empresa e ON v.empresa = e.nroEmpresa WHERE v.status = 'true' AND e.nombreEmpresa = '"+empresa+"'";
        TypedQuery<Vehiculo> query = em.createQuery(queryString, Vehiculo.class);
        List<Vehiculo> vehiculos = query.getResultList();

        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            boolean permisoAlDia = vehiculo.getPermisoNacCirculacion() != null && vehiculo.getPermisoNacCirculacion().alDia();

            if (!permisoAlDia || vehiculo.getPermisoNacCirculacion() == null) {
                VehiculoDTO vehiculoDTO = new VehiculoDTO();
                vehiculoDTO.setCapacidad(vehiculo.getCapacidad());
                vehiculoDTO.setMarca(vehiculo.getMarca());
                vehiculoDTO.setMatricula(vehiculo.getMatricula());
                vehiculoDTO.setModelo(vehiculo.getModelo());
                vehiculoDTO.setPeso(vehiculo.getPeso());
                vehiculoDTO.setEmpresa(empresa);
                System.out.println(vehiculoDTO);
                vehiculosDTO.add(vehiculoDTO);
            }
        }

        return vehiculosDTO;
	}

}
