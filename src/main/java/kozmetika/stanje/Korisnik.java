
package kozmetika.stanje;

/**
 * IKorisnik
 */
public class Korisnik {

	public Korisnik(){

	}

	
	public enum Uloga {
		MENADZER,
		KOZMETICAR,
		RECEPCIONER,
		KLIJENT
	}

	public enum Pol {
		m, z
	}

	private String korisnickoIme;
	private Uloga uloga;
	private String lozinka;
	private String ime;
	private String prezime;
	private Pol pol;
	private String telefon;
	private String adresa;

	public Korisnik(String korisnickoIme, Uloga uloga, String lozinka, String ime, String prezime, Pol pol,
			String telefon, String adresa) {
		this.korisnickoIme = korisnickoIme;
		this.uloga = uloga;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.telefon = telefon;
		this.adresa = adresa;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public Pol getPol() {
		return this.pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

}
