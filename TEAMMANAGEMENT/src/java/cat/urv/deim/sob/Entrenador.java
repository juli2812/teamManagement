package cat.urv.deim.sob;

import java.sql.Date;

public class Entrenador extends Usuari{
    
    private String compteBancari;
    private String certificat;
    private String nivellEntrenador;
    private int tipusModalitatActual;
    private String funcioEntrenador;
    private String fotocopiaDNI;
    private String foto;
    private String numSalut;
    private String reconeixementMedic;
    private boolean totEntregat;

    public Entrenador(String compteBancari, String nivellEntrenador, int tipusModalitatActual, String certificat, String funcioEntrenador, String fotocopiaDNI, String foto, String numSalut, String reconeixementMedic, boolean totEntregat, String NIF, String nom, String cognom, String cognom2, String address, int telefon, String idUsuari, Date dataNaix, String contrasenya, Date dataIncorporacio) {
        super(NIF, nom, cognom, cognom2, address, telefon, idUsuari, dataNaix, contrasenya, dataIncorporacio);
        this.compteBancari = compteBancari;
        this.certificat = certificat;
        this.nivellEntrenador = nivellEntrenador;
        this.tipusModalitatActual = tipusModalitatActual;
        this.funcioEntrenador = funcioEntrenador;
        this.fotocopiaDNI = fotocopiaDNI;
        this.foto = foto;
        this.numSalut = numSalut;
        this.reconeixementMedic = reconeixementMedic;
        this.totEntregat = totEntregat;
    }

    public String getCertificat() {
        return certificat;
    }

    public void setCertificat(String certificat) {
        this.certificat = certificat;
    }

    public Entrenador(String nom, String cognom, String idUsuari) {
        super(nom, cognom, idUsuari);
    }

    public String getCompteBancari() {
        return compteBancari;
    }

    public void setCompteBancari(String compteBancari) {
        this.compteBancari = compteBancari;
    }

    public String getNivellEntrenador() {
        return nivellEntrenador;
    }

    public void setNivellEntrenador(String nivellEntrenador) {
        this.nivellEntrenador = nivellEntrenador;
    }

    public int getTipusModalitatActual() {
        return tipusModalitatActual;
    }

    public void setTipusModalitatActual(int tipusModalitatActual) {
        this.tipusModalitatActual = tipusModalitatActual;
    }

    public String getFuncioEntrenador() {
        return funcioEntrenador;
    }

    public void setFuncioEntrenador(String funcioEntrenador) {
        this.funcioEntrenador = funcioEntrenador;
    }

    public String getFotocopiaDNI() {
        return fotocopiaDNI;
    }

    public void setFotocopiaDNI(String fotocopiaDNI) {
        this.fotocopiaDNI = fotocopiaDNI;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumSalut() {
        return numSalut;
    }

    public void setNumSalut(String numSalut) {
        this.numSalut = numSalut;
    }

    public String getReconeixementMedic() {
        return reconeixementMedic;
    }

    public void setReconeixementMedic(String reconeixementMedic) {
        this.reconeixementMedic = reconeixementMedic;
    }

    public boolean isTotEntregat() {
        return totEntregat;
    }

    public void setTotEntregat(boolean totEntregat) {
        this.totEntregat = totEntregat;
    }
    
}
