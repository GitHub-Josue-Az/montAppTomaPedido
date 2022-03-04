package com.example.proyectomontpedidosinicial.data.remote.request;

import com.example.proyectomontpedidosinicial.data.entities.UserLoginEntity;
import com.example.proyectomontpedidosinicial.data.entities.body.UserCredentials;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RequestLogin {


    /*  Login de usuario  */
    @POST("UsuarioExterno/Login")
    Call<UserLoginEntity> getDataPostLogin(@Body UserCredentials usuario);


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
