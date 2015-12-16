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
    private Spinner tipoTortilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_tortilla);

    }
}


