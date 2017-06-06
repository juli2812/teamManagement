/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

/**
 *
 * @author Maria
 */
public class ValoracioPartit {
    private String fk_jugador;
    private int fk_partit;
    private int assistencia;
    private int gols;
    private int tarjetes_grogues;
    private int tarjetes_vermelles;
    private int valoracio;
    private String lessions;
    private boolean puntualitat;
    private int min_jugats;
    private int partits_jugats;

    public ValoracioPartit(String fk_jugador, int fk_partit, int assistencia, int gols, int tarjetes_grogues, int tarjetes_vermelles, int valoracio, String lessions, boolean puntualitat, int min_jugats, int partits_jugats) {
        this.fk_jugador = fk_jugador;
        this.fk_partit = fk_partit;
        this.assistencia = assistencia;
        this.gols = gols;
        this.tarjetes_grogues = tarjetes_grogues;
        this.tarjetes_vermelles = tarjetes_vermelles;
        this.valoracio = valoracio;
        this.lessions = lessions;
        this.puntualitat = puntualitat;
        this.min_jugats = min_jugats;
        this.partits_jugats = partits_jugats;
    }

    public String getFk_jugador() {
        return fk_jugador;
    }

    public void setFk_jugador(String fk_jugador) {
        this.fk_jugador = fk_jugador;
    }

    public int getFk_partit() {
        return fk_partit;
    }

    public void setFk_partit(int fk_partit) {
        this.fk_partit = fk_partit;
    }

    public int getAssistencia() {
        return assistencia;
    }

    public void setAssistencia(int assistencia) {
        this.assistencia = assistencia;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public int getTarjetes_grogues() {
        return tarjetes_grogues;
    }

    public void setTarjetes_grogues(int tarjetes_grogues) {
        this.tarjetes_grogues = tarjetes_grogues;
    }

    public int getTarjetes_vermelles() {
        return tarjetes_vermelles;
    }

    public void setTarjetes_vermelles(int tarjetes_vermelles) {
        this.tarjetes_vermelles = tarjetes_vermelles;
    }

    public int getValoracio() {
        return valoracio;
    }

    public void setValoracio(int valoracio) {
        this.valoracio = valoracio;
    }

    public String getLessions() {
        return lessions;
    }

    public void setLessions(String lessions) {
        this.lessions = lessions;
    }

    public boolean isPuntualitat() {
        return puntualitat;
    }

    public void setPuntualitat(boolean puntualitat) {
        this.puntualitat = puntualitat;
    }

    public int getMin_jugats() {
        return min_jugats;
    }

    public void setMin_jugats(int min_jugats) {
        this.min_jugats = min_jugats;
    }

    public int getPartits_jugats() {
        return partits_jugats;
    }

    public void setPartits_jugats(int partits_jugats) {
        this.partits_jugats = partits_jugats;
    }
    
    
}
