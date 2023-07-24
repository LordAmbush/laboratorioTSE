package tec.inf.javaEE.lab2023.business;


import java.util.List;

import tec.inf.javaEE.lab2023.dto.BackofficeDTO;
import tec.inf.javaEE.lab2023.dto.UsuarioDTO;

public interface UsuarioBusinessLocal {

	boolean esConductor(String ci);

	void altaUsuario(String ci, String username, String email, String fechaNac, String tipoUser, String pass, String empresa) throws Exception;
	void updateUsuario(String ci, String username, String email, String fechaNac) throws Exception;
	void deleteUsuario(String ci) throws Exception;
	
	UsuarioDTO obtenerUsuario(String ci);
	List<UsuarioDTO> getUsuarios();
	List<UsuarioDTO> getConductores(String empresa);

	BackofficeDTO login(String user, String pass);
	
	
}
