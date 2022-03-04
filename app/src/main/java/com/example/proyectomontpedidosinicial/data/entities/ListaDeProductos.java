package com.example.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class ListaDeProductos implements Serializable {


    public int id;


    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double subtotal;



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

    public String producto;

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String promocion;
    public double costo;
    public int cantidad;



    @Override
    public String toString() {
        return "ListaDeProductos{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", promocion=" + promocion +
                ", costo=" + costo +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }







}
