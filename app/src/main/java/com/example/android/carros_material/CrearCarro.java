package com.example.android.carros_material;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class CrearCarro extends AppCompatActivity {
    private EditText txtPlaca, txtPrecio;
    private TextInputLayout cajaPlaca, cajaPrecio;
    private Spinner comboMarca, comboModelo, comboColor;
    private String [] opcMarca, opcModelo, opcColor;
    private ArrayList<Integer> fotos;
    private ArrayAdapter<String> adapter;
    private Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carro);
        res=this.getResources();

        txtPlaca=(EditText)findViewById(R.id.txtPlaca);
        txtPrecio=(EditText)findViewById(R.id.txtPrecio);
        cajaPlaca=(TextInputLayout)findViewById(R.id.cajaPlaca);
        cajaPrecio=(TextInputLayout)findViewById(R.id.cajaPrecio);
        comboMarca=(Spinner)findViewById(R.id.cmbMarca);
        comboModelo=(Spinner)findViewById(R.id.cmbModelo);
        comboColor=(Spinner)findViewById(R.id.cmbColor);
        opcMarca= res.getStringArray(R.array.marca);
        opcModelo = res.getStringArray(R.array.modelo);
        opcColor = res.getStringArray(R.array.color);

        ArrayAdapter <String> adapterMarca = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcMarca);
        ArrayAdapter <String> adapterModelo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcModelo);
        ArrayAdapter <String> adapterColor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcColor);

        comboMarca.setAdapter(adapterMarca);
        comboModelo.setAdapter(adapterModelo);
        comboColor.setAdapter(adapterColor);

        iniciar_fotos();
    }

    public void iniciar_fotos(){
        fotos=new ArrayList<>();
        fotos.add(R.drawable.audi);
        fotos.add(R.drawable.chevrolet);
        fotos.add(R.drawable.kia);
    }

    public void guardar(View v){

        String placa;
        double precio;
        int marca, modelo, color;

        placa=txtPlaca.getText().toString();
        marca=comboMarca.getSelectedItemPosition();
        modelo=comboModelo.getSelectedItemPosition();
        color=comboColor.getSelectedItemPosition();
        precio= Double.parseDouble(txtPrecio.getText().toString());

        Carro c = new Carro(Metodos.fotoAleatoria(fotos),placa,marca,modelo,color,precio);
        c.guardar();
        Snackbar.make(v, res.getString(R.string.msjGuardar), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearCarro.this,Principal.class);
        startActivity(i);
    }
}
