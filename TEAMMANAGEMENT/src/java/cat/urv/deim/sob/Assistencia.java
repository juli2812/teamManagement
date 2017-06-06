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
public class Assistencia {
    private String jugador;
    private int partit;
    private int assistencia;

    public Assistencia(String jugador, int partit, int assistencia) {
        this.jugador = jugador;
        this.partit = partit;
        this.assistencia = assistencia;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getPartit() {
        return partit;
    }

    public void setPartit(int partit) {
        this.partit = partit;
    }

    public int getAssistencia() {
        return assistencia;
    }

    public void setAssistencia(int assistencia) {
        this.assistencia = assistencia;
    }
    
    
}
