package com.gtoz.uxsocialmedia;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by GtoZ on 10/30/2016.
 */

public class LoginFragment extends Fragment {
    private Button btnLogin, btnRegister, btnGuest, imgBtnFacebook, imgBtnTwitter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize Components
        imgBtnFacebook = (Button) view.findViewById(R.id.imgBtnFacebook);
        imgBtnTwitter = (Button) view.findViewById(R.id.imgBtnTwitter);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnGuest = (Button) view.findViewById(R.id.btnGuest);

        // Button click listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert user to skip log in
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Skip log in?");
                alert.setMessage("You may log in later in settings.");
                //alert.setCancelable(false);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Load main activity
                        Intent mainIntent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        imgBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgBtnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}