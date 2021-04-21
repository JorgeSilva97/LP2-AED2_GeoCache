package edu.ufp.inf.projeto.models.utilizadores;

import edu.ufp.inf.projeto.models.GeoCache;

import java.util.ArrayList;

public class PremiumParticipante extends Participante
{

    private ArrayList<GeoCache> geoCaches = new ArrayList<>();


    public PremiumParticipante(String id, String nome, String mail) {
        super(id, nome, mail);
    }

    /**
     * Adiciona GeoCache à ArrayList
     * @param gc GeoCache a adicionar
     */
    public void addGeoCache(GeoCache gc)
    {
        for (GeoCache g : this.geoCaches)
        {
            if (g.equals(gc))
            {
                System.out.println("GeoCache já existente!");
                return;
            }
        }
        geoCaches.add(gc);
        System.out.println("GeoCache adicioanado com sucesso!");
    }

    /**
     * Remove GeoCache ao ArrayList
     * @param gc
     */
    public void removeGeoCache(GeoCache gc)
    {
        for (GeoCache g : this.geoCaches)
        {
            if (g.equals(gc))
            {
                geoCaches.remove(gc);
                System.out.println("GeoCache removido com sucesso!");
                return;
            }
        }
        System.out.println("GeoCache impossível de remover!");
    }



    public ArrayList<GeoCache> getGeoCaches() {
        return geoCaches;
    }

    public void setGeoCaches(ArrayList<GeoCache> geoCaches) {
        this.geoCaches = geoCaches;
    }
}
