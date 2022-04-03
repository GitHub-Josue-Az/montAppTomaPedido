package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosPago;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/*implements LoginContract.View*/
public class PedidosPagarFragment extends BaseFragment{

    private static final String TAG = PedidosPagarFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;


    /* Fotos  */
    private static final int COD_FOTO = 20;
    private String pathh;
    File fileImagen;
    private long bytesize;
    private byte[] bytess;

    private static final String CARPETA_PRINCIPAL = "montGroupImagenes/";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL ;


    /* Fecha */
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String GUION = "-";
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    @BindView(R.id.ln_pedidos_pagar_regresar)
    LinearLayoutCompat lnPagarRegresar;
    @BindView(R.id.ln_pedidos_pagar_inicio)
    LinearLayoutCompat lnPagarInicio;
    @BindView(R.id.et_pedidos_pagar_banco)
    AppCompatEditText etPagarBanco;
    @BindView(R.id.et_pedidos_pagar_op)
    AppCompatEditText etPagarOP;
    @BindView(R.id.et_pedidos_pagar_monto)
    AppCompatEditText etPagarMonto;
    @BindView(R.id.et_pedidos_pagar_fecha)
    AppCompatEditText etPagarFecha;
    @BindView(R.id.et_pedidos_pagar_cuenta)
    AppCompatEditText etPagarCuenta;
    @BindView(R.id.iv_pedidos_pagar_foto_pagar)
    ImageView ivPedidosPagar;
    @BindView(R.id.imagenprevia)
    ImageView ivPedidosprevio;
    @BindView(R.id.name_image)
    TextView tvnombreImagen;
    @BindView(R.id.ln_pedidos_pagar_deletelayou)
    LinearLayoutCompat lnpedidospagardelete;
    @BindView(R.id.ln_pedidos_pagar_options_camera)
    RelativeLayout rlpedidospagaroptions;


    private LoginContract.Presenter mPresenter;
    private String nombrefoto;


    public PedidosPagarFragment() {
    }

    public interface changeFragmentActivity {
        void gotoInicioFragmentPagar();
        void gotoBackPagar();
    }

    public static PedidosPagarFragment newInstance() {
        return new PedidosPagarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedidos_pagar, container, false);
        ButterKnife.bind(this, root);


        /* TODO: VER LA LIMPIEZA DE DATOS Y QUE DATOS MANDAMOS AL REGISTRAR */

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");
        lnPagarInicio.setOnClickListener(view ->gotoPagarInicio());
        lnPagarRegresar.setOnClickListener(view ->gotoPagarRegresar());
        ivPedidosPagar.setOnClickListener(view -> gotoTomarFoto());
        etPagarFecha.setOnClickListener(view->showPopUpAgregarFecha());
        lnpedidospagardelete.setOnClickListener(view->gotoDeleteFile());

        return root;
    }

    private void gotoDeleteFile() {

        tvnombreImagen.setText("");
        ivPedidosprevio.setImageResource(R.drawable.logomont);
        /*rlpedidospagaroptions.setVisibility(View.GONE);*/
    }

    private void showPopUpAgregarFecha() {

        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                /* (((ViewHolder) holder).editFechaProblemaSmall).setText(diaFormateado + BARRA + mesFormateado + BARRA + year);*/
                etPagarFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            } }, anio, mes, dia);
        /* Dias anteriores no valen */
        recogerFecha.getDatePicker().setMinDate(new Date().getTime());
        recogerFecha.show();

    }

    private void gotoPagarRegresar() {
        mCallback.gotoBackPagar();
    }

    private void gotoPagarInicio() {
        mCallback.gotoInicioFragmentPagar();
    }

    public void limpiarData(){
        if (!etPagarBanco.getText().toString().isEmpty() || etPagarBanco.getText().toString().length() >0){
            etPagarBanco.setText("");
        }
        if (!etPagarOP.getText().toString().isEmpty() || etPagarOP.getText().toString().length() >0){
            etPagarOP.setText("");
        }
        if (!etPagarMonto.getText().toString().isEmpty() || etPagarMonto.getText().toString().length() >0){
            etPagarMonto.setText("");
        }
        if (!etPagarFecha.getText().toString().isEmpty() || etPagarFecha.getText().toString().length() >0){
            etPagarFecha.setText("");
        }
        if (!etPagarCuenta.getText().toString().isEmpty() || etPagarCuenta.getText().toString().length() >0){
            etPagarCuenta.setText("");
        }

        if (!tvnombreImagen.getText().toString().isEmpty() || tvnombreImagen.getText().toString().length() >0){
            tvnombreImagen.setText("");
        }
         if (!ivPedidosprevio.getDrawable().equals(R.drawable.logomont)){
             ivPedidosprevio.setImageResource(R.drawable.logomont);
        }


    }

    public void gotoTomarFoto(){

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                fotosConPermiso();
            } else {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        } else {
            fotosConPermiso();
        }
    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(getContext(), permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    private void requestPermission(String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
            Toast.makeText(getContext(), "Por favor permitir permisos", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, 1);
            /*fotosConPermiso();*/
        }
    }

    private void fotosConPermiso(){


        File miFiler = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),CARPETA_PRINCIPAL);

        boolean isCreate = miFiler.exists();

        if (!isCreate){ isCreate = miFiler.mkdirs(); }

        if (isCreate){
            Long consecutivo = System.currentTimeMillis()/1000;
            nombrefoto = "mont"+consecutivo.toString()+".jpg";

            /*Toast.makeText(getContext(),"Entra2",Toast.LENGTH_SHORT).show();*/

            pathh = Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN+File.separator+nombrefoto;
            fileImagen = new File(pathh);

            Uri fotoUri = FileProvider.getUriForFile(getContext(),"com.gob.proyectomontpedidosinicial.provider",fileImagen);

            Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            /*intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));*/
            intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);

            someActivityResultLauncher.launch(intentPhoto);
        }
    }


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                            Log.e(TAG,pathh);

                            if (pathh != null) {

                                File filem = new File(pathh);

                                bytesize = filem.length();

                                /* Peso maximo */
                                if (bytesize < 8000000){
                                    String concatenar = new File(pathh).getName();

                                    try {
                                        bytess = getFileBytes(filem);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    Toast.makeText(getContext(),"Imagen realizada",Toast.LENGTH_LONG).show();

                                    String encoded = android.util.Base64.encodeToString(bytess, android.util.Base64.DEFAULT);

                                    Bitmap bitmap = BitmapFactory.decodeFile(pathh);
                                    ivPedidosprevio.setImageBitmap(bitmap);

                                    tvnombreImagen.setText(nombrefoto);

                                    /*editAdjuntoRecycler = vieww.findViewById(R.id.edit_seleccionar_archivos_small);
                                    editAdjuntoRecycler.setText(concatenar);

                                    mAdapter.getItems().get(posicionItem).setArchiv(encoded);*/

                                    /* editSeleccionarArchivos.setText(concatenar);*/
                                }else{
                                    Toast.makeText(getContext(),"Se excede los 8MB",Toast.LENGTH_LONG).show();
                                   /* mAdapter.getItems().get(posicionItem).setArchiv("");*/
                                }
                            }
                    }
                }
            });


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



    public static byte[] getFileBytes(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1)
                ous.write(buffer, 0, read);
        } finally {
            try {
                if (ous != null)
                    ous.close();
            } catch (IOException e) {
                // swallow, since not that important
            }
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
                // swallow, since not that important
            }
        }
        return ous.toByteArray();
    }


}
