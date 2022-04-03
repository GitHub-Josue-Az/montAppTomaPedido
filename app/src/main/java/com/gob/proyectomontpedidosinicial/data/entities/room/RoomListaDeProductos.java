package com.gob.proyectomontpedidosinicial.data.entities.room;


import androidx.room.Entity;

@Entity
public class RoomListaDeProductos{


    public int id;
    public double subtotal;
    public String producto;
    public String promocion;
    public double costo;
    public int cantidad;


}
