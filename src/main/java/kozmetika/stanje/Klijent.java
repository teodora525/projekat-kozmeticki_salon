package kozmetika.stanje;

public class Klijent extends Korisnik implements Icsvpersistable {

	private Double karticaLojalnostiStanje;

    public Klijent() {
    }

    public Klijent(String korisnickoIme, Uloga uloga, String lozinka, String ime, String prezime, Pol pol,
            String telefon, String adresa, Double karticaLojalnostiStanje) {
        super(korisnickoIme, uloga, lozinka, ime, prezime, pol, telefon, adresa);
        this.karticaLojalnostiStanje = karticaLojalnostiStanje;
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
                this.getKarticaLojalnostiStanje();
    }

    public Icsvpersistable fromcsv(String csvString) {

        Klijent k = new Klijent();

        String[] linija = csvString.split(",");
        k.setIme(linija[0]);
        k.setPrezime(linija[1]);
        k.setPol(Korisnik.Pol.valueOf(linija[2]));
        k.setTelefon(linija[3]);
        k.setAdresa(linija[4]);
        k.setKorisnickoIme(linija[5]);
        k.setLozinka(linija[6]);
        k.setUloga(Korisnik.Uloga.valueOf(linija[7]));
        k.setKarticaLojalnostiStanje(Double.parseDouble(linija[8]));
        return k;
    }

	public Double getKarticaLojalnostiStanje() {
		return karticaLojalnostiStanje;
	}

	public void setKarticaLojalnostiStanje(Double karticaLojalnostiStanje) {
		this.karticaLojalnostiStanje = karticaLojalnostiStanje;
	}

}

