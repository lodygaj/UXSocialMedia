package com.gtoz.uxsocialmedia;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ebli Jr on 11/11/2016.
 */

public class InstagramRetrievalService extends IntentService {

    // Client ID and Secret
    private String clientId = getString(R.string.Instagram_client_ID);
    private String clientSecret = getString(R.string.Instagram_client_secret);

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * Name Used to name the worker thread, important only for debugging.
     */
    public InstagramRetrievalService() {
        super("InstagramRetrievalService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Connecting to a network
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // Request URL
        String stringUrl = "https://api.instagram.com/v1/tags/PokeGo/media/recent?access_token="
                + getString(R.string.Instagram_client_secret);
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch media content
            try {
                InputStream response = downloadMedia(stringUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // display error
        }
    }

    private InputStream downloadMedia (String requestUrl) throws IOException {
        // To receive request response
        InputStream is = null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Make request
            conn.connect();
            is = conn.getInputStream();

            return is;
        }finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
