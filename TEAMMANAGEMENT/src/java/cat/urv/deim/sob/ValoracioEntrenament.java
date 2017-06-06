/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import java.sql.Date;

/**
 *
 * @author Maria
 */
public class ValoracioEntrenament {
    private String fkJugador;
    private int fkEntrenament;
    private Date dataEntrenament;
    private String condicioFisica;
    private String actitud;
    private Boolean puntualitat;
    private Boolean haVingut;
    private String comentari;

    public ValoracioEntrenament(String fkJugador, int fkEntrenament, Date dataEntrenament, String condicioFisica, String actitud, Boolean puntualitat, Boolean haVingut, String comentari) {
        this.fkJugador = fkJugador;
        this.fkEntrenament = fkEntrenament;
        this.dataEntrenament = dataEntrenament;
        this.condicioFisica = condicioFisica;
        this.actitud = actitud;
        this.puntualitat = puntualitat;
        this.haVingut = haVingut;
        this.comentari = comentari;
    }

    public String getFkJugador() {
        return fkJugador;
    }

    public void setFkJugador(String fkJugador) {
        this.fkJugador = fkJugador;
    }

    public int getFkEntrenament() {
        return fkEntrenament;
    }

    public void setFkEntrenament(int fkEntrenament) {
        this.fkEntrenament = fkEntrenament;
    }

    public Date getDataEntrenament() {
        return dataEntrenament;
    }

    public void setDataEntrenament(Date dataEntrenament) {
        this.dataEntrenament = dataEntrenament;
    }

    public String getCondicioFisica() {
        return condicioFisica;
    }

    public void setCondicioFisica(String condicioFisica) {
        this.condicioFisica = condicioFisica;
    }

    public String getActitud() {
        return actitud;
    }

    public void setActitud(String actitud) {
        this.actitud = actitud;
    }

    public Boolean getPuntualitat() {
        return puntualitat;
    }

    public void setPuntualitat(Boolean puntualitat) {
        this.puntualitat = puntualitat;
    }

    public Boolean getHaVingut() {
        return haVingut;
    }

    public void setHaVingut(Boolean haVingut) {
        this.haVingut = haVingut;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }
    
    
}
