package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import javax.persistence.NoResultException;

import tec.inf.javaEE.lab2023.dto.BackofficeDTO;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;
import tec.inf.javaEE.lab2023.entity.UserBackoffice;
import tec.inf.javaEE.lab2023.entity.Usuario;

public interface UsuarioDaoLocal {

		void altaUsuario(String ci, String username, String email, String fechaNac, String tipoUser, String pass, String empresa) throws NoResultException;
		void eliminarUsuario(String ci) throws NoResultException;
		void updateUsuario(String ci, String username, String email, String fechaNac) throws NoResultException;
		
		Usuario obtenerUsuario(String ci);
		
		boolean esConductor(String ci) throws NoResultException;
		
		List<UsuarioDTO> getUsuarios()throws NoResultException;
		List<UsuarioDTO> getConductoresDisponibles(String empresa)throws NoResultException;
		String generarSalt() throws NoResultException;
		String cifrarContraseña(String contraseña) throws NoResultException;
		UserBackoffice registroBackoffice(String ci, String username, String email, String fechaNac, String password)throws NoResultException;
		BackofficeDTO login(String user, String pass);
		List<GraficasDTO> UsuariosTipos();
	}
