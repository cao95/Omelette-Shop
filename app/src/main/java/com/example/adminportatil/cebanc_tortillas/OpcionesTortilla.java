package com.example.adminportatil.cebanc_tortillas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
public class OpcionesTortilla extends AppCompatActivity {
    ////////////Declaracion de atributos a utilizar/////////
    private Button salir, siguiente, calcular;
    private Spinner tamaño, tipoHuevo, tipoTortilla;
    private EditText cantidad;
    private TextView txtTotal,txtCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_tortilla);
//////////Asignacion de los campos del layout mediantes IDS a sus atributos correspondientes/////////
        ///////////////Button//////////////
        calcular= (Button) findViewById(R.id.btnCalcular);
        salir = (Button) findViewById(R.id.btnSalir);
        siguiente = (Button) findViewById(R.id.btnSiguiente);
        //////////////Spinners////////////////////
        tamaño = (Spinner) findViewById(R.id.spinner_tamaño);
        tipoHuevo = (Spinner) findViewById(R.id.spinner_tipoHuevo);
        tipoTortilla = (Spinner) findViewById(R.id.spinner_tipoTortilla);
        ///////////////EditText//////////////////
        cantidad = (EditText) findViewById(R.id.editText_Cantidad);
        //////////////////TextViews///////////////////
        txtCantidad = (TextView) findViewById(R.id.editText_Cantidad);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
/////////////////Codigo para bloquear el scroll horizontal de la pantalla/////////////////
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

/////////////////Uso de Botones mediante listeners////////////////////////////
        salir.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                SalirAplicacion(null);
            }
        });
        siguiente.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                SiguienteBebidas(null);

            }
        });
        calcular.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                calcular();

            }
        });

//////////////////Lista desplegable para el tamaño de la tortilla.

        ArrayAdapter<CharSequence> tamañoAdaptador = ArrayAdapter.createFromResource(this, R.array.Tamaño, android.R.layout.simple_spinner_item);
        tamañoAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tamaño.setAdapter(tamañoAdaptador);

//////////////////////Lista desplegable para el tipo de huevo de la tortilla.

        ArrayAdapter<CharSequence> tipoHuevoAdaptador = ArrayAdapter.createFromResource(this, R.array.Tipo_Huevos, android.R.layout.simple_spinner_item);
        tipoHuevoAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoHuevo.setAdapter(tipoHuevoAdaptador);

///////////////////Lista desplegable para el tipo de tortilla.

        ArrayAdapter<CharSequence> tipoTortillaAdaptador = ArrayAdapter.createFromResource(this, R.array.Tipo_Tortilla, android.R.layout.simple_spinner_item);
        tipoTortillaAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoTortilla.setAdapter(tipoTortillaAdaptador);


////////////Método para ir al layout de Bebidas, recoger los datos que nos envian desde DatosCliente y mandarlos al layout Bebidas.
    }
////////////////Metodo para ir al siguiente layout Bebidas////////////////
    public void SiguienteBebidas(View view) {
//////////////////Validar que se an seleccionado los datos necesarios para pasar al siguiente spinner////////////////////////7
        if (!cantidad.getText().toString().equals("")) {
            Intent intent = new Intent(OpcionesTortilla.this, Bebidas.class);
            startActivity(intent);

            Bundle extras = getIntent().getExtras();
/////////////////Recoger datos de DatosCliente////////////////////////////////
            String datosNombre = extras.getString("Nombre ");
            String datosDireccion = extras.getString("Direccion ");
            Integer datosTelefono = extras.getInt("Telefono ");

//////////////Enviar datos al layout bebidas///////////////////////////
            intent.putExtra("CantidadTotalTortillas ",Integer.parseInt(cantidad.getText().toString()));
            intent.putExtra("PrecioTotal ", Float.parseFloat(txtTotal.getText().toString()));
            intent.putExtra("Nombre ", datosNombre);
            intent.putExtra("Direccion ", datosDireccion);
            intent.putExtra("Telefono ", datosTelefono);
            startActivityForResult(intent, 1234);

///////////////////////////////////Guardamos datos en la BBDD////////////////////////////////
            PedidosSQLiteHelper admin = new  PedidosSQLiteHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String cantidad = txtCantidad.getText().toString();
            String tama = tamaño.getSelectedItem().toString();
            String tiphuevo = tipoHuevo.getSelectedItem().toString();
            String tiptortilla = tipoTortilla.getSelectedItem().toString();
            String preciotota = txtTotal.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("cantidad", cantidad);
            registro.put("tamaño", tama);
            registro.put("tipohuevo", tiphuevo);
            registro.put("tipotortilla", tiptortilla);
            registro.put("preciototal", preciotota);
            bd.insert("Pedidos", null, registro);
            bd.close();
            Toast.makeText(this, "Se cargaron los datos de las tortillas",
                    Toast.LENGTH_SHORT).show();






        } else {
            Toast.makeText(this, "Error, escriba una cantidad", Toast.LENGTH_SHORT).show();
        }
    }

