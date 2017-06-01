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
public class Activitat {
    private int idActivitat;
    private Date dataHora;
    private String valoracioGeneral;

    public Activitat(int idActivitat, Date dataHora, String valoracioGeneral) {
        this.idActivitat = idActivitat;
        this.dataHora = dataHora;
        this.valoracioGeneral = valoracioGeneral;
    }

    public int getIdActivitat() {
        return idActivitat;
    }

    public void setIdActivitat(int idActivitat) {
        this.idActivitat = idActivitat;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getValoracioGeneral() {
        return valoracioGeneral;
    }

    public void setValoracioGeneral(String valoracioGeneral) {
        this.valoracioGeneral = valoracioGeneral;
    }
    
}
