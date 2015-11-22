package com.example.franciscogutierrez.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeckActivity extends ActionBarActivity {

    private ListView lv;
    private static final String TAG = "MYTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        lv = (ListView) findViewById(R.id.listView);

        List<String> arrayList = new ArrayList<String>();

        try {
            String s = "http://54.183.149.62/test2.php";
            String[] url = new String[]{s};

            s = new GetData().execute(url).get();

            JSONObject jObject = new JSONObject(s);
            JSONArray jArray = jObject.getJSONArray("decks");

            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    String oneObjectsItem = oneObject.getString("deckName");
                    arrayList.add(oneObjectsItem);
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    arrayList );

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), FlashActivity.class);
                    intent.putExtra("deckName", (String) parent.getItemAtPosition(position));
                    startActivity(intent);
                }
            });

            lv.setAdapter(arrayAdapter);

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }

}
