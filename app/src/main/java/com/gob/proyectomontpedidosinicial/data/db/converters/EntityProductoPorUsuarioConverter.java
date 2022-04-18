package com.gob.proyectomontpedidosinicial.data.db.converters;


import androidx.room.TypeConverter;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class EntityProductoPorUsuarioConverter {

    @TypeConverter
    public String toJson(List<EntityProductoPorUsuario> entityProductoPorUsuario){

        if(entityProductoPorUsuario == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<EntityProductoPorUsuario>>(){}.getType();
        String json = gson.toJson(entityProductoPorUsuario,type);
        return json;
    }


    @TypeConverter
    public List<EntityProductoPorUsuario> toDataClass(String entityProducto){

        if (entityProducto == null){
            return (null);
        }

        Gson gson = new Gson();
        Type types = new TypeToken<List<EntityProductoPorUsuario>>(){}.getType();
        List<EntityProductoPorUsuario> productoPorUsuarios = gson.fromJson(entityProducto,types);
        return productoPorUsuarios;
    }



}





















