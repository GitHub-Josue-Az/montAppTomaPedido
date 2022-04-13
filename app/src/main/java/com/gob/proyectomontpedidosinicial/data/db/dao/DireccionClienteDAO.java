package com.gob.proyectomontpedidosinicial.data.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;

import java.util.List;

@Dao
public abstract class DireccionClienteDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertDireccionCliente(List<EntityDireccionCliente> entityDireccionClientes);

    /* Tiene que ser el nombre de la tabla y la tabla tiene el nombre del constant  */
    @Transaction
    @Query("SELECT * FROM entitydireccioncliente")
    public abstract List<EntityDireccionCliente> findAllDireccionCliente();

    @Query("SELECT * FROM entitydireccioncliente where clientes_id_cliente LIKE :idCliente")
    public abstract List<EntityDireccionCliente> findDireccionByClienteId(String idCliente);

    @Query("DELETE FROM entityDireccionCliente")
    public abstract void deleteAllDireccionCliente();

    @Transaction
    @Update
    public void updateDireccionCliente(List<EntityDireccionCliente> entityDireccionClientes){
        deleteAllDireccionCliente();
        insertDireccionCliente(entityDireccionClientes);
    }



}
