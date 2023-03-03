package com.example.tcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InicialActivity extends AppCompatActivity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        Button btnCons = findViewById(R.id.btnCons);
        Button btnCada = findViewById(R.id.btnCada);
        Button btnPort = findViewById(R.id.btnPort);

        btnCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InicialActivity.this, "Área de consulta", Toast.LENGTH_SHORT).show();
                Intent consultar = new Intent(InicialActivity.this, ConsultarActivity.class);
                startActivity(consultar);
            }
        });


        btnCada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InicialActivity.this, "Bem-vindo ao nosso Cadastro", Toast.LENGTH_SHORT).show();
                Intent cadastro = new Intent(InicialActivity.this, CadastroActivity.class);
                startActivity(cadastro);
            }
        });

        btnPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InicialActivity.this, "Bem-vindo ao nosso Cadastro", Toast.LENGTH_SHORT).show();
                Intent cadastro = new Intent(InicialActivity.this, PortfolioActivity.class);
                startActivity(cadastro);
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.inicial, menu);

        MenuItem sair = menu.findItem(R.id.exit);
        sair.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent main = new Intent(InicialActivity.this, MainActivity.class);
                startActivity(main);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}