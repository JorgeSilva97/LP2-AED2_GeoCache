package edu.ufp.inf.projeto.models;

import edu.ufp.inf.projeto.models.utilizadores.Participante;

public class CoordenadasGPS {

    private double x;
    private double y;
    private Participante participante;
    private PontoInteresse pontoInteresse;
    private GeoCache geoCache;

    public CoordenadasGPS(double x, double y, Participante participante, PontoInteresse pontoInteresse, GeoCache geoCache) {
        this.x = x;
        this.y = y;
        this.participante = participante;
        this.pontoInteresse = pontoInteresse;
        this.geoCache = geoCache;
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

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public PontoInteresse getPontoInteresse() {
        return pontoInteresse;
    }

    public void setPontoInteresse(PontoInteresse pontoInteresse) {
        this.pontoInteresse = pontoInteresse;
    }

    public GeoCache getGeoCache() {
        return geoCache;
    }

    public void setGeoCache(GeoCache geoCache) {
        this.geoCache = geoCache;
    }
}
