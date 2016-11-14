package com.gtoz.uxsocialmedia;

/**
 * Created by Brendan on 11/13/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrReaderActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private String qrString;
    private boolean scanResult;
    private ZXingScannerView mScannerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        System.out.println("THE CAMERA HAS STARTED");
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Get the Camera instance as the activity achieves full user focus
        if (mScannerView == null) {
            mScannerView.startCamera(); // Local method to handle camera init
            //System.out.println("THE CAMERA HAS STARTED");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public void handleResult(Result result) {
        //Do anything with result here
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        qrString = result.getText();

        //Intiialize result
        scanResult = false;


        builder.setTitle("Scan result");
        if(scanResult)
        {
            builder.setMessage("Reservation Successully Changed!");
//            Intent carActivityIntent = new Intent(QrReader.this, CarActivity.class);
//            QrReader.this.startActivity(carActivityIntent);
        }
        else {
            builder.setMessage("Car not included in reservation class!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        mScannerView.resumeCameraPreview(this);  //  use to Resume scanning
        mScannerView.stopCamera();
        //setContentView(R.layout.qr_code);
        //mScannerView.resumeCameraPreview(this);  //  use to Resume scanning
    }
}