package com.example.adminportatil.cebanc_tortillas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DatosCliente extends AppCompatActivity {
    private Button salir;
    private Button siguiente;
    private EditText nombre;
    private EditText direccion;
    private EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);
        salir=(Button)findViewById(R.id.btnSalir);
        siguiente=(Button)findViewById(R.id.btnSiguiente);
        nombre=(EditText)findViewById(R.id.edtNombre);
        direccion=(EditText)findViewById(R.id.edtDireccion);
        telefono=(EditText)findViewById(R.id.edtTelefono);

    }
}
