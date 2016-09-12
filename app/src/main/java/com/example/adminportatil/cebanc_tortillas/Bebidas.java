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

import java.text.DecimalFormat;

public class Bebidas extends AppCompatActivity {
////////////Declaracion de atributos a utilizar/////////
    private TextView txtCantidadCocaCola, txtCantidadLimon, txtCantidadNaranja,txtCantidadNestea, txtCantidadCerveza, txtCantidadAgua, txtCantidadTotaldeBebidas, txtCantidadTotalDinero;
    private Button sumarCocacola, restarCocacola, sumarLimon, restarLimon, sumarNaranja, restarNaranja, sumarNestea, restarNestea, sumarCerveza, restarCerveza, sumarAgua, restarAgua, siguiente;
    private Button Calcular, Atras, Siguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);
        /////////////////Codigo para bloquear el scroll horizontal de la pantalla/////////////////
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//////////Asignacion de los campos del layout mediantes IDS a sus atributos correspondientes/////////
/////////Text Views///////////////////////
        txtCantidadCocaCola= (TextView) findViewById(R.id.txtCantidadCocacola);
        txtCantidadLimon= (TextView) findViewById(R.id.txtCantidadLimon);
        txtCantidadNaranja= (TextView) findViewById(R.id.txtCantidadNaranja);
        txtCantidadNestea= (TextView) findViewById(R.id.txtCantidadNestea);
        txtCantidadCerveza= (TextView) findViewById(R.id.txtCantidadCerveza);
        txtCantidadAgua= (TextView) findViewById(R.id.txtCantidadAgua);
        txtCantidadTotaldeBebidas=(TextView) findViewById(R.id.txtCantidadTotalBebidas);
        txtCantidadTotalDinero=(TextView) findViewById(R.id.txtCantidadtotaldinero);
/////////Botones/////////////////////
        sumarCocacola = (Button)findViewById(R.id.buttonCocacola);
        restarCocacola = (Button)findViewById(R.id.buttonRestarCocacola);
        sumarLimon = (Button)findViewById(R.id.buttonLimon);
        restarLimon = (Button)findViewById(R.id.buttonRestarLimon);
        sumarNaranja = (Button)findViewById(R.id.buttonNaranja);
        restarNaranja = (Button)findViewById(R.id.buttonRestarNaranja);
        sumarNestea = (Button)findViewById(R.id.buttonSumarnestea);
        restarNestea = (Button)findViewById(R.id.buttonRestarNestea);
        sumarCerveza = (Button)findViewById(R.id.buttonSumarcerveza);
        restarCerveza = (Button)findViewById(R.id.buttonRestarcerveza);
        sumarAgua = (Button)findViewById(R.id.buttonSumaragua);
        restarAgua = (Button)findViewById(R.id.buttonRestaragua);
        Calcular = (Button)findViewById(R.id.Calcular);
        Atras = (Button)findViewById(R.id.buttonAtras);
        Siguiente = (Button)findViewById(R.id.btnSiguiente);

/////////////////////////////Uso de botones mediantes listeners////////////////////////////
        sumarCocacola.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarCocacola(null);

            }
        });

        restarCocacola.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarCocacola(null);
            }
        });

        sumarLimon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarLimon(null);
            }
        });

        restarLimon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarLimon(null);
            }
        });

        sumarNaranja.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarNaranja(null);
            }
        });

        restarNaranja.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarNaranja(null);
            }
        });

        sumarNestea.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarNestea(null);
            }
        });

        restarNestea.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarNestea(null);
            }
        });

        sumarCerveza.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarCerveza(null);
            }
        });

        restarCerveza.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarCerveza(null);
            }
        });

        sumarAgua.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarAgua(null);
            }
        });

        restarAgua.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarAgua(null);
            }
        });
        Calcular.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContadoresTotal();
                eurosTotal();
            }
        });

        Atras.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnteriorLayout(null);
            }
        });
        Siguiente.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
               LanzarOpciones(null);
            }
        });
    }

