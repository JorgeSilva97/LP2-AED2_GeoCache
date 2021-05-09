package edu.ufp.inf.projeto.models.utilizadores;

import edu.ufp.inf.projeto.models.GeoCache;
import edu.ufp.inf.projeto.models.PontoInteresse;
import edu.ufp.inf.projeto.models.TipoGeoCacheEnum;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PremiumParticipante extends Participante
{

    private ArrayList<GeoCache> geoCaches = new ArrayList<>();

    /**
     * Construtor da classe PremiumParticipante
     * @param id, id do PremiumParticipante
     * @param nome, nome do PremiumParticipante
     */
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
     * funcao que remove GeoCache ao ArrayList
     * @param gc, GeoCache a ser removido
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


    /**
     * Funcao que edita a GeoCache da lista de GeoCaches
     * @param pontoInteresse, novo ponto de interesse da Geocache
     * @param tipoGeoCacheEnum, no tipo de GeoCache
     * @param geoCache, geoCache a ser editado
     */
    public void editGeoCache(PontoInteresse pontoInteresse, TipoGeoCacheEnum tipoGeoCacheEnum, GeoCache geoCache){
        for(GeoCache geo : this.geoCaches){
            if(geo.getPontoInteresse().getNome().equals(geoCache.getPontoInteresse().getNome()) && geo.getPontoInteresse().getRegiao().equals(geoCache.getPontoInteresse().getRegiao()) && geo.getPontoInteresse().getX() == geoCache.getPontoInteresse().getX() && geo.getPontoInteresse().getY() == geoCache.getPontoInteresse().getY() && geo.getTipoGeoCache().equals(geoCache.getTipoGeoCache())){
                geo.getPontoInteresse().setNome(pontoInteresse.getNome());
                geo.getPontoInteresse().setRegiao(pontoInteresse.getRegiao());
                geo.getPontoInteresse().setX(pontoInteresse.getX());
                geo.getPontoInteresse().setY(pontoInteresse.getY());
                geo.setTipoGeoCache(tipoGeoCacheEnum);
                System.out.println("Operação efetuada com sucesso!");
                Date d = new Date();
                addLog("GeoCache Editado com sucesso!", new Timestamp(d.getTime()).toString());
                return;
            }
        }
    }

    /**
     * funcao que lista todas as GeoCaches do array
     */
    public void listGeoCache(){
        for(GeoCache geoCache : this.geoCaches){
            System.out.println(geoCache.getPontoInteresse().getRegiao());
            System.out.println(geoCache.getPontoInteresse().getNome());
            System.out.println(geoCache.getTipoGeoCache());
            System.out.println(geoCache.getPontoInteresse().getX());
            System.out.println(geoCache.getPontoInteresse().getY());
        }
    }

    /**
     * funcao main para testes dos metodos da classe
     * @param args
     */
    public static void main(String[] args) {
        PontoInteresse pi1 = new PontoInteresse(3.4,2.6,"norte","GeoCache1");
        PontoInteresse pi2 = new PontoInteresse(2.7,8.9,"sul","GeoCache2");
        GeoCache gc1 = new GeoCache(1,pi1,TipoGeoCacheEnum.BASIC);
        GeoCache gc2 = new GeoCache(2,pi2,TipoGeoCacheEnum.BASIC);

        PremiumParticipante premiumParticipante = new PremiumParticipante(1,"Rute");
        premiumParticipante.addGeoCache(gc1);
        premiumParticipante.addGeoCache(gc2);
        premiumParticipante.listGeoCache();

        System.out.println("----------removido---------");
        premiumParticipante.removeGeoCache(gc1);
        premiumParticipante.listGeoCache();


    }





    public ArrayList<GeoCache> getGeoCaches() {
        return geoCaches;
    }

    public void setGeoCaches(ArrayList<GeoCache> geoCaches) {
        this.geoCaches = geoCaches;
    }
}
