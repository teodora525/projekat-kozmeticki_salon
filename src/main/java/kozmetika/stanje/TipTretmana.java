package kozmetika.stanje;

/**
 * TipTretmana
 */
public class TipTretmana implements Icsvpersistable {

    private String oznaka;
    private String naziv;
    
    
    public TipTretmana(String oznaka, String naziv) {
    	this.naziv = naziv;
    	this.oznaka = oznaka;
    }
    
    public TipTretmana() {
    }
    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


	public String tocsv() {
        return this.oznaka + "," + this.naziv;
	}

    public Icsvpersistable fromcsv(String csvString) {
        TipTretmana tipTretmana = new TipTretmana();

        String[] linija = csvString.split(",");
        tipTretmana.setOznaka(linija[0]);
        tipTretmana.setNaziv(linija[1]);
        
        return tipTretmana;
	}
}
