package edu.ufp.inf.projeto.models.utilizadores;


import edu.princeton.cs.algs4.ST;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Participante implements Serializable
{

    private String id;
    private String nome;
    private String mail;
    private ST<String, ArrayList<String>> logs = new ST<>();

    public Participante(String id, String nome, String mail)
    {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
        Date d = new Date();
        addLog("Adicionado Participante: "+ this.nome +" com sucesso!", new Timestamp(d.getTime()).toString());
    }



    //adiciona, remove OBJETO
    //adiciona e lista LOgs
    //associar log a particpantes, geocache e travel bug

    /**
     *
     * @param l
     * @param dateTime
     */
    public void addLog (String l, String dateTime)
    {
        if(logs.contains(dateTime))
            logs.get(dateTime).add(l);
        else
        {
            ArrayList<String> lg = new ArrayList<>();
            lg.add(l);
            logs.put(dateTime, lg);
        }
        System.out.println("Log adicionado com sucesso!");
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
