package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class ListaDePedidos implements Serializable {

    private  int id;
    private String producto;
    private String total;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    private int estado;
    private String cliente;
    private String fecha;


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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }




    @Override
    public String toString() {
        return "ListaDePedidos{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", total='" + total + '\'' +
                ", estado='" + estado + '\'' +
                ", cliente='" + cliente + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
