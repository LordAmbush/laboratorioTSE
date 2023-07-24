package tec.inf.javaEE.lab2023.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tec.inf.javaEE.lab2023.dao.EmpresaDaoLocal;
import tec.inf.javaEE.lab2023.dto.EmpresaDTO;
import tec.inf.javaEE.lab2023.entity.AdminEmpresa;
import tec.inf.javaEE.lab2023.entity.Conductor;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.entity.Vehiculo;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;

@Stateless
public class EmpresaDao implements EmpresaDaoLocal{

	@JAVAEE2023
	@Inject
	public EntityManager em;
	
	public Empresa obtenerEmpresa(String nombre) throws NoResultException {
		String queryString = "SELECT * FROM Empresa WHERE nombreempresa = '"+nombre+"' AND status = 'true'";
		try {
			Query query = em.createNativeQuery(queryString, Empresa.class);
	    	Empresa result = (Empresa) query.getSingleResult();
	        return result;
	    } catch (NoResultException e) {
	    	System.out.println("No se encontro una empresa con el nombre: "+nombre+" y se devuelve null.");
	        return null;
	    }   
	}
	
	public void updateEmpresa(int nroEmpresa,String direccion,String nombre,String rsocial) {
			Empresa empresa = this.obtenerEmpresa(nombre);
			if (empresa != null) {
				empresa.setDireccion(direccion);
				empresa.setnombreEmpresa(nombre);
				empresa.setRazonSocial(rsocial);
				em.merge(empresa);
			}else {
				throw new NoResultException("No se pudo encontrar la empresa");
			}

	}
	
	 public void altaEmpresa(int numero,String direccion,String nombre,String rsocial) throws NoResultException {
			Empresa empresa = this.obtenerEmpresa(nombre);
			if (empresa == null) {
				Empresa nuevo = new Empresa();
				nuevo.setNroEmpresa(numero);
				nuevo.setDireccion(direccion);
				nuevo.setnombreEmpresa(nombre);
				nuevo.setRazonSocial(rsocial);
				em.persist(nuevo);
			}else {
				throw new NoResultException("Ya existe una empresa con esos datos!");
			} 
	}

		public void deleteEmpresa(String nombre) throws NoResultException {
			Empresa empresa = this.obtenerEmpresa(nombre);
			
			//deshabilitar Admin de la empresa
			AdminEmpresa a = empresa.getAdminEmpresa();
			if (a != null) {
				a.setStatus(false);
				em.persist(a);
			}

			//deshabilitar los vehiculos asociados a la empresa
			List<Vehiculo> vehiculos = empresa.getVehiculos();
			if(vehiculos != null) {
				for(Vehiculo v : vehiculos) {
					v.setStatus(false);
					em.persist(v);
				}

			}

			//deshabilitar los conductores asociados a la empresa
			List<Conductor> conductores = empresa.getConductores();
			if (conductores != null) {
				for(Conductor c : conductores) {
					c.setStatus(false);
					em.persist(c);
				}
			}
			
			//deshabilitar la empresa
			empresa.setStatus(false);		
			em.persist(empresa);
		}
		
		public List<EmpresaDTO> getEmpresas() throws NoResultException {
			String queryString = "SELECT e FROM Empresa e WHERE e.status = 'true'";
			try {
				TypedQuery<Empresa> query = em.createQuery(queryString, Empresa.class);
				List<Empresa> empresas = query.getResultList();
			    List<EmpresaDTO> empresasDTO = new ArrayList<>();
			    for (Empresa empresa : empresas) {
			    	EmpresaDTO empresaDTO = new EmpresaDTO();
			    	empresaDTO.setDireccion(empresa.getDireccion());
			    	empresaDTO.setNombreEmpresa(empresa.getNombreEmpresa());
			    	empresaDTO.setNroEmpresa(empresa.getNroEmpresa());
			    	empresaDTO.setRazonSocial(empresa.getRazonSocial());
			    	empresasDTO.add(empresaDTO);
			    }
			    return empresasDTO;
			}catch(NoResultException e) {
				throw new NoResultException("No se pudo listar las empresas");
			}
		}


