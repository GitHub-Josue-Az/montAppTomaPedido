package com.gob.proyectomontpedidosinicial.data.remote.request;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;
import com.gob.proyectomontpedidosinicial.data.entities.APIListaProductosPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.APIObjectGet;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.CondicionDePago;
import com.gob.proyectomontpedidosinicial.data.entities.DireccionCliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.TipoDeCliente;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestPedidos {


    @FormUrlEncoded
    @POST("cliente")
    Call<APIListaProductosPorUsuario<EntityCliente>> getDataCliente(@Field("usuario") String usuario);

    @FormUrlEncoded
    @POST("producto")
    Call<APIListaProductosPorUsuario<EntityProductoPorUsuario>> getDataProductos(@Field("usuario") String usuario);

    @FormUrlEncoded
    @POST("condicionpago")
    Call<APIListaProductosPorUsuario<EntityCondicionDePago>> getDataCondicionPago(@Field("usuario") String usuario);

    @FormUrlEncoded
    @POST("tipocliente")
    Call<APIListaProductosPorUsuario<EntityTipoDeCliente>> getDataTipoCliente(@Field("usuario") String usuario);


    @FormUrlEncoded
    @POST("direccioncliente")
    Call<APIListaProductosPorUsuario<EntityDireccionCliente>> getDataDireccionCliente(@Field("usuario") String usuario);


}
