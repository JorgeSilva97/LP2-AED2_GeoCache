package edu.ufp.inf.projeto.models.grafos;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.ufp.inf.projeto.models.PontoInteresse;
import edu.ufp.inf.projeto.models.enumerados.CustoEnum;

import java.io.Serializable;
import java.util.ArrayList;

public class GestorPontoInteresse implements Serializable
{
    private EdgeWeightedDigraph grafoGlobal;
    private ArrayList<PontoInteresse> pontoInteresses;
    protected static CustoEnum custoEnum = CustoEnum.DISTANCIA;


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
}
