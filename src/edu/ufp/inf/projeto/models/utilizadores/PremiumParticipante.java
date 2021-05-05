package edu.ufp.inf.projeto.models.utilizadores;

import edu.ufp.inf.projeto.models.GeoCache;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PremiumParticipante extends Participante
{

    private ArrayList<GeoCache> geoCaches = new ArrayList<>();


    public PremiumParticipante(int id, String nome)
    {
        super(id, nome);
        Date d = new Date();
        addLog("Adicionado ParticipantePremium: "+ nome +" com sucesso!", new Timestamp(d.getTime()).toString());
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
        gc.setCriadorPremiumParticipante(this);
        Date d = new Date();
        addLog("Adicionada GeoCache com sucesso pelo criador "+this.getNome(), new Timestamp(d.getTime()).toString());
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
                Date d = new Date();
                addLog("Removida GeoCache com sucesso!", new Timestamp(d.getTime()).toString());
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
