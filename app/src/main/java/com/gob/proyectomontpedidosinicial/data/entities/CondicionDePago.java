package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public  class CondicionDePago implements Serializable {


    private String nombre_condicion;
    private String id_condicion;

    public String getNombre_condicion() {
        return nombre_condicion;
    }

    public void setNombre_condicion(String nombre_condicion) {
        this.nombre_condicion = nombre_condicion;
    }

    public String getId_condicion() {
        return id_condicion;
    }

    public void setId_condicion(String id_condicion) {
        this.id_condicion = id_condicion;
    }



    @Override
    public String toString() {
        return "CondicionDePago{" +
                "nombre_condicion='" + nombre_condicion + '\'' +
                ", id_condicion='" + id_condicion + '\'' +
                '}';
    }


}
