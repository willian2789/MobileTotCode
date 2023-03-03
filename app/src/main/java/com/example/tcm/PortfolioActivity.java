package com.example.tcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PortfolioActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
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
                Intent home = new Intent(PortfolioActivity.this, InicialActivity.class);
                startActivity(home);
                return false;
            }
        });

        MenuItem sair = menu.findItem(R.id.exit);
        sair.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent main = new Intent(PortfolioActivity.this, MainActivity.class);
                startActivity(main);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}