package com.example.proyectomontpedidosinicial.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomontpedidosinicial.data.local.SessionManager;


public abstract  class BaseActivity extends AppCompatActivity {

    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    protected void next(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent = new Intent(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (destroy) context.finish();
    }

    protected void nextActivity(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }

    protected void newActivityClearPreview(Activity context, Bundle bundle, Class<?> activity) {
        Intent intent= new Intent(context,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        context.finish();
    }

    protected void nextActivityNewTask(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }
    

}
