package com.gob.proyectomontpedidosinicial.data.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityLoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;

import java.util.List;

@Dao
public abstract class LoginUsuarioPostDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLoginUsuarioPost(EntityLoginUsuarioPost loginUsuarioPosts);

    @Query("SELECT * FROM entityusuariopost")
    public abstract List<EntityLoginUsuarioPost> findAllLoginUsuarioPost();

    @Query("SELECT * FROM entityusuariopost where id_usuario LIKE :idUsuario")
    public abstract EntityLoginUsuarioPost findLoginUsuarioPostById(String idUsuario);

    @Query("DELETE FROM entityusuariopost")
    public abstract void deleteAllLoginUsuarios();

    @Transaction
    @Update
    public void updateCliente(EntityLoginUsuarioPost loginUsuarioPosts){
        deleteAllLoginUsuarios();
        insertLoginUsuarioPost(loginUsuarioPosts);
    }


}
