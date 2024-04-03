package kozmetika.stanje;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * ZakazaniTretman
 */
public class ZakazaniTretman implements Icsvpersistable {


	public enum Stanje {
        ZAKAZAN,
        IZVRSEN,
        OTKAZAO_KLIJENT,
        OTKAZAO_SALON,
        NIJE_SE_POJAVIO
    }

	private String oznaka;
	private String oznakaTretmana;
	private Calendar termin = null;
	private String kozmeticar;
	private String klijent;
	private Double cena;
	private Stanje stanje;

    public Stanje getStanje() {
		return stanje;
	}

    public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}
    
	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String getKlijent() {
		return klijent;
	}

	public void setKlijent(String klijent) {
		this.klijent = klijent;
	}

	public String getKozmeticar() {
		return kozmeticar;
	}

	public void setKozmeticar(String kozmeticar) {
		this.kozmeticar = kozmeticar;
	}

	public Calendar getTermin() {
		return termin;
	}

	public void setTermin(Calendar termin) {
		this.termin = termin;
	}

	public String getOznakaTretmana() {
		return oznakaTretmana;
	}

	public void setOznakaTretmana(String tretman) {
		this.oznakaTretmana = tretman;
	}

	public String tocsv() {
	    return this.oznaka + "," + 
		this.oznakaTretmana + "," +
		termin.getTimeInMillis() + "," +  
		kozmeticar + "," +
		klijent  + "," + 
		cena + "," + 
		stanje; 
	}

	public Icsvpersistable fromcsv(String csvString) {
		ZakazaniTretman zt = new ZakazaniTretman();
		String[] linija = csvString.split(",");
		termin = GregorianCalendar.getInstance();
		termin.setTimeInMillis(Long.parseLong(linija[2]));
		zt.setOznaka(linija[0]);
		zt.setOznakaTretmana(linija[1]);
		zt.setTermin(termin);
		zt.setKozmeticar(linija[3]);
		zt.setKlijent(linija[4]);
		zt.setCena(Double.parseDouble(linija[5]));
		zt.setStanje(Stanje.valueOf(linija[6]));
		return zt;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
}
