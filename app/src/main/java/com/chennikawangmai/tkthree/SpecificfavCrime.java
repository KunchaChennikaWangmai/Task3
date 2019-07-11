package com.chennikawangmai.tkthree;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecificfavCrime extends AppCompatActivity {

    String idadd,t;
    String date;
    data Data;    long[] ids;
    List<Crime> crimes;
    String content;
    TextView Dsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificfav_crime);
        Dsp = (TextView) findViewById(R.id.textfc);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Double m = bundle.getDouble("lat");
            Double n = bundle.getDouble("lng");
            date=String.valueOf(bundle.getString("date"));
            Data= new data(m,n);
            final String id = bundle.getString("id");
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
                        else{
                           content=("Sorry,no such crime  is present in the list of crimes as on "+date+" at latitude "+Data.t+" and longitude "+Data.j+" ." +
                                    "Please go back and change the date and location");
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
