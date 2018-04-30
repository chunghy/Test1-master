package com.example.irmin.test1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Progressing_Event extends AppCompatActivity {

    private static final String TAG_JSON="webnautes";
    private static final String TAG_NUM = "eventNum";
    private static final String TAG_ID = "userID";
    private static final String TAG_TITLE = "eventTitle";
    private static final String TAG_CONTENT = "eventContent";
    private static final String TAG_START = "startTime";
    private static final String TAG_CLOSE = "closeTime";
    private static final String TAG_AMOUNT = "amount";
    private static final String TAG_IMG = "eventImg";

    ArrayList<HashMap<String, String>> eventList;
    ListView list;
    String myJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressing__event);
        list = (ListView) findViewById(R.id.listView);
        eventList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://irmin95.cafe24.com/EventList2.php");
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Progressing_Event.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            myJSON = result;
            showList();
        }

        @Override
        protected String doInBackground(String... params) {

            String uri = params[0];

            try {

                URL url = new URL(uri);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                errorString = e.toString();

                return null;
            }
        }
    }

    protected  void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray ja = jsonObj.getJSONArray(TAG_JSON);

            for (int i=0; i < ja.length(); i++){
                JSONObject c = ja.getJSONObject(i);


                String num = c.optString(TAG_NUM);
                String id = c.optString(TAG_ID);
                String title = c.optString(TAG_TITLE);
                String content = c.optString(TAG_CONTENT);
                String start = c.optString(TAG_START);
                String close = c.optString(TAG_CLOSE);
                String amount = c.optString(TAG_AMOUNT);
                String img = c.optString(TAG_IMG);

                HashMap<String, String> list = new HashMap<>();

                list.put(TAG_NUM, num);
                list.put(TAG_ID, id);
                list.put(TAG_TITLE, title);
                list.put(TAG_CONTENT, content);
                list.put(TAG_START, start);
                list.put(TAG_CLOSE, close);
                list.put(TAG_AMOUNT, amount);
                list.put(TAG_IMG, img);

                eventList.add(list);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Progressing_Event.this, eventList, R.layout.list_item,
                    new String[]{TAG_ID, TAG_TITLE, TAG_CONTENT, TAG_START, TAG_CLOSE, TAG_AMOUNT, TAG_IMG},
                    new int[]{R.id.id, R.id.title, R.id.content, R.id.start, R.id.close, R.id.amount, R.id.img}
            );

            list.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    }

