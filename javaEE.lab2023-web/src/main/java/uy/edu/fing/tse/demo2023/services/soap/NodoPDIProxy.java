package uy.edu.fing.tse.demo2023.services.soap;

public class NodoPDIProxy implements uy.edu.fing.tse.demo2023.services.soap.NodoPDI {
  private String _endpoint = null;
  private uy.edu.fing.tse.demo2023.services.soap.NodoPDI nodoPDI = null;
  
  public NodoPDIProxy() {
    _initNodoPDIProxy();
  }
  
  public NodoPDIProxy(String endpoint) {
    _endpoint = endpoint;
    _initNodoPDIProxy();
  }
  
  private void _initNodoPDIProxy() {
    try {
      nodoPDI = (new uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceLocator()).getNodoPDIPort();
      if (nodoPDI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)nodoPDI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)nodoPDI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (nodoPDI != null)
      ((javax.xml.rpc.Stub)nodoPDI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public uy.edu.fing.tse.demo2023.services.soap.NodoPDI getNodoPDI() {
    if (nodoPDI == null)
      _initNodoPDIProxy();
    return nodoPDI;
  }
  
  public uy.edu.fing.tse.demo2023.services.soap.Empresa obtenerDatosEmpresa(java.lang.String arg0) throws java.rmi.RemoteException{
    if (nodoPDI == null)
      _initNodoPDIProxy();
    return nodoPDI.obtenerDatosEmpresa(arg0);
  }
  
  public uy.edu.fing.tse.demo2023.services.soap.Persona obtenerDatosPersona(java.lang.String arg0) throws java.rmi.RemoteException{
    if (nodoPDI == null)
      _initNodoPDIProxy();
    return nodoPDI.obtenerDatosPersona(arg0);
  }
  
  
}