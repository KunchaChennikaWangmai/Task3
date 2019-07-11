package com.chennikawangmai.tkthree;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favlist extends AppCompatActivity {
   private ListView listview;
   DataBaseHelper Db ;
   Double lat;Double lng;
   private static final String TAG = "Favlist";
   String Id;
   String date;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents);
        listview = (ListView) findViewById(R.id.listlsc);
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {date=String.valueOf(b.getString("date"));
         lat=b.getDouble("lat");
         lng=b.getDouble("lng");}
       Db = new DataBaseHelper(this);
       populateListView();

    }
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = Db.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter data.getString(1)
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listview.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                    Log.d(TAG, "onItemClick: The ID is: " + Id);
                    Intent editScreenIntent = new Intent(Favlist.this, SpecificfavCrime.class);
                    Bundle bh=new Bundle();
                    bh.putDouble("lat",lat);
                    bh.putDouble("lng",lng);
                    bh.putString("date",date);
                    bh.putString("id",listview.getItemAtPosition(i).toString());
                    editScreenIntent.putExtras(bh);
                    startActivity(editScreenIntent);

            }
        });

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
