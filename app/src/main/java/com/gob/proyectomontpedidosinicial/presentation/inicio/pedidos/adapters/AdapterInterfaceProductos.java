package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters;

import java.math.BigDecimal;

public interface AdapterInterfaceProductos {
    void deleteItemFile(int posicion,String subtotalDelete);

    void guardarItemFile(String guardarsubtotal,String subtotal);
}
