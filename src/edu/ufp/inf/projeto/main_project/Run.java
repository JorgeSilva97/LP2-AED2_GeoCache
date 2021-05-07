package edu.ufp.inf.projeto.main_project;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.projeto.models.*;
import edu.ufp.inf.projeto.models.utilizadores.AdminParticipante;
import edu.ufp.inf.projeto.models.utilizadores.Participante;
import edu.ufp.inf.projeto.models.utilizadores.PremiumParticipante;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        String path_cunha_criar = "/Users/franciscocunha/IdeaProjects/LP2_AED2/src/edu/ufp/inf/projeto/ficheiros/Output.txt";
        String path_jorge = "/Users/jorgesilva/Desktop/FAC/2_SEMESTRE/LP2_AED 2/code/src/edu/ufp/inf/projeto/ficheiros/InputSemGrafos.txt";
        String path_jorge_criar = "/Users/jorgesilva/Desktop/FAC/2_SEMESTRE/LP2_AED 2/code/src/edu/ufp/inf/projeto/ficheiros/Output.txt";
        loadFromFile(path_jorge);
        System.out.println("\tGEO CACHES");
        listGeoCache();
        System.out.println("\tPARTICIPANTES");
        listParticipante();
        System.out.println("\tOBJETOS");
        listObjetos();
        System.out.println("\tPONTOS DE INTERESSE");
        listPontosInteresse();
        //createFile(path_jorge_criar);
        writeToFile(path_jorge_criar);





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
                PontoInteresse pontoInteresse = new PontoInteresse(coordenadaX, coordenadaY, nomeRegiao, nomeG);
                switch (tipoG.trim())
                {
                    case "basic":
                        GeoCache geoCache = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.BASIC);
                        pontoInteresse.setGeoCache(geoCache);
                        pontosInteresse.add(pontoInteresse);
                        geoCaches.put(pontoInteresse.getNome(), geoCache);
                        for (int k=0; k <numObjetos; k++)
                        {
                            String objeto = geoCachess[k+5];
                            Objeto o = new Objeto(objeto);
                            geoCache.getObjetos().add(o);
                            objetos.add(o);
                        }
                        break;
                    case "premium":
                        GeoCache geoCachePremium = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.PREMIUM);
                        pontoInteresse.setGeoCache(geoCachePremium);
                        pontosInteresse.add(pontoInteresse);
                        geoCaches.put(geoCachePremium.getPontoInteresse().getNome(), geoCachePremium);
                        for (int k=0; k <numObjetos; k++)
                        {
                            String objeto = geoCachess[k+5];
                            Objeto o = new Objeto(objeto);
                            geoCachePremium.getObjetos().add(o);
                            objetos.add(o);
                        }
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
                    for (PontoInteresse pontoInteresse1 : pontosInteresse)
                    {
                        if(pontoInteresse1.getNome().compareTo(nomeGeoInicio)==0)
                        {
                            for (PontoInteresse pontoInteresse2: pontosInteresse)
                            {
                                if (pontoInteresse2.getNome().compareTo(nomeGeoFim)==0)
                                {
                                    TravelBug travelBug = new TravelBug(nomeTravel, pontoInteresse1.getGeoCache(), pontoInteresse2.getGeoCache());
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
    public static void createFile(String path){
        try{
            File myObj = new File(path);
            if(myObj.createNewFile()){
                System.out.println("File Created: " + myObj.getName());
            }else {
                System.out.println("File already exists.");
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String path){
        try{
            FileWriter myWriter = new FileWriter(path);
            int numParticipante = participantes.size();
            myWriter.write(String.valueOf(Long.valueOf(numParticipante)));
            myWriter.write("\n");
            for(String p_aux : participantes.keys()){
                Participante participante = participantes.get(p_aux);
                myWriter.write(String.valueOf(Long.valueOf(participante.getId())));
                myWriter.write(", ");
                myWriter.write(participante.getNome());
                myWriter.write(", ");
                if(participante instanceof PremiumParticipante){
                    if(participante instanceof AdminParticipante)
                        myWriter.write("admin");
                    else
                        myWriter.write("premium");

                }
                else
                    myWriter.write("basic");

                myWriter.write("\n");
            }

            myWriter.write(String.valueOf(Integer.valueOf(3)));
            myWriter.write("\n");
            ArrayList<PontoInteresse> norte = new ArrayList<>();
            ArrayList<PontoInteresse> centro = new ArrayList<>();
            ArrayList<PontoInteresse> sul = new ArrayList<>();

            for (PontoInteresse pi_aux : pontosInteresse){
                if(pi_aux.getRegiao().compareTo("norte") == 0){
                    norte.add(pi_aux);
                }else if(pi_aux.getRegiao().compareTo("centro") == 0){
                    centro.add(pi_aux);
                }else if(pi_aux.getRegiao().compareTo("sul") == 0){
                    sul.add(pi_aux);
                }
            }
            myWriter.write("norte, ");
            myWriter.write(String.valueOf(Integer.valueOf(norte.size())));
            myWriter.write("\n");
            for(PontoInteresse ponto_aux : norte){
                myWriter.write(ponto_aux.getNome()+", "+ponto_aux.getGeoCache().getTipoGeoCache()+", "
                        +ponto_aux.getX()+", "+ponto_aux.getY()+", ");
                int tamanhoObjetos = ponto_aux.getGeoCache().getObjetos().size();
                myWriter.write(String.valueOf(Integer.valueOf(tamanhoObjetos)));
                for (Objeto o : ponto_aux.getGeoCache().getObjetos())
                    myWriter.write( ", "+String.valueOf(o.getNome()));
                myWriter.write("\n");
            }
            myWriter.write("centro, ");
            myWriter.write(String.valueOf(Integer.valueOf(centro.size())));
            myWriter.write("\n");
            for(PontoInteresse ponto_aux1 : centro){
                myWriter.write(ponto_aux1.getNome()+", "+ponto_aux1.getGeoCache().getTipoGeoCache()+", "
                        +ponto_aux1.getX()+", "+ponto_aux1.getY()+", ");
                int tamanhoObjetos = ponto_aux1.getGeoCache().getObjetos().size();
                myWriter.write(String.valueOf(Integer.valueOf(tamanhoObjetos)));
                for (Objeto o : ponto_aux1.getGeoCache().getObjetos())
                    myWriter.write( ", "+String.valueOf(o.getNome()));
                myWriter.write("\n");
            }
            myWriter.write("sul, ");
            myWriter.write(String.valueOf(Integer.valueOf(sul.size())));
            myWriter.write("\n");
            for(PontoInteresse ponto_aux2 : sul){
                myWriter.write(ponto_aux2.getNome()+", "+ponto_aux2.getGeoCache().getTipoGeoCache()+", "
                        +ponto_aux2.getX()+", "+ponto_aux2.getY()+", ");
                int tamanhoObjetos = ponto_aux2.getGeoCache().getObjetos().size();
                myWriter.write(String.valueOf(Integer.valueOf(tamanhoObjetos)));
                for (Objeto o : ponto_aux2.getGeoCache().getObjetos())
                    myWriter.write( ", "+String.valueOf(o.getNome()));
                myWriter.write("\n");
            }
            myWriter.close();
        }catch (IOException e){
            System.out.println("Ocorreu um erro ao abrir o ficheiro");
            e.printStackTrace();
        }

    }
    public static void visitaGeoCache (GeoCache geoCache, Participante participante,
                                   ArrayList<Objeto> objetosInseridos, ArrayList<Objeto> objetosRetirados)
    {
        geoCache.visitado(participante, objetosInseridos, objetosRetirados);
        participante.visitouGeo(geoCache,objetosInseridos, objetosRetirados);
        for (Objeto o : objetosInseridos)
        {
            if (o instanceof TravelBug)
                ((TravelBug)o).update(geoCache, participante, true);
        }
    }


    public ArrayList<Participante> participantesMaisAtivos()
    {
        ArrayList<Participante> participantes1 = new ArrayList<>();
        int[] maisVisitados = {0,0,0,0,0};
        Long[] idMaisVisitados = {Long.valueOf(0),Long.valueOf(0),Long.valueOf(0),Long.valueOf(0),Long.valueOf(0)};
        for (String part : participantes.keys())
        {
            Participante p = participantes.get(part);
            int num = p.getVisitadas().size();
            for (int i : maisVisitados)
            {
                if (num > maisVisitados[i])
                {
                    maisVisitados[i] = num;
                    idMaisVisitados[i] = Long.valueOf(p.getId());
                }
            }
        }
        for (String part : participantes.keys())
        {
            Participante parti = participantes.get(part);
            for (Long id: idMaisVisitados)
            {
                if (parti.getId() == idMaisVisitados[Math.toIntExact(id)])
                    participantes1.add(parti);
            }
        }
        return participantes1;
    }

    public ArrayList<GeoCache> GeoCachesVisitadas(Participante p)
    {
        return p.getVisitadas();
    }

    public ArrayList<GeoCache> CachesPremiumComMaisUmObjeto()
    {
        ArrayList<GeoCache> geos = new ArrayList<>();
        for (String geo : geoCaches.keys())
        {
            GeoCache geoCache = geoCaches.get(geo);
            if (geoCache.getTipoGeoCache().equals(TipoGeoCacheEnum.PREMIUM))
            {
                int num = geoCache.getObjetos().size();
                if (num > 1)
                    geos.add(geoCache);
            }
        }
        return geos;
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
