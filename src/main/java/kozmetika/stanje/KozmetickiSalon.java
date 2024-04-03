package kozmetika.stanje;

import java.time.Duration;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import kozmetika.stanje.ZakazaniTretman.Stanje;
import kozmetika.stanje.Zaposleni.StrucnaSprema;

public class KozmetickiSalon implements Icsvpersistable {

    public KozmetickiSalon() {
    	
    	this.salonSmestajPodataka = new SmestajPodataka("salon.conf");
    	
    	ArrayList<DayOfWeek> RD = new ArrayList<DayOfWeek>();
    	int rvOdH = 0;
    	int rvOdM = 0;
    	int rvDoH = 0;
    	int rvDoM = 0;
    	
    	
    	for (String linija: this.salonSmestajPodataka.vratiPodatke()) {
    		String[] cnf = linija.split(":");
    		
    		switch (cnf[0]) {
			case "ImeSalona":
				this.setNaziv(cnf[1]);
				break;
			case "RadniDani":
				String[] rd = cnf[1].split(",");
				for(int i = 0; i < rd.length; i++) {
					DayOfWeek dw = DayOfWeek.valueOf(rd[i]);
					if (dw != null) RD.add(dw);
				}
				
				this.setRadniDani(RD);
				
				break;
			case "RadnoVremeOd":
				String[] rvod = cnf[1].split(",");
				rvOdH = Integer.parseInt(rvod[0]);
				rvOdM = Integer.parseInt(rvod[1]);
				break;
			case "RadnoVremeDo":
				String[] rvdo = cnf[1].split(",");
				rvDoH = Integer.parseInt(rvdo[0]);
				rvDoM = Integer.parseInt(rvdo[1]);
				break;
			case "LimitKarticeLojalnosti":
				this.setLimitKarticeLojalnosti(Double.parseDouble( cnf[1] ));
				break;
			case "LimitBonus":
				this.setBonusLimit(Double.parseDouble( cnf[1] ));
				break;
			default:
				break;
			}
    		
    	}
    	
    	this.setRadnoVreme(rvOdH, rvOdM, rvDoH, rvDoM);
    	
        Icsvpersistable z = new Zaposleni();
        this.zaposleniSmestajPodataka = new SmestajPodataka("zaposleni.csv");
        this.setZaposleni(new ArrayList<Zaposleni>());
        for (String zaposleniCSV : this.zaposleniSmestajPodataka.vratiPodatke()) {
            this.getZaposleni().add((Zaposleni) z.fromcsv(zaposleniCSV));
        }

        z = new Klijent();
        this.klijentiSmestajPodataka = new SmestajPodataka("klijenti.csv");
        this.setKlijenti(new ArrayList<Klijent>());
        for (String klijentCSV : this.klijentiSmestajPodataka.vratiPodatke()) {
            this.getKlijenti().add((Klijent) z.fromcsv(klijentCSV));
        }

        z = new TipTretmana();
        this.tipoviTretmanaSmestajPodataka = new SmestajPodataka("tipovi_tretmana.csv");
        this.setTipoviTretmana(new ArrayList<TipTretmana>());
        for (String tipTretmanaCSV : this.tipoviTretmanaSmestajPodataka.vratiPodatke()) {
            this.getTipoviTretmana().add((TipTretmana) z.fromcsv(tipTretmanaCSV));
        }

        z = new Tretman();
        this.tretmaniSmestajPodataka = new SmestajPodataka("tretmani.csv");
        this.setTretmani(new ArrayList<Tretman>());
        for (String tretmanCSV : this.tretmaniSmestajPodataka.vratiPodatke()) {
            this.getTretmani().add((Tretman) z.fromcsv(tretmanCSV));
        }

        z = new ZakazaniTretman();
        this.zakazaniTretmaniSmestajPodataka = new SmestajPodataka("zakazani_tretmani.csv");
        this.setZakazaniTretmani(new ArrayList<ZakazaniTretman>());
        for (String zakazaniTretmanCSV : this.zakazaniTretmaniSmestajPodataka.vratiPodatke()) {
            this.getZakazaniTretmani().add((ZakazaniTretman) z.fromcsv(zakazaniTretmanCSV));
        }

        z = new Obuka();
        this.obukeSmestajPodataka = new SmestajPodataka("obuke.csv");
        this.setObuke(new ArrayList<Obuka>());
        for (String obukaCSV : this.obukeSmestajPodataka.vratiPodatke()) {
            this.getObuke().add((Obuka) z.fromcsv(obukaCSV));
        }

        this.koeficijentStrucnaSprema = new HashMap<>(3);
        this.koeficijentStrucnaSprema.put(StrucnaSprema.SSS, 0.0);
        this.koeficijentStrucnaSprema.put(StrucnaSprema.VSS, 0.0);
        this.koeficijentStrucnaSprema.put(StrucnaSprema.VS, 0.0);

 
    }
    public class RadnoVreme {

