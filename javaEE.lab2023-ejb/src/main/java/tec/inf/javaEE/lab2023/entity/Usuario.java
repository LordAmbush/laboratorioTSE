package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String ci;
	private String email;
	private String fechaNac;
	private String username;
	private boolean status;
	public Usuario() {
		super();
	}
	public Usuario(String ci, String username, String email, String fechaNac) {
		super();
		this.ci = ci;
		this.username = username;
		this.email = email;
		this.fechaNac = fechaNac;
		this.status = true;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Override
	public String toString() {
		return "Usuario [ci=" + ci + ", email=" + email + ", fechaNac=" + fechaNac + ", username=" + username + "]";
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
