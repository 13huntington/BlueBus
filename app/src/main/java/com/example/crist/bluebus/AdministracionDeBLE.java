package com.example.crist.bluebus;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by crist on 03/10/2016.
 */
public class AdministracionDeBLE {
    private ArrayList<BluetoothDevice> mLeDeviceListAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    Handler handler1 = new Handler();
    BluePrincipal retorno;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 1000000;


    public AdministracionDeBLE() {
        mHandler = new Handler();
        mBluetoothAdapter =  BluetoothAdapter.getDefaultAdapter();
    }


//Inicializa la busqueda del colectivo
   public void find(BluePrincipal atc){
        mLeDeviceListAdapter  = new ArrayList<BluetoothDevice>();
        scanLeDevice(true);
        retorno =atc;
    }

//Va capturando los dispositivos ble en rango y comprueba si es el deseado
    private void scanLeDevice(final boolean enable) {
        if (enable) {
              mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
              mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }



    // Device scan callback.
    public BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            mLeDeviceListAdapter.add(device);
            if (device.getName().equals("EST")) {
                scanLeDevice(false);
                Log.d("Hola", device.getName());
                retorno.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(retorno.getApplicationContext(), "Colectio acercandoce",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            };
        };
    };
}
