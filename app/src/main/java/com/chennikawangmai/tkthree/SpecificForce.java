package com.chennikawangmai.tkthree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecificForce extends AppCompatActivity {
    TextView Dsp;
    Force force;
    String t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_force);
        Dsp = (TextView) findViewById(R.id.text);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String t = bundle.getString("ForceName");
            String string;

            if (t == "Police Service of Northern Ireland") {
                string = "northern-ireland";
            } else {
                string = t;
                string = string.trim();


                String lastWord = string.substring(string.lastIndexOf(' ') + 1);

                int n = t.length();
                string = string.replace(lastWord, "");
                string = string.trim();
                string = string.replaceAll(" ", "-").toLowerCase();
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://data.police.uk/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitJson jsonPlaceHolderApi = retrofit.create(RetrofitJson.class);

            Call<Force> call = jsonPlaceHolderApi.getForces(string);

            call.enqueue(new Callback<Force>() {
                @Override
                public void onResponse(Call<Force> call, Response<Force> response) {

                    if (!response.isSuccessful()) {
                        Dsp.setText("Code: " + response.code());
                        return;
                    }

                    force = response.body();
                    String content = " ";
                    content += "ID:" + force.getId() + "\n";
                    content += "URL:" + force.getUrl() + "\n";
                    content += "TELEPHONE:" + force.getTelephone() + "\n";
                    content += "DESCRIPTION:" + force.getDescription() + "\n";
                    Dsp.append(content);


                }

                @Override
                public void onFailure(Call<Force> call, Throwable t) {
                    Dsp.setText(t.getMessage());
                }
            });


        } else {
            Dsp.setText("dcedcfevfv");
        }
    }
}
