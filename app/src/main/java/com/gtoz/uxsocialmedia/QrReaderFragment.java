package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by GtoZ on 11/17/2016.
 */

public class QrReaderFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private String qrString;
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
        // Retrieve result
        qrString = result.getText();

        // Print result
        Toast.makeText(getActivity().getApplicationContext(), qrString, Toast.LENGTH_LONG).show();

        // Resume scanning
        mScannerView.resumeCameraPreview(this);
    }
}