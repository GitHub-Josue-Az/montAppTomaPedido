package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class APIListaProductosPorUsuario<T> implements Serializable {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<T> getData() {
        return data;
    }

    private int code;
    private ArrayList<T> data;

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    private String msj;

    private T dataObject;

    public T getDataObject() {
        return dataObject;
    }


    @Override
    public String toString() {
        return "APIListaProductosPorUsuario{" +
                "code=" + code +
                ", data=" + data +
                ", msj=" + msj +
                ", dataObject=" + dataObject +
                '}';
    }
}
