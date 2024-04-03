package kozmetika.stanje;

import java.time.Duration;

/**
 * Tretman
 */
public class Tretman implements Icsvpersistable {

    public Tretman(String oznaka, String naziv, String oznakaTipaTretmana, Duration trajanje, Double cena) {
		super();
		this.oznaka = oznaka;
		this.naziv = naziv;
		this.oznakaTipaTretmana = oznakaTipaTretmana;
		this.trajanje = trajanje;
		this.cena = cena;
	}
    
    public Tretman() {
    	
    }

	private String oznaka;
    private String naziv;
    private String oznakaTipaTretmana;
    private Duration trajanje;
    private Double cena;
    
	

    public String getOznaka() {
        return this.oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }
    
    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Duration getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Duration trajanje) {
        this.trajanje = trajanje;
    }

    public String getOznakaTipaTretmana() {
        return oznakaTipaTretmana;
    }

    public void setOznakaTipaTretmana(String oznakaTipaTretmana) {
        this.oznakaTipaTretmana = oznakaTipaTretmana;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String tocsv() {
        return this.oznaka + "," + this.naziv + "," + this.oznakaTipaTretmana + "," + this.trajanje + "," + this.cena;
    }

    public Icsvpersistable fromcsv(String csvString) {
        Tretman tretman = new Tretman();

        String[] linija = csvString.split(",");

        tretman.setOznaka(linija[0]);
        tretman.setNaziv(linija[1]);
        tretman.setOznakaTipaTretmana(linija[2]);
        tretman.setTrajanje(Duration.parse(linija[3]));
        tretman.setCena(Double.parseDouble(linija[4]));
        
        return tretman;
    }
}
