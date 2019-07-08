package com.chennikawangmai.tkthree;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class CrimeList extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    Double m,n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list);
        Bundle bundle= getIntent().getExtras();
if(bundle!=null)
        { m=bundle.getDouble("lat");
            n=bundle.getDouble("lng");
            fragmentManager=getSupportFragmentManager();
        if(findViewById(R.id.contl)!=null) {
            if (savedInstanceState != null) {
                return;
            }
            Bundle b = new Bundle();
            b.putDouble("lat", m);
            b.putDouble("lng", n);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CrimeFragment myFragment = new CrimeFragment();
            fragmentTransaction.add(R.id.contl, myFragment, null);
            myFragment.setArguments(bundle);
            fragmentTransaction.commit();
        }

        }
else {
    Toast.makeText(this, "This is my Toast message for crimelist!",
            Toast.LENGTH_LONG).show();
}




    }
}
