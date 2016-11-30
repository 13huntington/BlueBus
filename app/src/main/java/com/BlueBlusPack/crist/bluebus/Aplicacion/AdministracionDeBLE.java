package com.BlueBlusPack.crist.bluebus.Aplicacion;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;


public class AdministracionDeBLE {
    private BluetoothLeScanner mBluetoothAdapter;
    private BluePrincipal retorno;
    private String patronABuscar;

    public AdministracionDeBLE() {
        BluetoothAdapter mBluetoothAdapterViejo = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter =  mBluetoothAdapterViejo.getBluetoothLeScanner();
        patronABuscar="";
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
              mBluetoothAdapter.startScan(mScanCallback);
        } else {
              mBluetoothAdapter.stopScan(mScanCallback);
        }
    }



    // Device scan callback.
/*    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onScanResult(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            if(device != null && device.getName() != null){
                if (device.getName().equals(patronABuscar)) {
                    scanLeDevice(false);
                    retorno.runOnUiThread(new Runnable() {
                        public void run() {
                            retorno.detener();
                            Toast.makeText(retorno.getApplicationContext(), "Colectivo acercándose", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    };*/

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if(result.getDevice()!=null && result.getDevice().getName()!= null) {
                if (result.getDevice().getName().equals(patronABuscar)){
                    String mac = result.getDevice().getAddress();

                }
            }
        }
    };
}
