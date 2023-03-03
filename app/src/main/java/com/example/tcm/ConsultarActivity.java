package com.example.tcm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity
{
    ArrayList<DtoContato> arrayListContato;
    DaoContato daoContato = new DaoContato(ConsultarActivity.this);
    ListView LVContato;
    DtoContato contato;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        LVContato = findViewById(R.id.LVContato);

        LVContato.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l)
            {
                contato = arrayListContato.get(posicao);
                registerForContextMenu(LVContato);

                return false;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                arrayListContato = daoContato.consultarT();
                ArrayAdapter adapter = new ArrayAdapter(ConsultarActivity.this, android.R.layout.simple_list_item_1, arrayListContato);
                LVContato.setAdapter(adapter);

                swipeRefreshLayout.setRefreshing(false);

            }
        });

        arrayListContato = daoContato.consultarT();
        ArrayAdapter adapter = new ArrayAdapter(ConsultarActivity.this, android.R.layout.simple_list_item_1, arrayListContato);
        LVContato.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0, "Alterar");
        menu.add(0,1,1, "Deletar");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getItemId()==0)
        {
            Intent alterar = new Intent(ConsultarActivity.this, AlterarActivity.class);
            alterar.putExtra("id", contato.getId());
            alterar.putExtra("nome", contato.getNome());
            alterar.putExtra("login", contato.getLogin());
            alterar.putExtra("senha", contato.getSenha());
            startActivity(alterar);
        }
        else
        {
            AlertDialog.Builder msg = new AlertDialog.Builder(ConsultarActivity.this);
            msg.setMessage("Tem certeza que deseja excluir?");
            msg.setPositiveButton("Positivo", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    int deletados = daoContato.excluir(contato);
                    if (deletados > 0)
                    {
                        Toast.makeText(ConsultarActivity.this, "Deletado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ConsultarActivity.this, "Erro ao excluir, tente novamente ou contate a central", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            msg.setNegativeButton("negativo", null);
            msg.show();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem search = menu.findItem(R.id.Search);
        MenuItem home = menu.findItem(R.id.Home);
        SearchView consultarP = (SearchView)search.getActionView();
        consultarP.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                arrayListContato = daoContato.consultarP(newText);
                ArrayAdapter adapter = new ArrayAdapter(ConsultarActivity.this, android.R.layout.simple_list_item_1, arrayListContato);
                LVContato.setAdapter(adapter);
                return false;
            }
        });

        home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                Intent home = new Intent(ConsultarActivity.this, InicialActivity.class);
                startActivity(home);
                return false;
            }
        });
        ;

        return super.onCreateOptionsMenu(menu);
    }
}