package cat.urv.deim.sob;

import java.sql.Date;

public class Jugador extends Usuari{

    private String cursEscolar;
    private String escola;
    private String nomCompletPare;
    private String nomCompletMare;
    private boolean compteTutoritzat;
    private String compteBancari;
    private int dorsal;
    private String fotocopiaDNI;
    private String foto;
    private String numSalut;
    private String reconeixementMedic;
    private boolean totEntregat;
    private boolean lesionat;

    public Jugador(String nom, String cognom, String idUsuari) {
        super(nom, cognom, idUsuari);
    }

    public Jugador(String cursEscolar, String escola, String nomCompletPare, String nomCompletMare, boolean compteTutoritzat, String compteBancari, int dorsal, String fotocopiaDNI, String foto, String numSalut, String reconeixementMedic, boolean totEntregat, boolean lesionat, String NIF, String nom, String cognom, String cognom2, String address, int telefon, String idUsuari, Date dataNaix, String contrasenya, Date dataIncorporacio) {
        super(NIF, nom, cognom, cognom2, address, telefon, idUsuari, dataNaix, contrasenya, dataIncorporacio);
        this.cursEscolar = cursEscolar;
        this.escola = escola;
        this.nomCompletPare = nomCompletPare;
        this.nomCompletMare = nomCompletMare;
        this.compteTutoritzat = compteTutoritzat;
        this.compteBancari = compteBancari;
        this.dorsal = dorsal;
        this.fotocopiaDNI = fotocopiaDNI;
        this.foto = foto;
        this.numSalut = numSalut;
        this.reconeixementMedic = reconeixementMedic;
        this.totEntregat = totEntregat;
        this.lesionat = lesionat;
    }    

    public String getCursEscolar() {
        return cursEscolar;
    }

    public void setCursEscolar(String cursEscolar) {
        this.cursEscolar = cursEscolar;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getNomCompletPare() {
        return nomCompletPare;
    }

    public void setNomCompletPare(String nomCompletPare) {
        this.nomCompletPare = nomCompletPare;
    }

    public String getNomCompletMare() {
        return nomCompletMare;
    }

    public void setNomCompletMare(String nomCompletMare) {
        this.nomCompletMare = nomCompletMare;
    }

    public boolean isCompteTutoritzat() {
        return compteTutoritzat;
    }

    public void setCompteTutoritzat(boolean compteTutoritzat) {
        this.compteTutoritzat = compteTutoritzat;
    }

    public String getCompteBancari() {
        return compteBancari;
    }

    public void setCompteBancari(String compteBancari) {
        this.compteBancari = compteBancari;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
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

    public boolean isLesionat() {
        return lesionat;
    }

    public void setLesionat(boolean lesionat) {
        this.lesionat = lesionat;
    }
    
}
