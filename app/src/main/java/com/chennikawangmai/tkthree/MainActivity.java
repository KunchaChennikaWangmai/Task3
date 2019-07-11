package com.chennikawangmai.tkthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listviewm);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    getResources().getStringArray(R.array.Options));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String t=listView.getItemAtPosition(i).toString();
                    if(t.equals("List of UK police forces"))
                    { Intent intent = new Intent(MainActivity.this, ForcesList.class);
                    startActivity(intent);}
                    else if(t.equals("List of Crimes at a specific location")) {
                    Intent intentl=new Intent(MainActivity.this,Crimelatlng.class);
                    startActivity(intentl);
                    }
                    else if(t.equals("Favourites"))
                    {Intent intentl=new Intent(MainActivity.this,Favlist.class);
                        startActivity(intentl);
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,Instructions.class);
                        startActivity(intent);
                    }
                }
            });
            listView.setAdapter(mAdapter);
        }

    }

