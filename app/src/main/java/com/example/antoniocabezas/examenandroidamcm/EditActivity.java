package com.example.antoniocabezas.examenandroidamcm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private Contact clonedContact = new Contact();

    private AlertDialog editDialog;
    private AlertDialog backDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button btnEditContact = (Button)findViewById(R.id.btnEditContact);
        Button btnCancel2 = (Button)findViewById(R.id.btnCancel2);

        btnEditContact.setOnClickListener(this);
        btnCancel2.setOnClickListener(this);

        // RECUPERO EL CONTACTO ENVIADO DESDE LA ACTIVIDAD LIST

        clonedContact =  getIntent().getParcelableExtra("clickedContact");

    }

    // METODO QUE EDITA LOS CAMPOS DEL CONTACTO

    private void editContact() {
        EditText etNewName = (EditText)findViewById(R.id.etNewName);
        EditText etNewNumber = (EditText)findViewById(R.id.etNewNumber);
        EditText etNewEmail = (EditText)findViewById(R.id.etNewEmail);

        clonedContact.setName(etNewName.getText().toString());
        clonedContact.setNumber(Integer.parseInt(etNewNumber.getText().toString()));
        clonedContact.setEmail(etNewEmail.getText().toString());
    }

    // ALERTDIALOG QUE CONFIRMA LA EDICION Y DEVUELVE A LA ACTIVIDAD LISTA

    private AlertDialog editDialog() {
        AlertDialog.Builder edit=new AlertDialog.Builder(this);
        edit.setMessage("ARE YOU SURE ABOUT THE CHANGES?");
        edit.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editContact();
                Intent intent = new Intent();
                intent.putExtra("clonedContact", clonedContact);
                setResult(Activity.RESULT_OK, intent); // ENVIO CONTACTO EDITADO DE VUELTA
                EditActivity.this.finish();
            }
        });
        edit.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return edit.create();
    }

    // ALERTDIALOG PARA VOLVER A LA ACTIVIDAD LIST SIN REALIZAR NINGUNA ACCION

    private AlertDialog BackDialog() {
        AlertDialog.Builder text=new AlertDialog.Builder(this);
        text.setMessage("DO YOU WANT TO GO BACK?");
        text.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                EditActivity.this.finish();
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
        switch (v.getId()) {
            case R.id.btnEditContact:
                if (editDialog == null) {
                    editDialog=editDialog();
                }
                editDialog.show();
                break;
            case R.id.btnCancel2:
                if (backDialog == null) {
                    backDialog=BackDialog();
                }
                backDialog.show();
                break;
        }
    }
}
