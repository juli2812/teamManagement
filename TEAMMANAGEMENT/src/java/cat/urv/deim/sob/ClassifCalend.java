/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

/**
 *
 * @author Maria
 */
public class ClassifCalend {
    private String classificacio;
    private String calendari;

    public ClassifCalend(String classificacio, String calendari) {
        this.classificacio = classificacio;
        this.calendari = calendari;
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
    
    
}
