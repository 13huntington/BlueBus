package com.BlueBlusPack.crist.bluebus.Aplicacion;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.widget.Toast;
import com.BlueBlusPack.crist.bluebus.Datos.GestorDatos;


public class AdministracionDeBLE {
    private BluetoothLeScanner mBluetoothAdapter;
    private BluePrincipal activity;
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
        activity =atc;
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


    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            String deviceName;
            if(result.getDevice().getName()!=null) {
                deviceName = result.getDevice().getName();
            }else {
                deviceName = getUnidad(result.getScanRecord().getBytes());
            }
            if (deviceName != null && deviceName.equals(patronABuscar)){
                String mac = result.getDevice().getAddress();
                GestorDatos gestorDatos = new GestorDatos(activity.getApplicationContext());
                if(gestorDatos.verificarUnidad(mac)){
                        activity.runOnUiThread(new Runnable() {
                        public void run() {
                            activity.detener();
                            Toast.makeText(activity.getApplicationContext(), "Colectivo acercándose", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    };


    private String getUnidad(byte [] mScanRecord){
        StringBuilder unidad = new StringBuilder();
        unidad.append(getMajor(mScanRecord));
        unidad.append("-");
        unidad.append(getMinor(mScanRecord));
        return unidad.toString();
    }

    private String getMajor(byte [] mScanRecord) {
        String major = String.valueOf( (mScanRecord[25] & 0xff) * 0x100 + (mScanRecord[26] & 0xff));
        return major;
    }
    private String getMinor(byte [] mScanRecord) {
        String minor = String.valueOf( (mScanRecord[27] & 0xff) * 0x100 + (mScanRecord[28] & 0xff));
        return minor;
    }


}
