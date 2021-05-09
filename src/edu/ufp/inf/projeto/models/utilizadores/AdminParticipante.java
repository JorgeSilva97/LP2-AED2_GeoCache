package edu.ufp.inf.projeto.models.utilizadores;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class AdminParticipante extends PremiumParticipante
{

    private ArrayList<Participante> participantes = new ArrayList<>();

    /**
     * Construtor da classe AdminParticipante
     * @param id, id do admin
     * @param nome, nome do admin
     */
    public AdminParticipante(int id, String nome)
    {
        super(id, nome);
        Date d = new Date();
        addLog("Adicionado AdminParticipante: "+ nome +" com sucesso!", new Timestamp(d.getTime()).toString());
    }

    /**
     * Funcao que adiciona um participante a lista de participantes
     * @param p, participante a ser adicionado
     */
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
        Date d = new Date();
        addLog("Adicionado Participante: "+ p.getNome() +" com sucesso!", new Timestamp(d.getTime()).toString());
    }

    /**
     * Funcao que remove um participante da lista de participantes
     * @param p, participante a ser removido
     */
    public void removeParticipante(Participante p)
    {
        for (Participante u : this.participantes)
        {
            if (u.equals(p))
            {
                participantes.remove(p);
                System.out.println("Participante removido com sucesso!");
                Date d = new Date();
                addLog("Removido Participante: "+ p.getNome() +" com sucesso!", new Timestamp(d.getTime()).toString());
                return;
            }
        }
        System.out.println("Participante impossivel de remover!");
    }

    /**
     * Funcao que edita um participante da lista de participantes
     * @param nome, novo nome do participante
     * @param p, participante a ser editado
     */
    public void editParticipante(String nome, Participante p){
        for(Participante p_aux : this.participantes){
            if(p_aux.getNome().equals(p.getNome())){
                p_aux.setNome(nome);
                System.out.println("Operação efetuada com sucesso!");
                Date d = new Date();
                addLog("Paricipante Editado; "+ p_aux.getNome()+"com sucesso!", new Timestamp(d.getTime()).toString());
                return;
            }
        }
    }

    /**
     * funcao que lista todos os participantes presentes no array
     */
    public void listParticipante(){
        for(Participante pi : this.participantes){
            System.out.println(pi.getNome());
        }
    }

    /**
     * funcao main para testes de todos os metodos da classe
     * @param args
     */
    public static void main(String[] args) {
        Participante joaoSilva = new Participante(1,"Joao Silva");
        Participante franciscoCunha = new Participante(2,"Francisco Cunha");
        Participante vascoCardozo = new Participante(3,"Vasco Cardozo");
        Participante jorgeSilva = new Participante(1,"Jorge Silva");

        AdminParticipante admin = new AdminParticipante(1,"Rui");
        admin.addParticipante(joaoSilva);
        admin.addParticipante(franciscoCunha);
        admin.addParticipante(vascoCardozo);
        admin.addParticipante(jorgeSilva);

        admin.listParticipante();

        //System.out.println("------depois de removido-----------\n");

        //admin.removeParticipante(vascoCardozo);
        //admin.listParticipante();

        System.out.println("------depois de editado-----------\n");

        admin.editParticipante("Jose Carlos",franciscoCunha);
        admin.listParticipante();

    }


    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

}
