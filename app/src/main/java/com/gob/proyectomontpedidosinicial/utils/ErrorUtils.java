package com.gob.proyectomontpedidosinicial.utils;


import com.gob.proyectomontpedidosinicial.data.entities.APIErrorNuevo;
import com.gob.proyectomontpedidosinicial.data.remote.ServiceFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {


    public static APIErrorNuevo nuevoParserError(Response<?> response) {

        Converter<ResponseBody, APIErrorNuevo> converter =
                ServiceFactory.nuevoRetrofit()
                        .responseBodyConverter(APIErrorNuevo.class, new Annotation[0]);

        APIErrorNuevo error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIErrorNuevo();
        }

        return error;
    }


}
