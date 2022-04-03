package com.gob.proyectomontpedidosinicial.data.entities.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class RoomListaDePedidos{

    @PrimaryKey
    private  int id;
    @ColumnInfo(name = "producto")
    private String producto;
    @ColumnInfo(name = "total")
    private String total;
    @ColumnInfo(name = "estado")
    private int estado;
    @ColumnInfo(name = "cliente")
    private String cliente;
    @ColumnInfo(name = "fecha")
    private String fecha;





}
