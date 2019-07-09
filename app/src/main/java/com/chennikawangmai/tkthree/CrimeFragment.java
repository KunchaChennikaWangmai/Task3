package com.chennikawangmai.tkthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrimeFragment extends Fragment {


    private ListView listView;
    private List<Crime> crimes;
    private String[] crimeCategory;
   /* SpecificForce activityClass;*/
    long[] ids;long bgt;String[] id;
    /*String[] persistentid,category;*/
    Double p,q;
    String date;
    data Data;

    public CrimeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.listViewl);
       Bundle bg=this.getArguments();
       if(bg!=null) {
           Double m = bg.getDouble("lat");
            Double n = bg.getDouble("lng");
            date=String.valueOf(bg.getString("date"));
            p=m;q=n;
            String t = m.toString();
           String j = n.toString();
          Data= new data(date,j);
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
                   crimeCategory = new String[crimes.size()];
                   ids = new long[crimes.size()];
                  id = new String[crimes.size()];
                   for (int i = 0; i < crimes.size(); i++) {
                       crimeCategory[i] = crimes.get(i).getCategory();
                       ids[i]=crimes.get(i).getId();
                       id[i]=String.valueOf(ids[i]);
                   }
                   ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, id);

                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                       @Override
                       public void onItemClick(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
                           Intent intent = new Intent(getActivity(), SpecificCrime.class);
                           Bundle ble=new Bundle();
                           ble.putString("CrimeID", listView.getItemAtPosition(position).toString());
                           ble.putDouble("lat",p);
                           ble.putDouble("lng",q);
                           intent.putExtras(ble);
                           startActivity(intent);


                       }
                   });
                   listView.setAdapter(arrayAdapter);
               }

               @Override
               public void onFailure(@NonNull Call<List<Crime>> call, @NonNull Throwable t) {

               }
           });
       }
       else {
           Toast.makeText(getActivity(), "This is my Toast message for crimefragment!",
                   Toast.LENGTH_LONG).show();
       }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_crime_fragment, container, false);




        return view;



    }
public  class data
{
    public String lng;
    public String date;

    public data(String m,String n) {
        lng=String.valueOf(n);
        date=String.valueOf(m);

    }
}

}
