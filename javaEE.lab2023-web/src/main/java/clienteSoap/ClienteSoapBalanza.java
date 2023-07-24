package clienteSoap;

import uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaService;
import uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceLocator;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;

import uy.edu.fing.tse.demo2023.services.soap.NodoBalanza;
import uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceSoapBindingStub;

public class ClienteSoapBalanza {

	public static void main(String[] args) throws MalformedURLException, RemoteException {
		// TODO Auto-generated method stub

		//obtener url a donde me voy a conectar
		NodoBalanzaService servicio = new NodoBalanzaServiceLocator();
		
		//Creo el objeto que me permite conectar a las operaciones
		NodoBalanza ws = new NodoBalanzaServiceSoapBindingStub(new URL(servicio.getNodoBalanzaPortAddress()), servicio);
		
		
		String[] array = ws.obtenerPeso(7);
		
		System.out.println(Arrays.toString(array));
	}

}
