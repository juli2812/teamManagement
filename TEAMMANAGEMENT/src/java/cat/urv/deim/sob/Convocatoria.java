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
public class Convocatoria {
    private String nom;
    private String cognom;
    private String fkJugador;
    private int fkPartit;
    private String fkPartit2;
    private Date dataPartit;
    private int numConf;
    private int numNoConf;
    private String llocPartit;
    private Date dataLimit;
    private int minJug;
    private int maxJug;
    private boolean confirmat;
    private boolean haVingut;

    public Convocatoria(String fkJugador, int fkPartit, Date dataPartit, int numConf, int numNoConf, String llocPartit, Date dataLimit, int minJug, int maxJug, boolean confirmat, boolean haVingut) {
        this.fkJugador = fkJugador;
        this.fkPartit = fkPartit;
        this.dataPartit = dataPartit;
        this.numConf = numConf;
        this.numNoConf = numNoConf;
        this.llocPartit = llocPartit;
        this.dataLimit = dataLimit;
        this.minJug = minJug;
        this.maxJug = maxJug;
        this.confirmat = confirmat;
        this.haVingut = haVingut;
    }

    public Convocatoria(String nom, String cognom, String fkJugador, int fkPartit, Date dataPartit, int numConf, int numNoConf, String llocPartit, Date dataLimit, int minJug, int maxJug, boolean confirmat, boolean haVingut) {
        this.fkJugador = fkJugador;
        this.nom = nom;
        this.cognom = cognom;
        this.fkPartit = fkPartit;
        this.dataPartit = dataPartit;
        this.numConf = numConf;
        this.numNoConf = numNoConf;
        this.llocPartit = llocPartit;
        this.dataLimit = dataLimit;
        this.minJug = minJug;
        this.maxJug = maxJug;
        this.confirmat = confirmat;
        this.haVingut = haVingut;
    }

    public Convocatoria(String fkJugador, String fkPartit, Date dataPartit, int numConf, int numNoConf, String llocPartit, Date dataLimit, int minJug, int maxJug, boolean confirmat, boolean haVingut) {
        this.fkJugador = fkJugador;
        this.fkPartit2 = fkPartit;
        this.dataPartit = dataPartit;
        this.numConf = numConf;
        this.numNoConf = numNoConf;
        this.llocPartit = llocPartit;
        this.dataLimit = dataLimit;
        this.minJug = minJug;
        this.maxJug = maxJug;
        this.confirmat = confirmat;
        this.haVingut = haVingut;
    }

    public Convocatoria(String fkJugador, String fkPartit) {
        this.fkJugador = fkJugador;
        this.fkPartit2 = fkPartit;
    }
    
    public Convocatoria(){
    }

    public String getFkPartit2() {
        return fkPartit2;
    }

    public void setFkPartit2(String fkPartit2) {
        this.fkPartit2 = fkPartit2;
    }
    

    public String getFkJugador() {
        return fkJugador;
    }

    public void setFkJugador(String fkJugador) {
        this.fkJugador = fkJugador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getFkPartit() {
        return fkPartit;
    }

    public void setFkPartit(int fkPartit) {
        this.fkPartit = fkPartit;
    }
    public Date getDataPartit() {
        return dataPartit;
    }

    public void setDataPartit(Date dataPartit) {
        this.dataPartit = dataPartit;
    }

    public int getNumConf() {
        return numConf;
    }

    public void setNumConf(int numConf) {
        this.numConf = numConf;
    }

    public int getNumNoConf() {
        return numNoConf;
    }

    public void setNumNoConf(int numNoConf) {
        this.numNoConf = numNoConf;
    }

    public String getLlocPartit() {
        return llocPartit;
    }

    public void setLlocPartit(String llocPartit) {
        this.llocPartit = llocPartit;
    }

    public Date getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(Date dataLimit) {
        this.dataLimit = dataLimit;
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
    public boolean isConfirmat() {
        return confirmat;
    }

    public void setConfirmat(boolean confirmat) {
        this.confirmat = confirmat;
    }

    public boolean isHaVingut() {
        return haVingut;
    }

    public void setHaVingut(boolean haVingut) {
        this.haVingut = haVingut;
    }
    
    
}
