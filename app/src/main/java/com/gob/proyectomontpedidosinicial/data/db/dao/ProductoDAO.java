package com.gob.proyectomontpedidosinicial.data.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;


@Dao
public abstract class ProductoDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertProductos(List<EntityProductoPorUsuario> entityProductoPorUsuarios);

    /* Tiene que ser el nombre de la tabla y la tabla tiene el nombre del constant  */
    @Query("SELECT * FROM  entityproductoporusuario")
    public abstract List<EntityProductoPorUsuario> findAllProductos();

    @Query("SELECT * FROM entityproductoporusuario where id_producto LIKE :idProducto")
    public abstract EntityProductoPorUsuario findProductoeById(String idProducto);

    @Query("DELETE FROM entityproductoporusuario")
    public abstract void deleteAllProductos();

    @Transaction
    @Update
    public void updateProductos(List<EntityProductoPorUsuario>  entityTipoDeClientes){
        deleteAllProductos();
        insertProductos(entityTipoDeClientes);
    }


}
