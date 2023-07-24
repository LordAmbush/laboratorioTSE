package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;


@Entity
public class UserBackoffice extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
	private String password;

    public UserBackoffice() {
        super();
        // Constructor sin argumentos de UserBackoffice
    }

    public UserBackoffice(String ci, String username, String email, String fechaNac, String password) {
        super(ci, username, email, fechaNac);
        this.password = password;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
