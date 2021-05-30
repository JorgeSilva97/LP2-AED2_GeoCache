package edu.ufp.inf.projeto.models.grafos;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdOut;
import edu.ufp.inf.projeto.models.PontoInteresse;
import edu.ufp.inf.projeto.models.enumerados.CustoEnum;

import java.io.Serializable;
import java.util.ArrayList;

public class GestorPontoInteresse implements Serializable
{
    private EdgeWeightedDigraph grafoGlobal;
    private ArrayList<PontoInteresse> pontoInteresses;
    protected static CustoEnum custoEnum = CustoEnum.DISTANCIA;

    public GestorPontoInteresse(ArrayList<PontoInteresse> pontoInteresses) {
        this.pontoInteresses = pontoInteresses;
    }

    public void criaGrafoGlobal()
    {
        if (!this.pontoInteresses.isEmpty())
        {
            this.grafoGlobal = new EdgeWeightedDigraph(this.pontoInteresses.size());
            System.out.println("Grafo global criado com sucesso com "+this.pontoInteresses.size()+" vertices!");
        }
    }

    public PontoInteresse getPontoInteresseWhereVertexIs (int vertexId)
    {
        for (PontoInteresse pontoInteresse : pontoInteresses)
        {
            if (pontoInteresse.getVertexId() == vertexId)
                return pontoInteresse;
        }
        return null;
    }

    public ArrayList<Conexao> getEdgesFrom (int primeiroVertex, int ultimoVertex)
    {
        ArrayList<Conexao> edges = new ArrayList<>();
        for (DirectedEdge edge : this.grafoGlobal.edges())
        {
            if (edge.from() >= primeiroVertex && edge.from() <= ultimoVertex &&
                    edge.to() >= primeiroVertex && edge.to() <= ultimoVertex)
                edges.add((Conexao) edge);

        }
        return edges;
    }

    public SubGrafo getSubGrafoFromVertices (ArrayList<PontoInteresse> pontoInteresses)
    {
        if (!pontoInteresses.isEmpty())
        {
            int minIndex = pontoInteresses.get(0).getVertexId();
            int maxIndex = pontoInteresses.get(0).getVertexId();
            for (PontoInteresse pontoInteresse : pontoInteresses)
            {
                if (pontoInteresse.getVertexId() > maxIndex)
                    maxIndex = pontoInteresse.getVertexId();
                if (pontoInteresse.getVertexId() < minIndex)
                    minIndex = pontoInteresse.getVertexId();
            }
            ArrayList<Conexao> dEdges = this.getEdgesFrom(minIndex, maxIndex);
            System.out.println("A retornar sub-grafo");
            return new SubGrafo(dEdges, minIndex, maxIndex, this);
        }
        return null;
    }

    public SubGrafo getSubGrafoPremiumCaches (ArrayList<PontoInteresse> pontoInteresses)
    {
        if (!pontoInteresses.isEmpty())
        {
            int minIndex = pontoInteresses.get(0).getVertexId();
            int maxIndex = pontoInteresses.get(0).getVertexId();
            for (PontoInteresse pontoInteresse : pontoInteresses)
            {
                if (pontoInteresse.getGeoCache().getTipoGeoCache().ordinal() == 1)
                {
                    if (pontoInteresse.getVertexId() > maxIndex)
                        maxIndex = pontoInteresse.getVertexId();
                    if (pontoInteresse.getVertexId() < minIndex)
                        minIndex = pontoInteresse.getVertexId();
                }
            }
            ArrayList<Conexao> dEdges = this.getEdgesFrom(minIndex, maxIndex);
            System.out.println("A retornar sub-grafo de Caches Premium");
            return new SubGrafo(dEdges, minIndex, maxIndex, this);
        }
        return null;
    }

    public SubGrafo getSubGrafoBasicCaches (ArrayList<PontoInteresse> pontoInteresses)
    {
        if (!pontoInteresses.isEmpty())
        {
            int minIndex = pontoInteresses.get(0).getVertexId();
            int maxIndex = pontoInteresses.get(0).getVertexId();
            for (PontoInteresse pontoInteresse : pontoInteresses)
            {
                if (pontoInteresse.getGeoCache().getTipoGeoCache().ordinal() == 0)
                {
                    if (pontoInteresse.getVertexId() > maxIndex)
                        maxIndex = pontoInteresse.getVertexId();
                    if (pontoInteresse.getVertexId() < minIndex)
                        minIndex = pontoInteresse.getVertexId();
                }
            }
            ArrayList<Conexao> dEdges = this.getEdgesFrom(minIndex, maxIndex);
            System.out.println("A retornar sub-grafo de Caches Basic");
            return new SubGrafo(dEdges, minIndex, maxIndex, this);
        }
        return null;
    }

