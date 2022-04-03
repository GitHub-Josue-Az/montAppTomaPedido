package com.gob.proyectomontpedidosinicial.data.entities.body;

import java.io.Serializable;

public class CambiarClave implements Serializable {


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

    @Override
    public String toString() {
        return "CambiarClave{" +
                "Contrasenia='" + Contrasenia + '\'' +
                ", Usuario='" + Usuario + '\'' +
                '}';
    }

    private String Usuario;


}
