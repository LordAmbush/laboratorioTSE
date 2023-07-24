package tec.inf.javaEE.lab2023.business.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.business.UsuarioBusinessLocal;
import tec.inf.javaEE.lab2023.dao.UsuarioDaoLocal;
import tec.inf.javaEE.lab2023.dto.BackofficeDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.entity.AdminEmpresa;
import tec.inf.javaEE.lab2023.entity.Administrador;
import tec.inf.javaEE.lab2023.entity.Autoridad;
import tec.inf.javaEE.lab2023.entity.Conductor;
import tec.inf.javaEE.lab2023.entity.Funcionario;
import tec.inf.javaEE.lab2023.entity.Usuario;
@Stateless
@Named("usuarioBusiness")
public class UsuarioBusiness implements UsuarioBusinessLocal{

	@EJB
	UsuarioDaoLocal usuarioDao;
	
	@Override
	public void altaUsuario(String ci, String username, String email, String fechaNac, String tipoUser, String pass, String empresa) throws Exception {
    		Usuario user = usuarioDao.obtenerUsuario(ci);
    		if (user == null) {
    			usuarioDao.altaUsuario(ci, username, email, fechaNac,tipoUser, pass, empresa);
    		}else {
    			throw new Exception("No se pudo crear usuario");
    		}
    	}

	@Override
	public void updateUsuario(String ci, String username, String email, String fechaNac) throws Exception {
    	try {
    		Usuario user = usuarioDao.obtenerUsuario(ci);
    		if (user != null) {
    			usuarioDao.updateUsuario(ci, username, email, fechaNac);
    		}else {
    			System.out.println("El usuario de ci"+ ci +"no se encuentra en el sistema.");
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean esConductor(String ci) {
		return usuarioDao.esConductor(ci);
	}

	@Override
	public void deleteUsuario(String ci) throws Exception {
		try {
			usuarioDao.eliminarUsuario(ci);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UsuarioDTO> getUsuarios() {
		List<UsuarioDTO> usuarios = usuarioDao.getUsuarios();
		if (usuarios != null) {
			for(UsuarioDTO user : usuarios) {
				System.out.println(user.toString());
			}
			return usuarios;
		}else {
			return null;
		}
	}
	
	public List<UsuarioDTO> getConductores(String empresa) {
		List<UsuarioDTO> usuarios = usuarioDao.getConductoresDisponibles(empresa);
		if (usuarios != null) {
			return usuarios;
		}else {
			return null;
		}
	}

	@Override
	public UsuarioDTO obtenerUsuario(String ci) {
	    String tipoUser = "";
	    String empresa = null;
	    Usuario user = usuarioDao.obtenerUsuario(ci);
	    if (user == null) {
	    	return null;
	    } else {
	        if (user instanceof Administrador) {
	            tipoUser = "Administrador";
	        } else if (user instanceof Autoridad) {
	            tipoUser = "Autoridad";
	        } else if (user instanceof Conductor) {
	            tipoUser = "Conductor";
	        } else if (user instanceof Funcionario) {
	            tipoUser = "Funcionario";
	        } else if (user instanceof AdminEmpresa) {
	            tipoUser = "AdminEmpresa";
	            AdminEmpresa adminAux = (AdminEmpresa) user;
	            empresa = adminAux.getEmpresa().getNombreEmpresa();
	        }
	        UsuarioDTO ret = new UsuarioDTO(user.getCi(), user.getEmail(), user.getFechaNac(), user.getUsername(),tipoUser);
	        ret.setEmpresa(empresa);
	        return ret;
	    }
	}


	@Override
	public BackofficeDTO login(String ci, String pass) {
		BackofficeDTO res = usuarioDao.login(ci, pass);
		return res;
	}
}

