/**
 * Empresa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uy.edu.fing.tse.demo2023.services.soap;

public class Empresa  implements java.io.Serializable {
    private java.lang.Integer cantEmpleados;

    private java.lang.String direccion;

    private java.lang.String nombre;

    private java.lang.String nombreFantasia;

    private java.lang.String rut;

    private java.lang.String telefono;

    public Empresa() {
    }

    public Empresa(
           java.lang.Integer cantEmpleados,
           java.lang.String direccion,
           java.lang.String nombre,
           java.lang.String nombreFantasia,
           java.lang.String rut,
           java.lang.String telefono) {
           this.cantEmpleados = cantEmpleados;
           this.direccion = direccion;
           this.nombre = nombre;
           this.nombreFantasia = nombreFantasia;
           this.rut = rut;
           this.telefono = telefono;
    }


    /**
     * Gets the cantEmpleados value for this Empresa.
     * 
     * @return cantEmpleados
     */
    public java.lang.Integer getCantEmpleados() {
        return cantEmpleados;
    }


    /**
     * Sets the cantEmpleados value for this Empresa.
     * 
     * @param cantEmpleados
     */
    public void setCantEmpleados(java.lang.Integer cantEmpleados) {
        this.cantEmpleados = cantEmpleados;
    }


    /**
     * Gets the direccion value for this Empresa.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this Empresa.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the nombre value for this Empresa.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Empresa.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the nombreFantasia value for this Empresa.
     * 
     * @return nombreFantasia
     */
    public java.lang.String getNombreFantasia() {
        return nombreFantasia;
    }


    /**
     * Sets the nombreFantasia value for this Empresa.
     * 
     * @param nombreFantasia
     */
    public void setNombreFantasia(java.lang.String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }


    /**
     * Gets the rut value for this Empresa.
     * 
     * @return rut
     */
    public java.lang.String getRut() {
        return rut;
    }


    /**
     * Sets the rut value for this Empresa.
     * 
     * @param rut
     */
    public void setRut(java.lang.String rut) {
        this.rut = rut;
    }


    /**
     * Gets the telefono value for this Empresa.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this Empresa.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Empresa)) return false;
        Empresa other = (Empresa) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cantEmpleados==null && other.getCantEmpleados()==null) || 
             (this.cantEmpleados!=null &&
              this.cantEmpleados.equals(other.getCantEmpleados()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.nombreFantasia==null && other.getNombreFantasia()==null) || 
             (this.nombreFantasia!=null &&
              this.nombreFantasia.equals(other.getNombreFantasia()))) &&
            ((this.rut==null && other.getRut()==null) || 
             (this.rut!=null &&
              this.rut.equals(other.getRut()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCantEmpleados() != null) {
            _hashCode += getCantEmpleados().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getNombreFantasia() != null) {
            _hashCode += getNombreFantasia().hashCode();
        }
        if (getRut() != null) {
            _hashCode += getRut().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Empresa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.services.demo2023.tse.fing.edu.uy/", "empresa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantEmpleados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantEmpleados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreFantasia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreFantasia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
