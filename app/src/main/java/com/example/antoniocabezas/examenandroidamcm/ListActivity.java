package com.example.antoniocabezas.examenandroidamcm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog backDialog;
    private AlertDialog editDialog;

    private ListView showList;
    private ArrayList<Contact> contactList = new ArrayList<>();
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnBack2 = (Button)findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(this);

        Button btnEdit = (Button)findViewById(R.id.btnEditContact);
        btnEdit.setOnClickListener(this);

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

    private AlertDialog EditDialog() {
        AlertDialog.Builder edit = new AlertDialog.Builder(this);
        edit.setMessage("DO YOU WANT TO EDIT OR DELETE A CONTACT?");
        edit.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        edit.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return edit.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack2:
                if (backDialog == null) {
                    backDialog = Back2Dialog();
                }
                backDialog.show();
                break;
            case R.id.btnEditContact:
                if (editDialog == null) {
                    editDialog = EditDialog();
                }
                editDialog.show();
                break;
        }
    }
}
