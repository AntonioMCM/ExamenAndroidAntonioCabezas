package com.example.antoniocabezas.examenandroidamcm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog backDialog;

    public static final Integer EDITCONTACT=400;

    private ListView showList;
    private ArrayList<Contact> contactList = new ArrayList<>();

      private AdapterView.OnItemClickListener adapterView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            EditDialog(adapterView, i);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnBack2 = (Button)findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(this);

        showList = (ListView)findViewById(R.id.showList);
        contactList =  getIntent().getParcelableArrayListExtra("contactList");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList);
        showList.setAdapter(adapter);
        showList.setOnItemClickListener(adapterView);
    }

    // ALERTDIALOG PARA VOLVER AL MENU PRINCIPAL, DEVOLVIENDO LA LISTA UPDATEADA

    private AlertDialog Back2Dialog() {
        AlertDialog.Builder text = new AlertDialog.Builder(this);
        text.setMessage("DO YOU WANT TO GO BACK?");
        text.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("contactList", contactList);
                setResult(Activity.RESULT_OK, intent);
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

    // METODO QUE SE EJECUTA SI SELECCIONAMOS EDITAR EN EL ALERTDIALOG DE LA LISTA, VA A LA ACTIVIDAD EDITAR ENVIANDOLE UN CONTACTO

    private void intentEdit(final AdapterView adapterView, final int position) {
        Contact clickedContact = (Contact) adapterView.getItemAtPosition(position);
        Integer intentId = EDITCONTACT;
        Intent intentE = new Intent (ListActivity.this, EditActivity.class);
        intentE.putExtra("clickedContact", clickedContact);
        startActivityForResult(intentE, intentId);
    }

    // METODO QUE SE EJECUTA SI SELECCIONAS BORRAR EN EL ALERTDIALOG DE LA LISTA, BORRA EL CONTACTO SELECCIONADO AUTOMATICAMENTE

    private void intentDelete(final AdapterView adapterView, final int position) {
        Contact clickedContact = (Contact) adapterView.getItemAtPosition(position);
        contactList.remove(clickedContact);
        showList.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList));
    }

    // ALERTDIALOG QUE SALTA AL CLICKAR SOBRE UN CONTACTO DE LA LISTA

    private AlertDialog EditDialog(final AdapterView adapterView, final int position) {
        AlertDialog.Builder edit = new AlertDialog.Builder(this);
        edit.setMessage("DO YOU WANT TO EDIT OR DELETE THE CONTACT?");
        edit.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intentEdit(adapterView, position);
            }
        });
        edit.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intentDelete(adapterView, position);
            }
        });
        return edit.show();
    }

    @Override
    public void onClick(View v) {
        if (backDialog == null) {
            backDialog = Back2Dialog();
        }
        backDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (EDITCONTACT == requestCode) {
            if (resultCode == Activity.RESULT_OK) { // RECOGE EL CONTACTO EDITADO Y ACTUALIZA LA LISTA
                if (data.hasExtra("clonedContact")) {
                    contactList.set(0, (Contact) data.getParcelableExtra("clonedContact"));
                    ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList);
                    showList.setAdapter(adapter);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) { // ACTUALIZA LA LISTA AL VOLVER A LA ACTIVIDAD
                ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList);
                showList.setAdapter(adapter);
            }
        }
    }
}
