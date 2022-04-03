package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.PedidosAgregarProductosAdapter;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarProductosAdapter;

import java.math.BigDecimal;

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

    private BigDecimal subtotal;
    private int posicion;

    public PopUpAgregarProductosTableDialog(Context context, PopUpAgregarProductosTableInterface popUpAgregarProductosTableInterface, ListaDeProductos producto, int position) {
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
                }else{
                 cbProductoPromocion.setChecked(false);
                }
           }

        if (!(producto.getCosto() == 0)){
            etProductoCosto.setText(String.valueOf(producto.getCosto()));
        }

        if (!(producto.getCantidad() == 0)){
            etProductoCantidad.setText(String.valueOf(producto.getCantidad()));
        }

        if (!(producto.getSubtotal() == null)){
            etProductoSubtotal.setText(String.valueOf(producto.getSubtotal()));
        }else {
            etProductoSubtotal.setText("0");
        }



        etProductoCosto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (etProductoCosto.getText().toString().length() > 0 && etProductoCantidad.getText().toString().length() >0) {
                    BigDecimal costo = new BigDecimal(etProductoCosto.getText().toString());
                    BigDecimal cantidad = new BigDecimal(etProductoCantidad.getText().toString());
                    subtotal = costo.multiply(cantidad);
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
                    BigDecimal costo = new BigDecimal(etProductoCosto.getText().toString());
                    BigDecimal cantidad = new BigDecimal(etProductoCantidad.getText().toString());
                    subtotal = costo.multiply(cantidad);
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

                ListaDeProductos listaDeProductos = new ListaDeProductos();
                String promo =String.valueOf(cbProductoPromocion.isChecked());
                String cantidad = etProductoCantidad.getText().toString();
                String costo = etProductoCosto.getText().toString();
                int cant =0;
                BigDecimal sub = new BigDecimal("0");
                double cost =0;

                if(!cantidad.isEmpty() && !cantidad.equals("")){
                   cant =  Integer.parseInt(cantidad);
                }
                if(!costo.isEmpty() && !costo.equals("")){
                    cost =  Double.parseDouble(costo);
                }

                if(!etProductoSubtotal.getText().toString().isEmpty() && etProductoSubtotal.getText().toString().length() > 0 ){
                    sub = new BigDecimal(etProductoSubtotal.getText().toString());
                }


                listaDeProductos.setProducto(tvProductoDialog.getText().toString());
                listaDeProductos.setCantidad(cant);
                listaDeProductos.setCosto(cost);
                listaDeProductos.setSubtotal(sub);
                listaDeProductos.setPromocion(promo);
                mPopUpAgregarProductosTableInterface.guardarProductoListaDeProductos(listaDeProductos,posicion);
                break;
            case R.id.btn_pedidos_agregar_cancelar:
                mPopUpAgregarProductosTableInterface.cerrarDialogProductosTable();
                break;
            case R.id.iv_productos_dialog_close:
                mPopUpAgregarProductosTableInterface.cerrarDialogProductosTable();
                break;
            default:
                mPopUpAgregarProductosTableInterface.cerrarDialogProductosTable();
                break;
        }
    }



}