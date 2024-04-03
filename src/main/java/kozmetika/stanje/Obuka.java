package kozmetika.stanje;

/**
 * Obuka
 */
public class Obuka implements Icsvpersistable {

    private String zaposleni;
    private String tipTretmana;

    public String getZaposleni() {
        return this.zaposleni;
    }

    public void setZaposleni(String zaposleni) {
        this.zaposleni = zaposleni;
    }

    public String getTipTretmana() {
        return this.tipTretmana;
    }

    public void setTipTretmana(String tipTretmana) {
        this.tipTretmana = tipTretmana;
    }
    
    public String tocsv() {
		return this.zaposleni + "," + this.tipTretmana;
	}

	public Icsvpersistable fromcsv(String csvString) {
		Obuka obuka = new Obuka();

        String[] linija = csvString.split(",");
        obuka.setZaposleni(linija[0]);
        obuka.setTipTretmana(linija[1]);
        
        return obuka;
	}
    
}
