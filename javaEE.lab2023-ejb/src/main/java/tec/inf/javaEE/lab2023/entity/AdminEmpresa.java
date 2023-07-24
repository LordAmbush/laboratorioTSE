package tec.inf.javaEE.lab2023.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class AdminEmpresa extends Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
	
    public AdminEmpresa(String ci, String username, String email, String fechaNac, Empresa e) {
    	super(ci, username, email, fechaNac);	
    	this.empresa = e;
    	}
    public AdminEmpresa() {
    	super();    
    	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "AdminEmpresa :" + super.toString() + "";
	}

}