/////////////////Método para volver al layout anterior (Datos Cliente)/////////////////////////////////
    public void SalirAplicacion(View view) {
        Intent intent = new Intent(this, DatosCliente.class);
        startActivity(intent);

    }
////////////////Metodo para hacer el calculo total//////////////////
    public void calcular() {
        double precioTamaño = 0;
        double precioHuevos = 0;
        double precioTortilla = 0;
        double sumatodo;

        if (tamaño.getSelectedItemPosition() != 0 && tamaño.getSelectedItemPosition() != 1) {
            precioTamaño = 22.99;
        }
        else if(tamaño.getSelectedItemPosition() != 0 && tamaño.getSelectedItemPosition() != 2){
            precioTamaño = 12.19;
        }
        else if (tamaño.getSelectedItemPosition() != 1 && tamaño.getSelectedItemPosition() != 2) {
            precioTamaño = 8.29;
        }

        if (tipoHuevo.getSelectedItemPosition() != 0 && tipoHuevo.getSelectedItemPosition() != 1) {
            precioHuevos = 5.49;
        }
        else if (tipoHuevo.getSelectedItemPosition() != 0 && tipoHuevo.getSelectedItemPosition() != 2) {
            precioHuevos = 3.39;
        }
        else if (tipoHuevo.getSelectedItemPosition() != 1 && tipoHuevo.getSelectedItemPosition() != 2)   {
            precioHuevos = 5.23;
        }


        if (tipoTortilla.getSelectedItemPosition() != 0 && tipoTortilla.getSelectedItemPosition() != 1 && tipoTortilla.getSelectedItemPosition() != 2 && tipoTortilla.getSelectedItemPosition() != 3 && tipoTortilla.getSelectedItemPosition() != 4) {
            precioTortilla = 5.69;}
        else if (tipoTortilla.getSelectedItemPosition() != 0 && tipoTortilla.getSelectedItemPosition() != 1 && tipoTortilla.getSelectedItemPosition() != 2 && tipoTortilla.getSelectedItemPosition() != 3 && tipoTortilla.getSelectedItemPosition() != 5 )  {
            precioTortilla = 11.59;}

        else if (tipoTortilla.getSelectedItemPosition() != 0 && tipoTortilla.getSelectedItemPosition() != 1 && tipoTortilla.getSelectedItemPosition() != 2 && tipoTortilla.getSelectedItemPosition() != 4 && tipoTortilla.getSelectedItemPosition() != 5 )   {
            precioTortilla = 19.29; }

        else if (tipoTortilla.getSelectedItemPosition() != 0 && tipoTortilla.getSelectedItemPosition() != 1 && tipoTortilla.getSelectedItemPosition() != 3 && tipoTortilla.getSelectedItemPosition() != 4 && tipoTortilla.getSelectedItemPosition() != 5 )  {
            precioTortilla = 16.89;  }
        else if (tipoTortilla.getSelectedItemPosition() != 0 && tipoTortilla.getSelectedItemPosition() != 2 && tipoTortilla.getSelectedItemPosition() != 3 && tipoTortilla.getSelectedItemPosition() != 4 && tipoTortilla.getSelectedItemPosition() != 5 )  {
            precioTortilla = 10.19;   }
        else if (tipoTortilla.getSelectedItemPosition() != 5 && tipoTortilla.getSelectedItemPosition() != 1 && tipoTortilla.getSelectedItemPosition() != 2 && tipoTortilla.getSelectedItemPosition() != 3 && tipoTortilla.getSelectedItemPosition() != 4 )  {
            precioTortilla = 12.79;

        }
        sumatodo =   (precioTamaño + precioHuevos + precioTortilla);
        double TotalMulti = sumatodo * Double.parseDouble(txtCantidad.getText().toString());
        DecimalFormat precision = new DecimalFormat("0.00");
        txtTotal.setText(precision.format(TotalMulti));

    }

}


