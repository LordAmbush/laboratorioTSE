package tec.inf.javaEE.lab2023.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import tec.inf.javaEE.lab2023.dao.EmpresaDaoLocal;
import tec.inf.javaEE.lab2023.dao.UsuarioDaoLocal;
import tec.inf.javaEE.lab2023.dto.BackofficeDTO;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;

import tec.inf.javaEE.lab2023.entity.Usuario;
import tec.inf.javaEE.lab2023.entity.Autoridad;
import tec.inf.javaEE.lab2023.entity.UserBackoffice;
import tec.inf.javaEE.lab2023.entity.AdminEmpresa;
import tec.inf.javaEE.lab2023.entity.Administrador;
import tec.inf.javaEE.lab2023.entity.Funcionario;
import tec.inf.javaEE.lab2023.entity.Guia;
import tec.inf.javaEE.lab2023.entity.Conductor;
import tec.inf.javaEE.lab2023.entity.Empresa;
import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;
@Stateless
public class UsuarioDao implements UsuarioDaoLocal{
	
	@JAVAEE2023
	@Inject
	public EntityManager em;
	@EJB
	EmpresaDaoLocal empresaDao;

	@Override
	public void altaUsuario(String ci, String username, String email, String fechaNac, String tipoUser, String pass, String empresa) throws NoResultException {
			switch (tipoUser) {
			//ADMIN EMPRESA
            case "3":	   
            	//llega el nombre de la empresa
            	Empresa e = empresaDao.obtenerEmpresa(empresa);
                AdminEmpresa user1 = new AdminEmpresa(ci, username, email,fechaNac,e);
                e.setAdminEmpresa(user1);
                user1.setStatus(true);
                em.persist(user1);
                em.persist(e);
                break;
            //CONDUCTOR
            case "4":
            	Empresa e2 = empresaDao.obtenerEmpresa(empresa);
            	Conductor user2 = new Conductor(ci, username, email,  fechaNac, e2);
            	List<Conductor> listCond = e2.getConductores();
            	listCond.add(user2);
            	e2.setConductores(listCond);
            	user2.setStatus(true);
	            em.persist(user2);
	            em.persist(e2);
                break;
            //FUNCIONARIO
            case "5":
            	Funcionario user3 = new Funcionario(ci, username, email,fechaNac);
            	user3.setStatus(true);
                em.persist(user3);	                
                break;
                
        //vvvvvvvvvvv    IMPLEMENTAR HASH CON SALT PARA ESTOS USUARIOS    vvvvvvvvvvv  
                
            //ADMINISTRADOR
            case "2":
                Administrador user4 = new Administrador(ci, username, email, fechaNac, pass);
                user4.setStatus(true);
                em.persist(user4);
                break;
            //AUTORIDAD
            case "1":
            	Autoridad user5 = new Autoridad(ci, username, email,fechaNac,pass);
            	user5.setStatus(true);
                em.persist(user5);	                
                break;
            default:
                System.out.println("Tipo de usuario no reconocido");
                break;
        }
	}
	public Usuario obtenerUsuario(String ci){
		//FALTA LA PARTE DE CASTEO DEL TIPO DE USUARIO
			String queryString = "SELECT * FROM Usuario WHERE ci = '"+ci+"' AND status = 'true'";
		    try {
			    Query query = em.createNativeQuery(queryString, Usuario.class);
		    	Usuario result = (Usuario) query.getSingleResult();
		        return result;
		    }catch(NoResultException e) {
		    	return null;
		    }
	}
	

	public void eliminarUsuario(String ci) {
		Usuario u = obtenerUsuario(ci);
	   if (u != null) {
		   u.setStatus(false);
		   em.persist(u);
	   }
	}

