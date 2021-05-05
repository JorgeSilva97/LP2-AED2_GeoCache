package edu.ufp.inf.projeto.models;

public class PontoInteresse
{
    private double x;
    private double y;
    private String Regiao;
    private String nome;
    private GeoCache geoCache;

    /*public PontoInteresse(String nome, CoordenadasGPS coordenadasGPS) {
        this.nome = nome;
        this.coordenadasGPS = coordenadasGPS;
    }*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        return Regiao;
    }

    public void setRegiao(String regiao) {
        Regiao = regiao;
    }

    public GeoCache getGeoCache() {
        return geoCache;
    }

    public void setGeoCache(GeoCache geoCache) {
        this.geoCache = geoCache;
    }
}