        public ArrayList<DayOfWeek> radniDani = null;

        public int satOtvaranja;
        public int minutOtvaranja;
        public int satZatvaranja;
        public int minutZatvaranja;
    }

    public void setRadnoVreme(int satOtvaranja, int minutOtvaranja, int satZatvaranja, int minutZatvaranja) {

        this.radnoVreme.satOtvaranja = satOtvaranja;
        this.radnoVreme.minutOtvaranja = minutOtvaranja;
        this.radnoVreme.satZatvaranja = satZatvaranja;
        this.radnoVreme.minutZatvaranja = minutZatvaranja;
    }

    public RadnoVreme getRadnoVreme() {
        return this.radnoVreme;
    }

    public void setRadniDani(ArrayList<DayOfWeek> radniDani) {
        this.radnoVreme.radniDani = radniDani;
    }

    public ArrayList<DayOfWeek> getRadniDani() {
        return this.radnoVreme.radniDani;
    }

    private ArrayList<Zaposleni> zaposleni = null;
    private ArrayList<Klijent> klijenti = null;
    private ArrayList<TipTretmana> tipoviTretmana = null;
    private ArrayList<Tretman> tretmani = null;
    private ArrayList<ZakazaniTretman> zakazaniTretmani = null;
    private ArrayList<Obuka> obuke = null;

    private RadnoVreme radnoVreme = new RadnoVreme();

    private String naziv = "";

    private SmestajPodataka salonSmestajPodataka = null;
    private SmestajPodataka zaposleniSmestajPodataka = null;
    private SmestajPodataka klijentiSmestajPodataka = null;
    private SmestajPodataka tipoviTretmanaSmestajPodataka = null;
    private SmestajPodataka tretmaniSmestajPodataka = null;
    private SmestajPodataka zakazaniTretmaniSmestajPodataka = null;
    private SmestajPodataka obukeSmestajPodataka = null;

    private double bilans;
    private HashMap<Zaposleni.StrucnaSprema, Double> koeficijentStrucnaSprema;
    private Double bonusLimit;

    public Double getBonusLimit() {
        return bonusLimit;
    }

    public void setBonusLimit(Double bonusLimit) {
        this.bonusLimit = bonusLimit;
    }

    public void updateKoeficijentStrucnaSprema(Zaposleni.StrucnaSprema zaposleniStrucnaSprema, Double vrednostKoeficijentaSS){
        koeficijentStrucnaSprema.replace(zaposleniStrucnaSprema, vrednostKoeficijentaSS);
    }
    
    public Double getKoeficijentStrucneSpreme(Zaposleni.StrucnaSprema zaposleniStrucnaSprema){
        return this.koeficijentStrucnaSprema.get(zaposleniStrucnaSprema);
    }

    public void setKoeficijentRadniStaz(double koeficijentRadniStaz) {
        this.koeficijentRadniStaz = koeficijentRadniStaz;
    }

    private double koeficijentRadniStaz;

    public double getKoeficijentRadniStaz() {
        return koeficijentRadniStaz;
    }

    public double getBilans() {
        return bilans;
    }

    public void setBilans(double bilans) {
        this.bilans = bilans;
    }
    
    public void zapamtiSalon() {
    	ArrayList<String> salonPodaci = new ArrayList<String>();
    	salonPodaci.add("ImeSalona:" + this.getNaziv());
    	
    	String rd = "";
    	for(DayOfWeek dw: this.getRadniDani()) {
    		rd += dw.toString() + ",";
    	}
    	salonPodaci.add("RadniDani:" + rd.substring(0, rd.length() - 1) );
    	
    	salonPodaci.add("RadnoVremeOd:" + this.radnoVreme.satOtvaranja + "," + this.radnoVreme.minutOtvaranja);
    	salonPodaci.add("RadnoVremeDo:" + this.radnoVreme.satZatvaranja + "," + this.radnoVreme.minutZatvaranja);
    	
    	salonPodaci.add("LimitKarticeLojalnosti:" + this.getLimitKarticeLojalnosti());
    	salonPodaci.add("LimitBonus:" + this.getBonusLimit());
    	
    	this.salonSmestajPodataka.zapamtiPodatke(salonPodaci);
	}

