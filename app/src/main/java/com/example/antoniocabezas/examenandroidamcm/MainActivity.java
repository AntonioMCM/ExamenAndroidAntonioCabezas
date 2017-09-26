package com.example.antoniocabezas.examenandroidamcm;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final Integer ADDCONTACT=100;
    public static final Integer DELETECONTACT=200;
    public static final Integer LISTCONTACT=300;
    public static final Integer EDITCONTACT=400;
    private Set<Contact> contactManager = new TreeSet<>();
    private ArrayList<Parcelable> contactList = new ArrayList<Parcelable>(contactManager);

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
        Intent intent;
        Integer intentId;
        switch (v.getId()){
            case R.id.btnAdd:
                intentId = ADDCONTACT;
                intent = new Intent (this, AddActivity.class);
                startActivityForResult(intent, intentId);
                break;
            case R.id.btnDelete:
                intentId = DELETECONTACT;
                intent = new Intent (this, DeleteActivity.class);
                startActivityForResult(intent, intentId);
                break;
            case R.id.btnList:
                intentId = LISTCONTACT;
                Intent intentL = new Intent (this, ListActivity.class);
                intentL.putParcelableArrayListExtra("contactList", contactList);
                startActivityForResult(intentL, intentId);
                break;
        }
;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ADDCONTACT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    contactList.add(data.getParcelableExtra("contact"));
                    TextView tvContactList = (TextView)findViewById(R.id.tvContactList);
                    String list = "";
                    for (Parcelable aux : contactList) {
                        list = list + aux.toString() + "; ";
                        tvContactList.setText("Contactos: " + list);
                    }
                }
            }
        }
        if (DELETECONTACT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    contactList.remove(data.getParcelableExtra("contact"));
                    TextView tvContactList = (TextView)findViewById(R.id.tvContactList);
                    String list = "";
                    for (Parcelable aux : contactList) {
                        list = list + aux.toString() + "; ";
                        tvContactList.setText("Contactos: " + list);
                    }
                }
            }
        }
        if (LISTCONTACT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    //c;
                    TextView tvContactList = (TextView)findViewById(R.id.tvContactList);
                    String list = "";
                    for (Parcelable aux : contactList) {
                        list = list + aux.toString() + "; ";
                        tvContactList.setText("Contactos: " + list);
                    }
                }
            }
        }
    }
}
