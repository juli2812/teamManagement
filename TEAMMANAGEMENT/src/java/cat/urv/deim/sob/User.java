package cat.urv.deim.sob;

public class User {

    private String idUsuari;
    private String nomUsuari;
    
    public User(String idUsuari, String nomUsuari){
        this.idUsuari=idUsuari;
        this.nomUsuari=nomUsuari;
    }
    public String getNomUsuari() {
        return this.nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getIdUsuari() {
        return this.idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }
}
