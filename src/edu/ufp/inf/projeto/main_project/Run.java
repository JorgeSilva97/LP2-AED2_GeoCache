package edu.ufp.inf.projeto.main_project;


import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.projeto.models.GeoCache;
import edu.ufp.inf.projeto.models.Objeto;
import edu.ufp.inf.projeto.models.TravelBug;
import edu.ufp.inf.projeto.models.utilizadores.Participante;

import java.util.ArrayList;

public class Run
{

    /**
     * Main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("\t\tMAIN");

        RedBlackBST<String, GeoCache> geoCaches = new RedBlackBST<>();
        RedBlackBST<String, Participante> participantes = new RedBlackBST<>();







    }

    public void visitaGeoCache (GeoCache geoCache, Participante participante,
                                   ArrayList<Objeto> objetosInseridos, ArrayList<Objeto> objetosRetirados)
    {
        geoCache.visitado(participante, objetosInseridos, objetosRetirados);
        participante.visitouGeo(geoCache,objetosInseridos, objetosRetirados); //criar array objetos
        for (Objeto o : objetosInseridos)
        {
            if (o instanceof TravelBug)
            {
                ((TravelBug)o).update(geoCache, participante, true);
            }
        }
    }











}
