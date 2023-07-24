package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;


@Entity
public class Funcionario extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public Funcionario(String ci, String username, String email, String fechaNac) {
    	super(ci, username, email, fechaNac);	
    	}
    public Funcionario() {
    	super();    
    	}
    
	@Override
	public String toString() {
		return "Funcionario :" + super.toString() + "";
	}
}
