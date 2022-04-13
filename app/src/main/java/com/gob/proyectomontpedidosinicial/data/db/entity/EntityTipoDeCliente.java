package com.gob.proyectomontpedidosinicial.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.gob.proyectomontpedidosinicial.data.constans.Constans;

import java.io.Serializable;

@Entity(tableName = Constans.NAME_TABLE_ENTITY_TIPO_CLIENTE)
public class EntityTipoDeCliente implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "nombre_tipo_cliente")
    private String nombre_tipo_cliente;
    @ColumnInfo(name = "id_tipo_cliente")
    private String id_tipo_cliente;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
        return "EntityTipoDeCliente{" +
                "uid=" + uid +
                ", nombre_tipo_cliente='" + nombre_tipo_cliente + '\'' +
                ", id_tipo_cliente='" + id_tipo_cliente + '\'' +
                '}';
    }
}
