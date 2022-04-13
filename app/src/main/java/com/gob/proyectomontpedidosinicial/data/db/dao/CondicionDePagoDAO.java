package com.gob.proyectomontpedidosinicial.data.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;

import java.util.List;

@Dao
public abstract class CondicionDePagoDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertCondicionDePago(List<EntityCondicionDePago> condicionDePagos);

    /* Tiene que ser el nombre de la tabla y la tabla tiene el nombre del constant  */
    @Query("SELECT * FROM entitycondicionpago")
    public abstract List<EntityCondicionDePago> findAllCondicionDePago();

    @Query("SELECT * FROM entitycondicionpago where id_condicion LIKE :idCondicion")
    public abstract EntityCondicionDePago findCondicionDePagoById(String idCondicion);

    @Query("DELETE FROM entitycondicionpago")
    public abstract void deleteAllCondicionDePago();

    @Transaction
    @Update
    public void updateCondicionDePago(List<EntityCondicionDePago> condicionDePagos){
        deleteAllCondicionDePago();
        insertCondicionDePago(condicionDePagos);
    }


}