    public void zapamtiZaposlene() {
        ArrayList<String> zcsv = new ArrayList<String>();
        for (Zaposleni zap : this.getZaposleni())
            zcsv.add(zap.tocsv());
        this.zaposleniSmestajPodataka.zapamtiPodatke(zcsv);
    }

    public void zapamtiKlijente() {
        ArrayList<String> kcsv = new ArrayList<String>();
        for (Klijent kli : this.getKlijenti())
            kcsv.add(kli.tocsv());
        this.klijentiSmestajPodataka.zapamtiPodatke(kcsv);
    }

    public void zapamtiTipoveTretmana() {
        ArrayList<String> ttcsv = new ArrayList<String>();
        for (TipTretmana ttr : this.getTipoviTretmana())
            ttcsv.add(ttr.tocsv());
        this.tipoviTretmanaSmestajPodataka.zapamtiPodatke(ttcsv);
    }

    public void zapamtiTretmane() {
        ArrayList<String> tcsv = new ArrayList<String>();
        for (Tretman tret : this.getTretmani())
            tcsv.add(tret.tocsv());
        this.tretmaniSmestajPodataka.zapamtiPodatke(tcsv);
    }

    public void zapamtiZakazaneTretmane() {
        ArrayList<String> ztcsv = new ArrayList<String>();
        for (ZakazaniTretman ztret : this.getZakazaniTretmani())
            ztcsv.add(ztret.tocsv());
        this.zakazaniTretmaniSmestajPodataka.zapamtiPodatke(ztcsv);
    }

    public void zapamtiObuke() {
        ArrayList<String> ocsv = new ArrayList<String>();
        for (Obuka o : this.getObuke())
            ocsv.add(o.tocsv());
        this.obukeSmestajPodataka.zapamtiPodatke(ocsv);
    }
 
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public String tocsv() {
        return this.naziv + "," +
                this.radnoVreme.satOtvaranja + "," +
                this.radnoVreme.minutOtvaranja + "," +
                this.radnoVreme.satZatvaranja + "," +
                this.radnoVreme.minutZatvaranja;
    }

    public Icsvpersistable fromcsv(String csvString) {
        return new Klijent();
    }

    public Zaposleni getZaposleniByKorisnickoIme(String korisnickoIme) {
        for (Zaposleni zp : this.getZaposleni()) {
            if (zp.getKorisnickoIme().equals(korisnickoIme))
                return zp;
        }
        System.out.println("Zaposleni nije pronadjen\n");
        return null;
    }

    public TipTretmana getTipTretmanaByOznaka(String oznaka) {
        for (TipTretmana tipTretmana : this.getTipoviTretmana()) {
            if (tipTretmana.getOznaka().equals(oznaka))
                return tipTretmana;
        }
        System.out.println("Tip tretmana nije pronadjen\n");
        return null;
    }

    public Tretman getTretmanByOznaka(String oznaka) {
        for (Tretman tretman : this.getTretmani()) {
            if (tretman.getOznaka().equals(oznaka))
                return tretman;
        }
        System.out.println("Tretman nije pronadjen\n");
        return null;
    }

    public Klijent getKlijentByKorisnickoIme(String korisnickoIme) {
        for (Klijent klijent : this.getKlijenti()) {
            if (klijent.getKorisnickoIme().equals(korisnickoIme))
                return klijent;
        }
        System.out.println("Klijent nije pronadjen\n");
        return null;
    }

    public ZakazaniTretman getZakazaniTretmanByOznaka(String oznaka) {
        for (ZakazaniTretman ztretman : this.getZakazaniTretmani()) {
            if (ztretman.getOznaka().equals(oznaka))
                return ztretman;
        }
        System.out.println("Zakazani tretman nije pronadjen\n");
        return null;
    }

    public void zakaziTretman(Klijent klijent, Zaposleni kozmeticar, Tretman tretman, Calendar vreme) throws Exception {

        	if (this.isSlobodanTermin(kozmeticar, vreme, tretman.getTrajanje())) {

        	ZakazaniTretman zakazaniTretman = new ZakazaniTretman();
        	zakazaniTretman.setOznaka("t" + (getZakazaniTretmani().size() + 1));
        	zakazaniTretman.setKlijent(klijent.getKorisnickoIme());
        	zakazaniTretman.setKozmeticar(kozmeticar.getKorisnickoIme());

        	zakazaniTretman.setTermin(vreme);

        	if (this.uslovZaPopust(klijent)){
        		zakazaniTretman.setCena(tretman.getCena() - 0.1 * tretman.getCena());

        	} else{
        		zakazaniTretman.setCena(tretman.getCena());

        	}

        	zakazaniTretman.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        	zakazaniTretman.setOznakaTretmana(tretman.getOznaka());
        	getZakazaniTretmani().add(zakazaniTretman);
        	zapamtiZakazaneTretmane();
        } else throw new Exception("Kozmetičar " + kozmeticar.getKorisnickoIme() + " nije slobodan u terminu!");
    }