/////////////////////Variables de contadores////////////////////////////

    int Ccocacola = 0;
    int Cnaranja = 0;
    int Climon= 0;
    int CNestea = 0;
    int Ccerveza = 0;
    int Cagua = 0;
    int contadoresTotal = 0;
    double totalEuros = 0;

/////////////////////////Metodos para sumar y restar/////////////////////////

    public void sumarCocacola(View view){

        Ccocacola=Ccocacola+1;
        String resultado=String.valueOf(Ccocacola);
        txtCantidadCocaCola.setText(resultado);
    }

    public void restarCocacola(View view) {

        Ccocacola = Ccocacola - 1;

        String resultado = String.valueOf(Ccocacola);

        if (Integer.parseInt(resultado) <=1){
            Toast.makeText(this, "No puede bajar de 0", Toast.LENGTH_SHORT).show();
            Ccocacola = 0;
            resultado = String.valueOf(Ccocacola);
        }
        txtCantidadCocaCola.setText(resultado);
    }

    public void sumarLimon(View view){

        Climon++;
        String resultado=String.valueOf(Climon);
        txtCantidadLimon.setText(resultado);
        }
    public void restarLimon(View view){

        Climon--;
        String resultado=String.valueOf(Climon);
        if (Integer.parseInt(resultado) <=1){
            Toast.makeText(this, "No puede bajar de 0", Toast.LENGTH_SHORT).show();
            Climon = 0;
            resultado = String.valueOf(Climon);
        }
        txtCantidadLimon.setText(resultado);
    }
    public void sumarNaranja(View view){

        Cnaranja++;
        String resultado=String.valueOf(Cnaranja);
        txtCantidadNaranja.setText(resultado);
    }
    public void restarNaranja(View view){

        Cnaranja--;
        String resultado=String.valueOf(Cnaranja);
        if (Integer.parseInt(resultado) <=1){
            Toast.makeText(this, "No puede bajar de 0", Toast.LENGTH_SHORT).show();
            Cnaranja = 0;
            resultado = String.valueOf(Cnaranja);
        }
        txtCantidadNaranja.setText(resultado);
    }
    public void sumarNestea(View view){

       CNestea++;
        String resultado=String.valueOf(CNestea);
        txtCantidadNestea.setText(resultado);
    }
    public void restarNestea(View view){

        CNestea--;
        String resultado=String.valueOf(CNestea);
        if (Integer.parseInt(resultado) <=1){
            Toast.makeText(this, "No puede bajar de 0", Toast.LENGTH_SHORT).show();
            CNestea = 0;
            resultado = String.valueOf(CNestea);
        }
        txtCantidadNestea.setText(resultado);
    }
    public void sumarCerveza(View view){

        Ccerveza++;
        String resultado=String.valueOf(Ccerveza);
        txtCantidadCerveza.setText(resultado);
    }
    public void restarCerveza(View view){

        Ccerveza--;
        String resultado=String.valueOf(Ccerveza);
        if (Integer.parseInt(resultado) <=1){
            Toast.makeText(this, "No puede bajar de 0", Toast.LENGTH_SHORT).show();
            Ccerveza = 0;
            resultado = String.valueOf(Ccerveza);
        }
        txtCantidadCerveza.setText(resultado);
    }
    public void sumarAgua(View view){

       Cagua++;
        String resultado=String.valueOf(Cagua);
        txtCantidadAgua.setText(resultado);
    }
    public void restarAgua(View view){

       Cagua--;
        String resultado=String.valueOf(Cagua);
        if (Integer.parseInt(resultado) <=1){
            Toast.makeText(this, "No puede bajar de 0", Toast.LENGTH_SHORT).show();
            Cagua = 0;
            resultado = String.valueOf(Cagua);
        }
        txtCantidadAgua.setText(resultado);
    }
