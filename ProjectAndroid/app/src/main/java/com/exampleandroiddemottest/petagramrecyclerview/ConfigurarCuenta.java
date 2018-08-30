package com.exampleandroiddemottest.petagramrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ConfigurarCuenta extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        toolbar = findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }


    }

    public void btnGuardarCuenta(View view) {

        Toast.makeText(this,"No se puedo guardar cuenta por que ya no existe el endpoint",Toast.LENGTH_LONG).show();
    }
}
