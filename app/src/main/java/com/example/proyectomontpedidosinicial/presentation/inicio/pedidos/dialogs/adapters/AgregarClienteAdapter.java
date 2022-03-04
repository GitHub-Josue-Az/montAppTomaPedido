package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.core.LoaderAdapter;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.example.proyectomontpedidosinicial.data.entities.ListaDePedidos;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.AdapterInterfacePagar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgregarClienteAdapter extends LoaderAdapter<ListaDeClientes> {


    private Context context;
    private SessionManager sessionManager;
    private AdapterInterfaceAgregarCliente adapterInterfaceAgregarCliente;
    private ListaDeClientes listaDeClientes;

    public AgregarClienteAdapter(ArrayList<ListaDeClientes> listaDeClientes, Context context, AdapterInterfaceAgregarCliente adapterInterfaceAgregarCliente) {
        super(context);
        setItems(listaDeClientes);
        this.context = context;
        this.sessionManager = new SessionManager(context);
        this.adapterInterfaceAgregarCliente = adapterInterfaceAgregarCliente;
    }

    public ArrayList<ListaDeClientes> getItems() {
        return (ArrayList<ListaDeClientes>) getmItems();
    }


    @Override
    public long getYourItemId(int position) {
        return getmItems().get(position).getId();
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        View rooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agregar_cliente, parent, false);
        return new ViewHolder(rooter);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int posi) {
        /* Para que no se recicle  */
        holder.setIsRecyclable(false);

        ListaDeClientes listaDeClientes = getItems().get(posi);
        (((ViewHolder) holder).tvAgregarNombreDialog).setText(listaDeClientes.getNombreCliente());
        (((ViewHolder) holder).tvAgregarRucDialog).setText(listaDeClientes.getRuc());

        ((ViewHolder) holder).lnAgregarOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterfaceAgregarCliente.agregarCliente(listaDeClientes);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pedidos_agregar_nombre_dialog_table)
        TextView tvAgregarNombreDialog;
        @BindView(R.id.tv_pedidos_agregar_ruc_dialog_table)
        TextView tvAgregarRucDialog;
        @BindView(R.id.ln_pedidos_agregar_opciones_dialog_table)
        LinearLayoutCompat lnAgregarOpciones;

         ViewHolder(View itemView){
            super(itemView);
             ButterKnife.bind(this, itemView);
           }


        }



}
