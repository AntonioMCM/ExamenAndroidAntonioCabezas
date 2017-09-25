package com.example.antoniocabezas.mortalcontact;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog dialog;

    private ListView showList;
    private ArrayList<Contact> contactList = new ArrayList<>();
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnBack2 = (Button)findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(this);

        showList = (ListView)findViewById(R.id.contactList);
        contactList =  getIntent().getParcelableArrayListExtra("contactList");
        List<String> aux=crearAdapter(contactList);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,aux);
        showList.setAdapter(adapter);

    }

    private List<String> crearAdapter(ArrayList<Contact> contactList) {
        List<String>aux=new ArrayList<>();
        for(Contact c:contactList){

            aux.add(c.getName()+", "+c.getNumber()+", "+c.getEmail());

        }
        return aux;
    }

    private AlertDialog Back2Dialog() {
        AlertDialog.Builder text = new AlertDialog.Builder(this);
        text.setMessage("DO YOU WANT TO GO BACK?");
        text.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListActivity.this.finish();
            }
        });
        text.setNegativeButton("STAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return text.create();
    }

    @Override
    public void onClick(View v) {
            if (dialog == null) {
                dialog=Back2Dialog();
            }
            dialog.show();
    }
}
