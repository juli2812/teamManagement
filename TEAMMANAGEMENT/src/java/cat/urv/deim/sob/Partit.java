/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import java.sql.Date;


/**
 *
 * @author BEC.CA2
 */
public class Partit extends Activitat{
    private String rival;

    public Partit(String rival, int idActivitat, Date dataHora, String valoracioGeneral) {
        super(idActivitat, dataHora, valoracioGeneral);
        this.rival = rival;
    }

    @Override
    public String toString() {
        return "Partit{" + "rival=" + rival + '}';
    }

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }
    
}
