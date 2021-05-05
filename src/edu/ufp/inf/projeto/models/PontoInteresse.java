package edu.ufp.inf.projeto.models;

public class PontoInteresse
{
    private double x;
    private double y;
    private String regiao;
    private String nome;
    private GeoCache geoCache;


    public PontoInteresse(double x, double y, String regiao, GeoCache geoCache) {
        this.x = x;
        this.y = y;
        this.regiao = regiao;
        this.geoCache = geoCache;
    }

    public PontoInteresse(double x, double y, String regiao, String nome) {
        this.x = x;
        this.y = y;
        this.regiao = regiao;
    }

    public PontoInteresse() {
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
