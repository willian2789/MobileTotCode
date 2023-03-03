package com.example.tcm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DaoContato extends SQLiteOpenHelper
{
    private final String TABELA = "TB_CONTATO";

    public DaoContato(@Nullable Context context)
    {
        super(context, "DB_CONTATO", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String comando = "CREATE TABLE " + TABELA + "(" +
                "ID INTEGER PRIMARY KEY," +
                "NOME VARCHAR (50)," +
                "LOGIN VARCHAR (20)," +
                "SENHA VARCHAR (15))";

        sqLiteDatabase.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long inserir(DtoContato contato)
    {
        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());
        values.put("LOGIN", contato.getLogin());
        values.put("SENHA", contato.getSenha());

        long nLinhas = getWritableDatabase().insert(TABELA, null, values);
        return nLinhas;

    }

    public ArrayList<DtoContato> consultarT()
    {
        String comando = "SELECT * FROM " + TABELA;
        Cursor cursor = getReadableDatabase().rawQuery(comando,null);
        ArrayList<DtoContato> arrayListContato = new ArrayList<>();

        while (cursor.moveToNext())
        {
            DtoContato dtoContato = new DtoContato();
            dtoContato.setId(cursor.getInt(0));
            dtoContato.setNome(cursor.getString(1));
            dtoContato.setLogin(cursor.getString(2));
            dtoContato.setSenha(cursor.getString(3));

            arrayListContato.add(dtoContato);
        }

        return arrayListContato;

    }

    public ArrayList<DtoContato> consultarP(String nome)
    {
        String comando = "SELECT * FROM " + TABELA + " WHERE NOME LIKE ? ";
        String[] args = {"%"+nome+"%"};
        Cursor cursor = getReadableDatabase().rawQuery(comando, args);
        ArrayList<DtoContato> arrayListContato = new ArrayList<>();

        while (cursor.moveToNext())
        {
            DtoContato dtoContato = new DtoContato();
            dtoContato.setId(cursor.getInt(0));
            dtoContato.setNome(cursor.getString(1));
            dtoContato.setLogin(cursor.getString(2));
            dtoContato.setSenha(cursor.getString(3));

            arrayListContato.add(dtoContato);
        }
        return arrayListContato;
    }

    public int excluir(DtoContato contato)
    {
        String id = "id=?";
        String[] args = {contato.getId()+""};
        return getWritableDatabase().delete(TABELA,id,args);
    }

    public long alterar(DtoContato contato)
    {
        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());
        values.put("LOGIN", contato.getLogin());
        values.put("SENHA", contato.getSenha());
        String id = "id=?";
        String[] args = {contato.getId()+""};

        return getWritableDatabase().update(TABELA, values, id, args);
    }
}
