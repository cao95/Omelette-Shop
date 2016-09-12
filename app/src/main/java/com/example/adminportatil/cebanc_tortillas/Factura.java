package com.example.adminportatil.cebanc_tortillas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Factura extends AppCompatActivity {
////////////Declaracion de atributos a utilizar/////////

    private TextView  txtPrecioTortillas,txtCantidadTortillas,txtCantidadBebidas,txtPrecioBebidas,txtTotalImporte,txtdatosNombre,txtdatosDireccion,txtdatosTelefono, txtImporteTotal;
    private Button salir, reiniciar, calcularImporte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
//////////Asignacion de los campos del layout mediantes IDS a sus atributos correspondientes/////////
///////////////TextViews////////////////////////////
        txtCantidadBebidas = (TextView) findViewById(R.id.txtCantidadBebidas);
        txtPrecioBebidas = (TextView) findViewById(R.id.txtPrecioBebidas);
        txtTotalImporte = (TextView) findViewById(R.id.txtImporteTotal);
        txtdatosNombre = (TextView) findViewById(R.id.txtDatosNombre);
        txtdatosDireccion = (TextView) findViewById(R.id.txtDatosDireccion);
        txtdatosTelefono = (TextView) findViewById(R.id.txtDatosTelefono);
        txtImporteTotal=(TextView) findViewById(R.id.txtImporteTotal);
        txtCantidadTortillas=(TextView) findViewById(R.id.txtCantidadTortillas);
        txtPrecioTortillas=(TextView) findViewById(R.id.txtPrecioTortillas);
//////////////Button////////////////////////////
        calcularImporte=(Button)findViewById(R.id.buttonCalcularImporte);
        salir=(Button)findViewById(R.id.buttonSalir);
        reiniciar=(Button)findViewById(R.id.buttonReiniciar);
/////////////////Codigo para bloquear el scroll horizontal de la pantalla/////////////////
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ////////Recoger todos los datos/////////////////
         Bundle extras = getIntent().getExtras();
         Integer cantidad = extras.getInt("CantidadTotalTortillas ");
         Integer datosCantidadBebidas = extras.getInt("CantidadBebidas ");
         Integer datosTelefono = extras.getInt("Telefono ");
         Float datosDinero = extras.getFloat("CantidadDinero ");
         Float precioTotal=extras.getFloat("PrecioTotal ");
         String datosNombre = extras.getString("Nombre ");
         String datosDireccion = extras.getString("Direccion ");

////////Pasar los datos a los campos correspondientes///////////
         txtCantidadBebidas.setText(Integer.toString(datosCantidadBebidas));
         txtPrecioBebidas.setText(Float.toString(datosDinero));
         txtdatosNombre.setText(datosNombre);
         txtdatosDireccion.setText(datosDireccion);
         txtdatosTelefono.setText(Integer.toString(datosTelefono));
         txtCantidadTortillas.setText(Integer.toString(cantidad));
         txtPrecioTortillas.setText((Float.toString(precioTotal)));

////////////////////Listeners para los dos botones, salir, reiniciar y calcularImporte///////////////////
         salir.setOnClickListener(new Button.OnClickListener() {
             public void onClick(View view) {
                 SalirAplicacion(null);
             }
         });
         reiniciar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                ReiniciarAplicacion(null);
            }
        });
         calcularImporte.setOnClickListener(new Button.OnClickListener() {
             public void onClick(View view) {
                 CalcularImporte(null);
             }
         });
    }

///////////////Metodos de los botones, salir, reiniciar y calcular el importe////////////////////
    public void SalirAplicacion(View view){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(this, "Que tenga un buen dia, gracias por su pedido", Toast.LENGTH_SHORT).show();
    }
    public void ReiniciarAplicacion(View view){
        Intent intent = new Intent(Factura.this, DatosCliente.class);
        startActivity(intent);
    }
    public void CalcularImporte(View view){
        double total;

        Double precioBebidas=Double.parseDouble(txtPrecioBebidas.getText().toString());
        Double precioTortillas=Double.parseDouble(txtPrecioTortillas.getText().toString());
        total=precioBebidas+precioTortillas;
        txtImporteTotal.setText(String.valueOf(total));

        PedidosSQLiteHelper admin = new  PedidosSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nomb = txtdatosNombre.getText().toString();
        String direc = txtdatosDireccion.getText().toString();
        String telef = txtdatosTelefono.getText().toString();
        String bebidacomprada = txtCantidadBebidas.getText().toString();
        String preciobebidas = txtPrecioBebidas.getText().toString();
        String cantidadtortillas = txtCantidadTortillas.getText().toString();
        String preciotortillas = txtPrecioTortillas.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nomb);
        registro.put("direccion", direc);
        registro.put("telefono", telef);
        registro.put("bebidacomprada", bebidacomprada);
        registro.put("preciobebidas", preciobebidas);
        registro.put("cantidadtortillas", cantidadtortillas);
        registro.put("preciotortillas", preciotortillas);

        bd.insert("Pedidos", null, registro);
        bd.close();
        Toast.makeText(this, "Se cargaron los datos del pedido",
                Toast.LENGTH_SHORT).show();






        if(total>50){
            Toast.makeText(this, "Felicidades ha ganado un vale descuento del 40%", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Consiga un vale descuento haciendo una comprar superior a 50€", Toast.LENGTH_SHORT).show();
        }

    }
}
