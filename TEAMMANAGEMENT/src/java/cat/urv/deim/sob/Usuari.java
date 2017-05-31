package cat.urv.deim.sob;

import java.sql.Date;

public class Usuari {

    private String NIF;
    private String nom;
    private String cognom;
    private String cognom2;
    private String address;
    private int telefon;
    private String idUsuari;
    private Date dataNaix;
    private Date dataIncorporacio;
    private String contrasenya;
    
    public Usuari(String nom, String cognom,String idUsuari){
        this.idUsuari=idUsuari;
        this.nom=nom;
        this.cognom=cognom;
    }

    public Usuari(String NIF, String nom, String cognom, String cognom2, String address, int telefon, String idUsuari, Date dataNaix, String contrasenya, Date dataIncorporacio) {
        this.NIF = NIF;
        this.nom = nom;
        this.cognom = cognom;
        this.cognom2 = cognom2;
        this.address = address;
        this.telefon = telefon;
        this.idUsuari = idUsuari;
        this.dataNaix = dataNaix;
        this.contrasenya = contrasenya;
        this.dataIncorporacio = dataIncorporacio;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
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

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(Date dataNaix) {
        this.dataNaix = dataNaix;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Date getDataIncorporacio() {
        return dataIncorporacio;
    }

    public void setDataIncorporacio(Date dataIncorporacio) {
        this.dataIncorporacio = dataIncorporacio;
    }

    public String getIdUsuari() {
        return this.idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }
}
