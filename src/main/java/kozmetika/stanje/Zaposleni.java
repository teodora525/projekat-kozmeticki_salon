
package kozmetika.stanje;

public class Zaposleni extends Korisnik implements Icsvpersistable {

	public enum StrucnaSprema {
		SSS, VSS, VS
	}

	private StrucnaSprema strucnaSprema;
    private Double staz;
	private Double osnova;
	
    public Double getOsnova() {
        return osnova;
    }

    public void setOsnova(Double osnova) {
        this.osnova = osnova;
    }

    public Double getStaz() {
		return staz;
	}

	public void setStaz(Double staz) {
		this.staz = staz;
	}

	public StrucnaSprema getStrucnaSprema() {
		return strucnaSprema;
	}

	public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
		this.strucnaSprema = strucnaSprema;
	}
    public String tocsv() {
        return this.getIme() + "," +
                this.getPrezime() + "," +
                this.getPol() + "," +
                this.getTelefon() + "," +
                this.getAdresa() + "," +
                this.getKorisnickoIme() + "," +
                this.getLozinka() + "," +
                this.getUloga() + "," +
                this.getStrucnaSprema() + "," +
                this.getStaz()+ "," +
                this.getOsnova();
    }

    public Icsvpersistable fromcsv(String csvString) {

        Zaposleni zaposleni = new Zaposleni();

        String[] linija = csvString.split(",");
        zaposleni.setIme(linija[0]);
        zaposleni.setPrezime(linija[1]);
        zaposleni.setPol(Korisnik.Pol.valueOf(linija[2]));
        zaposleni.setTelefon(linija[3]);
        zaposleni.setAdresa(linija[4]);
        zaposleni.setKorisnickoIme(linija[5]);
        zaposleni.setLozinka(linija[6]);
        zaposleni.setUloga(Korisnik.Uloga.valueOf(linija[7]));
        zaposleni.setStrucnaSprema(StrucnaSprema.valueOf(linija[8]));
        zaposleni.setStaz(Double.parseDouble( linija[9]));
        zaposleni.setOsnova(Double.parseDouble( linija[10]));
        
        return zaposleni;
    }

    public Zaposleni() {
        super();
    }

    public Zaposleni(String korisnickoIme, Uloga uloga, String lozinka,
                     String ime, String prezime, Pol pol, String telefon,
                     String adresa,StrucnaSprema strucnaSprema, Double staz, Double osnova) {
        this.setKorisnickoIme(korisnickoIme);
        this.setUloga(uloga);
        this.setLozinka(lozinka);
        this.setIme(ime);
        this.setPrezime(prezime);
        this.setPol(pol);
        this.setTelefon(telefon);
        this.setAdresa(adresa);
        this.setStrucnaSprema(strucnaSprema);
        this.setStaz(staz);
        this.setOsnova(osnova);
    }
}
