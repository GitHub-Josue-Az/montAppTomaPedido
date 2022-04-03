package com.gob.proyectomontpedidosinicial.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.gob.proyectomontpedidosinicial.R;



public class ProgressDialogCustom extends ProgressDialog {


    public ProgressDialogCustom(Context context, String text) {
        super(context);
        setIndeterminate(true);
        setMessage(text);
        setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setCancelable(false);
        setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.circle_progress));
    }


}
