package com.gob.proyectomontpedidosinicial.presentation.inicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseActivity;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.db.database.AppDb;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.inicio.clientes.ClientesAgregarFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.clientes.ClientesListadoFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.menu.MenuInicioFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosAgregarFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosListadoFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosPago.PedidosPagarFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.productos.ProductosAgregarFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.productos.ProductosListadoFragment;
import com.gob.proyectomontpedidosinicial.presentation.login.BaseCredencialesActivity;
import com.gob.proyectomontpedidosinicial.utils.ActivityUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InicioMenuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener,
        ClientesAgregarFragment.changeFragmentActivity,
        MenuInicioFragment.changeFragmentActivity,
        PedidosAgregarFragment.changeFragmentActivity,
        PedidosListadoFragment.changeFragmentActivity,
        PedidosPagarFragment.changeFragmentActivity{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment fragmentos;
    private Fragment NewIntance;
    private BaseFragment baseFragmentNav;

    private String title;
    private String fragmentoTag;


    /*  FRAGMENTOS Visibilidad */
    private static final int MENU_HOME = 1;
    private static final int CLIENTES_AGREGAR = 2;
    private static final int CLIENTES_LISTADO = 3;
    private static final int PRODUCTOS_AGREGAR = 4;
    private static final int PRODUCTOS_LISTADO = 5;
    private static final int PEDIDOS_AGREGAR = 6;
    private static final int PEDIDOS_LISTADO = 7;

    /*  Fragmentos */
    public static final String INICIO = "Inicio";
    public static final String AGREGARCLIENTES = "Agregar cliente";
    public static final String LISTADOCLIENTES = "Listado de clientes";
    public static final String AGREGARPRODUCTOS =  "Agregar producto";
    public static final String LISTADOPRODUCTOS = "Listado de productos";
    public static final String AGREGARPEDIDOS =  "Agregar pedido";
    public static final String LISTADOPEDIDOS =  "Listado de pedidos";
    public static final String PAGARPEDIDOS =  "Pagar pedido";


    public static final int NAVINICIO =  R.id.menu_nav_inicio;
    public static final int NAVAGREGARCLIENTES =  R.id.menu_nav_clientes_agregar;
    public static final int NAVLISTADOCLIENTES =  R.id.menu_nav_clientes_listado;
    public static final int NAVAGREGARPRODUCTOS =  R.id.menu_nav_pructos_agrega;
    public static final int NAVLISTADOPRODUCTOS =  R.id.menu_nav_pructos_listado;
    public static final int NAVAGREGARPEDIDOS =  R.id.menu_nav_pedidos_agregar;
    public static final int NAVLISTADOPEDIDOS =  R.id.menu_nav_pedidos_listado;


    /* HEADER */
    private View head;
    private ImageViewCompat imgV;
    private ImageView imagenn2;
    private TextView nombreUsuario;


    @BindView(R.id.toolbar)
    Toolbar toolbarbind;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayoutbind;
    @BindView(R.id.navigation_view)
    NavigationView navigationViewbind;
    @BindView(R.id.home_content)
    FrameLayout frameLayoutBind;

    private SessionManager mSessionManager;


    /* ASYNC */
    /*private LeerListaDeProductostask leerListaDeProductostask;*/
    private List<EntityProductoPorUsuario> entityProductoPorUsuariosList;

    private static final int TIPO_INSERT = 2;
    private static final int TIPO_UPDATE = 3;
    private static final int TIPO_NINGUNO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_menu);
        ButterKnife.bind(this);
        mSessionManager = new SessionManager(this);

        setSupportActionBar((Toolbar) toolbarbind);
        toolbarbind.setElevation(0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayoutbind, toolbarbind, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayoutbind.addDrawerListener(toggle);
        toggle.syncState();
        navigationViewbind.setNavigationItemSelectedListener(this);
        drawerLayoutbind.addDrawerListener(this);

        View vie = navigationViewbind.getHeaderView(0);
        /* Imagen de menu  */
        imagenn2 = vie.findViewById(R.id.inicio_navheader_img);
        /* Nombre usuario */
        nombreUsuario = vie.findViewById(R.id.menu_nombreape);

        /* Traer de las preferencias el nombre */
        agregarNombreDelUsuario();

        fragmentos = (MenuInicioFragment) getSupportFragmentManager().findFragmentByTag(INICIO);

        if(fragmentos == null){
            fragmentos = MenuInicioFragment.newInstance();
            ActivityUtils.addFragmentToFragment(getSupportFragmentManager(),
                    fragmentos, R.id.home_content,INICIO);
            setTitle(INICIO);
            fragmentoTag = INICIO;
        }
    }

    public void agregarNombreDelUsuario(){
        String nombre = mSessionManager.getUserNameLogin();
        if (mSessionManager.getUserNameLogin() != null){
            if (!nombre.isEmpty() && !nombre.equals("")){
                nombreUsuario.setText(nombre);
            }else{
                nombreUsuario.setText("Usuario");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                mSessionManager.setUserLogeado(false);
                next(this, null, BaseCredencialesActivity.class, true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()) {

            case NAVINICIO:
               /* fragmentos = MenuInicioFragment.newInstance();*/
                fragmentos = (MenuInicioFragment) getSupportFragmentManager().findFragmentByTag(INICIO);
                NewIntance = MenuInicioFragment.newInstance();
                title = INICIO;
                break;
            case NAVAGREGARCLIENTES:
                fragmentos = (ClientesAgregarFragment) getSupportFragmentManager().findFragmentByTag(AGREGARCLIENTES);
                NewIntance = ClientesAgregarFragment.newInstance();
                title = AGREGARCLIENTES;
                break;
            case NAVLISTADOCLIENTES:
                fragmentos = (ClientesListadoFragment) getSupportFragmentManager().findFragmentByTag(LISTADOCLIENTES);
                NewIntance = ClientesListadoFragment.newInstance();
                title = LISTADOCLIENTES;
                break;
            case NAVAGREGARPRODUCTOS:
                fragmentos = (ProductosAgregarFragment) getSupportFragmentManager().findFragmentByTag(AGREGARPRODUCTOS);
                NewIntance = ProductosAgregarFragment.newInstance();
                title = AGREGARPRODUCTOS;
                break;
            case NAVLISTADOPRODUCTOS:
                fragmentos = (ProductosListadoFragment) getSupportFragmentManager().findFragmentByTag(LISTADOPRODUCTOS);
                NewIntance = ProductosListadoFragment.newInstance();
                title = LISTADOPRODUCTOS;
                break;
            case NAVAGREGARPEDIDOS:
                fragmentos = (PedidosAgregarFragment) getSupportFragmentManager().findFragmentByTag(AGREGARPEDIDOS);
                NewIntance = PedidosAgregarFragment.newInstance();
                title = AGREGARPEDIDOS;
                break;
            case NAVLISTADOPEDIDOS:
                fragmentos = (PedidosListadoFragment) getSupportFragmentManager().findFragmentByTag(LISTADOPEDIDOS);
                NewIntance = PedidosListadoFragment.newInstance();
                title = LISTADOPEDIDOS;
                break;
            default:
                break;
        }

        /*  Oculta el fragmento actual si en caso no estoy seleccionando el fragmento en el que estoy */
        if (!fragmentoTag.equals(title)){
            getSupportFragmentManager().findFragmentByTag(fragmentoTag).getView().setVisibility(View.GONE);
            fragmentoTag = title;
        }

        if (fragmentos != null) {
            getSupportFragmentManager().findFragmentByTag(title).getView().setVisibility(View.VISIBLE);
            setTitle(title);
            drawerLayoutbind.closeDrawer(GravityCompat.START);
            return true;
        }

        if (fragmentos == null) {
            fragmentos = NewIntance;
            ActivityUtils.addFragmentToFragment(getSupportFragmentManager(),
                    fragmentos, R.id.home_content,title);
        }

        setTitle(title);
        drawerLayoutbind.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostrarFragmento(String nombreFragmento,int navnuevo){

        getSupportFragmentManager().findFragmentByTag(fragmentoTag).getView().setVisibility(View.GONE);
        Fragment frag = getSupportFragmentManager().findFragmentByTag(nombreFragmento);
        if (frag != null){
            frag.getView().setVisibility(View.VISIBLE);
            setTitle(nombreFragmento);
            fragmentoTag = nombreFragmento;
            navigationViewbind.setCheckedItem(navnuevo);
        }else{
            getSupportFragmentManager().findFragmentByTag(INICIO).getView().setVisibility(View.VISIBLE);
            setTitle(INICIO);
            fragmentoTag = INICIO;
            navigationViewbind.setCheckedItem(NAVINICIO);
        }
    }


    @Override
    public void onBackPressed() {

        //Chequear si el drawer está abierto o no
        if (drawerLayoutbind.isDrawerOpen(GravityCompat.START)){
            drawerLayoutbind.closeDrawer(GravityCompat.START);
        }else {
            /*int size = getSupportFragmentManager().getFragments().size();
            String tag = getSupportFragmentManager().getFragments().get(size - 1).getTag();
            Log.e("FRAGMENT LIST", "--------------------->  " + String.valueOf(size));
            Log.e("TAG FRAGMENT 1", "--------------------->  " + tag);*/

            /* DECIR QUE EL NUEVO FRAGMENTOTAG ES INICIO */
                switch (fragmentoTag) {
                    case INICIO:
                        finish();
                        break;
                    case AGREGARCLIENTES:
                        mostrarFragmento(LISTADOCLIENTES,NAVLISTADOCLIENTES);
                        break;
                    case LISTADOCLIENTES:
                        mostrarFragmento(INICIO,NAVINICIO);
                        break;
                    case AGREGARPRODUCTOS:
                        mostrarFragmento(LISTADOPRODUCTOS,NAVLISTADOPRODUCTOS);
                        break;
                    case LISTADOPRODUCTOS:
                        mostrarFragmento(INICIO,NAVINICIO);
                        break;
                    case AGREGARPEDIDOS:
                        mostrarFragmento(LISTADOPEDIDOS,NAVLISTADOPEDIDOS);
                        break;
                    case LISTADOPEDIDOS:
                        mostrarFragmento(INICIO,NAVINICIO);
                        break;
                    case PAGARPEDIDOS:
                        mostrarFragmento(LISTADOPEDIDOS,NAVLISTADOPEDIDOS);
                        break;
                    default:
                        getSupportFragmentManager().popBackStack();
                        break;
                }
        }
    }


    /*  Inicio callback */
    @Override
    public void gotoListadoPedidosFragment() {
        /* Hacer click desde el navigator y ocultar donde estoy naa mas */
        MenuItem menuItem = navigationViewbind.getMenu().findItem(NAVLISTADOPEDIDOS);
        onNavigationItemSelected(menuItem);
        navigationViewbind.setCheckedItem(NAVLISTADOPEDIDOS);
    }


    /*  Listado de pediddos callbacks */
    @Override
    public void gotoInicioFragmentListado() {
        MenuItem menuItem = navigationViewbind.getMenu().findItem(NAVINICIO);
        onNavigationItemSelected(menuItem);
        navigationViewbind.setCheckedItem(NAVINICIO);
    }
    @Override
    public void gotoPedidoAgregarFragment() {
        MenuItem menuItem = navigationViewbind.getMenu().findItem(NAVAGREGARPEDIDOS);
        onNavigationItemSelected(menuItem);
        navigationViewbind.setCheckedItem(NAVAGREGARPEDIDOS);
    }
    @Override
    public void gotoPagarFragment() {

        fragmentos = (PedidosPagarFragment) getSupportFragmentManager().findFragmentByTag(PAGARPEDIDOS);
        NewIntance = PedidosPagarFragment.newInstance();
        title = PAGARPEDIDOS;
        getSupportFragmentManager().findFragmentByTag(fragmentoTag).getView().setVisibility(View.GONE);
        fragmentoTag = title;

        /*  Limpiar toda su data */
        if (fragmentos != null){
            PedidosPagarFragment frag = (PedidosPagarFragment) fragmentos;
            frag.limpiarData();
            getSupportFragmentManager().findFragmentByTag(fragmentoTag).getView().setVisibility(View.VISIBLE);
        }

        if (fragmentos == null) {
            fragmentos = NewIntance;
            ActivityUtils.addFragmentToFragment(getSupportFragmentManager(),
                    fragmentos, R.id.home_content,title);
        }

        setTitle(title);
    }


    /*  Pagar Pedidos callbacks */
    @Override
    public void gotoInicioFragmentPagar() {
        mostrarFragmento(INICIO,NAVINICIO);
    }
    @Override
    public void gotoBackPagar() {
        mostrarFragmento(LISTADOPEDIDOS,NAVLISTADOPEDIDOS);
    }


    @Override
    public void gotoInicioFragmentAgregar() {
        mostrarFragmento(INICIO,NAVINICIO);
    }
    @Override
    public void gotoBackAgregar() {
        mostrarFragmento(LISTADOPEDIDOS,NAVLISTADOPEDIDOS);
    }


    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}
    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        RotateAnimation rotarImagen = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,.5f,RotateAnimation.RELATIVE_TO_SELF,.5f);
        rotarImagen.setDuration(1000);
        imagenn2.startAnimation(rotarImagen);

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {}
    @Override
    public void onDrawerStateChanged(int newState) {}


    /* ASYNC */
    /*private class LeerListaDeProductostask extends AsyncTask<Void, Void, List<EntityProductoPorUsuario>> {

        @Override
        protected List<EntityProductoPorUsuario> doInBackground(Void... voids) {
            entityProductoPorUsuariosList = AppDb.getAppDb(getApplicationContext()).productoDAO().findAllProductos();
            return entityProductoPorUsuariosList;
        }
        @Override
        protected void onPostExecute(List<EntityProductoPorUsuario> entityProductoPorUsuarios){
            *//*verificarInformacionProdu(entityProductoPorUsuarios);*//*
        }
    }*/

    /*public void verificarInformacionProdu(List<EntityProductoPorUsuario> entityProductoPorUsuarios){

        if (entityProductoPorUsuarios != null){
            if (entityProductoPorUsuarios.size() >0 && !entityProductoPorUsuarios.isEmpty()){
                ArrayList<EntityProductoPorUsuario> entityListaDeProductosArray = (ArrayList<EntityProductoPorUsuario>) entityProductoPorUsuarios;
                listaProductos(entityListaDeProductosArray,TIPO_NINGUNO);
            }else{
                mPresenter.getListaProductos(TIPO_INSERT);
            }
        }else{
            mPresenter.getListaProductos(TIPO_INSERT);
        }
    }*/



}