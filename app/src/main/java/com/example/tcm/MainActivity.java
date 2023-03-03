package com.example.tcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText ETLogin;
    EditText ETSenha;
    DtoContato contato;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        Button btnConf = findViewById(R.id.btnConf);
        Button btnCanc = findViewById(R.id.btnCanc);
        ETLogin = findViewById(R.id.ETLogin);
        ETSenha = findViewById(R.id.ETSenha);
        ProgressBar PB = findViewById(R.id.progressBar);


        btnConf.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                contato = new DtoContato();
                contato.setId(id);
                contato.setLogin(ETLogin.getText().toString());
                contato.setSenha(ETSenha.getText().toString());

                try
                {
                    if (ETLogin.getText().toString().equals("" + contato.getLogin()) && ETSenha.getText().toString().equals("" + contato.getSenha()))
                    {
                        Toast.makeText(MainActivity.this, "Bem-vindo ao nosso App", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(MainActivity.this, InicialActivity.class);
                        startActivity(login);
                    } else {
                        Toast.makeText(MainActivity.this, "Por favor corrija o(s) campo(s)", Toast.LENGTH_SHORT).show();
                        limparCampos(ETLogin, ETSenha);
                    }
                }
                catch (Exception ex)
                    {
                        Toast.makeText(MainActivity.this, "Por favor verifique se usuário ou senha estão corretos. Caso persistente do possível erro: consulte a central de atendimentos " + ex.toString(), Toast.LENGTH_SHORT).show();
                    }

            }
        });

        btnCanc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                limparCampos(ETLogin, ETSenha);
            }
        });

    }

    private void limparCampos(EditText ETLogin, EditText ETSenha)
    {
        ETLogin.setText("");
        ETSenha.setText("");
        ETLogin.requestFocus();
    }

}

