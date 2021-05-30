package edu.ufp.inf.projeto.models;

import java.io.Serializable;

public class Objeto implements Serializable
{

    private String nome;


    public Objeto(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
