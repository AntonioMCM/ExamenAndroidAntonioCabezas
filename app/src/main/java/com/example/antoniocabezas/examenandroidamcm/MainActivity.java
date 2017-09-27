package com.example.antoniocabezas.examenandroidamcm;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

// HECHO: ADD FUNCIONA PERFECTAMENTE
//        DELETE FUNCIONA PERFECTAMENTE SI INTRODUCES LOS DATOS DE UN CONTACTO EXISTENTE
//        LIST FUNCIONA PERFECTAMENTE
//        EDIT FROM LIST EDITA EL CONTACTO PERFECTAMENTE, PERO SOLO PUEDO SELECCIONAR LA POSICION 0 DE LA LISTA <ARREGLADO POR PROFESOR>
//        DELETE FROM LIST BORRA EL CONTACTO DE LA LISTA INSTANTANEAMENTE

// POR HACER: DEFENDERSE DE LOS NULLS
//            adapterView.getItemAtPosition(position) no funciona, uso la posicion 0 de la lista para pruebas. <ARREGLADO POR PROFESOR>
//            CONSEGUIR QUE LA LISTA SELECCIONE EL ITEM CORRECTO EN LUGAR DE LA POSICION 0



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // CONSTANTES PARA EL SET RESULT DE LOS INTENTS

    public static final Integer ADDCONTACT=100;
    public static final Integer DELETECONTACT=200;
    public static final Integer LISTCONTACT=300;

    // DECLARACION DE LA LISTA DE CONTACTOS

    private Set<Contact> contactManager = new TreeSet<>();
    private ArrayList<Parcelable> contactList = new ArrayList<Parcelable>(contactManager);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DECLARACION BOTONES ACTIVIDAD PRINCIPAL

        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnList = (Button)findViewById(R.id.btnList);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnList.setOnClickListener(this);


        // CONTACTOS POR DEFECTO PARA PRUEBAS

        contactList.add(new Contact("A", 1, "a"));
        contactList.add(new Contact("B", 2, "b"));
        contactList.add(new Contact("C", 3, "c"));
        contactList.add(new Contact("D", 4, "d"));

    }

    @Override
    public void onClick(View v) {

        // METODO ONCLICK CON SWITCH PARA LOS DISTINTOS BOTONES Y SUS ACCIONES

        Intent intent;
        Integer intentId;
        switch (v.getId()){
            case R.id.btnAdd: // INTENT HACIA LA ACTIVIDAD ADD
                intentId = ADDCONTACT;
                intent = new Intent (this, AddActivity.class);
                startActivityForResult(intent, intentId);
                break;
            case R.id.btnDelete: // INTENT HACIA LA ACTIVIDAD DELETE
                intentId = DELETECONTACT;
                intent = new Intent (this, DeleteActivity.class);
                startActivityForResult(intent, intentId);
                break;
            case R.id.btnList: // INTENT HACIA LA ACTIVIDAD LIST
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
        if (ADDCONTACT == requestCode) { // RESPUESTA RECIBIDA TRAS FINALIZAR ADD
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    contactList.add(data.getParcelableExtra("contact"));
                }
            }
        }
        if (DELETECONTACT == requestCode) { // RESPUESTA RECIBIDA TRAS FINALIZAR DELETE
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contact")) {
                    contactList.remove(data.getParcelableExtra("contact"));
                }
            }
        }
        if (LISTCONTACT == requestCode) { // RESPUESTA RECIBIDA TRAS FINALIZAR LIST
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("contactList")) {
                    contactList = data.getParcelableArrayListExtra("contactList");
                }
            }
        }
    }
}
