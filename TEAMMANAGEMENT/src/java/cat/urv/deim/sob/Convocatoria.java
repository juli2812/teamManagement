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
public class Convocatoria {
    
    private String jugador;
    private String partit;
    private Date dataPartit;
    private int numJugsConf;
    private int numJugsNoConf;
    private String llocPartit;
    private Date dataLimitConf;
    private int minJug;
    private int maxJug;
    private boolean confirmacio, haVingut;
    
    
    public Convocatoria() {
    }
    
    public Date getDataPartit() {
        return dataPartit;
    }

    public void setDataPartit(Date dataPartit) {
        this.dataPartit = dataPartit;
    }

    public int getNumJugsConf() {
        return numJugsConf;
    }

    public void setNumJugsConf(int numJugsConf) {
        this.numJugsConf = numJugsConf;
    }

    public int getNumJugsNoConf() {
        return numJugsNoConf;
    }

    public void setNumJugsNoConf(int numJugsNoConf) {
        this.numJugsNoConf = numJugsNoConf;
    }

    public String getLlocPartit() {
        return llocPartit;
    }

    public void setLlocPartit(String llocPartit) {
        this.llocPartit = llocPartit;
    }

    public Date getDataLimitConf() {
        return dataLimitConf;
    }

    public void setDataLimitConf(Date dataLimitConf) {
        this.dataLimitConf = dataLimitConf;
    }

    public int getMinJug() {
        return minJug;
    }

    public void setMinJug(int minJug) {
        this.minJug = minJug;
    }

    public int getMaxJug() {
        return maxJug;
    }

    public void setMaxJug(int maxJug) {
        this.maxJug = maxJug;
    }

    public boolean isConfirmacio() {
        return confirmacio;
    }

    public void setConfirmacio(boolean confirmacio) {
        this.confirmacio = confirmacio;
    }

    public boolean isHaVingut() {
        return haVingut;
    }

    public void setHaVingut(boolean haVingut) {
        this.haVingut = haVingut;
    }

    public Convocatoria(String jugador, String partit, Date dataPartit, int numJugsConf, int numJugsNoConf, String llocPartit, Date dataLimitConf, int minJug, int maxJug, boolean confirmacio, boolean haVingut) {
        this.jugador = jugador;
        this.partit = partit;
        this.dataPartit = dataPartit;
        this.numJugsConf = numJugsConf;
        this.numJugsNoConf = numJugsNoConf;
        this.llocPartit = llocPartit;
        this.dataLimitConf = dataLimitConf;
        this.minJug = minJug;
        this.maxJug = maxJug;
        this.confirmacio = confirmacio;
        this.haVingut = haVingut;
    }

    public Convocatoria(String jugador, String partit) {
        this.jugador = jugador;
        this.partit = partit;
    }
    
    

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getPartit() {
        return partit;
    }

    public void setPartit(String partit) {
        this.partit = partit;
    }
    
    
}
