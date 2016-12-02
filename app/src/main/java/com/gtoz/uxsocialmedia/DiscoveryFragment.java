package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.jinstagram.entity.users.feed.MediaFeedData;

import java.util.ArrayList;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class DiscoveryFragment extends Fragment {
    private Context context;
    private FragmentManager fm;
    private ListView categoryList;
    private RecyclerView thriftyList, recommendedList;

    private ArrayList<Story> list1;
    private ArrayList<Story> list2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Initialize layout objects
        thriftyList = (RecyclerView) view.findViewById(R.id.rv1);
        recommendedList = (RecyclerView) view.findViewById(R.id.rv2);
        //categoryList = (ListView) view.findViewById(R.id.rv3);
        
        // Testing InstagramRetrievalService
        Intent mServiceIntent = new Intent(getActivity(), InstagramRetrievalService.class);
        getActivity().startService(mServiceIntent);

        // Receive broadcast from InstagramIntentService
        IntentFilter filter = new IntentFilter("REFRESH_ACTION");
        ResponseReceiver receiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);


        // Create arrays of sample stories
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        createLists();


        // Set initial horizontal list layout and spacing values
        ListSpacing dec = new ListSpacing(0, 10);

        // Set up Thrifty horizontal list
        thriftyList.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        thriftyList.setLayoutManager(layoutManager1);
        thriftyList.setItemAnimator(new DefaultItemAnimator());
        DiscoveryListAdapter thriftyAdapter = new DiscoveryListAdapter(context, fm, list1);
        thriftyList.setAdapter(thriftyAdapter);
        thriftyList.addItemDecoration(dec);

        // Set up Recommended horizontal list
        recommendedList.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendedList.setLayoutManager(layoutManager2);
        recommendedList.setItemAnimator(new DefaultItemAnimator());
        DiscoveryListAdapter recommendedAdapter = new DiscoveryListAdapter(context, fm, list2);
        recommendedList.setAdapter(recommendedAdapter);
        recommendedList.addItemDecoration(dec);

        //

        return view;
    }
    public class ResponseReceiver extends BroadcastReceiver {

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        @Override
        public void onReceive(Context context, Intent intent) {
            // Deliver message in toast
            CharSequence text = "Broadcasted and received from InstagramRetrievalService.";
            int duration = Toast.LENGTH_SHORT;

            // Handle content from Intent
            //ArrayList<MediaFeedData> mediaFeed = intent.getParcelableArrayListExtra("MEDIA");

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    // Used to hardcode sample stories for testing
    public void createLists() {
        Story story1 = new Story("Germain Arena", "Estero, FL", "https://germainarena.com/", "image", "Sport",
                "In addition to hosting Florida Everblades hockey, Southwest Florida’s premier entertainment venue offers a wide variety of choice entertainment. " +
                        "Since the arena opened in 1998, it has hosted NHL, NBA, USBL, and Arena football games.",
                R.drawable.germainarena, 237);

        Story story2 = new Story("Naples Zoo", "Naples, FL", "http://napleszoo.org/", "image", "Animal",
                "Naples Zoo is a nationally accredited zoo and yet much more than a walk-through zoo. The nonprofit 501(c)(3) charitable institution also features a full day of fun activities. " +
                        "The paved path winds nearly a mile past rare and beautiful animals residing within a historic tropical garden of exotic plants first planted in 1919 with a fascinating history.",
                R.drawable.napleszoo, 1216);

        Story story3 = new Story("Naples Botanical Garden", "Naples, FL", "https://www.naplesgarden.org/", "image", "Nature",
                "Connecting people with plants by conserving and researching the biological diversity of our collections and ecosystems; engaging our visitors in learning about plants," +
                        " gardens and ecosystems; and inspiring our visitors to value plants, gardens and natural habitats.",
                R.drawable.naplesbotgarden, 7243);

        Story story4 = new Story("Ding Darling Wildlife Preserve", "Sanibel, FL", "https://www.fws.gov/refuge/jn_ding_darling/", "image", "Nature",
                "The J.N. \"Ding\" Darling National Wildlife Refuge is located on the subtropical barrier island of Sanibel in the Gulf of Mexico. " +
                        "The refuge is part of the largest undeveloped mangrove ecosystem in the United States. It is world famous for its spectacular migratory bird populations. ",
                R.drawable.dingdarling, 5231);

        //Story story5 = new Story("The Veranda", "Fort Myers, FL", "http://www.explorenaples.com/barefoot-beach-preserve-county-park.phtml", "image", "Food", "This is sample Text", R.drawable.theveranda, 863);
        Story story6 = new Story("Barefoot Beach Preserve", "Bonita Springs, FL", "https://www.verandarestaurant.com/", "image", "Nature",
                "Collier County's desirable coast reaches its zenith at Barefoot Beach Preserve, where numerous animal species reside and visitors are able to enjoy the ambience of the park's natural surroundings.",
                R.drawable.barefootbeach, 220);

        Story story7 = new Story("The Buddha Rock Club", "Fort Myers, FL", "https://buddharockclub.com/", "image", "Music",
                " You'll know you are in the right place by the giant statue of Buddha, sitting in the parking lot. A couple of things you can be sure of in this bar, " +
                        "the drinks are cold and cheap and the music is loud. Local bands love to rock here as well as those on the touring circuit.",
                R.drawable.buddhaclub, 3655);

        Story story8 = new Story("Sun Splash Water Park", "Cape Coral, FL", "http://sunsplashwaterpark.com/", "image", "Water",
                "Seasonal amusement park with 14+ acres of waterslides, a lazy river & kids' water play area.", R.drawable.sunsplash, 2643);

        Story story9 = new Story("Alico Arena", "Estero, FL", "http://www.fgcuathletics.com/sports/2014/8/16/alicoarena.aspx", "image", "Sport",
                "Alico Arena, also known as The Nest and Dunk City, is a 120,000 sq ft multipurpose arena on the campus of Florida Gulf Coast University in Fort Myers, Florida. " +
                        "It is the home of the FGCU Eagles volleyball and men's and women's basketball teams.",
                R.drawable.alicoarena, 1364);

        Story story10 = new Story("Mad Hatter", "Captiva, FL", "http://www.madhatterrestaurant.com/", "video", "Food",
                "This former bungalow on the beach has been home the The Mad Hatter Restaurant for nearly 30 years, boasting one of Sanibel and Captiva's finest dining experiences.",
                R.drawable.madhatter, 1744);

        list1.add(story1);
        list1.add(story2);
        list1.add(story3);
        list1.add(story4);
        //list1.add(story5);
        list1.add(story6);
        list1.add(story7);
        list1.add(story8);
        list1.add(story9);
        list1.add(story10);

        list2.add(story10);
        list2.add(story9);
        list2.add(story8);
        list2.add(story7);
        list2.add(story6);
        //list2.add(story5);
        list2.add(story4);
        list2.add(story3);
        list2.add(story2);
        list2.add(story1);
    }
}