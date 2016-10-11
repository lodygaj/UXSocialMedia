package com.gtoz.uxsocialmedia;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    private TextView txtViewTitle;

    public void setTxtViewTitle(String text) {
        txtViewTitle.setText(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        txtViewTitle = (TextView) view.findViewById(R.id.txtViewTitle);




        return view;
    }

    public void onBackPressed()
    {
        FragmentManager fm = getActivity().getFragmentManager();
        fm.popBackStack();
    }
}
