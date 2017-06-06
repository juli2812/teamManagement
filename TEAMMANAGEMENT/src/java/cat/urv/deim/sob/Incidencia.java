/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import java.sql.Date;

/**
 *
 * @author realm
 */
public class Incidencia {
    private String motiu;
    private Date dataIncid;
    private String tipusIncid;
    private int numPartSanc;
    private String fkJugador;
    private int fkActivitat;
    private int idIncidencia;

    public Incidencia(String motiu, Date dataIncid, String tipusIncid, int numPartSanc, String fkJugador, int fkActivitat, int idIncidencia) {
        this.motiu = motiu;
        this.dataIncid = dataIncid;
        this.tipusIncid = tipusIncid;
        this.numPartSanc = numPartSanc;
        this.fkJugador = fkJugador;
        this.fkActivitat = fkActivitat;
        this.idIncidencia = idIncidencia;
    }

    public String getMotiu() {
        return motiu;
    }

    public void setMotiu(String motiu) {
        this.motiu = motiu;
    }

    public Date getDataIncid() {
        return dataIncid;
    }

    public void setDataIncid(Date dataIncid) {
        this.dataIncid = dataIncid;
    }

    public String getTipusIncid() {
        return tipusIncid;
    }

    public void setTipusIncid(String tipusIncid) {
        this.tipusIncid = tipusIncid;
    }

    public int getNumPartSanc() {
        return numPartSanc;
    }

    public void setNumPartSanc(int numPartSanc) {
        this.numPartSanc = numPartSanc;
    }

    public String getFkJugador() {
        return fkJugador;
    }

    public void setFkJugador(String fkJugador) {
        this.fkJugador = fkJugador;
    }

    public int getFkActivitat() {
        return fkActivitat;
    }

    public void setFkActivitat(int fkActivitat) {
        this.fkActivitat = fkActivitat;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }
    
    
    
    
}
