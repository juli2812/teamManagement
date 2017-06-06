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
public class Quota {
    private int fk_club;
    private String fk_jugador;
    private Date dataLimit;
    private boolean pagada;
    private int quantitat;

    public Quota(int fk_club, String fk_jugador, Date dataLimit, boolean pagada, int quantitat) {
        this.fk_club = fk_club;
        this.fk_jugador = fk_jugador;
        this.dataLimit = dataLimit;
        this.pagada = pagada;
        this.quantitat = quantitat;
    }

    public int getFk_club() {
        return fk_club;
    }

    public void setFk_club(int fk_club) {
        this.fk_club = fk_club;
    }

    public String getFk_jugador() {
        return fk_jugador;
    }

    public void setFk_jugador(String fk_jugador) {
        this.fk_jugador = fk_jugador;
    }

    public Date getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(Date dataLimit) {
        this.dataLimit = dataLimit;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    
}
