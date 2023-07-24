package uy.edu.fing.tse.demo2023.services.soap;

public class NodoBalanzaProxy implements uy.edu.fing.tse.demo2023.services.soap.NodoBalanza {
  private String _endpoint = null;
  private uy.edu.fing.tse.demo2023.services.soap.NodoBalanza nodoBalanza = null;
  
  public NodoBalanzaProxy() {
    _initNodoBalanzaProxy();
  }
  
  public NodoBalanzaProxy(String endpoint) {
    _endpoint = endpoint;
    _initNodoBalanzaProxy();
  }
  
  private void _initNodoBalanzaProxy() {
    try {
      nodoBalanza = (new uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceLocator()).getNodoBalanzaPort();
      if (nodoBalanza != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)nodoBalanza)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)nodoBalanza)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (nodoBalanza != null)
      ((javax.xml.rpc.Stub)nodoBalanza)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public uy.edu.fing.tse.demo2023.services.soap.NodoBalanza getNodoBalanza() {
    if (nodoBalanza == null)
      _initNodoBalanzaProxy();
    return nodoBalanza;
  }
  
  public java.lang.String[] obtenerPeso(int arg0) throws java.rmi.RemoteException{
    if (nodoBalanza == null)
      _initNodoBalanzaProxy();
    return nodoBalanza.obtenerPeso(arg0);
  }
  
  
}