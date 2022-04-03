package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class APIErrorNuevo implements Serializable{


    private String Error;
    private boolean Resultado;

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public boolean getResultado() {
        return Resultado;
    }

    public void setResultado(boolean Resultado) {
        this.Resultado = Resultado;
    }
}
