package edu.ufp.inf.projeto.models;

import edu.ufp.inf.projeto.models.utilizadores.Participante;

public class CoordenadasGPS {

    private double x;
    private double y;
    private String regiao;
    private PontoInteresse pontoInteresse;

    public CoordenadasGPS(double x, double y, PontoInteresse pontoInteresse) {
        this.x = x;
        this.y = y;
        this.pontoInteresse = pontoInteresse;
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

    public PontoInteresse getPontoInteresse() {
        return pontoInteresse;
    }

    public void setPontoInteresse(PontoInteresse pontoInteresse) {
        this.pontoInteresse = pontoInteresse;
    }

}
