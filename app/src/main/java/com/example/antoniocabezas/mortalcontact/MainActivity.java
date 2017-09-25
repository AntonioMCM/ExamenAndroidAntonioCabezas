package com.example.antoniocabezas.mortalcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final Integer ADDCONTACT=100;
    public static final Integer DELETECONTACT=200;
    // public static final Integer LISTCONTACT=300;
    private ArrayList<Parcelable> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnList = (Button)findViewById(R.id.btnList);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class c = null;
        Intent intent;
        Integer intentId = null;
        switch (v.getId()){
            case R.id.btnAdd:
                intentId = ADDCONTACT;
                c=AddActivity.class;
                break;
            case R.id.btnDelete:
                intentId = DELETECONTACT;
                c=DeleteActivity.class;
                break;
            case R.id.btnList:
                Intent intentL = new Intent (this, ListActivity.class);
                intentL.putParcelableArrayListExtra("contactList", contactList);
                startActivity(intentL);
                return;
        }
        intent = new Intent (this, c);
        startActivityForResult(intent, intentId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ADDCONTACT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    contactList.add(data.getParcelableExtra("contact"));
/*                    TextView tvContactList = (TextView)findViewById(R.id.tvContactList);
                    String list = "";
                    for (Parcelable aux : contactList) {
                        list = list + aux.toString() + "; ";
                        tvContactList.setText("Contactos: " + list);
                    }*/
                }
            }
        }
        if (DELETECONTACT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    contactList.remove(data.getParcelableExtra("contact"));
/*                    TextView tvContactList = (TextView)findViewById(R.id.tvContactList);
                    String list = "";
                    for (Parcelable aux : contactList) {
                        list = list + aux.toString() + "; ";
                        tvContactList.setText("Contactos: " + list);
                    }*/
                }
            }
        }
    }
}
