package edu.ufp.inf.projeto.main_project;


import edu.princeton.cs.algs4.In;
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

    /**
     * funcao que faz lê do ficheiro input toda a informação dele
     * @param path
     */
    public static void loadFromFile(String path){
        In in = new In(path);
        String nParticipantes = in.readLine();

        for(int i = 0; i < Integer.parseInt(nParticipantes); i++){
            //manuel, luis, premium
            String[] temParticipante = in.readLine().split(",");
            int id = Integer.parseInt(temParticipante[0]);
            String nome = temParticipante[1];
            String tipo = temParticipante[2];
            switch (tipo){
                case "premium":
                    //PremiumParticipante pp = new PremiumParticipante(id,nome);
                    break;
                case "basic":
                    break;
                case "admin":
                    break;
            }
        }
        String nRegioes = in.readLine();
        for(int i = 0; i < Integer.parseInt(nRegioes); i++){
            String[] temRegioes = in.readLine().split(",");
            String nomeRegiao =temRegioes[0];
            String numeroGeoCaches = temRegioes[1];
            for (int j = 0; j < Integer.parseInt(numeroGeoCaches); j++){
                String[] geoCaches = in.readLine().split(",");
                String nomeG = geoCaches[0];
                String tipoG = geoCaches[1];
                String x = geoCaches[2];
                String y = geoCaches[3];
                String numObjetos = geoCaches[4];
                ArrayList<Objeto> objetos = new ArrayList<Objeto>();
                for (int k=5; k < Integer.parseInt(numObjetos);k++){
                    String objeto = geoCaches[k];

                }
                //GeoCache geoCache = new GeoCache(j,);

            }
        }

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
