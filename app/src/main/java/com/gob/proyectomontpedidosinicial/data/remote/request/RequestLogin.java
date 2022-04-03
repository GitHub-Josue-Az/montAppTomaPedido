package com.gob.proyectomontpedidosinicial.data.remote.request;

import com.gob.proyectomontpedidosinicial.data.entities.APIObjectGet;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuariosNoConectados;
import com.gob.proyectomontpedidosinicial.data.entities.UserLoginEntity;
import com.gob.proyectomontpedidosinicial.data.entities.body.UserCredentials;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RequestLogin {


    /*  Login de usuario  */
   /* @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "accept-encoding: gzip, deflate",
            "access_token: mtlNzTVmXP4IBSba3z4XXXX",
            "Authorization: Basic a2FtaWwua2ljaW5za2lAc3R1ZGVudC53YXQuZXXX"
    })
    @FormUrlEncoded*/
    @FormUrlEncoded
    @POST("restusuario")
    Call<APIObjectGet<LoginUsuarioPost>> getDataPostLogin(@Field("usuario") String usuario,@Field("clave") String clave);


    @GET("restusuario")
    Call<ArrayList<LoginUsuariosNoConectados>> getUsuariosNoConectados();


    /* RegisterServices*/
    /*@POST("maestra/GetMaestroGrupo")
    Call<ServicioObjectEntity> getTipoServices(@Body int[] code);
*/

    /* Actualizar */
    /*@POST("UsuarioExterno/ActualizarDatos")
    Call<Void> actualizarUsuarioService(@Header("Authorization") String token, @Body ActualizarUserEntity actualizarUserEntity);
*/

    /* Obtener perfil */
    /*@GET("UsuarioExterno/ObtenerPerfil")
    Call<UserRegister> getUserProfile(@Header("Authorization") String token);*/


}
