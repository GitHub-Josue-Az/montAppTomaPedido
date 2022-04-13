package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class TipoDeCliente implements Serializable {


    private String nombre_tipo_cliente;
    private String id_tipo_cliente;

    public String getNombre_tipo_cliente() {
        return nombre_tipo_cliente;
    }

    public void setNombre_tipo_cliente(String nombre_tipo_cliente) {
        this.nombre_tipo_cliente = nombre_tipo_cliente;
    }

    public String getId_tipo_cliente() {
        return id_tipo_cliente;
    }

    public void setId_tipo_cliente(String id_tipo_cliente) {
        this.id_tipo_cliente = id_tipo_cliente;
    }

    @Override
    public String toString() {
        return "TipoDeCliente{" +
                "nombre_tipo_cliente='" + nombre_tipo_cliente + '\'' +
                ", id_tipo_cliente='" + id_tipo_cliente + '\'' +
                '}';
    }
}