	public List<UsuarioDTO> getUsuarios() {
		String queryString = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> query = em.createQuery(queryString, Usuario.class);
		
		try {
			List<Usuario> usuarios = query.getResultList();
		    List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setCi(usuario.getCi());
                usuarioDTO.setEmail(usuario.getEmail());
                usuarioDTO.setFechaNac(usuario.getFechaNac());
                usuarioDTO.setUsername(usuario.getUsername());
                if(usuario.isStatus()){
                	usuarioDTO.setStatus("Activo");
                }else {
                	usuarioDTO.setStatus("Inactivo");
                }
                if(usuario instanceof AdminEmpresa) {
                    AdminEmpresa aux = (AdminEmpresa) usuario;
                    usuarioDTO.setEmpresa(aux.getEmpresa().getNombreEmpresa());
                    usuarioDTO.setTipoUsuario("AdminEmpresa");
                }else if(usuario instanceof Conductor) {
                    Conductor aux = (Conductor) usuario;
                    usuarioDTO.setEmpresa(aux.getEmpresa().getNombreEmpresa());
                    usuarioDTO.setTipoUsuario("Conductor");
                }else if(usuario instanceof Administrador) {
                    usuarioDTO.setTipoUsuario("Administrador");
                }else if(usuario instanceof Autoridad) {
                    usuarioDTO.setTipoUsuario("Autoridad");
                }else if(usuario instanceof Funcionario) {
                    usuarioDTO.setTipoUsuario("Funcionario");
                }
                usuariosDTO.add(usuarioDTO);
            }
		    return usuariosDTO;
		}catch (NoResultException e) {
	    	 return null;
		       // throw new NoResultException("No se encontró ningún usuario con la CI proporcionada: " + ci);
		    }
	}
	
	public List<UsuarioDTO> getConductoresDisponibles(String empresa) {
		//Retorna solo los usuarios con todos los viajes finalizados
		//Falta contemplar que sean de la empresa que es el admin empresa que esta logueado
		Empresa ee = empresaDao.obtenerEmpresa(empresa);
		String queryString = "SELECT * FROM Usuario u Where dtype = 'Conductor' and u.empresa_id ="+ee.getNroEmpresa()+" and NOT EXISTS(SELECT * FROM viaje v WHERE v.conductor_id = u.ci AND v.estado <> 'Finalizado') AND status = 'true'";
		Query query = em.createNativeQuery(queryString, Usuario.class);		
		try {
			List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		    List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		    for (Usuario usuario : usuarios) {
		    	UsuarioDTO usuarioDTO = new UsuarioDTO();
		    	usuarioDTO.setCi(usuario.getCi());
		    	usuarioDTO.setEmail(usuario.getEmail());
		    	usuarioDTO.setFechaNac(usuario.getFechaNac());
		    	usuarioDTO.setUsername(usuario.getUsername());
		    	usuariosDTO.add(usuarioDTO);
		    }
		    return usuariosDTO;
		}catch (NoResultException e) {
	    	 return null;
		       // throw new NoResultException("No se encontró ningún usuario con la CI proporcionada: " + ci);
		    }
	}
	
	public boolean esConductor(String ci) throws NoResultException{
		Usuario u = obtenerUsuario(ci);
		if(u instanceof Conductor) {
			return true;
		}
		return false;
	}
	
	@Override
	public void updateUsuario(String ci, String username, String email, String fechaNac) throws NoResultException {
		Usuario res = this.obtenerUsuario(ci);
		res.setUsername(username);
		res.setEmail(email);
		res.setFechaNac(fechaNac);
		em.persist(res);
	}
	
	

//LOGIN BACKOFFICE
	    
    public UserBackoffice registroBackoffice(String ci, String username, String email, String fechaNac, String password)throws NoResultException {
    	try {
    		Usuario usuario = this.obtenerUsuario(ci);
    		if (usuario != null) {
                String contraseñaCifrada = cifrarContraseña(password);
                UserBackoffice user = new UserBackoffice(ci, username, email, fechaNac, contraseñaCifrada);
                user.toString();
                return user;
    		}
    	}catch(NoResultException e) {
    		System.out.println("No se pudo crear el usuario de backoffice");
    		return null;
    	}
		return null;
    }
    
    
	    // Método para cifrar una contraseña con hash y salt
	    public String cifrarContraseña(String contraseña) throws NoResultException {
	        String salt = generarSalt(); // Generar un salt aleatorio
	        
	        // Concatenar la contraseña con el salt
	        String contraseñaConSalt = contraseña + salt;
	        
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashBytes = md.digest(contraseñaConSalt.getBytes());
	            
	            // Convertir los bytes del hash a una representación legible (por ejemplo, Base64)
	            String contraseñaCifrada = Base64.getEncoder().encodeToString(hashBytes);
	            return contraseñaCifrada;
	        } catch (NoSuchAlgorithmException e) {
	        	return null;
	        }
	    }
	    
	    // Método para generar un salt aleatorio
	    public String generarSalt()throws NoResultException{
	        SecureRandom random = new SecureRandom();
	        byte[] saltBytes = new byte[16];
	        random.nextBytes(saltBytes);
	        return Base64.getEncoder().encodeToString(saltBytes);
	    }
	    
	    
		@Override
		public BackofficeDTO login(String ci, String pass) {
			Usuario u = obtenerUsuario(ci);
			if(u == null) {
				return null;
			}else {
				if(u instanceof Administrador) {
					Administrador ad = (Administrador) u;
					 if (BCrypt.checkpw(pass, ad.getPassword())) {
						 BackofficeDTO ret = new BackofficeDTO(ad.getCi(),ad.getEmail(),ad.getFechaNac(),ad.getUsername(),"Administrador");
						 return ret;
					 }
				}else if(u instanceof Autoridad) {
					Autoridad au = (Autoridad) u;
					 if (BCrypt.checkpw(pass, au.getPassword())) {
						 BackofficeDTO ret = new BackofficeDTO(au.getCi(),au.getEmail(),au.getFechaNac(),au.getUsername(),"Autoridad");
						 return ret;
					 }
				}
				return null;
			}
		}
		
		@Override
		public List<GraficasDTO> UsuariosTipos() {
		    String queryString = "SELECT u.dtype AS data, COUNT(u.ci) AS cantidad "
		            + "FROM usuario u WHERE u.status = 'true'"
		            + "GROUP BY u.dtype";
		    try {
		        Query query = em.createNativeQuery(queryString, "GraficasDTO");
		        List<GraficasDTO> ret = query.getResultList();
		        return ret;
		    } catch (NoResultException e) {
		        throw new NoResultException("No se pudo listar las empresas");
		    }
		}
		
}

