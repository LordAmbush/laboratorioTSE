package tec.inf.javaEE.lab2023.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tec.inf.javaEE.lab2023.business.BalanzaBusinessLocal;
import tec.inf.javaEE.lab2023.business.UsuarioBusinessLocal;
import tec.inf.javaEE.lab2023.business.ViajeBusinessLocal;
import tec.inf.javaEE.lab2023.dto.ViajeDTO;
import uy.edu.fing.tse.demo2023.services.soap.NodoBalanza;
import uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaService;
import uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceLocator;
import uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceSoapBindingStub;


@RequestScoped
@Path("/api")
@Produces("application/json")
@Consumes("application/json")
public class RestAPI {

	@EJB
	ViajeBusinessLocal serviceViaje;
	@EJB
	UsuarioBusinessLocal serviceUsuario;
	@EJB
	BalanzaBusinessLocal serviceBalanza;
	

	
	@GET
	@Path("/viaje/{ci}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLastViajeByCi(@PathParam("ci") String ci) throws InterruptedException, ExecutionException {
		
		try {
			ViajeDTO viaje = serviceViaje.consultarEstadoViaje(ci);
			return Response.status(Response.Status.OK).entity(viaje).build();
			
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} 
	}
	
	
	@GET
	@Path("/updateEstadoViaje/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEstadoViaje(@PathParam("id") int id) throws MalformedURLException, RemoteException {
		
		try {
			boolean fin = serviceViaje.updateEstadoViaje(id);
			if(fin) {
				//obtener url a donde me voy a conectar
				NodoBalanzaService servicio = new NodoBalanzaServiceLocator();
				
				//Creo el objeto que me permite conectar a las operaciones
				NodoBalanza ws = new NodoBalanzaServiceSoapBindingStub(new URL(servicio.getNodoBalanzaPortAddress()), servicio);
				
				String[] array = ws.obtenerPeso(id);
								
				serviceBalanza.agregarPesada(id, array);
			}
			return Response.status(Response.Status.OK).build();
			
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} 
	}
	
	@GET
	@Path("/esConductor/{ci}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEstadoViaje(@PathParam("ci") String ci) {
		
		try {
			if(serviceUsuario.esConductor(ci)) {
				return Response.status(Response.Status.OK).build();
			}
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} 
	}
	
	/*@PUT
	@Path("/iniciativas/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateIniciativa(@PathParam("id") int id, 
			@FormParam("nombre") String nombre,
			@FormParam("descripcion") String descripcion,
			@FormParam("presupuesto") double presupuesto) {
		
		IniciativaDTO iniciativa = new IniciativaDTO(id, nombre, descripcion, presupuesto);
		int updated = service.actualizarIniciativa(iniciativa);
		
		if (updated == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(404).build();
		}
	}*/

}
