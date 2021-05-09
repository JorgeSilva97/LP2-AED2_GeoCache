package edu.ufp.inf.projeto.models;

public class PontoInteresse
{
    private double x;
    private double y;
    private String regiao;
    private String nome;
    private GeoCache geoCache;

    /**
     * Construtor da classe PontoInteresse com a GeoCache
     * @param x, coordenada x do ponto de interesse
     * @param y, coordenada y do ponto de interesse
     * @param regiao, regiao do ponto de interesse
     * @param geoCache, geoCache do ponto de interesse
     */
    public PontoInteresse(double x, double y, String regiao, GeoCache geoCache) {
        this.x = x;
        this.y = y;
        this.regiao = regiao;
        this.geoCache = geoCache;
    }

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

    public PontoInteresse() {
    }

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
}
