package com.darshan.tutorial11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    JSONArray itemJsonArray;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Users List");
        listView = findViewById(R.id.lstUserListView);
        dialog = new ProgressDialog(this);

        setVollyAPI();
    }

    public void setVollyAPI()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, MyUtil.USER_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    itemJsonArray=response;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CustomAdapter myAdapter=new CustomAdapter(MainActivity.this,itemJsonArray);
                listView.setAdapter(myAdapter);
                if(dialog.isShowing())
                    dialog.dismiss();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        try {
                            MyUtil.userJSONObj = itemJsonArray.getJSONObject(position);
                            Intent intent = new Intent(MainActivity.this, UserData.class);
                            startActivity(intent);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setTitle("Fetching Data");
        dialog.show();
        requestQueue.add(jsonArrayRequest);
    }
}