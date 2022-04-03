package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class ListaDeClientes implements Serializable {


    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String nombreCliente;
    public String ruc;

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    private String documentoCliente;


    @Override
    public String toString() {
        return "ListaDeClientes{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", documentoCliente='" + documentoCliente + '\'' +
                ", ruc='" + ruc + '\'' +
                '}';
    }



}
