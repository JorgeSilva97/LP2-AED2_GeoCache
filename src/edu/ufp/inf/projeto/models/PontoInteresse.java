package edu.ufp.inf.projeto.models;

public class PontoInteresse
{
    private String nome;
    private CoordenadasGPS coordenadasGPS;
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

    public CoordenadasGPS getCoordenadasGPS() {
        return coordenadasGPS;
    }

    public void setCoordenadasGPS(CoordenadasGPS coordenadasGPS) {
        this.coordenadasGPS = coordenadasGPS;
    }
}
