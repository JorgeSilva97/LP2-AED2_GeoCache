package edu.ufp.inf.projeto.models.grafos;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.ufp.inf.projeto.models.PontoInteresse;

import java.io.Serializable;
import java.util.ArrayList;

public class SubGrafo implements Serializable
{
    private GestorPontoInteresse gestorPontoInteresse;
    private EdgeWeightedDigraph grafo;
    private int offset;                                 //desvio entre grafo e sub-grafo


    public SubGrafo(ArrayList<Conexao> directedEdge, int min, int max, GestorPontoInteresse gestorPontoInteresse)
    {
        this.gestorPontoInteresse = gestorPontoInteresse;
        this.grafo = new EdgeWeightedDigraph((max-min) + 1);
        this.offset = min;
        this.addEdge(directedEdge);
        System.out.println("Grafo criado com sucesso!!");
    }


    private void addEdge (ArrayList<Conexao> directedEdges)
    {
        for(Conexao directedEdge : directedEdges)
        {
            Conexao offsetDirectedEdge = new Conexao(directedEdge.from()-offset, directedEdge.to()-offset,
                    directedEdge.getDistancia(), directedEdge.getPesoTempo());
            this.grafo.addEdge(offsetDirectedEdge);
        }
    }

    public PontoInteresse getPontoInteresseFromVertexId(int vertexId)
    {
        int globalVertexId = vertexId + this.offset;
        return this.gestorPontoInteresse.getPontoInteresseWhereVertexIs(globalVertexId);
    }




    public GestorPontoInteresse getGestorPontoInteresse() {
        return gestorPontoInteresse;
    }

    public void setGestorPontoInteresse(GestorPontoInteresse gestorPontoInteresse) {
        this.gestorPontoInteresse = gestorPontoInteresse;
    }

    public EdgeWeightedDigraph getGrafo() {
        return grafo;
    }

    public void setGrafo(EdgeWeightedDigraph grafo) {
        this.grafo = grafo;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
