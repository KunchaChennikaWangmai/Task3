package com.chennikawangmai.tkthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Crimelatlng extends AppCompatActivity {
    public TextView txt1,txt2,txt4,txt6;
    EditText txt3,txt5,txt7;
    Button btn;
    boolean a;boolean b;boolean c;
    public boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return true;

        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimelatlng);

        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txt4=(TextView)findViewById(R.id.txt4);
        txt3=(EditText) findViewById(R.id.txt3);
        txt5=(EditText) findViewById(R.id.txt5);
        txt6=(TextView)findViewById(R.id.txt6);
        txt7=(EditText)findViewById(R.id.txt7);
        btn=(Button)findViewById(R.id.btn);
        b=isEmpty(txt5);
        a=isEmpty(txt3);
        c=isEmpty(txt7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMain2Activity();

            }
        });

    }
    public void openMain2Activity()
    {
        Intent intent = new Intent(Crimelatlng.this, CrimeList.class);
        Bundle bundle=new Bundle();
        Double lat= Double.parseDouble(txt3.getText().toString());
        Double lng= Double.parseDouble(txt5.getText().toString());
        String date=txt7.getText().toString();
        bundle.putDouble("lat",lat);
        bundle.putDouble("lng",lng);
        bundle.putString("date",date);
        intent.putExtras(bundle);
        startActivity(intent);


    }
}
