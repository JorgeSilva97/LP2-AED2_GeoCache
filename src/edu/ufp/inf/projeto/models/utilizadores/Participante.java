package edu.ufp.inf.projeto.models.utilizadores;


import java.io.Serializable;
import java.util.ArrayList;

public class Participante implements Serializable
{

    private String id;
    private String nome;
    private String mail;
    private ArrayList<String> logs = new ArrayList<>();

    public Participante(String id, String nome, String mail)
    {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
    }



    //adiciona, remove OBJETO
    //adiciona e lista LOgs
    //associar log a particpantes, geocache e travel bug

    public void adicionaLog(String s)
    {

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
