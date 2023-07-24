/**
 * NodoBalanzaServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uy.edu.fing.tse.demo2023.services.soap;

public class NodoBalanzaServiceLocator extends org.apache.axis.client.Service implements uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaService {

    public NodoBalanzaServiceLocator() {
    }


    public NodoBalanzaServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NodoBalanzaServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NodoBalanzaPort
    private java.lang.String NodoBalanzaPort_address = "http://localhost:8080/demo2023-services/NodoBalanza";

    public java.lang.String getNodoBalanzaPortAddress() {
        return NodoBalanzaPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NodoBalanzaPortWSDDServiceName = "NodoBalanzaPort";

    public java.lang.String getNodoBalanzaPortWSDDServiceName() {
        return NodoBalanzaPortWSDDServiceName;
    }

    public void setNodoBalanzaPortWSDDServiceName(java.lang.String name) {
        NodoBalanzaPortWSDDServiceName = name;
    }

    public uy.edu.fing.tse.demo2023.services.soap.NodoBalanza getNodoBalanzaPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NodoBalanzaPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNodoBalanzaPort(endpoint);
    }

    public uy.edu.fing.tse.demo2023.services.soap.NodoBalanza getNodoBalanzaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceSoapBindingStub _stub = new uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getNodoBalanzaPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNodoBalanzaPortEndpointAddress(java.lang.String address) {
        NodoBalanzaPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uy.edu.fing.tse.demo2023.services.soap.NodoBalanza.class.isAssignableFrom(serviceEndpointInterface)) {
                uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceSoapBindingStub _stub = new uy.edu.fing.tse.demo2023.services.soap.NodoBalanzaServiceSoapBindingStub(new java.net.URL(NodoBalanzaPort_address), this);
                _stub.setPortName(getNodoBalanzaPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("NodoBalanzaPort".equals(inputPortName)) {
            return getNodoBalanzaPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.services.demo2023.tse.fing.edu.uy/", "NodoBalanzaService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.services.demo2023.tse.fing.edu.uy/", "NodoBalanzaPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NodoBalanzaPort".equals(portName)) {
            setNodoBalanzaPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
