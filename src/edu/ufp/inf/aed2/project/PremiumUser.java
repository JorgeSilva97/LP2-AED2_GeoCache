package edu.ufp.inf.aed2.project;

import java.util.ArrayList;

public class PremiumUser extends Participante{

    private ArrayList<GeoCache> geoCaches = new ArrayList<>();


    public PremiumUser(String id, String nome, String mail) {
        super(id, nome, mail);
    }

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
    }

    public void removeGeoCache(GeoCache gc)
    {
        for (GeoCache g : this.geoCaches)
        {
            if (g.equals(gc))
            {
                System.out.println("GeoCache removido com sucesso!");
                geoCaches.remove(gc);
                return;
            }
        }
    }

    //ADMIN
    //adicionar, remover utlizador

}
