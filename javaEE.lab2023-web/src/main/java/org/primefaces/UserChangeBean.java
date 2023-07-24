package org.primefaces;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ManagedBean
@ViewScoped
public class UserChangeBean {
	
    private String primerValor;
    private boolean mostrarSegundoCombo;
    
    
	
    public String getPrimerValor() {
		return primerValor;
	}



	public void setPrimerValor(String primerValor) {
		this.primerValor = primerValor;
	}



	public boolean isMostrarSegundoCombo() {
		return mostrarSegundoCombo;
	}



	public void setMostrarSegundoCombo(boolean mostrarSegundoCombo) {
		this.mostrarSegundoCombo = mostrarSegundoCombo;
	}



	public void comboChange() {
        if ("3".equals(primerValor) || "4".equals(primerValor)) {
            mostrarSegundoCombo = true;
        } else {
            mostrarSegundoCombo = false;
        }
    }
}
