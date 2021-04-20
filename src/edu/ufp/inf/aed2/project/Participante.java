package edu.ufp.inf.aed2.project;


import java.io.Serializable;

public class Participante implements Serializable
{

    private String id;
    private String nome;
    private String mail;

    public Participante(String id, String nome, String mail) {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
    }
}