///////////////Metodos para sumar la cantidad total/////////
    public void getContadoresTotal() {
        contadoresTotal = Integer.parseInt(txtCantidadCocaCola.getText().toString())
                + Integer.parseInt(txtCantidadLimon.getText().toString())
                + Integer.parseInt(txtCantidadNaranja.getText().toString())
                + Integer.parseInt(txtCantidadNestea.getText().toString())
                + Integer.parseInt(txtCantidadCerveza.getText().toString())
                + Integer.parseInt(txtCantidadAgua.getText().toString());

        String resultado = String.valueOf(contadoresTotal);
        txtCantidadTotaldeBebidas.setText(resultado);
    }

    public void eurosTotal(){
        totalEuros =  Float.parseFloat(txtCantidadCocaCola.getText().toString())* 1.99 +
                Float.parseFloat(txtCantidadLimon.getText().toString()) * 0.99+
                Float.parseFloat(txtCantidadNaranja.getText().toString()) * 1.49+
                Float.parseFloat(txtCantidadNestea.getText().toString()) * 3.99+
                Float.parseFloat(txtCantidadCerveza.getText().toString()) * 1.99+
                Float.parseFloat(txtCantidadAgua.getText().toString()) * 0.50 ;


        DecimalFormat precision = new DecimalFormat("0.00");
        txtCantidadTotalDinero.setText(precision.format(totalEuros));

    }
////////////Metodo para lanzar el layout de la factura//////////////////////
    public void LanzarOpciones(View view) {
        if (!txtCantidadTotaldeBebidas.getText().toString().equals(0) && !txtCantidadTotalDinero.getText().toString().equals(0)) {
            Intent intent = new Intent(Bebidas.this, Factura.class);
            startActivity(intent);

            Bundle extras = getIntent().getExtras();
            /////////////////Recoger datos de DatosCliente y OpcionesTortilla////////////////////////////////
            String datosNombre = extras.getString("Nombre ");
            String datosDireccion = extras.getString("Direccion ");
            Integer datosTelefono = extras.getInt("Telefono ");
            Integer cantidad = extras.getInt("CantidadTotalTortillas ");
            Float precioTotal=extras.getFloat("PrecioTotal ");

//////////////Enviar datos de DatosCliente, OpcionesTortilla y Bebidas al layout Factura///////////////////////////
            intent.putExtra("CantidadDinero ", Float.parseFloat(txtCantidadTotalDinero.getText().toString()));
            intent.putExtra("CantidadBebidas ", Integer.parseInt(txtCantidadTotaldeBebidas.getText().toString()));
            intent.putExtra("CantidadTotalTortillas ", cantidad);
            intent.putExtra("PrecioTotal ", precioTotal);
            intent.putExtra("Nombre ", datosNombre);
            intent.putExtra("Direccion ", datosDireccion);
            intent.putExtra("Telefono ", datosTelefono);

            startActivityForResult(intent, 1234);

///////////////////////////////////Guardamos datos en la BBDD////////////////////////////////
            PedidosSQLiteHelper admin = new  PedidosSQLiteHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String cocacola = txtCantidadCocaCola.getText().toString();
            String limon = txtCantidadLimon.getText().toString();
            String naranja = txtCantidadNaranja.getText().toString();
            String nestea = txtCantidadNestea.getText().toString();
            String cerveza = txtCantidadCerveza.getText().toString();
            String agua = txtCantidadAgua.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("cocacola", cocacola);
            registro.put("limon", limon);
            registro.put("naranja", naranja);
            registro.put("nestea", nestea);
            registro.put("cerveza", cerveza);
            registro.put("agua", agua);

            //bd.execSQL("INSERT INTO Pedidos (cocacola,limon, naranja, nestea, cerveza, agua) VALUES (?,?,?,?,?,?) ");
            bd.insert("Pedidos", null, registro);
            bd.close();
            Toast.makeText(this, "Se cargaron los datos de las bebidas",
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Seleccione alguna bebida, y pulse Calcular", Toast.LENGTH_SHORT).show();
        }
    }
///////////Metodo para ir al anterior layout////////////////
    public void AnteriorLayout(View view){
        Intent intent = new Intent(this, OpcionesTortilla.class);
        startActivity(intent);
    }

}

