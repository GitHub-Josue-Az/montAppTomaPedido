package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class LoginUsuariosNoConectados implements Serializable {

    private String almacen;
    private String tipo_usuario_id_tipo_usuario;
    private String unique_id;

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getTipo_usuario_id_tipo_usuario() {
        return tipo_usuario_id_tipo_usuario;
    }

    public void setTipo_usuario_id_tipo_usuario(String tipo_usuario_id_tipo_usuario) {
        this.tipo_usuario_id_tipo_usuario = tipo_usuario_id_tipo_usuario;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getCodigo_vendedor() {
        return codigo_vendedor;
    }

    public void setCodigo_vendedor(String codigo_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
    }

    public String getFecha_clave() {
        return fecha_clave;
    }

    public void setFecha_clave(String fecha_clave) {
        this.fecha_clave = fecha_clave;
    }

    public String getEstado_usuario() {
        return estado_usuario;
    }

    public void setEstado_usuario(String estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public String getUsuario_usuario() {
        return usuario_usuario;
    }

    public void setUsuario_usuario(String usuario_usuario) {
        this.usuario_usuario = usuario_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    private String correo_usuario;
    private String codigo_vendedor;
    private String fecha_clave;
    private String estado_usuario;
    private String clave_usuario;
    private String usuario_usuario;
    private String nombre_usuario;
    private String id_usuario;



    @Override
    public String toString() {
        return "LoginUsuariosNoConectados{" +
                "almacen='" + almacen + '\'' +
                ", tipo_usuario_id_tipo_usuario='" + tipo_usuario_id_tipo_usuario + '\'' +
                ", unique_id='" + unique_id + '\'' +
                ", correo_usuario='" + correo_usuario + '\'' +
                ", codigo_vendedor='" + codigo_vendedor + '\'' +
                ", fecha_clave='" + fecha_clave + '\'' +
                ", estado_usuario='" + estado_usuario + '\'' +
                ", clave_usuario='" + clave_usuario + '\'' +
                ", usuario_usuario='" + usuario_usuario + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", id_usuario='" + id_usuario + '\'' +
                '}';
    }


}
