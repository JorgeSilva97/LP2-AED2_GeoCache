package edu.ufp.inf.aed2.project;

import java.util.ArrayList;

public class AdminParticipante extends PremiumUser {


    private ArrayList<Participante> participantes = new ArrayList<>();

    public AdminParticipante(String id, String nome, String mail) {
        super(id, nome, mail);
    }




}
