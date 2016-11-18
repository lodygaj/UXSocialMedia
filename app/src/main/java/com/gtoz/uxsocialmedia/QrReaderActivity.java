package com.gtoz.uxsocialmedia;

/**
 * Created by GtoZ on 11/13/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrReaderActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private String qrString;
    private boolean scanResult;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        // Start camera
        mScannerView.startCamera();
        System.out.println("THE CAMERA HAS STARTED");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop camera
        mScannerView.stopCamera();
        System.out.println("THE CAMERA HAS STOPPED");
    }

    @Override
    public void handleResult(Result result) {
        //Handle result here
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        qrString = result.getText();

        //Intiialize result
        scanResult = false;

        builder.setTitle("Scan result");
        if(scanResult)
        {
            builder.setMessage("Found!");
        }
        else {
            builder.setMessage("Not found!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        mScannerView.resumeCameraPreview(this);  //  use to Resume scanning
        mScannerView.stopCamera();
    }

    @Override
    public void onBackPressed() {
        // Load main activity
        Intent mainIntent = new Intent(QrReaderActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}