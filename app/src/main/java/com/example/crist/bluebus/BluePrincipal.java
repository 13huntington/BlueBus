package com.example.crist.bluebus;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class BluePrincipal extends AppCompatActivity {
    Spinner corredor,linea ;
    Button buscar;
    String sCorredor [];
    String sLineas [];
     BluetoothAdapter mBluetoothAdapter;
    int REQUEST_ENABLE_BT = 1;
    Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState =  savedInstanceState;
        sCorredor = new String[] {"Corredor 1","Corredor 2","Corredor 3","Corredor 4","Corredor 2" };
        sLineas = new String[]{"10","11","12"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_principal);
        corredor = (Spinner) findViewById(R.id.spEmpresa);
        linea = (Spinner) findViewById(R.id.spLinea);
        buscar =(Button) findViewById(R.id.btnBuscar);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
}
    public void buscarA(View v) {
        AdministracionDeBLE dsa = new AdministracionDeBLE();
        dsa.find(this);

    }

    }
