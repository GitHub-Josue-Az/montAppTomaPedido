package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarProducto;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarProductosAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUpAgregarProductosTableDialog extends AlertDialog{

    @BindView(R.id.et_producto_nombre_dialog)
    TextView tvProductoDialog;
    @BindView(R.id.cb_producto_promocion_dialog)
    AppCompatCheckBox cbProductoPromocion;
    @BindView(R.id.et_producto_costo_dialog)
    AppCompatEditText etProductoCosto;
    @BindView(R.id.et_producto_cantidad_dialog)
    AppCompatEditText etProductoCantidad;
    @BindView(R.id.et_producto_subtotal_dialog)
    AppCompatEditText etProductoSubtotal;


    private Context mContext;
    private PopUpAgregarProductosTableInterface mPopUpAgregarProductosTableInterface;

    private LinearLayoutManager mlinearLayoutManager;
    private AgregarProductosAdapter agregarProductosAdapter;

    private double subtotal = 0;
    private int posicion;

    public PopUpAgregarProductosTableDialog(Context context, PopUpAgregarProductosTableInterface popUpAgregarProductosTableInterface,ListaDeProductos producto,int position) {
        super(context);
        this.mPopUpAgregarProductosTableInterface = popUpAgregarProductosTableInterface;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_agregarproductos_inputs, null);
        setView(view);
        ButterKnife.bind(this, view);
        mContext = context;
        posicion  = position;
        tvProductoDialog.setText(producto.getProducto());
        etProductoSubtotal.setText("0");

        /* Verificar si tienen data y si arrojan error  */

           if (!(producto.getPromocion() == null)){
                if (producto.getPromocion().equals("true")){
                 cbProductoPromocion.setChecked(true);
                }
           }



        etProductoCosto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (etProductoCosto.getText().toString().length() > 0 && etProductoCantidad.getText().toString().length() >0) {
                   double costo = Double.parseDouble(etProductoCosto.getText().toString());
                    int cantidad = Integer.parseInt(etProductoCantidad.getText().toString());
                    subtotal = costo*cantidad;
                    etProductoSubtotal.setText(String.valueOf(subtotal));
                } else {
                    /* Que el subtotal sea 0 */
                    etProductoSubtotal.setText("0");
                }}
        });
        etProductoCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (etProductoCosto.getText().toString().length() > 0 && etProductoCantidad.getText().toString().length() >0) {
                    double costo = Double.parseDouble(etProductoCosto.getText().toString());
                    int cantidad = Integer.parseInt(etProductoCantidad.getText().toString());
                    subtotal = costo*cantidad;
                    etProductoSubtotal.setText(String.valueOf(subtotal));
                } else {
                    /* Que el subtotal sea 0 */
                    etProductoSubtotal.setText("0");
                }}
        });
    }

    @OnClick({R.id.btn_pedidos_agregar_guardar, R.id.btn_pedidos_agregar_cancelar,R.id.iv_productos_dialog_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pedidos_agregar_guardar:
                /* Traer toda la data y pasarlo meterlo en un objeto sino */
                /*  Al guardar debo informarle al fragmento que debo solicitar a todos los subtotales y sumarlos o simplemente el ultimo subtotal sumarlo  */
                ListaDeProductos listaDeProductos = new ListaDeProductos();
                String promo =String.valueOf(cbProductoPromocion.isChecked());
                String cantidad = etProductoCantidad.getText().toString();
                String costo = etProductoCosto.getText().toString();
                int cant =0;
                double cost =0;
                if(!cantidad.isEmpty() && !cantidad.equals("")){
                   cant =  Integer.parseInt(cantidad);
                }
                if(!costo.isEmpty() && !costo.equals("")){
                    cost =  Double.parseDouble(costo);
                }
                double sub = Double.parseDouble(etProductoSubtotal.getText().toString());
                listaDeProductos.setProducto(tvProductoDialog.getText().toString());
                listaDeProductos.setCantidad(cant);
                listaDeProductos.setCosto(cost);
                listaDeProductos.setSubtotal(sub);
                listaDeProductos.setPromocion(promo);
                mPopUpAgregarProductosTableInterface.guardarProductoListaDeProductos(listaDeProductos,posicion);
                break;
            case R.id.btn_pedidos_agregar_cancelar:
                dismiss();
                break;
            case R.id.iv_productos_dialog_close:
                dismiss();
                break;
            default:
                dismiss();
                break;
        }
    }



}