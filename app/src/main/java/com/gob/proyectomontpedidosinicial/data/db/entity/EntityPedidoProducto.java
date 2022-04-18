package com.gob.proyectomontpedidosinicial.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = Constans.NAME_TABLE_ENTITY_PEDIDO_PRODUCTO)
public class EntityPedidoProducto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "id_cliente")
    private String id_cliente;
    @ColumnInfo(name = "coa_cliente")
    private String coa_cliente;
    @ColumnInfo(name = "codigo_vendedor")
    private String codigo_vendedor;
    @ColumnInfo(name = "id_direccion")
    private String id_direccion;
    @ColumnInfo(name = "id_condicion")
    private String id_condicion;
    @ColumnInfo(name = "id_tipo_cliente")
    private String id_tipo_cliente;
    @ColumnInfo(name = "total")
    private String total;
    @ColumnInfo(name = "despacho")
    private String despacho;
    @ColumnInfo(name = "fecha_entrega")
    private String fecha_entrega;
    @ColumnInfo(name = "observaciones")
    private String observaciones;
    @ColumnInfo(name = "entityProductoPorUsuario")
    private List<EntityProductoPorUsuario> entityProductoPorUsuario;

    /* Others */
    @ColumnInfo(name = "fecha_del_dispositivo")
    private String fecha_del_dispositivo;
    @ColumnInfo(name = "ubicacion_actual")
    private String ubicacion_actual;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCoa_cliente() {
        return coa_cliente;
    }

    public void setCoa_cliente(String coa_cliente) {
        this.coa_cliente = coa_cliente;
    }

    public String getCodigo_vendedor() {
        return codigo_vendedor;
    }

    public void setCodigo_vendedor(String codigo_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
    }

    public String getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(String id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getId_condicion() {
        return id_condicion;
    }

    public void setId_condicion(String id_condicion) {
        this.id_condicion = id_condicion;
    }

    public String getId_tipo_cliente() {
        return id_tipo_cliente;
    }

    public void setId_tipo_cliente(String id_tipo_cliente) {
        this.id_tipo_cliente = id_tipo_cliente;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<EntityProductoPorUsuario> getEntityProductoPorUsuario() {
        return entityProductoPorUsuario;
    }

    public void setEntityProductoPorUsuario(List<EntityProductoPorUsuario> entityProductoPorUsuario) {
        this.entityProductoPorUsuario = entityProductoPorUsuario;
    }

    public String getFecha_del_dispositivo() {
        return fecha_del_dispositivo;
    }

    public void setFecha_del_dispositivo(String fecha_del_dispositivo) {
        this.fecha_del_dispositivo = fecha_del_dispositivo;
    }

    public String getUbicacion_actual() {
        return ubicacion_actual;
    }

    public void setUbicacion_actual(String ubicacion_actual) {
        this.ubicacion_actual = ubicacion_actual;
    }


    @Override
    public String toString() {
        return "EntityPedidoProducto{" +
                "uid=" + uid +
                ", id_cliente='" + id_cliente + '\'' +
                ", coa_cliente='" + coa_cliente + '\'' +
                ", codigo_vendedor='" + codigo_vendedor + '\'' +
                ", id_direccion='" + id_direccion + '\'' +
                ", id_condicion='" + id_condicion + '\'' +
                ", id_tipo_cliente='" + id_tipo_cliente + '\'' +
                ", total='" + total + '\'' +
                ", despacho='" + despacho + '\'' +
                ", fecha_entrega='" + fecha_entrega + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", entityProductoPorUsuario=" + entityProductoPorUsuario +
                ", fecha_del_dispositivo='" + fecha_del_dispositivo + '\'' +
                ", ubicacion_actual='" + ubicacion_actual + '\'' +
                '}';
    }
}















