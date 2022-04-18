package com.gob.proyectomontpedidosinicial.data.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityPedidoProducto;

import java.util.List;

@Dao
public abstract class PedidoProductoDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertPedidoProducto(EntityPedidoProducto entityPedidoProducto);

    /* Tiene que ser el nombre de la tabla y la tabla tiene el nombre del constant  */
    @Query("SELECT * FROM entitypedidoproducto")
    public abstract EntityPedidoProducto findAllPedidoProducto();

    @Query("SELECT * FROM entitypedidoproducto where uid LIKE :id")
    public abstract EntityPedidoProducto findPedidoById(Integer id);

    @Query("DELETE FROM entitypedidoproducto")
    public abstract void deleteAllPedidosProducto();

    @Transaction
    @Update
    public void updatePedidoProducto(EntityPedidoProducto entityPedidoProductos){
        deleteAllPedidosProducto();
        insertPedidoProducto(entityPedidoProductos);
    }


}
