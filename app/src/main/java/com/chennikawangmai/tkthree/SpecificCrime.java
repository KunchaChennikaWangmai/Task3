package com.chennikawangmai.tkthree;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecificCrime extends AppCompatActivity  {
    long[] ids;
    List<Crime> crimes;
    String content;
    TextView Dsp;
    DataBaseHelper Db ;
    Button b, v;
    String idadd;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_crime);
        b = (Button) findViewById(R.id.fav);
        v = (Button) findViewById(R.id.add);
        Dsp = (TextView) findViewById(R.id.textl);
        Db = new DataBaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Double m = bundle.getDouble("lat");
            Double n = bundle.getDouble("lng");
            final String id = bundle.getString("CrimeID");
            idadd = String.valueOf(id);
            final Long Id = Long.parseLong(id);
            String t = m.toString();
            String j = n.toString();
            final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://data.police.uk/api/").addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitJson api = retrofit.create(RetrofitJson.class);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("lat", t);
            parameters.put("lng", j);
            Call<List<Crime>> call = api.getcrimes(parameters);
            call.enqueue(new Callback<List<Crime>>() {
                @Override
                public void onResponse(@NonNull Call<List<Crime>> call, @NonNull Response<List<Crime>> response) {
                    crimes = response.body();

                    ids = new long[crimes.size()];

                    for (int i = 0; i < crimes.size(); i++) {

                        ids[i] = crimes.get(i).getId();
                        if (Id == ids[i]) {
                            content = " ";
                            content += "ID:" + crimes.get(i).getId() + "\n";
                            content += "CATEGORY:" + crimes.get(i).getCategory() + "\n";

                        }

                    }
                    Dsp.append(content);

                }

                @Override
                public void onFailure(@NonNull Call<List<Crime>> call, @NonNull Throwable t) {
                    Dsp.setText("On Response not successful");

                }
            });
        } else {
            Dsp.setText("Bundle not sucessful");
        }
 b.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         String id=idadd;
         if(!(id.equals("")&&Db.insertData(id) ))
         {
             Toast.makeText(SpecificCrime.this,"Data added",Toast.LENGTH_SHORT).show();
         }
         else
         {
             Toast.makeText(SpecificCrime.this,"Data not added",Toast.LENGTH_SHORT).show();
         }
     }
 });

/*v.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        viewData();
    }
});*/


    }
    public void viewData()
    {
        Cursor cursor=Db.viewData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this,"No Data to show",Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext())
            {
              String t=String.valueOf(cursor.getString(1));
                Intent intent=new Intent(this,Favlist.class);
                Bundle b=new Bundle();
                b.putString("CrimeId",t);
                intent.putExtras(b);
                startActivity(intent);
            }
        }
    }

}

