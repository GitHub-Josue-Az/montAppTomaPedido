package com.gob.proyectomontpedidosinicial.presentation;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseActivity;
import com.gob.proyectomontpedidosinicial.presentation.login.BaseCredencialesActivity;

public class SplashActivity extends BaseActivity{

    public final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next(SplashActivity.this,null, BaseCredencialesActivity.class,true);
            }
        }, 4000);

    }




}
