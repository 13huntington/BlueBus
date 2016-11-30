package com.BlueBlusPack.crist.bluebus.Aplicacion;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;



import com.BlueBlusPack.crist.bluebus.Entidades.*;
import com.BlueBlusPack.crist.bluebus.Datos.*;
import com.BlueBlusPack.crist.bluebus.R;

import java.util.ArrayList;
import java.util.List;

public class BluePrincipal extends AppCompatActivity {
    protected Spinner corredor, linea;
    Button buscar;
    RadioButton ida, vuelta;
    AdministracionDeBLE dsa ;
    BluetoothAdapter mBluetoothAdapter;
    int REQUEST_ENABLE_BT = 1;
    Bundle savedInstanceState;
    Vibrator vibrador;
    private GestorDatos gestorDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BluePrincipal.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, yourPermissionRequestCode);
        }
        gestorDatos = new GestorDatos(this.getApplicationContext());
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_blue_principal);
        linea = (Spinner) findViewById(R.id.spLinea);
        corredor = (Spinner) findViewById(R.id.spCorredor);
        buscar = (Button) findViewById(R.id.btnBuscar);
        ida = (RadioButton) findViewById(R.id.radioButtonIda);
        vuelta = (RadioButton) findViewById(R.id.radioButtonVuelta);
        dsa = new AdministracionDeBLE();
        addItemsOnSpinner();
        vibrador = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }
    public void detener(){
        dsa.stop();
        vibrador.vibrate(2500);
        habilitarBotones();
        buscar.setText("Buscar");
    }

    public void buscarA(View v) {
        if (buscar.getText().equals("Buscar")) {
            desHabilitarBotones();
            buscar.setText("Detener");
            dsa.find(this, construirCadenaPatron());
        } else {
            dsa.stop();
            habilitarBotones();
            buscar.setText("Buscar");
        }
    }

    public void desHabilitarBotones(){
        corredor.setEnabled(false);
        linea.setEnabled(false);
        ida.setEnabled(false);
        vuelta.setEnabled(false);
    }
    public void habilitarBotones(){
        corredor.setEnabled(true);
        linea.setEnabled(true);
        ida.setEnabled(true);
        vuelta.setEnabled(true);
    }



    private String construirCadenaPatron() {
        String retornoCadena;
        Linea lineaSeleccionada = (Linea) linea.getSelectedItem();
        if (ida.isChecked()) {
            retornoCadena = lineaSeleccionada.getNumero() + "-ida";
            return retornoCadena;
        } else {
            retornoCadena = lineaSeleccionada.getNumero() + "-vuelta";
            return retornoCadena;
        }
    }

    private void addItemsOnSpinner() {
        final List<Corredor> listaCorredores = obtenerCorredores();
        final ArrayAdapter<Corredor> dataAdapterCorredor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCorredores);
        dataAdapterCorredor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        corredor.setAdapter(dataAdapterCorredor);
        linea.setAdapter(dataAdapterCorredor);
        corredor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                Corredor corredorSeleccionado = (Corredor) corredor.getItemAtPosition(position);
                ArrayList<Linea> listaLineas = obtenerLineas(corredorSeleccionado.getNumero());

                final ArrayAdapter<Linea> dataAdapterLinea = new ArrayAdapter<>(corredor.getContext(), android.R.layout.simple_spinner_item, listaLineas);
                dataAdapterLinea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                linea.setAdapter(dataAdapterLinea);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //no seleccionó nada
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        if(requestCode == yourPermissionRequestCode)
        {

        }
    }

    private ArrayList<Corredor> obtenerCorredores(){
        return gestorDatos.obtenerCorredores();
    }

    private ArrayList<Linea> obtenerLineas(int nroCorredor){
        return gestorDatos.obtenerLineas(nroCorredor);
    }

}


