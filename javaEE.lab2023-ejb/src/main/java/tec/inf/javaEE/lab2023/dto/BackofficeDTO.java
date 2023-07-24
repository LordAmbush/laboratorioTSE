package tec.inf.javaEE.lab2023.dto;

import javax.persistence.NoResultException;

public class BackofficeDTO extends UsuarioDTO {
	private String rol;

	public BackofficeDTO() {
		super();
	}

	public BackofficeDTO(String ci, String email, String fechaNac, String username, String rol) throws NoResultException {
		super(ci, email, fechaNac, username, rol);
	}
	
	
}
