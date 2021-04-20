package edu.ufp.inf.projeto.models;

import java.util.ArrayList;

public class GeoCache {

    private int id;
    private CoordenadasGPS coordenadas;
    private int dificuldade;
    private TipoGeoCacheEnum tipoGeoCache;
    private ArrayList<Objeto> objetos = new ArrayList<>();
    private ArrayList<String> logs = new ArrayList<>();

    public GeoCache(int id, CoordenadasGPS coordenadas, int dificuldade, TipoGeoCacheEnum tipoGeoCache)
    {
        this.id = id;
        this.coordenadas = coordenadas;
        this.dificuldade = dificuldade;
        this.tipoGeoCache = tipoGeoCache;
    }

    /**
     * Adiciona Objeto à ArrayList
     * @param o objeto a adicionar
     */
    public void addObjeto (Objeto o)
    {
        for (Objeto ob : this.objetos)
        {
            if (ob.equals(o))
            {
                System.out.println("Objeto já existente!");
                return;
            }
        }
        objetos.add(o);
        System.out.println("Objeto adicionado com sucesso!");
    }

    /**
     * Remove Objeto ao ArrayList
     * @param o
     */
    public void removeObjeto (Objeto o)
    {
        for (Objeto ob : this.objetos)
        {
            if (ob.equals(o))
            {
                objetos.remove(o);
                System.out.println("Objeto removido com sucesso!");
                return;
            }
        }
        System.out.println("Objeto impossível de remover!");
    }

    /**
     *
     * @param l
     */
    public void addLog (String l)
    {
        for (String lg : this.logs)
        {

        }
        logs.add(l);
        System.out.println("Log adicionado com sucesso!");
    }

    /**
     *
     * @param l
     */
    public void removeLog (String l)
    {
        for (String lg : this.logs)
        {
            if (lg.equals(l))
            {
                logs.remove(l);
                System.out.println("Log removido com sucesso!");
                return;
            }
        }
        System.out.println("Log impossível de remover!");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoordenadasGPS getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(CoordenadasGPS coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public TipoGeoCacheEnum getTipoGeoCache() {
        return tipoGeoCache;
    }

    public void setTipoGeoCache(TipoGeoCacheEnum tipoGeoCache) {
        this.tipoGeoCache = tipoGeoCache;
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Objeto> objetos) {
        this.objetos = objetos;
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<String> logs) {
        this.logs = logs;
    }
}
