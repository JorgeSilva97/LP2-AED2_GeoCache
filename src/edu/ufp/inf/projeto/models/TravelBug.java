package edu.ufp.inf.projeto.models;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.utilizadores.Participante;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class TravelBug extends Objeto implements Serializable
{

    private ST_Proj<String, ArrayList<String>> logs = new ST_Proj<>();
    private GeoCache inicio;
    private GeoCache objetivoFinal;
    private Participante participante;


    /**
     * Construtor da classe TravelBug
     * @param nome nome do TravelBug
     * @param inicio inicio da GeoCache
     * @param objetivoFinal objetivo final da geocache
     */
    public TravelBug(String nome, GeoCache inicio, GeoCache objetivoFinal)
    {
        super(nome);
        this.inicio = inicio;
        this.objetivoFinal = objetivoFinal;
        Date d = new Date();
        //addLog("Adicionado Participante: "+ this.nome +" com sucesso!", new Timestamp(d.getTime()).toString());
    }

    /**
     *funcao que adiciona logs
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

    /**
     * funcao que faz o update do travelBug
     * @param geoCache
     * @param participante
     * @param insere
     */
    public void update(GeoCache geoCache, Participante participante, boolean insere)
    {
        if(insere)
        {
            //está a inserir
            Date d = new Date();
            addLog("TravelBug inserido na GeoCache pelo Participante " + participante.getNome(), new Timestamp(d.getTime()).toString());
            if (geoCache.equals(this.objetivoFinal))
                addLog("TravelBug chegou ao Objetivo Final!!", new Timestamp(d.getTime()).toString());
        }
        else
        {
            //está a retirar
            Date d = new Date();
            addLog("TravelBug retirado na GeoCache pelo Participante " + participante.getNome(), new Timestamp(d.getTime()).toString());
        }
    }

    public ST_Proj<String, ArrayList<String>> getLogs() {
        return logs;
    }

    public void setLogs(ST_Proj<String, ArrayList<String>> logs) {
        this.logs = logs;
    }

    public GeoCache getInicio() {
        return inicio;
    }

    public void setInicio(GeoCache inicio) {
        this.inicio = inicio;
    }

    public GeoCache getObjetivoFinal() {
        return objetivoFinal;
    }

    public void setObjetivoFinal(GeoCache objetivoFinal) {
        this.objetivoFinal = objetivoFinal;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
