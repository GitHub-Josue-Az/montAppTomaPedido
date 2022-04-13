package com.gob.proyectomontpedidosinicial.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;
import java.util.List;

@Dao
public abstract class TipoDeClienteDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertTipoDeCliente(List<EntityTipoDeCliente> entityTipoDeClientes);

    /* Tiene que ser el nombre de la tabla y la tabla tiene el nombre del constant  */
    @Query("SELECT * FROM entitytipocliente")
    public abstract List<EntityTipoDeCliente> findAllTipoDeCliente();

    @Query("SELECT * FROM entitytipocliente where id_tipo_cliente LIKE :idTipoCliente")
    public abstract EntityTipoDeCliente findTipoDeClienteById(String idTipoCliente);

    @Query("DELETE FROM entitytipocliente")
    public abstract void deleteAllTipoDeCliente();

    @Transaction
    @Update
    public void updateTipoDeCliente(List<EntityTipoDeCliente>  entityTipoDeClientes){
        deleteAllTipoDeCliente();
        insertTipoDeCliente(entityTipoDeClientes);
    }


}
