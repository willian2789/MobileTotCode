package com.example.tcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        EditText ETNomeC = findViewById(R.id.ETNomeC);
        EditText ETLoginC = findViewById(R.id.ETLoginC);
        EditText ETSenhaC = findViewById(R.id.ETSenhaC);
        Button btnConfC = findViewById(R.id.btnConfC);
        Button btnLimparC = findViewById(R.id.btnLimparC);
        int id;

        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        ETNomeC.setText(b.getString("nome"));
        ETLoginC.setText(b.getString("login"));
        ETSenhaC.setText(b.getString("senha"));

        btnConfC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DtoContato dtoContato = new DtoContato();
                dtoContato.setId(id);
                dtoContato.setNome(ETNomeC.getText().toString());
                dtoContato.setLogin(ETLoginC.getText().toString());
                dtoContato.setSenha(ETSenhaC.getText().toString());

                DaoContato daoContato = new DaoContato(AlterarActivity.this);
                try
                {
                    long linhasInseridas = daoContato.alterar(dtoContato);
                    if (linhasInseridas>0 && ETNomeC.getText().length()>0 && ETLoginC.getText().length()>0 && ETSenhaC.getText().length()>0)
                    {
                        Toast.makeText(AlterarActivity.this, "Dados alterados com sucesso", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(AlterarActivity.this, ConsultarActivity.class);
                        startActivity(login);
                    }
                    else
                    {
                        Toast.makeText(AlterarActivity.this, "Falha ao alterar dados", Toast.LENGTH_SHORT).show();
                        limpar(ETLoginC, ETSenhaC, ETNomeC);
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(AlterarActivity.this, "Possível erro: consulte a central de atendimentos " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimparC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                limpar(ETNomeC, ETLoginC, ETSenhaC);
            }
        });
    }

    private void limpar(EditText ETNomeC, EditText ETLoginC, EditText ETSenhaC) {
        ETNomeC.setText("");
        ETLoginC.setText("");
        ETSenhaC.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.home, menu);

        MenuItem home = menu.findItem(R.id.Home);
        home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                Intent home = new Intent(AlterarActivity.this, InicialActivity.class);
                startActivity(home);
                return false;
            }
        });

        MenuItem sair = menu.findItem(R.id.exit);
        sair.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent main = new Intent(AlterarActivity.this, MainActivity.class);
                startActivity(main);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}