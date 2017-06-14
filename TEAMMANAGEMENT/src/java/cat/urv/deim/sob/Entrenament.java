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
public class Entrenament extends Activitat{
    private String data;

    public Entrenament(int idActivitat, Date dataHora, String valoracioGeneral) {
        super(idActivitat, dataHora, valoracioGeneral);
    }

    public Entrenament(String data, int idActivitat, Date dataHora, String valoracioGeneral) {
        super(idActivitat, dataHora, valoracioGeneral);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }    
    
    
}
