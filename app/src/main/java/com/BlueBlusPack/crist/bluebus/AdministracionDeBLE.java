package com.BlueBlusPack.crist.bluebus;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.widget.Toast;

public class AdministracionDeBLE {
    private BluetoothAdapter mBluetoothAdapter;
    private BluePrincipal retorno;
    private String patronABuscar;



    public AdministracionDeBLE() {
      mBluetoothAdapter =  BluetoothAdapter.getDefaultAdapter();
    }


//Inicializa la busqueda del colectivo
   public void find(BluePrincipal atc , String colectivo){
        patronABuscar = colectivo;
        scanLeDevice(true);
        retorno =atc;
    }
    public void stop(){
        scanLeDevice(false);
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
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {

            if (device.getName().equals(patronABuscar)) {
                scanLeDevice(false);
                           retorno.runOnUiThread(new Runnable() {
                    public void run() {
                        retorno.detener();
                        Toast.makeText(retorno.getApplicationContext(), "Colectivo acercándose",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    };
}
