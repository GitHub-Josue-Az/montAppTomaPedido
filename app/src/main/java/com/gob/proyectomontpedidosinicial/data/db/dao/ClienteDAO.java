package com.gob.proyectomontpedidosinicial.data.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;

import java.util.List;

@Dao
public abstract class ClienteDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertClientes(List<EntityCliente> clientes);

    /* Tiene que ser el nombre de la tabla y la tabla tiene el nombre del constant  */
    @Query("SELECT * FROM entitycliente")
    public abstract List<EntityCliente> findAllClientes();

    @Query("SELECT * FROM entitycliente where coa_cliente LIKE :coacliente")
    public abstract EntityCliente findClienteById(String coacliente);

    @Query("DELETE FROM entitycliente")
    public abstract void deleteAllClientes();

    @Transaction
    @Update
    public void updateCliente(List<EntityCliente> clientes){
        deleteAllClientes();
        insertClientes(clientes);
    }


}
