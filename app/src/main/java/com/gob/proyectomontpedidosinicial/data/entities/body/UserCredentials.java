package com.gob.proyectomontpedidosinicial.data.entities.body;

import java.io.Serializable;

public class UserCredentials implements Serializable{

    @Override
    public String toString() {
        return "UserCredentials{" +
                "usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }

    public String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String clave;


}
