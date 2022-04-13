package com.gob.proyectomontpedidosinicial.data.db.entity;

import androidx.room.ColumnInfo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;

import java.io.Serializable;


@Entity(tableName = Constans.NAME_TABLE_ENTITY_CONDICION_PAGO)
public  class EntityCondicionDePago implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "nombre_condicion")
    private String nombre_condicion;
    @ColumnInfo(name = "id_condicion")
    private String id_condicion;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
        return "EntityCondicionDePago{" +
                "uid=" + uid +
                ", nombre_condicion='" + nombre_condicion + '\'' +
                ", id_condicion='" + id_condicion + '\'' +
                '}';
    }
}
