package com.gob.proyectomontpedidosinicial.data.remote.request;

import com.gob.proyectomontpedidosinicial.data.entities.APIListaProductosPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.APIObjectGet;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestPedidos {


    @FormUrlEncoded
    @POST("cliente")
    Call<APIListaProductosPorUsuario<Cliente>> getDataCliente(@Field("usuario") String usuario);



}
