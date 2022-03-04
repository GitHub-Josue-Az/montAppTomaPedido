package com.example.proyectomontpedidosinicial.data.entities.body;

import java.io.Serializable;

public class UserCredentials implements Serializable {


    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    private String Contrasenia;
    private String Usuario;

    @Override
    public String toString() {
        return "UserCredentials{" +
                "Contrasenia='" + Contrasenia + '\'' +
                ", Usuario='" + Usuario + '\'' +
                '}';
    }



}
