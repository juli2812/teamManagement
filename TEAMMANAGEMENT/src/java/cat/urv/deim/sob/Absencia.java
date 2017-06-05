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
public class Absencia {
    private String fk_jugador;
    private String motiu;
    private Date dia;
    private Boolean justificada;

    public Absencia(String fk_jugador, Date dia) {
        this.fk_jugador = fk_jugador;
        this.dia = dia;
    }
    
    public Absencia(String fk_jugador, String motiu, Date dia, Boolean justificada) {
        this.fk_jugador = fk_jugador;
        this.motiu = motiu;
        this.dia = dia;
        this.justificada = justificada;
    }

    public String getFk_jugador() {
        return fk_jugador;
    }

    public void setFk_jugador(String fk_jugador) {
        this.fk_jugador = fk_jugador;
    }

    public String getMotiu() {
        return motiu;
    }

    public void setMotiu(String motiu) {
        this.motiu = motiu;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Boolean getJustificada() {
        return justificada;
    }

    public void setJustificada(Boolean justificada) {
        this.justificada = justificada;
    }
    
    
}
