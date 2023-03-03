package com.example.tcm;

import androidx.annotation.NonNull;

public class DtoContato
{
    private int id;
    private String nome, login, senha;

    public DtoContato(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public DtoContato(){

    }

    @NonNull
    @Override
    public String toString()
    {
        return nome + " - " + login;
    }

    public int getId() {
        return id;
    }

    public void setId( int id)
    {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