    public int getMaiorVertex(ArrayList<PontoInteresse> pontoInteresses)
    {
        if (!pontoInteresses.isEmpty())
        {
            int maxIndex = pontoInteresses.get(0).getVertexId();
            for (PontoInteresse pontoInteresse : pontoInteresses);
            for (PontoInteresse pontoInteresse : pontoInteresses)
            {
                if (pontoInteresse.getVertexId() > maxIndex)
                    maxIndex = pontoInteresse.getVertexId();
            }
            return maxIndex;
        }
        return 0;

    }

    public void printSubGrafo(ArrayList<DirectedEdge> subGarfo)
    {
        for (DirectedEdge directedEdge : subGarfo)
            System.out.println(directedEdge.toString());
    }

    public void createEdge (int source, int dest, double distancia, double custoTempo)
    {
        Conexao conexao = new Conexao(source, dest, distancia, custoTempo);
        if (this.grafoGlobal != null)
        {
            this.grafoGlobal.addEdge(conexao);
            System.out.println("Uma conexão de " +source+" para "+dest+" com distancia "+distancia+
                    " e com custo "+custoTempo+" foi adicionado com sucesso ao grafo!");
        }
    }

    public double calculaPeso (PontoInteresse source, PontoInteresse dest)
    {
        return source.getDistanciaParaOutroPontoInteresse(dest);
    }

    public void addPontoInteresse (PontoInteresse pontoInteresse)
    {
        if (!this.pontoInteresses.contains(pontoInteresse))
        {
            pontoInteresse.setVertexId(this.pontoInteresses.size());
            this.pontoInteresses.add(pontoInteresse);
            System.out.println("Ponto de Interesse adicionado com sucesso aos pontosInteresse");
        }
    }

    public void caminhoMaisCurtoEntre (int source, int dest, EdgeWeightedDigraph grafo, int offset, CustoEnum custo)
    {
        custoEnum = custo;
        DijkstraSP dijkstraSP = new DijkstraSP(grafo, source-offset);
        if (dijkstraSP.hasPathTo(dest-offset))
        {
            StdOut.printf("%d para %d (%.2f) ", source, dest, dijkstraSP.distTo(dest-offset));
            for (DirectedEdge e : dijkstraSP.pathTo(dest-offset))
            {
                StdOut.print((e.from()+offset)+"->"+(e.to()+offset)+" " +String.format("%5.2f", e.weight())+"\n");
            }
        }
        else
            StdOut.printf("%d to %d         no path\n", source + offset, dest + offset);
    }

    public ArrayList<PontoInteresse> retiraPontosInteresse (ArrayList<PontoInteresse> pontosInteresse,
                                                            ArrayList<PontoInteresse> invalidosPontosInteresse)
    {
        ArrayList<PontoInteresse> piAux = new ArrayList<>();
        for (PontoInteresse pi : pontosInteresse)
        {
            if (!invalidosPontosInteresse.contains(pi))
                piAux.add(pi);
        }
        return piAux;
    }

    public PontoInteresse getPontoInteresseMaisProximo (int x, int y)
    {
        PontoInteresse qualquer = pontoInteresses.get(0);
        double min = pontoInteresses.get(0).getDistanciaFromCoordenada(x, y);
        for (PontoInteresse pi : this.pontoInteresses)
        {
            double distancia = pi.getDistanciaFromCoordenada(x, y);
            if (distancia <= min)
            {
                min = distancia;
                qualquer = pi;
            }
        }
        return qualquer;
    }

    public boolean isConnected(EdgeWeightedDigraph g)
    {
        int s = 0;
        int flag = 0;
        DijkstraSP sp = new DijkstraSP(g, s);
        for (int t = 0; t < g.V(); t++)
        {
            if (!sp.hasPathTo(t))
                flag = 1;

        }
        if (flag == 0)
            return true;
        return false;
    }













    public EdgeWeightedDigraph getGrafoGlobal() {
        return grafoGlobal;
    }

    public void setGrafoGlobal(EdgeWeightedDigraph grafoGlobal) {
        this.grafoGlobal = grafoGlobal;
    }

    public ArrayList<PontoInteresse> getPontoInteresses() {
        return pontoInteresses;
    }

    public void setPontoInteresses(ArrayList<PontoInteresse> pontoInteresses) {
        this.pontoInteresses = pontoInteresses;
    }
}
