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

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button btnSave = (Button)findViewById(R.id.btnSave);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    // METODO QUE CREA CONTACTO NUEVO

    private Contact AddContact() {

        EditText etName = (EditText)findViewById(R.id.etName);
        EditText etNumber = (EditText)findViewById(R.id.etNumber);
        EditText etEmail = (EditText)findViewById(R.id.etEmail);

        Contact contact = new Contact(etName.getText().toString(), Integer.parseInt(etNumber.getText().toString()), etEmail.getText().toString());
        return contact;
    }

    // METODO QUE CFEA UNA ALERTA DE DIALOGO PARA VOLVER ATRÁS

    private AlertDialog cancelDialog() {
        AlertDialog.Builder text=new AlertDialog.Builder(this);
        text.setMessage("DO YOU WANT TO GO BACK?");
        text.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddActivity.this.finish();
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
            case R.id.btnSave:
                Contact contact = AddContact();
                if (contact != null) {
                    Intent intent = new Intent();
                    intent.putExtra("contact", contact);
                    setResult(Activity.RESULT_OK, intent); // DEVUELVE EL NUEVO CONTACTO A LA ACTIVIDAD MAIN
                }
                this.finish();
                break;
            case R.id.btnCancel:
                if (window == null) {
                    window=cancelDialog();
                }
                window.show();
                break;
        }
    }
}
