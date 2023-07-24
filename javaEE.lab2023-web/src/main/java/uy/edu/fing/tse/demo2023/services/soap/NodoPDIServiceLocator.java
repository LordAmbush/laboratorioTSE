/**
 * NodoPDIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uy.edu.fing.tse.demo2023.services.soap;

public class NodoPDIServiceLocator extends org.apache.axis.client.Service implements uy.edu.fing.tse.demo2023.services.soap.NodoPDIService {

    public NodoPDIServiceLocator() {
    }


    public NodoPDIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NodoPDIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NodoPDIPort
    private java.lang.String NodoPDIPort_address = "http://localhost:8080/demo2023-services/NodoPDI";

    public java.lang.String getNodoPDIPortAddress() {
        return NodoPDIPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NodoPDIPortWSDDServiceName = "NodoPDIPort";

    public java.lang.String getNodoPDIPortWSDDServiceName() {
        return NodoPDIPortWSDDServiceName;
    }

    public void setNodoPDIPortWSDDServiceName(java.lang.String name) {
        NodoPDIPortWSDDServiceName = name;
    }

    public uy.edu.fing.tse.demo2023.services.soap.NodoPDI getNodoPDIPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NodoPDIPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNodoPDIPort(endpoint);
    }

    public uy.edu.fing.tse.demo2023.services.soap.NodoPDI getNodoPDIPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub _stub = new uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getNodoPDIPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNodoPDIPortEndpointAddress(java.lang.String address) {
        NodoPDIPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uy.edu.fing.tse.demo2023.services.soap.NodoPDI.class.isAssignableFrom(serviceEndpointInterface)) {
                uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub _stub = new uy.edu.fing.tse.demo2023.services.soap.NodoPDIServiceSoapBindingStub(new java.net.URL(NodoPDIPort_address), this);
                _stub.setPortName(getNodoPDIPortWSDDServiceName());
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
        if ("NodoPDIPort".equals(inputPortName)) {
            return getNodoPDIPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.services.demo2023.tse.fing.edu.uy/", "NodoPDIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.services.demo2023.tse.fing.edu.uy/", "NodoPDIPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NodoPDIPort".equals(portName)) {
            setNodoPDIPortEndpointAddress(address);
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
