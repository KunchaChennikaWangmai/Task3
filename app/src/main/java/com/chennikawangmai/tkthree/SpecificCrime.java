package com.chennikawangmai.tkthree;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    Button b,v;
    String idadd,t;
    String date;
    data Data;
    SQLiteDatabase database;
    ListView listview;
    ArrayList<String> listitem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_crime);
        b = (Button) findViewById(R.id.fav);
        v=(Button)findViewById(R.id.addl);
        Dsp = (TextView) findViewById(R.id.textl);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Double m = bundle.getDouble("lat");
            Double n = bundle.getDouble("lng");
            date=String.valueOf(bundle.getString("date"));
            Data= new data(m,n);
            final String id = bundle.getString("CrimeID");
            idadd = String.valueOf(id);
            final Long Id = Long.parseLong(id);
            String t = m.toString();
            String j = n.toString();
            final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://data.police.uk/api/").addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitJson api = retrofit.create(RetrofitJson.class);
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("date",date);
            parameters.put("lat", Data.t);
            parameters.put("lng",Data.j);

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
        Db = new DataBaseHelper(this);
 b.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         String newEntry=String.valueOf(idadd);
         if (idadd.length() != 0) {
             AddData(newEntry);

         } else {
             toastMessage("You must put something in the text field!");
         }
     }
 });


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificCrime.this, Favlist.class);
                startActivity(intent);
            }
        });



    }


    public void AddData(String newEntry) {
        boolean insertData =Db.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    public  class data
    {
        public Double lat;
        public Double lng;
        public String t,j;

        public data(Double m,Double n) {
            lng=n;
            lat=m;
            t = m.toString();
            j = n.toString();

        }
    }


}

