package com.darshan.tutorial11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {
    JSONArray itemJSONArray;
    Context context;

    public CustomAdapter(Context context, JSONArray itemJSONArray)
    {
        this.itemJSONArray=itemJSONArray;
        this.context=context;
    }


    @Override
    public int getCount() {
        return itemJSONArray.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false);
        }

        TextView textFormula=(TextView) convertView.findViewById(R.id.txtName);
        TextView textUrl=(TextView)convertView.findViewById(R.id.txtEmail);

        JSONObject jsonObject=null;

        try{
            jsonObject=itemJSONArray.getJSONObject(position);
            textFormula.setText(jsonObject.getString("name"));
            textUrl.setText(jsonObject.getString("email"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return convertView;
    }
}
