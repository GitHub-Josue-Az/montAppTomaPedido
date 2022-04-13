package com.gob.proyectomontpedidosinicial.data.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(tableName = Constans.NAME_TABLE_ENTITY_PRODUCTO_USUARIO)
public class EntityProductoPorUsuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int productouid;
     @ColumnInfo(name = "estado")
    private String estado;
     @ColumnInfo(name = "fecha_creacion")
    private String fecha_creacion;
     @ColumnInfo(name = "fecha_update")
    private String fecha_update;
     @ColumnInfo(name = "codigo")
    private String codigo;
     @ColumnInfo(name = "nombre_corto")
    private String nombre_corto;
     @ColumnInfo(name = "nombre_completo")
    private String nombre_completo;
     @ColumnInfo(name = "id_producto")
    private String id_producto;
     @ColumnInfo(name = "subtotal")
    public String subtotal;
     @ColumnInfo(name = "promocion")
    public String promocion;
     @ColumnInfo(name = "costo")
    public double costo;
     @ColumnInfo(name = "cantidad")
    public int cantidad;



    public int getProductouid() {
        return productouid;
    }

    public void setProductouid(int productouid) {
        this.productouid = productouid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(String fecha_update) {
        this.fecha_update = fecha_update;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
