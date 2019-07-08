package com.chennikawangmai.tkthree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Favlist extends AppCompatActivity {
    ListView listview;
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents);
        listview=(ListView)findViewById(R.id.listsc) ;
        Bundle b=getIntent().getExtras();
        String t=String.valueOf(b.getString("CrimeId"));
        listitem=new ArrayList<String>();
        listitem.add(t);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listitem);
        listview.setAdapter(adapter);

    }
}
