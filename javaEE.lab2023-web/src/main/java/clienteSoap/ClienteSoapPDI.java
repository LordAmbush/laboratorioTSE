package clienteSoap;

import uy.edu.fing.tse.demo2023.services.soap.NodoPDIService;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceLocator;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDI;
import uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub;
import uy.edu.fing.tse.demo2023.services.soap.Persona;

public class ClienteSoapPDI {

	public static void main(String[] args) throws MalformedURLException, RemoteException {
		// TODO Auto-generated method stub

		//obtener url a donde me voy a conectar
		NodoPDIService servicio = new NodoPDIServiceLocator();
		
		//Creo el objeto que me permite conectar a las operaciones
		NodoPDI ws = new NodoPDIServiceSoapBindingStub(new URL(servicio.getNodoPDIPortAddress()), servicio);
		
		Persona p = ws.obtenerDatosPersona("49536781");
		
		System.out.println(p.getFechaNac());
	}

}