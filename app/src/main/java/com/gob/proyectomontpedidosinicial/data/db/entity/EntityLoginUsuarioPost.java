package com.gob.proyectomontpedidosinicial.data.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;

import java.io.Serializable;

@Entity(tableName = Constans.NAME_TABLE_ENTITY_USUARIO_POST)
public class EntityLoginUsuarioPost implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int loginusuariouid;
    @ColumnInfo(name = "almacen")
    private String almacen;
    @ColumnInfo(name = "tipo_usuario_id_tipo_usuario")
    private String tipo_usuario_id_tipo_usuario;
    @ColumnInfo(name = "unique_id")
    private String unique_id;
    @ColumnInfo(name = "correo_usuario")
    private String correo_usuario;
    @ColumnInfo(name = "codigo_vendedor")
    private String codigo_vendedor;
    @ColumnInfo(name = "fecha_clave")
    private String fecha_clave;
    @ColumnInfo(name = "estado_usuario")
    private String estado_usuario;
    @ColumnInfo(name = "clave_usuario")
    private String clave_usuario;
    @ColumnInfo(name = "usuario_usuario")
    private String usuario_usuario;
    @ColumnInfo(name = "nombre_usuario")
    private String nombre_usuario;
    @ColumnInfo(name = "id_usuario")
    private String id_usuario;


    public int getLoginusuariouid() {
        return loginusuariouid;
    }

    public void setLoginusuariouid(int loginusuariouid) {
        this.loginusuariouid = loginusuariouid;
    }

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


}
