package edu.ufp.inf.projeto.models.utilizadores;


import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.GeoCache;
import edu.ufp.inf.projeto.models.Objeto;
import edu.ufp.inf.projeto.models.ST_Proj;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Participante implements Serializable
{

    private int id;
    private String nome;
    private ArrayList<GeoCache> visitadas = new ArrayList<>();
    private ArrayList<Objeto> objetos = new ArrayList<>();
    private ST_Proj<String, ArrayList<String>> logs = new ST_Proj<>();

    /**
     * Construtor da classe Participante
     * @param id, id do participante
     * @param nome, nome do participante
     */
    public Participante(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
        Date d = new Date();
        addLog("Adicionado Participante: "+ this.nome +" com sucesso!", new Timestamp(d.getTime()).toString());
    }


    /**
     * funcao que adiciona um log
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
        //System.out.println("Log adicionado com sucesso!");
    }

    /**
     * funcao que verifica se o participante visitou um GeoCache
     * @param geoCache, geoCache a verificar
     * @param objetosInseridos, array de objetos inseridos
     * @param objetosRetirados, array de objetos retirados
     */
    public void visitouGeo(GeoCache geoCache, ArrayList<Objeto> objetosInseridos, ArrayList<Objeto> objetosRetirados)
    {
        visitadas.add(geoCache);
        for (Objeto o : objetosInseridos)
            objetos.add(o);
        for (Objeto retirado : objetosRetirados)
        {
            for (Objeto atual : this.objetos)
            {
                if (retirado.equals(atual))
                {
                    objetos.remove(retirado);
                    Date d = new Date();
                    addLog("O Participante "+this.getNome()+" visitou a GeoCache que foi criada pelo "
                            +geoCache.getCriadorPremiumParticipante().getNome(), new Timestamp(d.getTime()).toString());
                }
            }
        }
    }



    @Override
    public String toString() {
        return "Participante{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

    public ArrayList<GeoCache> getVisitadas() {
        return visitadas;
    }

    public void setVisitadas(ArrayList<GeoCache> visitadas) {
        this.visitadas = visitadas;
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Objeto> objetos) {
        this.objetos = objetos;
    }

    public ST_Proj<String, ArrayList<String>> getLogs() {
        return logs;
    }

    public void setLogs(ST_Proj<String, ArrayList<String>> logs) {
        this.logs = logs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
