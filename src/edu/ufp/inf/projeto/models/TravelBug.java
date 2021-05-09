package edu.ufp.inf.projeto.models;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.utilizadores.Participante;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class TravelBug extends Objeto
{

    private ST<String, ArrayList<String>> logs = new ST<>();
    private GeoCache inicio;
    private GeoCache objetivoFinal;


    public TravelBug(String nome, GeoCache inicio, GeoCache objetivoFinal)
    {
        super(nome);
        this.inicio = inicio;
        this.objetivoFinal = objetivoFinal;
        Date d = new Date();
        //addLog("Adicionado Participante: "+ this.nome +" com sucesso!", new Timestamp(d.getTime()).toString());
    }

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


    public void update(GeoCache geoCache, Participante participante, boolean b)
    {
        if(b)
        {
            //está a inserir
            Date d = new Date();
            addLog("TravelBug inserido na GeoCache pelo Participante " + participante.getNome(), new Timestamp(d.getTime()).toString());
            if (geoCache.equals(this.objetivoFinal))
            {
                addLog("TravelBug chegou ao Objetivo Final!!", new Timestamp(d.getTime()).toString());
            }
        }
        else
        {
            Date d = new Date();
            addLog("TravelBug inserido na GeoCache pelo Participante " + participante.getNome(), new Timestamp(d.getTime()).toString());
            if (geoCache.equals(this.objetivoFinal))
            {
                addLog("TravelBug não chegou ao Objetivo Final!!", new Timestamp(d.getTime()).toString());
            }

        }
    }
}
