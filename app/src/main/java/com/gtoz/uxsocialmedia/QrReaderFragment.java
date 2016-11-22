package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by GtoZ on 11/17/2016.
 */

public class QrReaderFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private String qrString;
    private boolean scanResult;
    private ZXingScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity().getApplicationContext());
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Handle result here
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
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
}