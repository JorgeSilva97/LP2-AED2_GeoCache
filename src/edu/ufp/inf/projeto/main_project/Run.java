package edu.ufp.inf.projeto.main_project;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.projeto.models.*;
import edu.ufp.inf.projeto.models.enumerados.CustoEnum;
import edu.ufp.inf.projeto.models.enumerados.TipoGeoCacheEnum;
import edu.ufp.inf.projeto.models.grafos.Conexao;
import edu.ufp.inf.projeto.models.grafos.GestorPontoInteresse;
import edu.ufp.inf.projeto.models.grafos.SubGrafo;
import edu.ufp.inf.projeto.models.utilizadores.AdminParticipante;
import edu.ufp.inf.projeto.models.utilizadores.Participante;
import edu.ufp.inf.projeto.models.utilizadores.PremiumParticipante;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Run {

    /**
     * Array de todos os PontosInteresse que existem
     * Array de todos os Objetos que existem
     * ReadBlack de todos as GeoCache que exitem (ordenados alfabeticamente pelo nome do PontoInteresse)
     * ReadBlack de todos os Participantes que exitem (ordenados alfabeticamente pelo seu nome)
     */
    public static ArrayList<PontoInteresse> pontosInteresse = new ArrayList<>();
    public static ArrayList<Objeto> objetos = new ArrayList<>();
    public static RedBlackBST<String, GeoCache> geoCaches = new RedBlackBST<>();
    public static RedBlackBST<String, Participante> participantes = new RedBlackBST<>();
    public static GestorPontoInteresse gestorPontoInteresse = new GestorPontoInteresse(pontosInteresse);
    public static String PATH_GEOCACHES_BIN = "src/edu/ufp/inf/projeto/ficheiros/data_geocaches.bin";
    public static String PATH_PONNTOINTERESSE_BIN = "src/edu/ufp/inf/projeto/ficheiros/data_pontosInteresse.bin";
    public static String PATH_OBJETOS_BIN = "src/edu/ufp/inf/projeto/ficheiros/data_objetos.bin";
    public static String PATH_PARTICIPANTES_BIN = "src/edu/ufp/inf/projeto/ficheiros/data_participantes.bin";


    /**
     * Main function
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("\t\tMAIN");

        String path = "src/edu/ufp/inf/projeto/ficheiros/InputComGrafos.txt";


        //saveBinGeocaches(geoCaches, PATH_GEOCACHES_BIN);
        //readBinGeoCaches(geoCaches, PATH_GEOCACHES_BIN);
        //saveBinPontoInteresse(pontosInteresse,PATH_PONNTOINTERESSE_BIN);
        //readPontoInteresse(pontosInteresse,PATH_PONNTOINTERESSE_BIN);
        //saveBinObjetos(objetos,PATH_OBJETOS_BIN);
        //readBinObjetos(objetos,PATH_OBJETOS_BIN);
        saveBinParticipantes(participantes, PATH_PARTICIPANTES_BIN);
        readBinParticipantes(participantes, PATH_PARTICIPANTES_BIN);
        //listGeoCache();
        /*loadFromFile(path);
        System.out.println("\tGEO CACHES");
        listGeoCache();
        System.out.println("\tPARTICIPANTES");
        listParticipante();
        System.out.println("\tOBJETOS");
        listObjetos();
        System.out.println("\tPONTOS DE INTERESSE");
        listPontosInteresse();*/
        //createFile(path_jorge_criar);
        //writeToFile(path_jorge);


        PontoInteresse pi1 = new PontoInteresse(1, 3, 4, "porto");
        GeoCache geoCache1 = new GeoCache(pi1, TipoGeoCacheEnum.BASIC);
        PontoInteresse pi2 = new PontoInteresse(2, 4, 5, "portogarage");
        GeoCache geoCache2 = new GeoCache(pi2, TipoGeoCacheEnum.BASIC);
        PontoInteresse pi3 = new PontoInteresse(3, 5, 6, "portoalegre");
        GeoCache geoCache3 = new GeoCache(pi3, TipoGeoCacheEnum.PREMIUM);
        PontoInteresse pi4 = new PontoInteresse(4, 6, 7, "portoseguro");
        GeoCache geoCache4 = new GeoCache(pi4, TipoGeoCacheEnum.PREMIUM);

        pi1.setGeoCache(geoCache1);
        pi2.setGeoCache(geoCache2);
        pi3.setGeoCache(geoCache3);
        pi4.setGeoCache(geoCache4);

        gestorPontoInteresse.addPontoInteresse(geoCache1.getPontoInteresse());
        gestorPontoInteresse.addPontoInteresse(geoCache2.getPontoInteresse());
        gestorPontoInteresse.addPontoInteresse(geoCache3.getPontoInteresse());
        gestorPontoInteresse.addPontoInteresse(geoCache4.getPontoInteresse());

        gestorPontoInteresse.criaGrafoGlobal();

        gestorPontoInteresse.createEdge(geoCache1.getPontoInteresse().getVertexId(),
                geoCache2.getPontoInteresse().getVertexId(),
                gestorPontoInteresse.calculaPeso(geoCache1.getPontoInteresse(), geoCache2.getPontoInteresse()), 30);
        gestorPontoInteresse.createEdge(geoCache2.getPontoInteresse().getVertexId(),
                geoCache1.getPontoInteresse().getVertexId(),
                gestorPontoInteresse.calculaPeso(geoCache2.getPontoInteresse(), geoCache1.getPontoInteresse()), 20);
        gestorPontoInteresse.createEdge(geoCache3.getPontoInteresse().getVertexId(),
                geoCache4.getPontoInteresse().getVertexId(),
                gestorPontoInteresse.calculaPeso(geoCache3.getPontoInteresse(), geoCache4.getPontoInteresse()), 20);
        gestorPontoInteresse.createEdge(geoCache4.getPontoInteresse().getVertexId(),
                geoCache3.getPontoInteresse().getVertexId(),
                gestorPontoInteresse.calculaPeso(geoCache4.getPontoInteresse(), geoCache3.getPontoInteresse()), 20);


        EdgeWeightedDigraph graph = gestorPontoInteresse.getGrafoGlobal();
        System.out.println(graph.toString());
        //  System.out.println("--->" + gestorPontoInteresse.getSubGrafoBasicCaches(pontosInteresse));
        gestorPontoInteresse.getSubGrafoBasicCaches(pontosInteresse);
        gestorPontoInteresse.caminhoMaisCurtoEntre(0, 1, graph, 0, CustoEnum.DISTANCIA);


    }

    /**
     * funcao que procura o nome do pontoInteresse
     * @param nome, nome a procurar
     * @return o ponto de interesse ou null se nao encontrar
     */
    public static PontoInteresse pontoInteresseByNome(String nome){
        for (PontoInteresse p_aux : pontosInteresse){
            if(p_aux.getNome().compareTo(nome) == 0){
                return p_aux;
            }
        }
        return null;
    }

    /**
     * funcao que procura o nome do participante
     * @param nome, nome a procurar
     * @return o participante ou null se nao encontrar
     */
    public static PremiumParticipante participanteByNome(String nome){
        for (String p_aux : participantes.keys()){
            Participante p = participantes.get(p_aux);
            if(p instanceof PremiumParticipante){
                if(p.getNome().compareTo(nome) == 0){
                    return (PremiumParticipante) p;
                }
            }
        }
        return null;
    }

    /**
     * funcao que procura o tipo do tipoGeoCacheByTipo
     * @param tipo, tipo de geocache a procurar
     * @return o tipo ou null se nao encontrar
     */
    public static TipoGeoCacheEnum tipoGeoCacheByTipo(String tipo){
        for(String gc : geoCaches.keys()){
            GeoCache gc1 = geoCaches.get(gc);
            if(gc1.getTipoGeoCache() == TipoGeoCacheEnum.BASIC){
                return TipoGeoCacheEnum.BASIC;
            }else{
                return TipoGeoCacheEnum.PREMIUM;
            }
        }
        return null;
    }

    /**
     * funcao que procura na lista de objetos um objeto
     * @param nome, nome a procurar
     * @return a lista de objetos
     */
    public static ArrayList<Objeto> getObjetos(String nome){
        String[] nomoObj = nome.split(",");
        ArrayList<Objeto> array_obj = new ArrayList<>();
        for(int i = 0; i < Arrays.stream(nomoObj).count(); i++)
        {
            String nome2 = nomoObj[i].trim();
            Objeto o = new Objeto(nome2);
            array_obj.add(o);


        }
        return array_obj;
    }

    /**
     * funcao que adiciona uma geocache a st para ser usada na interface
     * @param geoCache geocache a ser adicionada
     */
    public static void addGeoCache(GeoCache geoCache){
        for(String gc : geoCaches.keys()){
            if (geoCaches.get(gc).getId() == geoCache.getId()){
                return;
            }
        }
        geoCaches.put(geoCache.getPontoInteresse().getNome(),geoCache);
    }

    /**
     * funcao que faz le do ficheiro input toda a informacao dele e guarda nas respetivas ST e Arrays
     *
     * @param path
     */
    public static void loadFromFile(String path) {
        In in = new In(path);
        String nParticipantes = in.readLine();
        for (int i = 0; i < Integer.parseInt(nParticipantes); i++) {
            String[] temParticipante = in.readLine().split(",");
            int id = Integer.parseInt(temParticipante[0]);
            String nome = temParticipante[1].trim();
            String tipo = temParticipante[2];
            switch (tipo.trim()) {
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
        for (int z = 0; z < Integer.parseInt(nRegioes); z++) {
            String[] temRegioes = in.readLine().split(",");
            String nomeRegiao = temRegioes[0];
            int numeroGeoCaches = Integer.parseInt(temRegioes[1].trim());
            for (int j = 0; j < numeroGeoCaches; j++) {
                String[] geoCachess = in.readLine().split(",");
                String nomeG = geoCachess[0];
                String tipoG = geoCachess[1];
                String x = geoCachess[2];
                double coordenadaX = Double.parseDouble(x);
                String y = geoCachess[3];
                double coordenadaY = Double.parseDouble(y);
                int numObjetos = Integer.parseInt(geoCachess[4].trim());
                PontoInteresse pontoInteresse = new PontoInteresse(coordenadaX, coordenadaY, nomeRegiao, nomeG);
                pontoInteresse.setVertexId(j);
                switch (tipoG.trim()) {
                    case "basic":
                        GeoCache geoCache = new GeoCache(j, pontoInteresse, TipoGeoCacheEnum.BASIC);
                        pontoInteresse.setGeoCache(geoCache);
                        pontosInteresse.add(pontoInteresse);
                        geoCaches.put(pontoInteresse.getNome(), geoCache);
                        for (int k = 0; k < numObjetos; k++) {
                            String objeto = geoCachess[k + 5].trim();
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
                        for (int k = 0; k < numObjetos; k++) {
                            String objeto = geoCachess[k + 5].trim();
                            Objeto o = new Objeto(objeto);
                            geoCachePremium.getObjetos().add(o);
                            objetos.add(o);
                        }
                        break;
                }
            }
        }
        String numeroTravelBugs = in.readLine();
        for (int y = 0; y < Integer.parseInt(numeroTravelBugs); y++) {
            String[] travelBugs = in.readLine().split(",");
            String nomeTravel = travelBugs[0];
            String nomeParticipante = travelBugs[1].trim();
            String nomeGeoInicio = travelBugs[2].trim();
            String nomeGeoFim = travelBugs[3].trim();
            for (String p : participantes.keys()) {
                Participante ppp = participantes.get(p);
                if (ppp.getNome().compareTo(nomeParticipante) == 0) {
                    for (PontoInteresse pontoInteresse1 : pontosInteresse) {
                        if (pontoInteresse1.getNome().compareTo(nomeGeoInicio) == 0) {
                            for (PontoInteresse pontoInteresse2 : pontosInteresse) {
                                if (pontoInteresse2.getNome().compareTo(nomeGeoFim) == 0) {
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
        String numeroLigacoes = in.readLine();
        for (int x = 0; x < Integer.parseInt(numeroLigacoes); x++) {
            String[] ligacoes = in.readLine().split(",");
            String ligacaoUm = ligacoes[0];
            String ligacaoDois = ligacoes[1].trim();
            Double distancia = Double.parseDouble(ligacoes[2].trim());
            Double tempo = Double.parseDouble(ligacoes[3].trim());
            ArrayList<PontoInteresse> pontoInteresses = new ArrayList<>();
            GestorPontoInteresse gestorPontoInteresse = new GestorPontoInteresse(pontosInteresse);
            gestorPontoInteresse.criaGrafoGlobal();
            //EdgeWeightedDigraph grafo = gestorPontoInteresse.getGrafoGlobal();
            for (String gcUm : geoCaches.keys()) {
                GeoCache geoUm = geoCaches.get(gcUm);
                if (geoUm.getPontoInteresse().getNome().compareTo(ligacaoUm) == 0) {
                    for (String gcDois : geoCaches.keys()) {
                        GeoCache geoDois = geoCaches.get(gcDois);
                        if (geoDois.getPontoInteresse().getNome().compareTo(ligacaoDois) == 0) {
                            gestorPontoInteresse.createEdge(geoUm.getPontoInteresse().getVertexId(),
                                    geoDois.getPontoInteresse().getVertexId(), distancia, tempo);
                            gestorPontoInteresse.createEdge(geoDois.getPontoInteresse().getVertexId(),
                                    geoUm.getPontoInteresse().getVertexId(), distancia, tempo);
                        }
                    }
                }
            }
        }
    }

    /**
     * funcao que guarda num ficheiro binaio toda a informacao das GeoCaches
     * @param geoCachesST, st da geocache a guardar
     * @param path, caminho para o ficheiro
     */
    public static void saveBinGeocaches(RedBlackBST<String, GeoCache> geoCachesST, String path) {
        File f = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(geoCachesST.size());
            for (String nome : geoCachesST.keys()) {
                GeoCache geoCache = geoCachesST.get(nome);
                oos.writeObject(geoCache);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que guarda num ficheiro binaio toda a informacao dos Pontos de Interesse
     * @param pontosInteresse, st do ponto de interesse a guardar
     * @param path, caminho para o ficheiro
     */
    public static void saveBinPontoInteresse(ArrayList<PontoInteresse> pontosInteresse, String path) {
        File f = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(pontosInteresse.size());
            for (PontoInteresse pp : pontosInteresse) {
                oos.writeObject(pp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que guarda num ficheiro binaio toda a informacao dos Objetos
     * @param Objetos, lista de objetos a guardar
     * @param path, caminho para o ficheiro
     */
    public static void saveBinObjetos(ArrayList<Objeto> Objetos, String path) {
        File f = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(objetos.size());
            for (Objeto objeto : objetos) {
                oos.writeObject(objeto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que guarda num ficheiro binaio toda a informacao dos participantes
     * @param participantesST, st dos participantes a guardar
     * @param path, caminho para o ficheiro
     */
    public static void saveBinParticipantes(RedBlackBST<String, Participante> participantesST, String path) {
        File f = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(participantesST.size());
            for (String nome : participantesST.keys()) {
                Participante participante = participantesST.get(nome);
                oos.writeObject(participante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * funcao que le de um ficheiro binario toda a informacao que esta na st de particpantes
     * @param participantesST, st a ler
     * @param path, caminho para o ficheiro
     */
    public static void readBinParticipantes(RedBlackBST<String, Participante> participantesST, String path) {
        File f = new File(path);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer size = (Integer) ois.readObject();

            System.out.println(size);
            for (int i = 0; i < size; i++) {
                Participante participante = (Participante) ois.readObject();
                System.out.println("participante---" + participante);
                participantesST.put(participante.getNome(), participante);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * funcao que le de um ficheiro binario toda a informacao que esta na st de geoCaches
     * @param geoCachesST, st a ler
     * @param path, caminho para o ficheiro
     */
    public static void readBinGeoCaches(RedBlackBST<String, GeoCache> geoCachesST, String path) {
        File f = new File(path);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer size = (Integer) ois.readObject();

            System.out.println(size);
            for (int i = 0; i < size; i++) {
                GeoCache geoCache = (GeoCache) ois.readObject();
                System.out.println("geocache---" + geoCache);
                geoCachesST.put(geoCache.getPontoInteresse().getNome(), geoCache);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que le de um ficheiro binario toda a informacao que esta no array de pontos de interesse
     * @param pontosInteresse, st a ler
     * @param path, caminho para o ficheiro
     */
    public static void readPontoInteresse(ArrayList<PontoInteresse> pontosInteresse, String path) {
        File f = new File(path);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer size = (Integer) ois.readObject();

            System.out.println(size);
            for (int i = 0; i < size; i++) {
                PontoInteresse pontoInteresse = (PontoInteresse) ois.readObject();
                System.out.println("pontoInteresse --- " + pontoInteresse);
                pontosInteresse.add(pontoInteresse);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que le de um ficheiro binario toda a informacao que esta no array de objetos
     * @param objetos, st a ler
     * @param path, caminho para o ficheiro
     */
    public static void readBinObjetos(ArrayList<Objeto> objetos, String path) {
        File f = new File(path);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer size = (Integer) ois.readObject();

            System.out.println(size);
            for (int i = 0; i < size; i++) {
                Objeto objeto = (Objeto) ois.readObject();
                System.out.println("Objeto --- " + objeto);
                objetos.add(objeto);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcao que cria o ficheiro para depois podermos escrever para este ficheiro
     *
     * @param path, caminho ate ao ficheiro
     */
    public static void createFile(String path) {
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("Ficheiro criado: " + myObj.getName());
            } else {
                System.out.println("Ficheiro já existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro, lamento.");
            e.printStackTrace();
        }
    }

    /**
     * funcao que escreve para o ficheiro output toda a informacao guardada nas ST e Arrays
     *
     * @param path, caminho do ficheiro output
     */
    public static void writeToFile(String path) {
        try {
            FileWriter myWriter = new FileWriter(path);
            int numParticipante = participantes.size();
            myWriter.write(String.valueOf(Long.valueOf(numParticipante)));
            myWriter.write("\n");

            for (String p_aux : participantes.keys()) {
                Participante participante = participantes.get(p_aux);
                myWriter.write(String.valueOf(Long.valueOf(participante.getId())));
                myWriter.write(", ");
                myWriter.write(participante.getNome());
                myWriter.write(", ");
                if (participante instanceof PremiumParticipante) {
                    if (participante instanceof AdminParticipante)
                        myWriter.write("admin");
                    else
                        myWriter.write("premium");
                } else
                    myWriter.write("basic");

                myWriter.write("\n");
            }
            myWriter.write(String.valueOf(Integer.valueOf(3)));
            myWriter.write("\n");
            ArrayList<PontoInteresse> norte = new ArrayList<>();
            ArrayList<PontoInteresse> centro = new ArrayList<>();
            ArrayList<PontoInteresse> sul = new ArrayList<>();
            for (PontoInteresse pi_aux : pontosInteresse) {
                if (pi_aux.getRegiao().compareTo("norte") == 0) {
                    norte.add(pi_aux);
                } else if (pi_aux.getRegiao().compareTo("centro") == 0) {
                    centro.add(pi_aux);
                } else if (pi_aux.getRegiao().compareTo("sul") == 0) {
                    sul.add(pi_aux);
                }
            }
            myWriter.write("norte, ");
            myWriter.write(String.valueOf(Integer.valueOf(norte.size())));
            myWriter.write("\n");
            for (PontoInteresse ponto_aux : norte) {
                myWriter.write(ponto_aux.getNome() + ", " + ponto_aux.getGeoCache().getTipoGeoCache() + ", "
                        + ponto_aux.getX() + ", " + ponto_aux.getY() + ", ");
                int tamanhoObjetos = ponto_aux.getGeoCache().getObjetos().size();
                myWriter.write(String.valueOf(Integer.valueOf(tamanhoObjetos)));
                for (Objeto o : ponto_aux.getGeoCache().getObjetos())
                    myWriter.write(", " + String.valueOf(o.getNome()));
                myWriter.write("\n");
            }
            myWriter.write("centro, ");
            myWriter.write(String.valueOf(Integer.valueOf(centro.size())));
            myWriter.write("\n");
            for (PontoInteresse ponto_aux1 : centro) {
                myWriter.write(ponto_aux1.getNome() + ", " + ponto_aux1.getGeoCache().getTipoGeoCache() + ", "
                        + ponto_aux1.getX() + ", " + ponto_aux1.getY() + ", ");
                int tamanhoObjetos = ponto_aux1.getGeoCache().getObjetos().size();
                myWriter.write(String.valueOf(Integer.valueOf(tamanhoObjetos)));
                for (Objeto o : ponto_aux1.getGeoCache().getObjetos())
                    myWriter.write(", " + String.valueOf(o.getNome()));
                myWriter.write("\n");
            }
            myWriter.write("sul, ");
            myWriter.write(String.valueOf(Integer.valueOf(sul.size())));
            myWriter.write("\n");
            for (PontoInteresse ponto_aux2 : sul) {
                myWriter.write(ponto_aux2.getNome() + ", " + ponto_aux2.getGeoCache().getTipoGeoCache() + ", "
                        + ponto_aux2.getX() + ", " + ponto_aux2.getY() + ", ");
                int tamanhoObjetos = ponto_aux2.getGeoCache().getObjetos().size();
                myWriter.write(String.valueOf(Integer.valueOf(tamanhoObjetos)));
                for (Objeto o : ponto_aux2.getGeoCache().getObjetos())
                    myWriter.write(", " + String.valueOf(o.getNome()));
                myWriter.write("\n");
            }

            int k = 0;
            for (Objeto objeto : objetos) {
                if (objeto instanceof TravelBug)
                    k++;
            }
            myWriter.write(String.valueOf(Integer.valueOf(k)) + "\n");
            for (Objeto objeto : objetos) {
                if (objeto instanceof TravelBug)
                    myWriter.write(objeto.getNome() + ", " + ((TravelBug) objeto).getParticipante().getNome() + ", " +
                            ((TravelBug) objeto).getInicio().getPontoInteresse().getNome() + ", " +
                            ((TravelBug) objeto).getObjetivoFinal().getPontoInteresse().getNome() + "\n");
            }
            int ultimoVertex = gestorPontoInteresse.getMaiorVertex(pontosInteresse);
            myWriter.write(String.valueOf(Integer.valueOf(ultimoVertex)) + "\n");
            SubGrafo subGrafo = gestorPontoInteresse.getSubGrafoFromVertices(pontosInteresse);
            for (int y = 0; y < ultimoVertex; y++) {
                ArrayList<Conexao> conexoes = gestorPontoInteresse.getEdgesFrom(0, ultimoVertex);
                for (Conexao conexao : conexoes) {
                    for (PontoInteresse pi1 : pontosInteresse) {
                        for (PontoInteresse pi2 : pontosInteresse) {
                            //if ()
                        }

                    }
                }
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao abrir o ficheiro");
            e.printStackTrace();
        }

    }

    /**
     * funcao que verifica se um participante visitou a GeoCache
     *
     * @param geoCache,         geoCache a verificar
     * @param participante,     participante a verificar
     * @param objetosInseridos, array de objetos inseridos na GeoCache
     * @param objetosRetirados, array de objetos retirados na Geocache
     */
    public static void visitaGeoCache(GeoCache geoCache, Participante participante,
                                      ArrayList<Objeto> objetosInseridos, ArrayList<Objeto> objetosRetirados) {
        geoCache.visitado(participante, objetosInseridos, objetosRetirados);
        participante.visitouGeo(geoCache, objetosInseridos, objetosRetirados);
        for (Objeto o : objetosInseridos) {
            if (o instanceof TravelBug)
                ((TravelBug) o).update(geoCache, participante, true);
        }
    }

    /**
     * funcao que verifica os participantes mais ativos
     *
     * @return arraylist desses participantes
     */
    public ArrayList<Participante> participantesMaisAtivos() {
        ArrayList<Participante> participantes1 = new ArrayList<>();
        int[] maisVisitados = {0, 0, 0, 0, 0};
        Long[] idMaisVisitados = {Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)};
        for (String part : participantes.keys()) {
            Participante p = participantes.get(part);
            int num = p.getVisitadas().size();
            for (int i : maisVisitados) {
                if (num > maisVisitados[i]) {
                    maisVisitados[i] = num;
                    idMaisVisitados[i] = Long.valueOf(p.getId());
                }
            }
        }
        for (String part : participantes.keys()) {
            Participante parti = participantes.get(part);
            for (Long id : idMaisVisitados) {
                if (parti.getId() == idMaisVisitados[Math.toIntExact(id)])
                    participantes1.add(parti);
            }
        }
        return participantes1;
    }

    /**
     * funcao que verifica as GeoCaches que foram visitadas pelos participantes
     *
     * @param p participante a verificar
     * @return arraylist de todas as GeoCaches visitadas pelo participante
     */
    public ArrayList<GeoCache> GeoCachesVisitadas(Participante p) {
        return p.getVisitadas();
    }

    /**
     * funcao que retorna a as CachePremium com mais do que um ojeto
     *
     * @return arraylist dessas CachePremium
     */
    public ArrayList<GeoCache> CachesPremiumComMaisUmObjeto() {
        ArrayList<GeoCache> geos = new ArrayList<>();
        for (String geo : geoCaches.keys()) {
            GeoCache geoCache = geoCaches.get(geo);
            if (geoCache.getTipoGeoCache().equals(TipoGeoCacheEnum.PREMIUM)) {
                int num = geoCache.getObjetos().size();
                if (num > 1)
                    geos.add(geoCache);
            }
        }
        return geos;
    }

    /**
     * funcao que lista todas as GeoCaches presentes na ST
     */
    public static void listGeoCache() {
        for (String geoCache : geoCaches.keys()) {
            GeoCache gc = geoCaches.get(geoCache);
            System.out.println(gc.getPontoInteresse().getRegiao() + " " + gc.getPontoInteresse().getNome() + " " + gc.getTipoGeoCache());
            System.out.println(gc.getPontoInteresse().getX() + " " + gc.getPontoInteresse().getY());
            System.out.println("----------------------");
        }
    }

    /**
     * funcao que lista todas os Participantes presentes na ST
     */
    public static void listParticipante() {
        for (String pi : participantes.keys()) {
            Participante participante = participantes.get(pi);
            System.out.println(participante.getId() + " " + participante.getNome());
            System.out.println("----------------");
        }
    }

    /**
     * funcao que lista todas os objetos presentes no Array
     */
    public static void listObjetos() {
        for (Objeto oi : objetos) {
            System.out.println(oi.getNome());
            System.out.println("----------------");
        }
    }

    /**
     * funcao que lista todas os Pontos de Interesses presentes no Array
     */
    public static void listPontosInteresse() {
        for (PontoInteresse pontoInteresse : pontosInteresse) {
            System.out.println(pontoInteresse.getNome() + " " + pontoInteresse.getRegiao());
            System.out.println(pontoInteresse.getX() + " " + pontoInteresse.getY());
            System.out.println("----------------");
        }
    }
}