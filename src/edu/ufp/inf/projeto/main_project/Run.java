package edu.ufp.inf.projeto.main_project;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.projeto.models.*;
import edu.ufp.inf.projeto.models.utilizadores.AdminParticipante;
import edu.ufp.inf.projeto.models.utilizadores.Participante;
import edu.ufp.inf.projeto.models.utilizadores.PremiumParticipante;

import java.util.ArrayList;

public class Run
{

    private static ArrayList<PontoInteresse> pontosInteresse = new ArrayList<>();
    private static ArrayList<Objeto> objetos = new ArrayList<>();
    private static RedBlackBST<String, GeoCache> geoCaches = new RedBlackBST<>();
    private static RedBlackBST<String, Participante> participantes = new RedBlackBST<>();

    /**
     * Main function
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        System.out.println("\t\tMAIN");

        String path = "...//ficheiros//InputSemGrafos.txt";
        loadFromFile("/Users/jorgesilva/Desktop/FAC/2_SEMESTRE/LP2_AED 2/code/src/edu/ufp/inf/projeto/ficheiros/InputSemGrafos.txt");
        System.out.println("\tGEO CACHES");
        listGeoCache();
        System.out.println("\tPARTICIPANTES");
        listParticipante();
        System.out.println("\tOBJETOS");
        listObjetos();
        System.out.println("\tPONTOS DE INTERESSE");
        listPontosInteresse();






    }

    /**
     * funcao que faz lê do ficheiro input toda a informação dele
     * @param path
     */
    public static void loadFromFile(String path)
    {
        In in = new In(path);
        String nParticipantes = in.readLine();
        for(int i = 0; i < Integer.parseInt(nParticipantes); i++)
        {
            String[] temParticipante = in.readLine().split(",");
            int id = Integer.parseInt(temParticipante[0]);
            String nome = temParticipante[1].trim();
            String tipo = temParticipante[2];
            switch (tipo.trim())
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
            int numeroGeoCaches = Integer.parseInt(temRegioes[1].trim());
            for (int j = 0; j < numeroGeoCaches; j++)
            {
                String[] geoCachess = in.readLine().split(",");
                String nomeG = geoCachess[0];
                String tipoG = geoCachess[1];
                String x = geoCachess[2];
                double coordenadaX = Double.parseDouble(x);
                String y = geoCachess[3];
                double coordenadaY = Double.parseDouble(y);
                int numObjetos = Integer.parseInt(geoCachess[4].trim());
                for (int k=0; k <numObjetos; k++)
                {
                    String objeto = geoCachess[k+5];
                    Objeto o = new Objeto(objeto);
                    objetos.add(o);
                }
                PontoInteresse pontoInteresse = new PontoInteresse(coordenadaX, coordenadaY, nomeRegiao, nomeG);
                switch (tipoG.trim())
                {
                    case "basic":
                        GeoCache geoCache = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.BASIC);
                        pontoInteresse.setGeoCache(geoCache);
                        pontosInteresse.add(pontoInteresse);
                        geoCaches.put(pontoInteresse.getNome(), geoCache);

                        break;
                    case "premium":
                        GeoCache geoCachePremium = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.PREMIUM);
                        pontoInteresse.setGeoCache(geoCachePremium);
                        pontosInteresse.add(pontoInteresse);
                        geoCaches.put(geoCachePremium.getPontoInteresse().getNome(), geoCachePremium);
                        break;
                }

            }
        }
        String numeroTravelBugs = in.readLine();
        for (int y = 0; y < Integer.parseInt(numeroTravelBugs); y++)
        {
            String[] travelBugs = in.readLine().split(",");
            String nomeTravel = travelBugs[0];
            String nomeParticipante = travelBugs[1].trim();
            String nomeGeoInicio = travelBugs[2].trim();
            String nomeGeoFim = travelBugs[3].trim();
            for (String p : participantes.keys())
            {
                Participante ppp = participantes.get(p);
                if (ppp.getNome().compareTo(nomeParticipante)==0)
                {
                    for (String inicio : geoCaches.keys())
                    {
                        GeoCache gcc = geoCaches.get(inicio);
                        if (gcc.getPontoInteresse().getNome().compareTo(nomeGeoInicio)==0)
                        {
                            for (String fim : geoCaches.keys())
                            {
                                GeoCache gccs = geoCaches.get(fim);
                                if (gcc.getPontoInteresse().getNome().compareTo(nomeGeoFim)==0)
                                {
                                    TravelBug travelBug = new TravelBug(nomeTravel, gcc, gccs);
                                    travelBug.setParticipante(ppp);
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
        participante.visitouGeo(geoCache,objetosInseridos, objetosRetirados);
        for (Objeto o : objetosInseridos)
        {
            if (o instanceof TravelBug)
            {
                ((TravelBug)o).update(geoCache, participante, true);
            }
        }
    }


    public void participantesMaisAtivos (RedBlackBST<String, Participante> participantes)
    {

        int numero = 0;
        for (String part : participantes.keys())
        {
            Participante p = participantes.get(part);
            int num = p.getVisitadas().size();
            if (num >= numero)
                numero = num;

        }
    }



    public static void listGeoCache()
    {
        for(String geoCache : geoCaches.keys())
        {
            GeoCache gc = geoCaches.get(geoCache);
            System.out.println(gc.getPontoInteresse().getRegiao()+ " " +gc.getPontoInteresse().getNome() + " " + gc.getTipoGeoCache());
            System.out.println(gc.getPontoInteresse().getX()+ " " +gc.getPontoInteresse().getY());
            System.out.println("----------------------");
        }
    }

    public static void listParticipante()
    {
        for(String pi : participantes.keys())
        {
            Participante participante = participantes.get(pi);
            System.out.println(participante.getId()+ " " +participante.getNome());
            System.out.println("----------------");
        }
    }

    public static void listObjetos()
    {
        for(Objeto oi : objetos) {
            System.out.println(oi.getNome());
            System.out.println("----------------");
        }
    }

    public static void listPontosInteresse()
    {
        for (PontoInteresse pontoInteresse : pontosInteresse)
        {
            System.out.println(pontoInteresse.getNome()+" "+pontoInteresse.getRegiao());
            System.out.println(pontoInteresse.getX()+" "+pontoInteresse.getY());
            System.out.println("----------------");
        }
    }











}
