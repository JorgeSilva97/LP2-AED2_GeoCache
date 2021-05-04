package edu.ufp.inf.projeto.models.utilizadores;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.GeoCache;
import edu.ufp.inf.projeto.models.PontoInteresse;
import edu.ufp.inf.projeto.models.TipoGeoCacheEnum;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PremiumParticipante extends Participante
{

    private ArrayList<GeoCache> geoCaches = new ArrayList<>();


    public PremiumParticipante(String id, String nome, String mail) {
        super(id, nome, mail);
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
        System.out.println("GeoCache adicioanado com sucesso!");
        Date d = new Date();
        addLog("Adicionada GeoCache com sucesso!", new Timestamp(d.getTime()).toString());
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
                //System.out.println(new Timestamp(d.getTime()).toString());
                return;
            }
        }
        System.out.println("GeoCache impossível de remover!");
    }


    public void editGeoCache(PontoInteresse pontoInteresse, int dificuldade, TipoGeoCacheEnum tipoGeoCacheEnum, GeoCache geoCache){
        for(GeoCache geo : this.geoCaches){
            if(geo.getPontoInteresse().equals(geoCache.getPontoInteresse()) && geo.getDificuldade() == geoCache.getDificuldade() && geo.getPremiumParticipante().equals(geoCache.getPremiumParticipante()) && geo.getTipoGeoCache().equals(geoCache.getTipoGeoCache())){
                geo.setPontoInteresse(pontoInteresse);
                geo.setDificuldade(dificuldade);
                geo.setTipoGeoCache(tipoGeoCacheEnum);
                System.out.println("Edicao efetuada com sucesso!");
                Date d = new Date();
                addLog("GeoCache Editada com sucesso!", new Timestamp(d.getTime()).toString());
            }
        }
    }






    public ArrayList<GeoCache> getGeoCaches() {
        return geoCaches;
    }

    public void setGeoCaches(ArrayList<GeoCache> geoCaches) {
        this.geoCaches = geoCaches;
    }
}
