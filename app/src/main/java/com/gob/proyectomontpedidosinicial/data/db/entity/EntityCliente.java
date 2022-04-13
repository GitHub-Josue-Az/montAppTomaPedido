package com.gob.proyectomontpedidosinicial.data.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;

@Entity(tableName = Constans.NAME_TABLE_ENTITY_CLIENTE)
public class EntityCliente {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "direccion")
    private String direccion;
      @ColumnInfo(name = "fecha_update")
    private String fecha_update;
      @ColumnInfo(name = "fecha_creacion")
    private String fecha_creacion;
      @ColumnInfo(name = "estado_sincronizacion")
    private String estado_sincronizacion;
      @ColumnInfo(name = "gps_longitud")
    private String gps_longitud;
      @ColumnInfo(name = "gps_latitud")
    private String gps_latitud;
      @ColumnInfo(name = "zona_venta")
    private String zona_venta;
      @ColumnInfo(name = "codigo_vendedor")
    private String codigo_vendedor;
      @ColumnInfo(name = "correo_cliente")
    private String correo_cliente;
      @ColumnInfo(name = "telefono_cliente")
    private String telefono_cliente;
      @ColumnInfo(name = "coa_cliente")
    private String coa_cliente;
      @ColumnInfo(name = "razon_social")
    private String razon_social;
      @ColumnInfo(name = "id_cliente")
    private String id_cliente;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
