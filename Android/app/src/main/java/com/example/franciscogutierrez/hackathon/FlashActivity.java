package com.example.franciscogutierrez.hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FlashActivity extends ActionBarActivity {

    private static final String TAG = "MYTAG";
    int currentCard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        Intent intent = getIntent();
        String deckName = intent.getStringExtra("deckName");

        final List<FlashCard> arrayList = new ArrayList<FlashCard>();

        try {
            String s = "http://54.183.149.62/test3.php?d=" + deckName;
            String[] url = new String[]{s};

            s = new GetData().execute(url).get();

            JSONObject jObject = new JSONObject(s);
            JSONArray jArray = jObject.getJSONArray("deckContent");

            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    String oneObjectsItem = oneObject.getString("e");
                    String twoObjectsItem = oneObject.getString("c");
                    arrayList.add(new FlashCard(oneObjectsItem, twoObjectsItem));
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }

            final int lastCard = arrayList.size() -1;
            final Button b1 = (Button) findViewById(R.id.button2);
            Button b2 = (Button) findViewById(R.id.button4);

            //b1 is the flash card, b2 gets next card

            //setup
            b1.setText(arrayList.get(0).getFront());
            b1.setBackgroundColor(Color.YELLOW);
            b2.setText("Next Flash Card");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (b1.getText().toString() == arrayList.get(currentCard).getFront()) {
                        //if current card = front, set to rear card
                        b1.setText(arrayList.get(currentCard).getBack());
                        b1.setBackgroundColor(Color.CYAN);
                    } else {
                        b1.setText(arrayList.get(currentCard).getFront());
                        b1.setBackgroundColor(Color.YELLOW);
                    }
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentCard != lastCard) {
                        currentCard++;
                        b1.setText(arrayList.get(currentCard).getFront());
                    } else {
                        currentCard = 0;
                        b1.setText(arrayList.get(currentCard).getFront());
                    }
                }
            });
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}