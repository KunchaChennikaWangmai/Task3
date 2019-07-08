package com.chennikawangmai.tkthree;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class ForcesList extends AppCompatActivity {

public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forces_list);
        fragmentManager=getSupportFragmentManager();
        if(findViewById(R.id.cont)!=null)
        {if(savedInstanceState!=null)
        {return;}
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        ForceFragment forcesList=new ForceFragment();
        fragmentTransaction.add(R.id.cont,forcesList,null);
        fragmentTransaction.commit();



        }

    }
}
