package com.gtoz.uxsocialmedia;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.jinstagram.Instagram;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

import java.util.List;

/**
 * Created by Ebli Jr on 11/11/2016.
 */

public class InstagramRetrievalService extends IntentService {

    // For log messages
    private static final String TAG = "com.gtoz.uxsocialmedia";

    // Client ID and Secret
    private String clientId = "29bf0da6be314e8685bf3a90d667b070"; //getString(R.string.Instagram_client_ID);
    private String clientSecret = "ae38e4c3ab8b498b917e2df73a89a907"; //getString(R.string.Instagram_client_secret);
    private String callbackUrl = "http://www.fgcu.edu";//getString(R.string.Instagram_callback_url);

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

        // Instagram service handler
        InstagramService service = new InstagramAuthService()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(callbackUrl)
                .build();

        // Validate user against Instagram
        String authorizationURl = service.getAuthorizationUrl();

        // Get Access Token
        Verifier verifier = new Verifier("verifier you get from the user");
        Token accessToken = new Token ("2036986811.29bf0da.904e942a8d3f426db6a6697dbfa5a957","ae38e4c3ab8b498b917e2df73a89a907");//service.getAccessToken(verifier);

        // Creating Instagram object
        Instagram instagram = new Instagram(accessToken);

        // Get tagged media
        String tagName = "PokeGo";
        TagMediaFeed mediaFeed = null;
        try {
            mediaFeed = instagram.getRecentMediaTags(tagName);
        } catch (InstagramException e) {
            e.printStackTrace();
        }

        List<MediaFeedData> mediaFeeds = mediaFeed.getData();

        // Return a status saying complete to calling activity
        if (mediaFeeds != null) {
            // Create new Intent containing status to broadcast
            Intent instagramResponseIntent = new Intent("REFRESH_ACTION")
                    .putExtra("STATUS", true);

            // Add response from Instagram API
            instagramResponseIntent.putExtra("MEDIA", (Parcelable) mediaFeeds);

            // Log Message
            Log.i(TAG, "InstagramRetrievalService Ran.");

            // Broadcast the Intent to receivers
            LocalBroadcastManager.getInstance(this).sendBroadcast(instagramResponseIntent);
        }
    }
}
