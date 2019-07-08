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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForceFragment extends Fragment {

    private ListView listView;
    private List<Forcelistforce> forces;
    private String[] forceName;
    SpecificForce activityClass;

    public ForceFragment() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.listView);


        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://data.police.uk/api/").addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitJson api = retrofit.create(RetrofitJson.class);
        Call<List<Forcelistforce>> call = api.getForcesList();
        call.enqueue(new Callback<List<Forcelistforce>>() {
            @Override
            public void onResponse(@NonNull Call<List<Forcelistforce>> call, @NonNull Response<List<Forcelistforce>> response) {
                forces = response.body();
                forceName = new String[forces.size()];
                for (int i = 0; i < forces.size(); i++) {
                    forceName[i] = forces.get(i).getName();
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, forceName);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        Intent intent = new Intent(getActivity(), SpecificForce.class);
                        intent.putExtra("ForceName", listView.getItemAtPosition(position).toString());
                        startActivity(intent);


                    }
                });
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Forcelistforce>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forceslist, container, false);


        return view;



    }
}