    public Boolean isSlobodanTermin (Zaposleni kozmeticar,Calendar vreme, Duration trajanje) {
    	Calendar pocetak1 = vreme;
    	Calendar kraj1 = (Calendar) pocetak1.clone();
    	kraj1.add(Calendar.MINUTE, (int) trajanje.toMinutes());
    	
       for (ZakazaniTretman zakazaniTretman: getAktivniTretmaniByKozmeticar(kozmeticar.getKorisnickoIme()) ){
            Tretman tretman = this.getTretmanByOznaka(zakazaniTretman.getOznakaTretmana());
            Calendar pocetak2 = zakazaniTretman.getTermin();
            Calendar kraj2 = (Calendar) pocetak2.clone();
            kraj2.add(Calendar.MINUTE, (int) tretman.getTrajanje().toMinutes());
            if ((pocetak1.after( pocetak2 ) || pocetak1.equals(pocetak2) ) && pocetak1.before( kraj2 ) || 
            		kraj1.after( pocetak2 ) && kraj1.before( kraj2 ) ) {
            	return false;
            }
       } 
       return true;
    }

    public ArrayList<ZakazaniTretman> getTretmaniByKozmeticar(String korisnickoImeKozmeticara){

        ArrayList<ZakazaniTretman> zakazaniTretmani = new ArrayList<>();
        for (ZakazaniTretman zakazaniTretman: getZakazaniTretmani()){
            if(zakazaniTretman.getKozmeticar().equals(korisnickoImeKozmeticara)){
                zakazaniTretmani.add(zakazaniTretman);
            }
        }
        return zakazaniTretmani;

    }

    public ArrayList<ZakazaniTretman> getAktivniTretmaniByKozmeticar(String korisnickoImeKozmeticara){
        ArrayList<ZakazaniTretman> zakazaniTretmani = new ArrayList<>();

        for (ZakazaniTretman zakazaniTretman: getZakazaniTretmani()){
            if(zakazaniTretman.getKozmeticar().equals(korisnickoImeKozmeticara) &&
                zakazaniTretman.getStanje() == ZakazaniTretman.Stanje.ZAKAZAN){
                zakazaniTretmani.add(zakazaniTretman);
            }
        }
        return zakazaniTretmani;
    }

    public void zavrsiZakazaniTretman(ZakazaniTretman zakazaniTretman, ZakazaniTretman.Stanje stanje) throws Exception{
    	if(zakazaniTretman.getStanje() == ZakazaniTretman.Stanje.IZVRSEN) throw new Exception("Izvršenom tretmanu se ne može promeniti stanje!");
            if (stanje == ZakazaniTretman.Stanje.IZVRSEN){
                Klijent klijent = this.getKlijentByKorisnickoIme(zakazaniTretman.getKlijent());
                klijent.setKarticaLojalnostiStanje(zakazaniTretman.getCena() + klijent.getKarticaLojalnostiStanje());
                zakazaniTretman.setStanje(stanje);
                //bilans salona
                this.setBilans(getBilans() + zakazaniTretman.getCena());
            } else if (stanje == ZakazaniTretman.Stanje.OTKAZAO_KLIJENT){
                //10% OSTAJE SALONU
                this.setBilans(getBilans() + 0.1 * zakazaniTretman.getCena());
                zakazaniTretman.setStanje(stanje);
            } else if (stanje == ZakazaniTretman.Stanje.OTKAZAO_SALON){
                zakazaniTretman.setStanje(stanje);
            } else if(stanje == ZakazaniTretman.Stanje.NIJE_SE_POJAVIO){
                zakazaniTretman.setStanje(stanje);
                this.setBilans(getBilans() + zakazaniTretman.getCena());

            } else{
                zakazaniTretman.setStanje(stanje);
            }
            
    }
    
    public Boolean uslovZaPopust(Klijent klijent) {
        return klijent.getKarticaLojalnostiStanje() >= this.limitKarticeLojalnosti ? true: false;
    }


