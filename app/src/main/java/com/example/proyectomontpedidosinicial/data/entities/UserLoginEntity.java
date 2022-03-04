package com.example.proyectomontpedidosinicial.data.entities;

import java.io.Serializable;

public class UserLoginEntity implements Serializable {

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "UserLoginEntity{" +
                "Token='" + Token + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Id=" + Id +
                '}';
    }

    private String Token;
    private String ApellidoPaterno;
    private String Nombre;
    private int Id;




}
