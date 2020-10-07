package com.darshan.tutorial11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

public class UserData extends AppCompatActivity {

    TextView textViewName,textViewUsername,textViewEmail,textViewStreet,textViewSuite,textViewCity,textViewZipCode,textViewLat,textViewLng,textViewPhone,textViewWebsite,textViewCompName,textViewCompCatchPhrase,textViewCompBs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        this.setTitle("User Data");
        Intent intent=getIntent();

        textViewName = findViewById(R.id.txtName);
        textViewUsername=findViewById(R.id.txtUsername);
        textViewEmail = findViewById(R.id.txtEmail);
        textViewStreet=findViewById(R.id.txtStreet);
        textViewSuite = findViewById(R.id.txtSuite);
        textViewCity=findViewById(R.id.txtCity);
        textViewZipCode = findViewById(R.id.txtZipcode);
        textViewLat=findViewById(R.id.txtLat);
        textViewLng = findViewById(R.id.txtLng);
        textViewPhone=findViewById(R.id.txtPhone);
        textViewWebsite=findViewById(R.id.txtWebsite);
        textViewCompName = findViewById(R.id.txtCompName);
        textViewCompCatchPhrase=findViewById(R.id.txtCompCatchPhrase);
        textViewCompBs = findViewById(R.id.txtCompBs);

        try {
            textViewName.setText("Name : "+MyUtil.userJSONObj.getString("name"));
            textViewUsername.setText("Username : "+MyUtil.userJSONObj.getString("username"));
            textViewEmail.setText("Email : "+MyUtil.userJSONObj.getString("email"));
            textViewPhone.setText("Phone : "+MyUtil.userJSONObj.getString("phone"));
            textViewWebsite.setText("Website : "+MyUtil.userJSONObj.getString("website"));

            JSONObject jsonObjectAddress=MyUtil.userJSONObj.getJSONObject("address");
            textViewStreet.setText("Street : "+jsonObjectAddress.getString("street"));
            textViewSuite.setText("Suite : "+jsonObjectAddress.getString("suite"));
            textViewCity.setText("City : "+jsonObjectAddress.getString("city"));
            textViewZipCode.setText("ZipCode : "+jsonObjectAddress.getString("zipcode"));

            JSONObject jsonObjectGeo=jsonObjectAddress.getJSONObject("geo");
            textViewLat.setText("Lat : "+jsonObjectGeo.getString("lat"));
            textViewLng.setText("Lng : "+jsonObjectGeo.getString("lng"));

            textViewCompName.setText("Name : "+MyUtil.userJSONObj.getJSONObject("company").getString("name"));
            textViewCompCatchPhrase.setText("Catch Phrase : "+MyUtil.userJSONObj.getJSONObject("company").getString("catchPhrase"));
            textViewCompBs.setText("bs : "+MyUtil.userJSONObj.getJSONObject("company").getString("bs"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}