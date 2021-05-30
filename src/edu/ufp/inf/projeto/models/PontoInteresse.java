package edu.ufp.inf.projeto.models;

import java.io.Serializable;

public class PontoInteresse implements Serializable
{
    /**
     * coordenada x de onde se encontra
     * coordenada y de onde se encontra
     * nome da regiao
     * nome do PontoInteresse
     * GeoCache que se encontra no PontoInteresse
     */
    private int vertexId = -1;
    private double x;
    private double y;
    private String regiao;
    private String nome;
    private GeoCache geoCache;

    /**
     * Construtor da classe PontoInteresse com o nome
     * @param x, coordenada x do ponto de interesse
     * @param y, coordenada y do ponto de interesse
     * @param regiao, regiao do ponto de interesse
     * @param nome, nome do ponto de interesse
     */
    public PontoInteresse(double x, double y, String regiao, String nome) {
        this.x = x;
        this.y = y;
        this.regiao = regiao;
        this.nome = nome;
    }

    public PontoInteresse(int vertexId, double x, double y, String nome) {
        this.vertexId = vertexId;
        this.x = x;
        this.y = y;
        this.nome = nome;
    }

    public PontoInteresse() {
    }



    public double getDistanciaParaOutroPontoInteresse(PontoInteresse pi)
    {
        return Math.sqrt((pi.getY() - this.y) * (pi.getY() - this.y) + (pi.getX() - this.x) * (pi.getX() - this.x));
    }

    public double getDistanciaFromCoordenada(int x , int y)
    {
        return Math.sqrt((y - this.y) * (y - this.y) + (x - this.x) * (x - this.x));
    }


    public int getVertexId()
    {
        if (this.vertexId == -1)
            System.out.println("vertexId não definido, logo sub-graph não está criado");
        return vertexId;
    }

    public void setVertexId(int vertexId) {
        this.vertexId = vertexId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public GeoCache getGeoCache() {
        return geoCache;
    }

    public void setGeoCache(GeoCache geoCache) {
        this.geoCache = geoCache;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
