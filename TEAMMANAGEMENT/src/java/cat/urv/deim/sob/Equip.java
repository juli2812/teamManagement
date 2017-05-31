package cat.urv.deim.sob;

import java.sql.Date;

public class Equip {

    private String nomEquip;
    private String categoria;
    private String classificacio;
    private String calendari;

    public Equip(String nomEquip, String categoria, String classificacio, String calendari) {
        this.nomEquip = nomEquip;
        this.categoria = categoria;
        this.classificacio = classificacio;
        this.calendari = calendari;
    }

    public String getNomEquip() {
        return nomEquip;
    }

    public void setNomEquip(String nomEquip) {
        this.nomEquip = nomEquip;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClassificacio() {
        return classificacio;
    }

    public void setClassificacio(String classificacio) {
        this.classificacio = classificacio;
    }

    public String getCalendari() {
        return calendari;
    }

    public void setCalendari(String calendari) {
        this.calendari = calendari;
    }

    @Override
    public String toString() {
        return "Equip{" + "nomEquip=" + nomEquip + ", categoria=" + categoria + ", classificacio=" + classificacio + ", calendari=" + calendari + '}';
    }
   
}
