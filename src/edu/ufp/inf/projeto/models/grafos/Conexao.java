package edu.ufp.inf.projeto.models.grafos;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.ufp.inf.projeto.models.enumerados.CustoEnum;

public class Conexao extends DirectedEdge
{
    private double pesoTempo;

    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
     * the given {@code weight}.
     *
     * @param v      the tail vertex
     * @param w      the head vertex
     * @param distancia the weight of the directed edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *                                  is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Conexao(int v, int w, double distancia, double tempo)
    {
        super(v, w, distancia);
        this.pesoTempo = tempo;
    }


    public double getPesoTempo() {
        return pesoTempo;
    }

    public void setPesoTempo(double pesoTempo) {
        this.pesoTempo = pesoTempo;
    }

    public double getDistancia()
    {
        return super.weight();
    }

    @Override
    public String toString() {
        return super.from() + "->" + super.to() + "- {Distancia: " + super.weight() + ", Tempo: " + this.pesoTempo + "}";
    }

    @Override
    public double weight()
    {
        if (GestorPontoInteresse.custoEnum == CustoEnum.TEMPO)
            return this.pesoTempo;
        return super.weight();
    }
}
