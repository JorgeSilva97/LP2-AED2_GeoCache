package edu.ufp.inf.projeto.main_project;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.*;
import edu.ufp.inf.projeto.models.utilizadores.AdminParticipante;
import edu.ufp.inf.projeto.models.utilizadores.Participante;
import edu.ufp.inf.projeto.models.utilizadores.PremiumParticipante;

import java.util.ArrayList;

public class Run
{

    /**
     * Main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("\t\tMAIN");

        ArrayList<PontoInteresse> pontosInteresse = new ArrayList<>();
        ArrayList<Objeto> objetos = new ArrayList<>();
        RedBlackBST<String, GeoCache> geoCaches = new RedBlackBST<>();
        RedBlackBST<String, Participante> participantes = new RedBlackBST<>();

        loadFromFile("/Users/jorgesilva/Desktop/FAC/2_SEMESTRE/LP2_AED 2/code/src/edu/ufp/inf/projeto/ficheiros/InputSemGrafos.txt",
                participantes, geoCaches, pontosInteresse, objetos);

        for (Objeto o : objetos)
            System.out.println(o.getNome());



    }

    /**
     * funcao que faz lê do ficheiro input toda a informação dele
     * @param path
     */
    public static void loadFromFile(String path, RedBlackBST<String, Participante> participantes,
                                    RedBlackBST<String, GeoCache> geoCachess, ArrayList<PontoInteresse> pontosInteresse,
                                    ArrayList<Objeto> objetos)
    {
        In in = new In(path);
        String nParticipantes = in.readLine();
        for(int i = 0; i < Integer.parseInt(nParticipantes); i++)
        {
            String[] temParticipante = in.readLine().split(",");
            int id = Integer.parseInt(temParticipante[0]);
            String nome = temParticipante[1];
            String tipo = temParticipante[2];
            switch (tipo)
            {
                case "premium":
                    PremiumParticipante premiumParticipante = new PremiumParticipante(id, nome);
                    participantes.put(premiumParticipante.getNome(), premiumParticipante);
                    break;
                case "basic":
                    Participante participante = new Participante(id, nome);
                    participantes.put(participante.getNome(), participante);
                    break;
                case "admin":
                    AdminParticipante adminParticipante = new AdminParticipante(id, nome);
                    participantes.put(adminParticipante.getNome(), adminParticipante);
                    break;
            }
        }
        String nRegioes = in.readLine();
        for(int z = 0; z < Integer.parseInt(nRegioes); z++)
        {
            String[] temRegioes = in.readLine().split(",");
            String nomeRegiao =temRegioes[0];
            int numeroGeoCaches = Integer.parseInt(temRegioes[1]);
            for (int j = 0; j < numeroGeoCaches; j++)
            {
                String[] geoCaches = in.readLine().split(",");
                String nomeG = geoCaches[0];
                String tipoG = geoCaches[1];
                String x = geoCaches[2];
                double coordenadaX = Double.parseDouble(x);
                String y = geoCaches[3];
                double coordenadaY = Double.parseDouble(y);
                String numObjetos = geoCaches[4];
                ArrayList<Objeto> obj = new ArrayList<>();
                for (int k=0; k < Integer.parseInt(numObjetos);k++)
                {
                    String objeto = geoCaches[k];
                    Objeto o = new Objeto(objeto);
                    obj.add(o);
                }
                PontoInteresse pontoInteresse = new PontoInteresse(coordenadaX, coordenadaY, nomeRegiao, nomeG);
                switch (tipoG)
                {
                    case "basic":
                        GeoCache geoCache = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.BASIC);
                        pontoInteresse.setGeoCache(geoCache);
                        pontosInteresse.add(pontoInteresse);
                        geoCachess.put(geoCache.getPontoInteresse().getNome(), geoCache);
                        break;
                    case "premium":
                        GeoCache geoCachePremium = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.PREMIUM);
                        pontoInteresse.setGeoCache(geoCachePremium);
                        pontosInteresse.add(pontoInteresse);
                        geoCachess.put(geoCachePremium.getPontoInteresse().getNome(), geoCachePremium);
                        break;
                }

            }
        }
        String numeroTravelBugs = in.readLine();
        for (int y = 0; y < Integer.parseInt(numeroTravelBugs); y++)
        {
            String[] travelBugs = in.readLine().split(",");
            String nomeTravel = travelBugs[0];
            String nomeParticipante = travelBugs[1];
            String nomeGeoInicio = travelBugs[2];
            String nomeGeoFim = travelBugs[3];
            for (String p : participantes.keys())
            {
                Participante ppp = participantes.get(p);
                if (ppp.getNome().compareTo(nomeParticipante)==0)
                {
                    for (String inicio : geoCachess.keys())
                    {
                        GeoCache gcc = geoCachess.get(inicio);
                        if (gcc.getPontoInteresse().getNome().compareTo(nomeGeoInicio)==0)
                        {
                            for (String fim : geoCachess.keys())
                            {
                                GeoCache gccs = geoCachess.get(fim);
                                if (gcc.getPontoInteresse().getNome().compareTo(nomeGeoFim)==0)
                                {
                                    TravelBug travelBug = new TravelBug(nomeTravel, gcc, gccs);
                                    objetos.add(travelBug);
                                }
                            }
                        }
                    }
                }
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
