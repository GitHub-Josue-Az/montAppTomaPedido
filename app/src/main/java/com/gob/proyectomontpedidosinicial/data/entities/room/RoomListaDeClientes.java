package com.gob.proyectomontpedidosinicial.data.entities.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class RoomListaDeClientes {

    @PrimaryKey
    public int id;
    public String nombreCliente;
    public String ruc;
    private String documentoCliente;






}
