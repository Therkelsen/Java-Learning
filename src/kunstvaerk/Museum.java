
package src.kunstvaerk;

public class Museum {

    private String navn;
    private String adresse;

    public Museum() {
    }

    public Museum(String navn, String adresse) {
        this.navn = navn;
        this.adresse = adresse;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }
    
}
