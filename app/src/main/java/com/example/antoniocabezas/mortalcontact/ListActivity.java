package com.example.antoniocabezas.mortalcontact;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnBack2 = (Button)findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(this);
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
