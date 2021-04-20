package edu.ufp.inf.aed2.project;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SymbolDigraph;

import java.io.Serializable;
import java.util.ArrayList;

public class Participante implements Serializable
{

    private String id;
    private String nome;
    private String mail;
    private ArrayList<CoordenadasGPS> coordenadas;

}
