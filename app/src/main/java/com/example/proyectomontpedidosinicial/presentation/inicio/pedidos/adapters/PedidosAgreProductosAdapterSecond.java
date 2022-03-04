package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters;

import  android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.core.LoaderAdapter;
import com.example.proyectomontpedidosinicial.data.entities.ListaDePedidos;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidosAgreProductosAdapterSecond extends
        RecyclerView.Adapter<PedidosAgreProductosAdapterSecond.ViewHolder>  {

    private Context context;
    private SessionManager sessionManager;
    private  AdapterInterfaceProductos adapterInterfaceProductos;
    private ArrayList<ListaDeProductos> listaDeProductos;

   /* HashMap<Integer, ViewHolder> holderlist;*/

    protected List<ListaDeProductos> mItems;

    public PedidosAgreProductosAdapterSecond(ArrayList<ListaDeProductos> listaDeProductos, Context context, AdapterInterfaceProductos adapterInterfaceProductoss) {
        super();
        setItems(listaDeProductos);
        this.context = context;
        this.listaDeProductos = listaDeProductos;
        this.sessionManager = new SessionManager(context);        /*holderlist = new HashMap<>();*/
        this.adapterInterfaceProductos = adapterInterfaceProductoss;
    }

    public ArrayList<ListaDeProductos> getItems() {
        return (ArrayList<ListaDeProductos>) getmItems();
    }


    public void setItems(List<ListaDeProductos> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public List<ListaDeProductos> getmItems() {
        return mItems;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewr = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido_listado_productos, parent, false);
        return new ViewHolder(viewr);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setIsRecyclable(false);


        ListaDeProductos listaDeProductoss = getItems().get(holder.getAdapterPosition());
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.tvAgregarProductoTable.setText(listaDeProductoss.getProducto());
        /*holder.lnAgregarDeleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterfaceProductos.deleteItemFile();
            }
        });*/

        Toast.makeText(context, "Item"+listaDeProductoss.getProducto()+ "Position"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }


    public void agregarItem(ListaDeProductos item) {
        mItems.add(item);
        /*notifyDataSetChanged();*/
        notifyItemInserted(mItems.size());
        notifyDataSetChanged();
    }


    public void deleteItemPro(int position){
        /*  String pos = String.valueOf(position);*/
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());
        notifyDataSetChanged();
       /* notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());*/
        /*Log.e(TAG , pos);*/
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_agregar_pedidos_producto_tabler)
        TextView tvAgregarProductoTable;
        @BindView(R.id.tv_agregar_pedidos_producto6_table)
        LinearLayoutCompat lnAgregarDeleteTable;

         ViewHolder(View itemView){
                super(itemView);
                 ButterKnife.bind(this, itemView);
               }
        }

}
