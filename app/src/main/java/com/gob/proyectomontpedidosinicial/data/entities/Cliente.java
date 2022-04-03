package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class Cliente implements Serializable {


    private String direccion;
    private String fecha_update;
    private String fecha_creacion;

    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                ", fecha_update='" + fecha_update + '\'' +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", estado_sincronizacion='" + estado_sincronizacion + '\'' +
                ", gps_longitud='" + gps_longitud + '\'' +
                ", gps_latitud='" + gps_latitud + '\'' +
                ", zona_venta='" + zona_venta + '\'' +
                ", codigo_vendedor='" + codigo_vendedor + '\'' +
                ", correo_cliente='" + correo_cliente + '\'' +
                ", telefono_cliente='" + telefono_cliente + '\'' +
                ", coa_cliente='" + coa_cliente + '\'' +
                ", razon_social='" + razon_social + '\'' +
                ", id_cliente='" + id_cliente + '\'' +
                '}';
    }

    private String estado_sincronizacion;
    private String gps_longitud;
    private String gps_latitud;
    private String zona_venta;
    private String codigo_vendedor;
    private String correo_cliente;
    private String telefono_cliente;
    private String coa_cliente;
    private String razon_social;
    private String id_cliente;


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(String fecha_update) {
        this.fecha_update = fecha_update;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado_sincronizacion() {
        return estado_sincronizacion;
    }

    public void setEstado_sincronizacion(String estado_sincronizacion) {
        this.estado_sincronizacion = estado_sincronizacion;
    }

    public String getGps_longitud() {
        return gps_longitud;
    }

    public void setGps_longitud(String gps_longitud) {
        this.gps_longitud = gps_longitud;
    }

    public String getGps_latitud() {
        return gps_latitud;
    }

    public void setGps_latitud(String gps_latitud) {
        this.gps_latitud = gps_latitud;
    }

    public String getZona_venta() {
        return zona_venta;
    }

    public void setZona_venta(String zona_venta) {
        this.zona_venta = zona_venta;
    }

    public String getCodigo_vendedor() {
        return codigo_vendedor;
    }

    public void setCodigo_vendedor(String codigo_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getCoa_cliente() {
        return coa_cliente;
    }

    public void setCoa_cliente(String coa_cliente) {
        this.coa_cliente = coa_cliente;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }


}
