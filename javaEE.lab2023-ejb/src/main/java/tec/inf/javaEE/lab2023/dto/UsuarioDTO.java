package tec.inf.javaEE.lab2023.dto;

import javax.persistence.NoResultException;


public class UsuarioDTO {
	
    private String empresa;
    private String tipoUsuario;
	private String ci;
	private String email;
	private String fechaNac;
	private String username;
	private String rol;
	private String status;
	
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String ci, String email, String fechaNac, String username, String rol)throws NoResultException  {
		super();
		this.ci = ci;
		this.email = email;
		this.fechaNac = fechaNac;
		this.username = username;
		this.rol = rol;
	}

	

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [ci=" + ci + ", email=" + email + ", fechaNac=" + fechaNac + ", username=" + username + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
