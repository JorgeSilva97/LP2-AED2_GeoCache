package edu.ufp.inf.projeto.models;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.enumerados.TipoGeoCacheEnum;
import edu.ufp.inf.projeto.models.utilizadores.Participante;
import edu.ufp.inf.projeto.models.utilizadores.PremiumParticipante;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class GeoCache
{

    /**
     * identificador da GeoCache
     * PontoInteresse onde esta se encontra
     * dificuldade da GeoCache ser encontrada
     * tipo diferente de GeoCachr
     * Array de todos os Participantes que visitaram esta GeoCache
     * Array de todos os Objetos que se encontram nesta GeoCache
     * ST de logs efectuados pela GeoCache
     */
    private int id;
    private PontoInteresse pontoInteresse;
    private int dificuldade;
    private TipoGeoCacheEnum tipoGeoCache;
    private PremiumParticipante criadorPremiumParticipante;
    private ArrayList<Participante> visitantes = new ArrayList<>();
    private ArrayList<Objeto> objetos = new ArrayList<>();
    private ST<String, ArrayList<String>> logs = new ST<>();


    /**
     * Construtor da classe GeoCache
     * @param id, id da GeoCache
     * @param pontoInteresse, ponto de interesse da GeoCache
     * @param tipoGeoCache, tipo de GeoCache
     */
    public GeoCache(int id, PontoInteresse pontoInteresse, TipoGeoCacheEnum tipoGeoCache)
    {
        this.id = id;
        this.pontoInteresse = pontoInteresse;
        this.tipoGeoCache = tipoGeoCache;
        Date d = new Date();
        addLog("Adicionado GeoCache com sucesso!", new Timestamp(d.getTime()).toString());
    }

    public GeoCache(PontoInteresse pontoInteresse, TipoGeoCacheEnum tipoGeoCache)
    {
        this.pontoInteresse = pontoInteresse;
        this.tipoGeoCache = tipoGeoCache;
        Date d = new Date();
        addLog("Adicionado GeoCache com sucesso!", new Timestamp(d.getTime()).toString());
    }


     

    /**
     * funcao que adiciona Objeto à ArrayList
     * @param o objeto a adicionar
     */
    public void addObjeto (Objeto o)
    {
        for (Objeto ob : this.objetos)
        {
            if (ob.equals(o))
            {
                System.out.println("Objeto já existente!");
                return;
            }
        }
        objetos.add(o);
        System.out.println("Objeto adicionado com sucesso!");
        Date d = new Date();
        addLog("Adicionada Objeto: "+ o.getNome() +" com sucesso!", new Timestamp(d.getTime()).toString());
    }

    /**
     * funcao que remove Objeto ao ArrayList
     * @param o, objeto a ser removido
     */
    public void removeObjeto (Objeto o)
    {
        for (Objeto ob : this.objetos)
        {
            if (ob.getNome().equals(o.getNome()))
            {
                objetos.remove(o);
                System.out.println("Objeto removido com sucesso!");
                Date d = new Date();
                addLog("Removido Objeto: "+ o.getNome()+ "com sucesso!", new Timestamp(d.getTime()).toString());
                //System.out.println(new Timestamp(d.getTime()).toString());
                return;
            }
        }
        System.out.println("Objeto impossível de remover!");
    }

    /**
     * funcao que edita um objetom presente no array
     * @param nome, novo nome do objeto
     * @param o, objeto a ser editado
     */
    public void editObjeto(String nome, Objeto o)
    {
        for(Objeto obj : this.objetos)
        {
           if(obj.getNome().equals(o.getNome()))
           {
                obj.setNome(nome);
               System.out.println("Operação efetuada com sucesso!");
               Date d = new Date();
               addLog("Editado Objeto; "+ obj.getNome()+"com sucesso!", new Timestamp(d.getTime()).toString());
               return;
           }
        }
    }

    /**
     * funcao que lista todos os objetos presentes no array
     */
    public void listObjetos(){
        for(Objeto oi : this.objetos){
            System.out.println(oi.getNome());
        }
    }

    /**
     * funcao que adiciona logs
     * @param l, string a adicionar
     * @param dateTime, data do log
     */
    public void addLog (String l, String dateTime)
    {
        if(logs.contains(dateTime))
            logs.get(dateTime).add(l);
        else
        {
            ArrayList<String> lg = new ArrayList<>();
            lg.add(l);
            logs.put(dateTime, lg);
        }
        //System.out.println("Log adicionado com sucesso!");
    }

    /**
     * funcao que verifica se a GeoCache foi visitada pelo Participante e se inseriu ou removeu objetos
     * @param participante participante a visitar GeoCache
     * @param objetosInseridos lista de objetos que foram inseridos
     * @param objetosRetirados lista de objetos que foram removidos
     */
    public void visitado(Participante participante, ArrayList<Objeto> objetosInseridos, ArrayList<Objeto> objetosRetirados)
    {
        visitantes.add(participante);
        for (Objeto o : objetosInseridos)
            objetos.add(o);
        for (Objeto retirado : objetosRetirados)
        {
            for (Objeto atual : this.objetos)
            {
                if (retirado.equals(atual))
                {
                    objetos.remove(retirado);
                    Date d = new Date();
                    addLog("A GeoCache que foi criada pelo " +this.criadorPremiumParticipante.getNome()+
                            " foi visitada pelo Participante "+ participante.getNome(), new Timestamp(d.getTime()).toString());
                }
            }
        }
    }

    /**
     * funcao main para testes dos metodos da classe
     * @param args
     */
    public static void main(String[] args)
    {
        Objeto o = new Objeto("telemovel");
        GeoCache gc = new GeoCache(1, new PontoInteresse(), TipoGeoCacheEnum.BASIC);
        gc.addObjeto(o);
        gc.editObjeto(o.getNome(),o);
        gc.removeObjeto(o);
        gc.getLogs();

        String[] cona = {"telemovel", "bola"};
        System.out.println(Arrays.stream(cona).count());
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof GeoCache)) return false;
        GeoCache geoCache = (GeoCache) o;
        return getId() == geoCache.getId() && getDificuldade() == geoCache.getDificuldade() && Objects.equals(getPontoInteresse(), geoCache.getPontoInteresse()) && getTipoGeoCache() == geoCache.getTipoGeoCache() && Objects.equals(getCriadorPremiumParticipante(), geoCache.getCriadorPremiumParticipante()) && Objects.equals(getObjetos(), geoCache.getObjetos()) && Objects.equals(getLogs(), geoCache.getLogs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPontoInteresse(), getDificuldade(), getTipoGeoCache(), getCriadorPremiumParticipante(), getObjetos(), getLogs());
    }


    @Override
    public String toString() {
        return "GeoCache{" +
                "id=" + id +
                ", pontoInteresse=" + pontoInteresse +
                ", tipoGeoCache=" + tipoGeoCache +
                ", criadorPremiumParticipante=" + criadorPremiumParticipante +
                '}';
    }

    public PremiumParticipante getCriadorPremiumParticipante() {
        return criadorPremiumParticipante;
    }

    public void setCriadorPremiumParticipante(PremiumParticipante criadorPremiumParticipante) {
        this.criadorPremiumParticipante = criadorPremiumParticipante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PontoInteresse getPontoInteresse() {
        return pontoInteresse;
    }

    public void setPontoInteresse(PontoInteresse pontoInteresse) {
        this.pontoInteresse = pontoInteresse;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public TipoGeoCacheEnum getTipoGeoCache() {
        return tipoGeoCache;
    }

    public void setTipoGeoCache(TipoGeoCacheEnum tipoGeoCache) {
        this.tipoGeoCache = tipoGeoCache;
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Objeto> objetos) {
        this.objetos = objetos;
    }

    public ST<String, ArrayList<String>> getLogs()
    {
        for (String lg: this.logs.keys())
        {
            System.out.println(lg + " " + logs.get(lg));
        }
        return logs;
    }

    public void setLogs(ST<String, ArrayList<String>> logs) {
        this.logs = logs;
    }


}
