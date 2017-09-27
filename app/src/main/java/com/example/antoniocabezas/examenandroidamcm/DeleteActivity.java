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

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnBack = (Button)findViewById(R.id.btnBack);

        btnDelete.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    // METODO QUE "CREA" UN NUEVO CONTACTO QUE COINCIDA CON UN EXISTENTE PARA BORRARLO

    private Contact DeleteContact() {

        EditText dtName = (EditText)findViewById(R.id.dtName);
        EditText dtNumber = (EditText)findViewById(R.id.dtNumber);
        EditText dtEmail = (EditText)findViewById(R.id.dtEmail);

        Contact contact = new Contact(dtName.getText().toString(), Integer.parseInt(dtNumber.getText().toString()), dtEmail.getText().toString());
        return contact;
    }

    // METODO QUE CREA UN ALERT DIALOG PARA VOLVER ATRÁS SIN BORRAR

    private AlertDialog BackDialog() {
        AlertDialog.Builder text=new AlertDialog.Builder(this);
        text.setMessage("DO YOU WANT TO GO BACK?");
        text.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteActivity.this.finish();
            }
        });
        text.setNegativeButton("STAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return text.create();
    }

    // METODO QUE CREA UN ALERT DIALOG PARA CONFIRMAR QUE QUIERE BORRAR EL CONTACTO

    private void DeleteDialog() {
        AlertDialog.Builder delete=new AlertDialog.Builder(this);
        delete.setMessage("DO YOU WANT TO DELETE THIS CONTACT?");
        delete.setPositiveButton("DO IT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Contact contact = DeleteContact();
                if (contact != null) {
                    Intent intent = new Intent();
                    intent.putExtra("contact", contact);
                    setResult(Activity.RESULT_OK, intent); // DEVUELVE EL CONTACTO A BORRAR A LA ACTIVIDAD MAIN
                }
                DeleteActivity.this.finish();
            }
        });
        delete.setNegativeButton("LOL NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        delete.create().show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDelete:
                DeleteDialog();
                break;
            case R.id.btnBack:
                if (window == null) {
                    window=BackDialog();
                }
                window.show();
                break;
        }
    }
}
