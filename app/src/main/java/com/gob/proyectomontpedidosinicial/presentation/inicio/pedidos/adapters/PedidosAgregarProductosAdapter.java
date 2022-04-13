package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.LoaderAdapter;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosTableDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosTableInterface;
import com.gob.proyectomontpedidosinicial.utils.OnClickListListener;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidosAgregarProductosAdapter extends LoaderAdapter<EntityProductoPorUsuario> implements  OnClickListListener, PopUpAgregarProductosTableInterface {

    private Context context;
    private SessionManager sessionManager;
    private  AdapterInterfaceProductos adapterInterfaceProductos;
    private EntityProductoPorUsuario listaDeProductos;
    private PopUpAgregarProductosTableDialog showPopUpAgregarProductosTableDialog;
    /*HashMap<Integer, ViewHolder> holderlist;*/
    /*private int pos;*/
    private BigDecimal inic;

    private EntityProductoPorUsuario producto;

    private RecyclerView.ViewHolder viewHolderPreferences;

    public PedidosAgregarProductosAdapter(ArrayList<EntityProductoPorUsuario> listaDeProductos, Context context, AdapterInterfaceProductos adapterInterfaceProductoss) {
        super(context);
        setItems(listaDeProductos);
        this.context = context;
        this.sessionManager = new SessionManager(context);
        /*holderlist = new HashMap<>();*/
        this.adapterInterfaceProductos = adapterInterfaceProductoss;
    }

    public ArrayList<EntityProductoPorUsuario> getItems() {
        return (ArrayList<EntityProductoPorUsuario>) getmItems();
    }

    @Override
    public long getYourItemId(int position) {
       return 0;
        /* return getmItems().get(position).getId();*/
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        View rooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido_listado_productos, parent, false);
        return new ViewHolder(rooter,this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int posi) {

        /* Tomara el ultimo nomas  */
        viewHolderPreferences = holder;

        holder.setIsRecyclable(false);

        EntityProductoPorUsuario listaproducts = getItems().get(posi);
        (((ViewHolder) holder).tvAgregarProductoTable).setText(listaproducts.getNombre_corto());
        (((ViewHolder) holder).tvAgregarCosto).setText(String.valueOf(listaproducts.getCosto()));
        (((ViewHolder) holder).tvAgregarCantidad).setText(String.valueOf(listaproducts.getCantidad()));

        if (!(listaproducts.getSubtotal() == null)){
             (((ViewHolder) holder).tvAgregarSubtotal).setText(String.valueOf(listaproducts.getSubtotal()));
            }else {
            /*inic= new BigDecimal("0");*/
            listaproducts.setSubtotal("0");
            (((ViewHolder) holder).tvAgregarSubtotal).setText("0");
        }




        if (!(listaproducts.getPromocion() == null)){
            if (listaproducts.getPromocion().equals("true")) {
                (((ViewHolder) holder).cbAgregarPromocion).setBackground(context.getDrawable(R.drawable.ic_baseline_check_box_24));
            }else{
                (((ViewHolder) holder).cbAgregarPromocion).setBackground(context.getDrawable(R.drawable.ic_baseline_check_box_outline_blank_24));
            }
        }else{
            (((ViewHolder) holder).cbAgregarPromocion).setBackground(context.getDrawable(R.drawable.ic_baseline_check_box_outline_blank_24));
        }


        ((ViewHolder) holder).lnAgregarDeleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterfaceProductos.deleteItemFile(posi,listaproducts.getSubtotal());
            }
        });

       /* if (!holderlist.containsKey(posi)) {
            holderlist.put(posi, (ViewHolder) holder);
        }*/

    }

   /* public ViewHolder getViewByPosition(int position) {
        return holderlist.get(position);
    }
*/

    @Override
    public void onClick(int position) {
        /*int problemasEntity = getItems().get(position).getIdProblema();
        serviceItem.clickItem(problemasEntity);*/

        /* Pasar la informacion actual de ese listproducto para que lo vea o lo modifique */

        /* Pasar la posicion */
        producto = getItems().get(position);

        /* Mandar el Subtotal al main para que reste */


        showPopUpAgregarProductosTableDialog = new PopUpAgregarProductosTableDialog(context, this,producto,position);
        showPopUpAgregarProductosTableDialog.show();
        showPopUpAgregarProductosTableDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showPopUpAgregarProductosTableDialog.setCancelable(false);

    }

    /*  Despues de guardar debo modificar el viewholder */
    @Override
    public void cerrarDialogProductosTable() {

        showPopUpAgregarProductosTableDialog.dismiss();
    }

    /* Esto viene del pop up table dialog */
    @Override
    public void guardarProductoListaDeProductos(EntityProductoPorUsuario listaDeProductos, int position) {

        /*   Midifcar el listado y refrescar  */
        /*ListaDeProductos pro =  getItems().get(position);*/
        getItems().set(position,listaDeProductos);
        adapterInterfaceProductos.guardarItemFile(listaDeProductos.getSubtotal(),producto.getSubtotal());
        /* getItems().get(position).setPromocion(listaDeProductos.getPromocion());
         getItems().get(position).setCantidad(listaDeProductos.getCantidad());
         getItems().get(position).setCosto(listaDeProductos.getCosto());
         getItems().get(position).setSubtotal(listaDeProductos.getSubtotal());*/
        notifyDataSetChanged();
        showPopUpAgregarProductosTableDialog.dismiss();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_agregar_pedidos_producto_tabler)
        TextView tvAgregarProductoTable;
        @BindView(R.id.tv_agregar_pedidos_producto2_table)
        AppCompatImageView cbAgregarPromocion;
        @BindView(R.id.tv_agregar_pedidos_producto3_table)
        TextView tvAgregarCosto;
        @BindView(R.id.tv_agregar_pedidos_producto4_table)
        TextView tvAgregarCantidad;
        @BindView(R.id.tv_agregar_pedidos_producto5_table)
        TextView tvAgregarSubtotal;
        @BindView(R.id.tv_agregar_pedidos_producto6_table)
        LinearLayoutCompat lnAgregarDeleteTable;
        private OnClickListListener onClickListListener;

         ViewHolder(View itemView, OnClickListListener onClickListListener){
                super(itemView);
                 ButterKnife.bind(this, itemView);
             this.onClickListListener = onClickListListener;
             this.itemView.setOnClickListener(this);
         }

        @Override
        public void onClick(View v) {
            onClickListListener.onClick(getAdapterPosition());
        }
    }



}
