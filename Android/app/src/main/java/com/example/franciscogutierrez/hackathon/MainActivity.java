package com.example.franciscogutierrez.hackathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MYTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameText = (EditText) findViewById(R.id.editText);
        final EditText passwordText = (EditText) findViewById(R.id.editText2);

        Button b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = usernameText.getText().toString();
                    String password = passwordText.getText().toString();
                    String s = "http://54.183.149.62/test.php?u=" + username + "&p=" + password;
                    String[] url = new String[]{s};

                    s = new GetData().execute(url).get();

                    if (s.equals("goodlogin")) {
                        Intent intent = new Intent(getBaseContext(), DeckActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        TextView tv = (TextView) findViewById(R.id.textView3);
                        tv.setText(s);
                    }



                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }
        });
    }
}

class GetData extends AsyncTask<String, Integer, String> {

    private static final String TAG = "MYTAG";

    @Override
    protected String doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(params[0]);

        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine sl = response.getStatusLine();
            int sc = sl.getStatusCode();

            if (sc == 200) {
                //HTTP 200 = OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(content));
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
