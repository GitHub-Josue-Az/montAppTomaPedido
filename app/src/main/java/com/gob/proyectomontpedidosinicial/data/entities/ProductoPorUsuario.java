package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductoPorUsuario implements Serializable {


    private String estado;
    private String fecha_creacion;
    private String fecha_update;
    private String codigo;
    private String nombre_corto;
    private String nombre_completo;
    private String id_producto;
    public BigDecimal subtotal;
    public String promocion;
    public double costo;
    public int cantidad;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
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


    @Override
    public String toString() {
        return "ProductoPorUsuario{" +
                "estado='" + estado + '\'' +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", fecha_update='" + fecha_update + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nombre_corto='" + nombre_corto + '\'' +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", id_producto='" + id_producto + '\'' +
                ", subtotal=" + subtotal +
                ", promocion='" + promocion + '\'' +
                ", costo=" + costo +
                ", cantidad=" + cantidad +
                '}';
    }


}