		@Override
		public void addVehiculo(Vehiculo vehiculo, Empresa empresa) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<GraficasDTO> EmpresasViajes() {
		    String queryString = "SELECT e.nombreEmpresa AS data, COUNT(v.id) AS cantidad "
		            + "FROM empresa e "
		            + "JOIN Usuario u ON u.empresa_id = e.nroEmpresa "
		            + "JOIN viaje v ON v.conductor_id = u.ci "
		            + "WHERE u.dtype = 'Conductor' "
		            + "GROUP BY e.nroEmpresa, e.nombreEmpresa "
		            + "ORDER BY cantidad DESC "
		            + "LIMIT 5";

		    try {
		        Query query = em.createNativeQuery(queryString, "GraficasDTO");
		        List<GraficasDTO> ret = query.getResultList();
		        return ret;
		    } catch (NoResultException e) {
		        throw new NoResultException("No se pudo listar las empresas");
		    }
		}
		
		@Override
		public List<GraficasDTO> EmpresasVehiculos() {
		    String queryString = "SELECT e.nombreEmpresa AS data, COUNT(v.matricula) AS cantidad "
		            + "FROM empresa e "
		            + "JOIN Vehiculo v ON v.empresa_id = e.nroEmpresa "
		            + "WHERE e.status = 'true' and v.status = 'true' "
		            + "GROUP BY e.nombreEmpresa "
		            + "ORDER BY cantidad DESC "
		            + "LIMIT 5";

		    try {
		        Query query = em.createNativeQuery(queryString, "GraficasDTO");
		        List<GraficasDTO> ret = query.getResultList();
		        return ret;
		    } catch (NoResultException e) {
		        throw new NoResultException("No se pudo listar las empresas");
		    }
		}

		@Override
		public GraficasDTO cantConductores(String empresa) {
		    String queryString = "SELECT e.nombreEmpresa AS data, COUNT(u.ci) AS cantidad "
		            + "FROM empresa e "
		            + "JOIN Usuario u ON u.empresa_id = e.nroEmpresa "
		            + "WHERE e.status = 'true' and u.status = 'true' AND u.dtype = 'Conductor' AND e.nombreEmpresa = '"+empresa+"' " 
		            + "GROUP BY e.nombreEmpresa";
		    try {
		        Query query = em.createNativeQuery(queryString, "GraficasDTO");
		        GraficasDTO ret = (GraficasDTO) query.getSingleResult();
		        return ret;
		    } catch (NoResultException e) {
		    	GraficasDTO ret = new GraficasDTO(empresa, 0);
                return ret;
		    }
		}
		
		@Override
		public GraficasDTO cantViajes(String empresa) {
			String queryString = "SELECT e.nombreEmpresa AS data, COUNT(v.id) AS cantidad "
                    + "FROM empresa e "
                    + "JOIN Usuario u ON u.empresa_id = e.nroEmpresa "
                    + "JOIN Viaje v ON v.conductor_id = u.ci "
                    + "WHERE e.status = 'true' and u.status = 'true' AND u.dtype = 'Conductor' AND e.nombreEmpresa = '"+empresa+"' "
                    + "GROUP BY e.nombreEmpresa";
		    try {
		        Query query = em.createNativeQuery(queryString, "GraficasDTO");
		        GraficasDTO ret = (GraficasDTO) query.getSingleResult();
		        return ret;
		    } catch (NoResultException e) {
		    	GraficasDTO ret = new GraficasDTO(empresa, 0);
                return ret;
		    }
		}
		
		@Override
		public GraficasDTO cantVehiculos(String empresa) {
		    String queryString = "SELECT e.nombreEmpresa AS data, COUNT(v.matricula) AS cantidad "
		            + "FROM empresa e "
		            + "JOIN Vehiculo v ON v.empresa_id = e.nroEmpresa "
		            + "WHERE e.status = 'true' and v.status = 'true' AND e.nombreEmpresa = '"+empresa+"' "
		            + "GROUP BY e.nombreEmpresa";
		    try {
		        Query query = em.createNativeQuery(queryString, "GraficasDTO");
		        GraficasDTO ret = (GraficasDTO) query.getSingleResult();
		        return ret;
		    } catch (NoResultException e) {
		    	GraficasDTO ret = new GraficasDTO(empresa, 0);
                return ret;
		    }
		}
}
