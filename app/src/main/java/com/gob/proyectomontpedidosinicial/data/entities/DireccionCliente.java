package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public  class DireccionCliente implements Serializable {


    private String descripcion_direccion;
    private String estado_direccion;
    private String tipo_direccion;
    private String departamento_direccion;
    private String provincia_direccion;
    private String distrito_direccion;
    private String clientes_id_cliente;
    private String id_direccion;

    public String getDescripcion_direccion() {
        return descripcion_direccion;
    }

    public void setDescripcion_direccion(String descripcion_direccion) {
        this.descripcion_direccion = descripcion_direccion;
    }

    public String getEstado_direccion() {
        return estado_direccion;
    }

    public void setEstado_direccion(String estado_direccion) {
        this.estado_direccion = estado_direccion;
    }

    public String getTipo_direccion() {
        return tipo_direccion;
    }

    public void setTipo_direccion(String tipo_direccion) {
        this.tipo_direccion = tipo_direccion;
    }

    public String getDepartamento_direccion() {
        return departamento_direccion;
    }

    public void setDepartamento_direccion(String departamento_direccion) {
        this.departamento_direccion = departamento_direccion;
    }

    public String getProvincia_direccion() {
        return provincia_direccion;
    }

    public void setProvincia_direccion(String provincia_direccion) {
        this.provincia_direccion = provincia_direccion;
    }

    public String getDistrito_direccion() {
        return distrito_direccion;
    }

    public void setDistrito_direccion(String distrito_direccion) {
        this.distrito_direccion = distrito_direccion;
    }

    public String getClientes_id_cliente() {
        return clientes_id_cliente;
    }

    public void setClientes_id_cliente(String clientes_id_cliente) {
        this.clientes_id_cliente = clientes_id_cliente;
    }

    public String getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(String id_direccion) {
        this.id_direccion = id_direccion;
    }

    @Override
    public String toString() {
        return "DireccionCliente{" +
                "descripcion_direccion='" + descripcion_direccion + '\'' +
                ", estado_direccion='" + estado_direccion + '\'' +
                ", tipo_direccion='" + tipo_direccion + '\'' +
                ", departamento_direccion='" + departamento_direccion + '\'' +
                ", provincia_direccion='" + provincia_direccion + '\'' +
                ", distrito_direccion='" + distrito_direccion + '\'' +
                ", clientes_id_cliente='" + clientes_id_cliente + '\'' +
                ", id_direccion='" + id_direccion + '\'' +
                '}';
    }
}
