package edu.ufp.inf.projeto.models;

import edu.princeton.cs.algs4.ST;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class TravelBug extends Objeto
{

    private ST<String, ArrayList<String>> logs = new ST<>();
    private GeoCache inicio;
    private GeoCache fim;

    public TravelBug(String nome, GeoCache inicio, GeoCache fim)
    {
        super(nome);
        this.inicio = inicio;
        this.fim = fim;
        Date d = new Date();
        //addLog("Adicionado Participante: "+ this.nome +" com sucesso!", new Timestamp(d.getTime()).toString());
    }







}
