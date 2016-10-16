package com.example.crist.bluebus;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class BluePrincipal extends AppCompatActivity  {
    protected Spinner corredor, linea;
    Button buscar;
    RadioButton ida , vuelta;

    BluetoothAdapter mBluetoothAdapter;
    int REQUEST_ENABLE_BT = 1;
    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_blue_principal);
        linea = (Spinner) findViewById(R.id.spLinea);
        corredor = (Spinner) findViewById(R.id.spCorredor);
         buscar = (Button) findViewById(R.id.btnBuscar);
        ida= (RadioButton) findViewById(R.id.radioButtonIda);
        vuelta= (RadioButton) findViewById(R.id.radioButtonVuelta);


        addItemsOnSpinner();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }

    public void buscarA(View v) {
        AdministracionDeBLE dsa = new AdministracionDeBLE();
        if (buscar.getText().equals("Buscar")){
            buscar.setText("Detener");
            Toast mostrar = Toast.makeText(getApplication(),construirCadenaPatron(),Toast.LENGTH_LONG);
            mostrar.show();
            dsa.find(this,construirCadenaPatron());
        }else{
            dsa.stop();
            buscar.setText("Buscar");
        }



    }

    private String construirCadenaPatron (){
       if (ida.isChecked() && linea.getSelectedItem().equals("10")){
           return "EST";
       }
        String cadena = "";
      return  cadena;
    }

    private void addItemsOnSpinner() {
        final List<Corredor> listaCorredores = new ArrayList<>();
        cargarLista(listaCorredores);
        final ArrayAdapter<Corredor> dataAdapterCorredor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCorredores);
        dataAdapterCorredor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        corredor.setAdapter(dataAdapterCorredor);
        linea.setAdapter(dataAdapterCorredor);


        corredor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                Corredor corredorSeleccionado = (Corredor) corredor.getItemAtPosition(position);
                ArrayList<String> listaLineas = corredorSeleccionado.getLineas();

                final ArrayAdapter<String> dataAdapterLinea = new ArrayAdapter<>(corredor.getContext(), android.R.layout.simple_spinner_item, listaLineas);
                dataAdapterLinea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                linea.setAdapter(dataAdapterLinea);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //no seleccionó nada
            }
        });


    }


    private void cargarLista(List<Corredor> listaCorredores) {
        ArrayList<String> lineas;
        Corredor auxCorredor = new Corredor(1);
        lineas = new ArrayList<String>() {{
            add("10");
            add("11");
            add("12");
            add("13");
            add("14");
            add("15");
            add("16");
            add("17");
            add("18");
            add("19");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
        auxCorredor = new Corredor(2);
        lineas = new ArrayList<String>() {{
            add("20");
            add("21");
            add("22");
            add("23");
            add("24");
            add("25");
            add("26");
            add("27");
            add("28");
            add("29");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
        auxCorredor = new Corredor(3);
        lineas = new ArrayList<String>() {{
            add("30");
            add("31");
            add("32");
            add("33");
            add("34");
            add("35");
            add("36");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
        auxCorredor = new Corredor(4);
        lineas = new ArrayList<String>() {{
            add("40");
            add("41");
            add("42");
            add("43");
            add("44");
            add("45");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
        auxCorredor = new Corredor(5);
        lineas = new ArrayList<String>() {{
            add("50");
            add("51");
            add("52");
            add("53");
            add("54");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
        auxCorredor = new Corredor(6);
        lineas = new ArrayList<String>() {{
            add("60");
            add("61");
            add("62");
            add("63");
            add("64");
            add("65");
            add("66");
            add("67");
            add("68");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
        auxCorredor = new Corredor(7);
        lineas = new ArrayList<String>() {{
            add("70");
            add("71");
            add("72");
            add("73");
            add("74");
            add("75");
            add("76");
        }};
        auxCorredor.setLineas(lineas);
        listaCorredores.add(auxCorredor);
    }




}


