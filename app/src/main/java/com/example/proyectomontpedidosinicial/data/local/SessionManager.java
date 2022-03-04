package com.example.proyectomontpedidosinicial.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.proyectomontpedidosinicial.data.entities.UserLoginEntity;
import com.google.gson.Gson;


public class SessionManager {

    public static final String USER_NAME_LOGIN = "user_name_login";
    public static final String PREFERENCE_NAME = "JosueAppPreference";
    public static int PRIVATE_MODE = 0;
    public static final String USER_TOKEN_LOGIN = "user_token_login";
    public static final String USER_LOGEADO = "user_logeado";
    public static final String USER_JSON_POST_LOGIN = "user_json_post_login";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String VISIBLE_ALERT_MODULE = "visible_alert_module";


    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }




    public String getUserNameLogin(){
        return preferences.getString(USER_NAME_LOGIN,null);
    }

    public void setUserNameLogin(String name){
        editor.putString(USER_NAME_LOGIN,name);
        editor.commit();
    }


    public void setUserLogeado(boolean logeado){
        editor.putBoolean(USER_LOGEADO,logeado);
        editor.commit();
    }

    public boolean getUserLogeado(){
        return  preferences.getBoolean(USER_LOGEADO,false);
    }

    public String getUserTokenLogin() {
        return preferences.getString(USER_TOKEN_LOGIN, null);
    }

    public void setUserTokenLogin(String token) {
        editor.putString(USER_TOKEN_LOGIN, token);
        editor.commit();
    }


    public void setUserObjectEntity(UserLoginEntity userLoginEntity) {

        if (userLoginEntity != null) {
            Gson gson = new Gson();
            String user = gson.toJson(userLoginEntity);
            editor.putString(USER_JSON_POST_LOGIN, user);
        }
        editor.commit();
    }


}

