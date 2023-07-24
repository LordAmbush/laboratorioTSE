package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Administrador extends UserBackoffice implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Administrador(String ci, String username, String email, String fechaNac, String password) {
        super(ci, username, email, fechaNac, password);
    }
    public Administrador() {
    	super();    
    	}
    
    
	@Override
	public String toString() {
		return "Administrador :" + super.toString() + "";
	}

}
