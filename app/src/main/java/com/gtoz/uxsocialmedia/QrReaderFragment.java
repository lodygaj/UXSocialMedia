package com.gtoz.uxsocialmedia;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.zxing.Result;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by GtoZ on 11/17/2016.
 */

public class QrReaderFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private String qrString;
    private ZXingScannerView mScannerView;
    private DBHelper dbHelper;
    private ArrayList<Story> stories;
    private Context context;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity().getApplicationContext());
        context = getActivity().getApplicationContext();
        fm = getFragmentManager();
        dbHelper = new DBHelper(context);
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
        if(StringUtils.isNumeric(qrString)) {
            if(dbHelper.isValidQr(Integer.parseInt(qrString))) {
                GridFragment gridFragment = new GridFragment();
                stories = dbHelper.getStoriesByQr(Integer.parseInt(qrString));
                gridFragment.setStories(stories);
                setFragment(gridFragment);
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "QR Not Found!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(), "QR Not Found!", Toast.LENGTH_LONG).show();
        }

        // Resume scanning
        mScannerView.resumeCameraPreview(this);
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        fm.beginTransaction().replace(R.id.fl_content, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }
}