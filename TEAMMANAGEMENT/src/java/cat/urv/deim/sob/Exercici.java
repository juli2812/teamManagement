/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

/**
 *
 * @author realm
 */
public class Exercici {
    private int idExercici;
    private int fkEntrenament;
    private String explicacio;
    private int tempsMin;
    private String material;
    private boolean fet;
    private String valoracio;

    public Exercici(int idExercici, int fkEntrenament, String explicacio, int tempsMin, String material, boolean fet, String valoracio) {
        this.idExercici = idExercici;
        this.fkEntrenament = fkEntrenament;
        this.explicacio = explicacio;
        this.tempsMin = tempsMin;
        this.material = material;
        this.fet = fet;
        this.valoracio = valoracio;
    }

    public int getIdExercici() {
        return idExercici;
    }

    public void setIdExercici(int idExercici) {
        this.idExercici = idExercici;
    }

    public int getFkEntrenament() {
        return fkEntrenament;
    }

    public void setFkEntrenament(int fkEntrenament) {
        this.fkEntrenament = fkEntrenament;
    }

    public String getExplicacio() {
        return explicacio;
    }

    public void setExplicacio(String explicacio) {
        this.explicacio = explicacio;
    }

    public int getTempsMin() {
        return tempsMin;
    }

    public void setTempsMin(int tempsMin) {
        this.tempsMin = tempsMin;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isFet() {
        return fet;
    }

    public void setFet(boolean fet) {
        this.fet = fet;
    }

    public String getValoracio() {
        return valoracio;
    }

    public void setValoracio(String valoracio) {
        this.valoracio = valoracio;
    }
    
    
    
}
