package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDePedidos;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.AdapterInterfacePagar;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.PedidosListadoAdapter;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*implements LoginContract.View*/
public class PedidosListadoFragment extends BaseFragment implements AdapterInterfacePagar {

    private static final String TAG = PedidosListadoFragment.class.getSimpleName();
    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;

    private LinearLayoutManager mlinearLayoutManager;
    private PedidosListadoAdapter pedidosListadoAdapter;
    /*private LoginContract.Presenter mPresenter;*/
    public ArrayList<ListaDePedidos> allistapedidos;


    @BindView(R.id.rv_pedidos_listado_reciclerView)
    RecyclerView recyclerViewlistado;
    @BindView(R.id.sw_pedidos_listado_refresView)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ln_pedidos_listado_agregar_nuevo)
    LinearLayoutCompat lnpedidosagregarnuevo;
    @BindView(R.id.ln_pedidos_listado_inicio)
    LinearLayoutCompat lnpedidosinicio;
    @BindView(R.id.et_pedidos_listado_search)
    SearchView svpedidosbuscar;

    public PedidosListadoFragment() {
    }


    public interface changeFragmentActivity {
        void gotoInicioFragmentListado();
        void gotoPedidoAgregarFragment();
        void gotoPagarFragment();
    }

    public static PedidosListadoFragment newInstance() {
        return new PedidosListadoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedidos_listado, container, false);
        ButterKnife.bind(this, root);


        /*  Aqui debemos consultar al ROOM los datos que tiene almacenado */

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        lnpedidosagregarnuevo.setOnClickListener(view ->gotoPedidosAgregar());
        lnpedidosinicio.setOnClickListener(view ->gotoPedidosInicio());

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pedidosListadoAdapter = new PedidosListadoAdapter(new ArrayList<ListaDePedidos>(), getContext(), this);
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewlistado.setAdapter(pedidosListadoAdapter);
        recyclerViewlistado.setLayoutManager(mlinearLayoutManager);

        /* Llamar */
        traerDatos();



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresarLista();
                pedidosListadoAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        svpedidosbuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*pedidosListadoAdapter.getFilter().filter(newText);*/
                pedidosListadoAdapter.filtrar(newText);
                return false;
            }
        });


    }

    private void gotoPedidosAgregar() {
        mCallback.gotoPedidoAgregarFragment();
    }
    private void gotoPedidosInicio() {
        mCallback.gotoInicioFragmentListado();
    }


    private void traerDatos() {

        ArrayList<ListaDePedidos> listaDePedidos = new ArrayList<>();

        ListaDePedidos pedidoUno = new ListaDePedidos();
        pedidoUno.setProducto("Alcohol");
        pedidoUno.setTotal("300");
        pedidoUno.setEstado(1);
        pedidoUno.setCliente("Josué");
        pedidoUno.setFecha("20/02/2022");

        ListaDePedidos pedidoDos = new ListaDePedidos();
        pedidoDos.setProducto("Guantes");
        pedidoDos.setTotal("100");
        pedidoDos.setEstado(2);
        pedidoDos.setCliente("Josué");
        pedidoDos.setFecha("16/02/2022");

        ListaDePedidos pedidoTres= new ListaDePedidos();
        pedidoTres.setProducto("Gel");
        pedidoTres.setTotal("500");
        pedidoTres.setEstado(3);
        pedidoTres.setCliente("Josué");
        pedidoTres.setFecha("18/02/2022");

        listaDePedidos.add(pedidoUno);
        listaDePedidos.add(pedidoDos);
        listaDePedidos.add(pedidoTres);

       pedidosListadoAdapter.setItems(listaDePedidos);
    }


    @Override
    public void pagarItemFile(int position) {

        /* Pasar a la otra vista y guardar data aqui en un session manager para luego mandar la data */
        /* Tomar el fragmento actual como base */
        mCallback.gotoPagarFragment();
    }

    public void refresarLista() {
        /*mPresenter.getListaPedidos();*/
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (changeFragmentActivity) context;
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }




}
