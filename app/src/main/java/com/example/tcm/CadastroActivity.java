package com.example.tcm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText ETNomeC = findViewById(R.id.ETNomeC);
        EditText ETLoginC = findViewById(R.id.ETLoginC);
        EditText ETSenhaC = findViewById(R.id.ETSenhaC);
        Button btnConfC = findViewById(R.id.btnConfC);
        Button btnLimparC = findViewById(R.id.btnLimparC);

        btnConfC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DtoContato dtoContato = new DtoContato();
                dtoContato.setNome(ETNomeC.getText().toString());
                dtoContato.setLogin(ETLoginC.getText().toString());
                dtoContato.setSenha(ETSenhaC.getText().toString());

                DaoContato daoContato = new DaoContato(CadastroActivity.this);
                try
                {
                    long linhasInseridas = daoContato.inserir(dtoContato);
                    if (linhasInseridas>0 && ETNomeC.getText().length()>0 && ETLoginC.getText().length()>0 && ETSenhaC.getText().length()>0)
                    {
                        AlertDialog.Builder msg = new AlertDialog.Builder(CadastroActivity.this);
                        msg.setMessage("Cadastro feito com sucesso");
                        msg.show();
                                Intent login = new Intent(CadastroActivity.this, ConsultarActivity.class);
                                startActivity(login);

                    }
                    else
                    {
                        Toast.makeText(CadastroActivity.this, "Falha ao inserir dados", Toast.LENGTH_SHORT).show();
                        limparCampos(ETNomeC, ETLoginC, ETSenhaC);
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(CadastroActivity.this, "Possível erro: consulte a central de atendimentos " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimparC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                limparCampos(ETNomeC, ETLoginC, ETSenhaC);
            }
        });
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
                Intent home = new Intent(CadastroActivity.this, InicialActivity.class);
                startActivity(home);
                return false;
            }
        });

        MenuItem sair = menu.findItem(R.id.exit);
        sair.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent main = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(main);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void limparCampos(EditText ETNomeC, EditText ETLoginC, EditText ETSenhaC) {
        ETNomeC.setText("");
        ETLoginC.setText("");
        ETSenhaC.setText("");
        ETNomeC.requestFocus();
    }
}