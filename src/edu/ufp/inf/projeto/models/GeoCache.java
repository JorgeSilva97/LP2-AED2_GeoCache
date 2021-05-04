package edu.ufp.inf.projeto.models;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.projeto.models.utilizadores.PremiumParticipante;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class GeoCache
{

    private int id;
    private PontoInteresse pontoInteresse;
    private int dificuldade;
    private TipoGeoCacheEnum tipoGeoCache;
    private PremiumParticipante premiumParticipante;
    private ArrayList<Objeto> objetos = new ArrayList<>();
    private ST<String, ArrayList<String>> logs = new ST<>();



    public GeoCache(int id, PontoInteresse pontoInteresse, int dificuldade, TipoGeoCacheEnum tipoGeoCache)
    {
        this.id = id;
        this.pontoInteresse = pontoInteresse;
        this.dificuldade = dificuldade;
        this.tipoGeoCache = tipoGeoCache;
    }

    /**
     *
     * Adiciona Objeto à ArrayList
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
     * Remove Objeto ao ArrayList
     * @param o
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

    public void editObjeto(String nome, Objeto o){
        for(Objeto obj : this.objetos){
           if(obj.getNome().equals(o.getNome())){
                obj.setNome(nome);
               System.out.println("Operação efetuada com sucesso!");
               Date d = new Date();
               addLog("Editado Objeto; "+ obj.getNome()+"com sucesso!", new Timestamp(d.getTime()).toString());
               return;
           }
        }
    }

    /**
     *
     * @param l
     * @param dateTime
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
        System.out.println("Log adicionado com sucesso!");
    }



    public static void main(String[] args)
    {
        Objeto o = new Objeto("telemovel");
        GeoCache gc = new GeoCache(1, new PontoInteresse(), 3, TipoGeoCacheEnum.BASIC);
        gc.addObjeto(o);
        gc.editObjeto(o.getNome(),o);
        gc.removeObjeto(o);
        gc.getLogs();
    }


    @Override
    public String toString()
    {
        return "GeoCache{" +
                "id=" + id +
                ", pontoInteresse=" + pontoInteresse +
                ", dificuldade=" + dificuldade +
                ", tipoGeoCache=" + tipoGeoCache +
                //for objetos
                ", objetos=" + objetos +
                '}';
    }

    public PremiumParticipante getPremiumParticipante() {
        return premiumParticipante;
    }

    public void setPremiumParticipante(PremiumParticipante premiumParticipante) {
        this.premiumParticipante = premiumParticipante;
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
