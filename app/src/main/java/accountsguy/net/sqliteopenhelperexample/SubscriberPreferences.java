package accountsguy.net.sqliteopenhelperexample;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AccountsGuy.Net on 05/03/2018.
 */

public class SubscriberPreferences extends AppCompatActivity {

    public static String Subscriber_Name, Subscriber_Email;
    public static Map<Object, Object> SubscriberDetails = new HashMap<>();

    public static void setSubscriberImage(File subscriberImage) {
        SubscriberPreferences.subscriberImage = subscriberImage;
    }
    public static File subscriberImage;

    ArrayList<String> preferences = new ArrayList<>();


    ArrayList<String> stringArrayList;
    private ListView listView;
    public ArrayAdapter<String> stringArrayAdapter;
    Intent intent;

    /**
     * listView.setBackgroundColor(R.color.colorAccent);
     */
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_preferences);

        stringArrayList = new ArrayList<String>(Arrays.asList("JANUARY", "FEBRUARY",
                "MARCH",
                "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
                "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"));

        listView = (ListView) findViewById(R.id.listview);

        /**
         * @SuppressLint("ResourceAsColor")
         */
        listView.setBackgroundColor(R.color.colorAccent);
        SubscriberDetails.put("Name","AccountsGuy.Net");
        SubscriberDetails.put("Phone", "9133188688");
        SubscriberDetails.put("Photo", subscriberImage);
        SubscriberDetails.put("Back Ground Color", "#FFFFFF");
        SubscriberDetails.put("Text Color", "#000000");

        stringArrayAdapter = new ArrayAdapter<String>(this, android.R
                .layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SubscriberPreferences.this, stringArrayList.get(position),Toast.LENGTH_SHORT)
                .show();
            }
        });


        /**
         * Setting Preferences
         */

        intent = getIntent();
        if(intent!=null){
            //String Extra = intent.getStringExtra("0");
            //Toast.makeText(this,Extra, Toast.LENGTH_SHORT).show();

            Bundle intentBundle = intent.getExtras();
            String[] intentPreferences = intentBundle.getStringArray("preferences");
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ic_launcher_background);
            Toast.makeText(this,intentPreferences[0].toString(), Toast.LENGTH_SHORT).show();

            Toast.makeText(this, intentBundle.get("Photo").toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static String getSubscriber_Name() {
        return Subscriber_Name;
    }
    public static void setSubscriber_Name(String subscriber_Name) {
        Subscriber_Name = subscriber_Name;
    }

    public static String getSubscriber_Email() {
        return Subscriber_Email;
    }
    public static void setSubscriber_Email(String subscriber_Email) {
        Subscriber_Email = subscriber_Email;
    }
}