    public Double getPrihodi(int mesec){
        Double prihodi = 0.0;

        for (ZakazaniTretman zakazaniTretman: this.getZakazaniTretmani()){
            GregorianCalendar zt = (GregorianCalendar)zakazaniTretman.getTermin();
            
            if( zt.get(GregorianCalendar.MONTH) == mesec &&
                zakazaniTretman.getStanje() == ZakazaniTretman.Stanje.IZVRSEN){
                prihodi += zakazaniTretman.getCena();
            }
        } 
        return prihodi;
    }
    
    
    public Double getPlata(Zaposleni zaposleni, int mesec) {
		return zaposleni.getOsnova() + (zaposleni.getOsnova() * 
				(this.getKoeficijentStrucneSpreme(zaposleni.getStrucnaSprema()) +  
				this.getKoeficijentRadniStaz() * zaposleni.getStaz() ) +
				this.getBonus(zaposleni.getKorisnickoIme(), mesec));
	}
	
	public Double getBonus(String korisnickoIme, int mesec) {
		Double promet = 0.0;
		for(ZakazaniTretman zakazaniTretman: this.getZakazaniTretmani() ) {
            
            GregorianCalendar zt = (GregorianCalendar)zakazaniTretman.getTermin();

			if(zakazaniTretman.getKozmeticar().equals(korisnickoIme) && 
					zakazaniTretman.getStanje() == ZakazaniTretman.Stanje.IZVRSEN && 
                        zt.get(GregorianCalendar.MONTH) == mesec) {

				        promet += zakazaniTretman.getCena();
			}   
		}

		
        // ukoliko zaposleni ostavri vecu zaradu za bonus => + 5% prometa 
		if (promet > this.getBonusLimit()) {
			return promet * 0.05;
		} else return 0.0;
	}


    public ArrayList<ZakazaniTretman> getTretmaniByKlijent(String korisnickoImeKlijenta){

        ArrayList<ZakazaniTretman> zakazaniTretmani = new ArrayList<>();

        for(ZakazaniTretman zakazaniTretman: this.getZakazaniTretmani()){
            if(zakazaniTretman.getKlijent().equals(korisnickoImeKlijenta)){
                zakazaniTretmani.add(zakazaniTretman);
            }
        }
        return zakazaniTretmani;
    }

    private double limitKarticeLojalnosti;

    public double getLimitKarticeLojalnosti(){
        return limitKarticeLojalnosti;

    }
    public void setLimitKarticeLojalnosti(double limitKarticeLojalnosti){
        this.limitKarticeLojalnosti = limitKarticeLojalnosti;
    }

    public Zaposleni izborKozmeticara(String oznakaTretmana, String oznakaTipaTretmana, Calendar termin){

        ArrayList<Zaposleni> sviZaposleni = this.getZaposleni();
    
        
        for(Zaposleni zaposleni: sviZaposleni){
            if(zaposleni.getUloga() == Zaposleni.Uloga.KOZMETICAR){
                ArrayList<Obuka> sveObuke = this.getObuke();
                for(Obuka obuka: sveObuke){
                    if(zaposleni.getKorisnickoIme().equals(obuka.getZaposleni()) && 
                        obuka.getTipTretmana().equals(oznakaTipaTretmana)){
                            if(this.isSlobodanTermin(zaposleni, termin, this.getTretmanByOznaka(oznakaTretmana).getTrajanje())){
                                return zaposleni;
                            }
                    }
                }
            }
        }
        return null;
    }

   

	public ArrayList<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(ArrayList<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public ArrayList<Klijent> getKlijenti() {
		return klijenti;
	}

	public void setKlijenti(ArrayList<Klijent> klijenti) {
		this.klijenti = klijenti;
	}

	public ArrayList<TipTretmana> getTipoviTretmana() {
		return tipoviTretmana;
	}

	public void setTipoviTretmana(ArrayList<TipTretmana> tipoviTretmana) {
		this.tipoviTretmana = tipoviTretmana;
	}

	public ArrayList<Tretman> getTretmani() {
		return tretmani;
	}

	public void setTretmani(ArrayList<Tretman> tretmani) {
		this.tretmani = tretmani;
	}

	public ArrayList<ZakazaniTretman> getZakazaniTretmani() {
		return zakazaniTretmani;
	}

	public void setZakazaniTretmani(ArrayList<ZakazaniTretman> zakazaniTretmani) {
		this.zakazaniTretmani = zakazaniTretmani;
	}

	public ArrayList<Obuka> getObuke() {
		return obuke;
	}

	public void setObuke(ArrayList<Obuka> obuke) {
		this.obuke = obuke;
	}

	public SmestajPodataka getSalonSmestajPodataka() {
		return salonSmestajPodataka;
	}

	public void setSalonSmestajPodataka(SmestajPodataka salonSmestajPodataka) {
		this.salonSmestajPodataka = salonSmestajPodataka;
	}
	
	
}

	
