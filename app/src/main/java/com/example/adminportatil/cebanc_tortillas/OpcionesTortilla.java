package com.example.adminportatil.cebanc_tortillas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;

public class OpcionesTortilla extends AppCompatActivity {
    private Button salir;
    private Button siguiente;
    private Spinner tamaño;
    private Spinner tipoHuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_tortilla);

        salir=(Button)findViewById(R.id.btnSalir);
        siguiente=(Button)findViewById(R.id.btnSiguiente);
        tamaño=(Spinner)findViewById(R.id.spinner_tamaño);
        tipoHuevo=(Spinner)findViewById(R.id.spinner_tipoHuevo);
        tipoTortilla=(Spinner)findViewById(R.id.spinner_tipoTortilla);
    }
}


