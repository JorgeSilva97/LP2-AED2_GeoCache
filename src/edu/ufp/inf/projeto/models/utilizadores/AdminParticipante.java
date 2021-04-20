package edu.ufp.inf.projeto.models.utilizadores;

import edu.ufp.inf.projeto.models.GeoCache;

import java.util.ArrayList;

public class AdminParticipante extends PremiumParticipante {


    private ArrayList<Participante> participantes = new ArrayList<>();

    public AdminParticipante(String id, String nome, String mail)
    {
        super(id, nome, mail);
    }

    public void addParticipante(Participante p)
    {
        for (Participante u : this.participantes)
        {
            if (u.equals(p))
            {
                System.out.println("Participante já existente!");
                return;
            }
        }
        participantes.add(p);
        System.out.println("Participante adicioanado com sucesso!");
    }

    public void removeParticipante(Participante p)
    {
        for (Participante u : this.participantes)
        {
            if (u.equals(p))
            {
                participantes.remove(p);
                System.out.println("Participante removido com sucesso!");
                return;
            }
        }
        System.out.println("Participante impossivel de remover!");
    }




    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

}
