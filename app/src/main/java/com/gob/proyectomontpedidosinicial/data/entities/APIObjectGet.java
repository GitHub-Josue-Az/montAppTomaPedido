package com.gob.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class APIObjectGet<T> implements Serializable {

    public T data;
    public int code;

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    private String msj;


    public T getData() {
        return data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "APIObjectGet{" +
                "data=" + data +
                ", code=" + code +
                ", msj=" + msj +
                '}';
    }

}
