package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.LoaderAdapter;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgregarClienteAdapter extends LoaderAdapter<Cliente> {


    private Context context;
    private SessionManager sessionManager;
    private AdapterInterfaceAgregarCliente adapterInterfaceAgregarCliente;
    private Cliente listaDeClientes;

    public AgregarClienteAdapter(ArrayList<Cliente> listaDeClientes, Context context, AdapterInterfaceAgregarCliente adapterInterfaceAgregarCliente) {
        super(context);
        setItems(listaDeClientes);
        this.context = context;
        this.sessionManager = new SessionManager(context);
        this.adapterInterfaceAgregarCliente = adapterInterfaceAgregarCliente;
    }

    public ArrayList<Cliente> getItems() {
        return (ArrayList<Cliente>) getmItems();
    }


    @Override
    public long getYourItemId(int position) {
        return 0;
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

        Cliente listaDeClientes = getItems().get(posi);
        (((ViewHolder) holder).tvAgregarNombreDialog).setText(listaDeClientes.getRazon_social());
        (((ViewHolder) holder).tvAgregarRucDialog).setText(listaDeClientes.getCoa_cliente());

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
