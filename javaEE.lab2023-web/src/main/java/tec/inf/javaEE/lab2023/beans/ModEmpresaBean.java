package tec.inf.javaEE.lab2023.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named("modEmpresaBean")
@RequestScoped
public class ModEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    @ManagedProperty("#{param.numero}")
    private int numero;

	@Inject
    @ManagedProperty("#{param.direccion}")
    private String direccion;

	@Inject
    @ManagedProperty("#{param.nombre}")
    private String nombre;

	@Inject
    @ManagedProperty("#{param.rsocial}")
    private String rsocial;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRsocial() {
        return rsocial;
    }

    public void setRsocial(String rsocial) {
        this.rsocial = rsocial;
    }

    public void init() {
        // Access the query parameters and do any additional processing if needed
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String numeroParam = params.get("numero");
        String direccionParam = params.get("direccion");
        String nombreParam = params.get("nombre");
        String rsocialParam = params.get("rsocial");
    
        //TERMINAR ESTE CU?

        // Set the values to the bean properties
        //numero = Integer.parseInt(numeroParam);
        //direccion = direccionParam;
        //nombre = nombreParam;
        //rsocial = rsocialParam;
    }
}