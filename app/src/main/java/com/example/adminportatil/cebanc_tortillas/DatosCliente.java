package com.example.adminportatil.cebanc_tortillas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatosCliente extends AppCompatActivity {

////////////Declaracion de atributos a utilizar/////////
    private Button salir, siguiente, buscar;
    private EditText nombre, direccion, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);

//////////Asignacion de los campos del layout mediantes IDS a sus atributos correspondientes/////////
        salir=(Button)findViewById(R.id.btnSalir);
        siguiente=(Button)findViewById(R.id.btnSiguiente);
        buscar=(Button)findViewById(R.id.btnBuscarCliente);
        nombre=(EditText)findViewById(R.id.edtNombre);
        direccion=(EditText)findViewById(R.id.edtDireccion);
        telefono=(EditText)findViewById(R.id.edtTelefono);
/////////////////Codigo para bloquear el scroll horizontal de la pantalla/////////////////
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

////////////////Listeners para los dos botones, siguiente y salir.
        siguiente.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                LanzarOpciones(null);
            }
        });
        salir.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                SalirAplicacion(null);
            }
        });
        buscar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                BuscaCliente(null);
            }
        });
    }
////////////////Método para llamar al siguiente layout, en "LanzarOpciones" llamamos a OpcionesTortilla y pasamos los datos del cliente (Nombre, Dirección y Telefono).

    public void LanzarOpciones(View view) {
        if (!nombre.getText().toString().equals("") && !direccion.getText().toString().equals("") && !telefono.getText().toString().equals("")) {
            Intent intent = new Intent(DatosCliente.this, OpcionesTortilla.class);
            startActivity(intent);

            intent.putExtra("Nombre ", nombre.getText().toString());
            intent.putExtra("Direccion ", direccion.getText().toString());
            intent.putExtra("Telefono ", Integer.parseInt(telefono.getText().toString()));
            startActivityForResult(intent, 1234);

///////////////////////////////////Guardamos datos en la BBDD////////////////////////////////
            UsuariosSQLiteHelper admin = new  UsuariosSQLiteHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String nomb = nombre.getText().toString();
            String direc = direccion.getText().toString();
            String telef = telefono.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("nombre", nomb);
            registro.put("direccion", direc);
            registro.put("telefono", telef);
            bd.insert("Usuarios", null, registro);
            bd.close();
            nombre.setText("");
            direccion.setText("");
            telefono.setText("");
            Toast.makeText(this, "Se cargaron los datos de la persona",
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Error, Escriba los datos necesarios", Toast.LENGTH_SHORT).show();
        }
    }
    ///////////////Método para buscar el cliente.
    public void BuscaCliente(View view){
        UsuariosSQLiteHelper admin = new UsuariosSQLiteHelper (this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nomb = nombre.getText().toString();
        //String direc = direccion.getText().toString();
        //String telef = telefono.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, telefono, direccion from Usuarios where nombre= '" +nomb+ "'", null);
        if (fila.moveToFirst()) {
            nombre.setText(fila.getString(0));
            telefono.setText(fila.getString(1));
            direccion.setText(fila.getString(2));
            Toast.makeText(this, "Este usuario existe",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "No existe una persona con dichos datos",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }
    }
///////////////Método para salir de la aplicación.
    public void SalirAplicacion(View view){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}


