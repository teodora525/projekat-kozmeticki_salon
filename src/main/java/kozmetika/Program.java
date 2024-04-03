package kozmetika;

import java.awt.BorderLayout;
import java.awt.Label;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import kozmetika.stanje.Klijent;
import kozmetika.stanje.Korisnik;
import kozmetika.stanje.KozmetickiSalon;
import kozmetika.stanje.Obuka;
import kozmetika.stanje.TipTretmana;
import kozmetika.stanje.Tretman;
import kozmetika.stanje.ZakazaniTretman;
import kozmetika.stanje.Zaposleni;



/**
 * Program
 */

public class Program {

    private KozmetickiSalon kozmetickiSalon = null;

    private Korisnik korisnik = null;

    public Program() {
        this.kozmetickiSalon = new KozmetickiSalon();
    }

    public KozmetickiSalon getKozmetickiSalon() {
        return this.kozmetickiSalon;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * @return korisnik - prijavljeni korisnik
     */
    public Korisnik getKorisnik() {
        return this.korisnik;
    }

    public static void main(String[] args) {

        Program program = new Program();

        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
        //        kreirajIPrikaziGUI();
        //    }
        //});

         //program.kozmetickiSalon.setKlijenti(new ArrayList<>());
         //program.kozmetickiSalon.setZaposleni(new ArrayList<>());
         //program.kozmetickiSalon.setTipoviTretmana(new ArrayList<>());
         //program.kozmetickiSalon.setTretmani(new ArrayList<>());
         //program.kozmetickiSalon.setObuke(new ArrayList<>());
         //program.kozmetickiSalon.setZakazaniTretmani(new ArrayList<>());
        // testScenario(program);

        /*Za KT3 */
         testScenarioKT3(program);
    }


    private static void kreirajIPrikaziGUI(){

        JPanel aktivniPanel;
        JFrame frame = new JFrame("Kozmetički salon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        aktivniPanel = new JPanel(null, false);

        JPanel glavniPanel = new JPanel();
        glavniPanel.add(aktivniPanel);
        glavniPanel.setSize(800, 600);
        glavniPanel.add(new JLabel("Dobro došli u 'Moj salon'"));

        frame.getContentPane().add(glavniPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        JButton prijaviSe = new JButton("Prijavi se");
        prijaviSe.setSize(100, 50);
        frame.add(prijaviSe, BorderLayout.CENTER);
        
    }

    private static void testScenario(Program program) {
        System.out.println("==== PODACI O SALONU === ");
        System.out.println();
        program.kozmetickiSalon.setNaziv("Moj salon");
        ArrayList<DayOfWeek> radniDani = new ArrayList<DayOfWeek>();
        radniDani.add(DayOfWeek.MONDAY);
        radniDani.add(DayOfWeek.TUESDAY);
        radniDani.add(DayOfWeek.THURSDAY);
        radniDani.add(DayOfWeek.FRIDAY);
        radniDani.add(DayOfWeek.SATURDAY);
        program.getKozmetickiSalon().setRadniDani(radniDani);
        program.getKozmetickiSalon().setRadnoVreme(7, 0, 16, 0);

        String nrv = "Kozmeticki salon: " + program.kozmetickiSalon.getNaziv() + "\n\n";
        nrv += "Radni dani i radno vreme:\n";
        for (DayOfWeek day : program.getKozmetickiSalon().getRadniDani()) {
            nrv += day + " ";
        }
        nrv += "\n";

        KozmetickiSalon.RadnoVreme rv = program.getKozmetickiSalon().getRadnoVreme();
        nrv += "Od " + rv.satOtvaranja + ": " + rv.minutOtvaranja + " do " + rv.satZatvaranja + ": " + rv.minutZatvaranja
                + "\n";
        System.out.println(nrv);

        // Korisnici


        Zaposleni nz = new Zaposleni();
        nz.setKorisnickoIme("Nikola");
        nz.setUloga(Korisnik.Uloga.MENADZER);
        nz.setLozinka("tajna");
        nz.setIme("Nikola");
        nz.setPrezime("Nikolic");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.VS);
        nz.setStaz(Double.parseDouble("2.5"));

        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Pera");
        nz.setUloga(Korisnik.Uloga.RECEPCIONER);
        nz.setLozinka("tajna");
        nz.setIme("Pera");
        nz.setPrezime("Peric");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.SSS);
        nz.setStaz(Double.parseDouble("1.5"));
        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Sima");
        nz.setUloga(Korisnik.Uloga.KOZMETICAR);
        nz.setLozinka("tajna");
        nz.setIme("Sima");
        nz.setPrezime("Simic");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.VSS);
        nz.setStaz(Double.parseDouble("3.5"));
        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Zika");
        nz.setUloga(Korisnik.Uloga.KOZMETICAR);
        nz.setLozinka("tajna");
        nz.setIme("Zika");
        nz.setPrezime("Zikic");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.SSS);
        nz.setStaz(Double.parseDouble("0.5"));
        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Jadranka");
        nz.setUloga(Korisnik.Uloga.KOZMETICAR);
        nz.setLozinka("tajna");
        nz.setIme("Jadranka");
        nz.setPrezime("Jovanovic");
        nz.setPol(Korisnik.Pol.z);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.VSS);
        nz.setStaz(Double.parseDouble("2.5"));
        program.getKozmetickiSalon().getZaposleni().add(nz);

        Klijent nk = new Klijent();
        nk.setKorisnickoIme("Milica");
        nk.setUloga(Korisnik.Uloga.KLIJENT);
        nk.setLozinka("tajna");
        nk.setIme("Milica");
        nk.setPrezime("Milic");
        nk.setPol(Korisnik.Pol.z);
        nk.setTelefon("011 000 000");
        nk.setAdresa("Neko Mesto bb");
        nk.setKarticaLojalnostiStanje(0.0);
        program.getKozmetickiSalon().getKlijenti().add(nk);

        nk = new Klijent();
        nk.setKorisnickoIme("Mika");
        nk.setUloga(Korisnik.Uloga.KLIJENT);
        nk.setLozinka("tajna");
        nk.setIme("Mika");
        nk.setPrezime("Mikic");
        nk.setPol(Korisnik.Pol.z);
        nk.setTelefon("011 000 000");
        nk.setAdresa("Neko Mesto bb");
        nk.setKarticaLojalnostiStanje(0.0);
        program.getKozmetickiSalon().getKlijenti().add(nk);

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");
        nz.setIme("Jovana");

        //prilikom pokretanja test scenaria zakomentarisati, duplira se prikaz

        program.getKozmetickiSalon().zapamtiZaposlene();
        program.getKozmetickiSalon().zapamtiKlijente();

        System.out.println();
        System.out.println("=== ZAPOSLENI === ");
        System.out.println();

        for (Zaposleni z : program.getKozmetickiSalon().getZaposleni()) {
            System.out.println(z.tocsv());
        }
        System.out.println();
        System.out.println("=== KLIJENTI === ");
        System.out.println();

        for (Klijent k : program.getKozmetickiSalon().getKlijenti()) {
            System.out.println(k.tocsv());
        }

        // Tipovi tretmana

        TipTretmana tt = new TipTretmana();
        tt.setOznaka("masaza");
        tt.setNaziv("masaza");
        program.getKozmetickiSalon().getTipoviTretmana().add(tt);

        tt = new TipTretmana();
        tt.setOznaka("manikir");
        tt.setNaziv("manikir");
        program.getKozmetickiSalon().getTipoviTretmana().add(tt);

        tt = new TipTretmana();
        tt.setOznaka("pedikir");
        tt.setNaziv("pedikir");
        program.getKozmetickiSalon().getTipoviTretmana().add(tt);


        // Tretmani 

        Tretman tr = new Tretman();
        tr.setOznaka("relaks masaza");
        tr.setNaziv("Relaks masaza");
        tr.setOznakaTipaTretmana("masaza");
        tr.setTrajanje(Duration.ofMinutes(45));
        tr.setCena(2000.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("sportska masaza");
        tr.setNaziv("Sportska masaza");
        tr.setOznakaTipaTretmana("masaza");
        tr.setTrajanje(Duration.ofMinutes(75));
        tr.setCena(2500.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("francuski manikir");
        tr.setNaziv("Francuski manikir");
        tr.setOznakaTipaTretmana("manikir");
        tr.setTrajanje(Duration.ofMinutes(50));
        tr.setCena(1500.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("gel lak");
        tr.setNaziv("Gel lak");
        tr.setOznakaTipaTretmana("manikir");
        tr.setTrajanje(Duration.ofMinutes(80));
        tr.setCena(1600.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("spa manikir");
        tr.setNaziv("Spa manikir");
        tr.setOznakaTipaTretmana("manikir");
        tr.setTrajanje(Duration.ofMinutes(90));
        tr.setCena(2000.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("spa pedikir");
        tr.setNaziv("Spa pedikir");
        tr.setOznakaTipaTretmana("pedikir");
        tr.setTrajanje(Duration.ofMinutes(45));
        tr.setCena(1600.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        System.out.println();
        System.out.println("=== TRETMANI === ");
        System.out.println();

        for (Tretman tretman : program.getKozmetickiSalon().getTretmani()) {
            System.out.println(tretman.tocsv());
        }
        

        for (Tretman tretman : program.getKozmetickiSalon().getTretmani()) {
            if (tretman.getOznaka() == "francuski manikir") {
                tretman.setTrajanje(Duration.ofMinutes(55));
            }
        }
        System.out.println();
        System.out.println("=== SVI TRETMANI sa promenama === ");
        System.out.println();

        for (Tretman tretman : program.getKozmetickiSalon().getTretmani()) {
            System.out.println(tretman.tocsv());
        }

        // brisanje tretmana PEDIKIR

        Iterator<TipTretmana> itrTipTretmana = program.getKozmetickiSalon().getTipoviTretmana().iterator();
        while (itrTipTretmana.hasNext()) {

            TipTretmana x = (TipTretmana) itrTipTretmana.next();
            if (x.getOznaka() == "pedikir")
                itrTipTretmana.remove();
        }

        Iterator<Tretman> itrTretman = program.getKozmetickiSalon().getTretmani().iterator();
        while (itrTretman.hasNext()) {

            Tretman x = (Tretman) itrTretman.next();
            if (x.getOznakaTipaTretmana() == "pedikir")
                itrTretman.remove();
        }

        System.out.println();
        System.out.println("=== PRIKAZ SVIH TRETMANA SA OBRISANIM PEDIKIROM === ");
        System.out.println();
        for (Tretman tretman : program.getKozmetickiSalon().getTretmani()) {
            System.out.println(tretman.tocsv());
        }


        // Sima Simic - masaza i manikir
        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Sima");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("masaza");
        Obuka ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("manikir");
        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        // Jovana Jovanovic  - masaza, manikir i pedikir

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("manikir");

        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        // kreiranje konkretnog tretmana t1 relaks masaza kod kozmeticara Sime Simica za Milicu Milic
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("relaks masaza");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Sima");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        // konkretan tretman t1
        ZakazaniTretman zt = new ZakazaniTretman();
        zt.setOznaka("t1");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 06, 3, 12, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);
        System.out.println();

        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());

        // konkretan tretman t2
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("gel lak");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Sima");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Mika");

        zt = new ZakazaniTretman();
        zt.setOznaka("t2");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 06, 3, 14, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());

        // konkretan tretman t3
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("spa manikir");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Mika");

        zt = new ZakazaniTretman();
        zt.setOznaka("t3");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 06, 3, 16, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());


        System.out.println();
        System.out.println("=== SVI ZAKAZANI TRETMANI ===");
        
        for (ZakazaniTretman ztretman : program.getKozmetickiSalon().getZakazaniTretmani()) {
            System.out.println(ztretman.tocsv());
        }

        //izmena t2 na francuski manikir
        zt = null;
        zt = program.getKozmetickiSalon().getZakazaniTretmanByOznaka("t2");
        zt.setOznakaTretmana("francuski manikir");

        System.out.println();
        System.out.println("=== SVI ZAKAZANI TRETMANI SA PROMENOM t2 ===");
        System.out.println();

        for (ZakazaniTretman ztretman : program.getKozmetickiSalon().getZakazaniTretmani()) {
            System.out.println(ztretman.tocsv());
        }

        System.out.println();
        System.out.println("=== CENE SVIH ZAKAZANIH TRETMANA ===");
        for (ZakazaniTretman ztretman : program.getKozmetickiSalon().getZakazaniTretmani()) {
            System.out.println(ztretman.getOznaka() + " - " +
                    program.getKozmetickiSalon().getTretmanByOznaka(ztretman.getOznakaTretmana()).getNaziv() + "\t" +
                    ztretman.getCena());
        }


        //promena cene relaks masaze na 1700
        program.getKozmetickiSalon().getTretmanByOznaka("relaks masaza").setCena(1700.0);


        System.out.println();
        System.out.println("=== PROMENJENE CENE SVIH ZAKAZANIH TRETMANA ===");
        for (ZakazaniTretman ztretman : program.getKozmetickiSalon().getZakazaniTretmani()) {
            System.out.println(ztretman.getOznaka() + " - " +
                    program.getKozmetickiSalon().getTretmanByOznaka(ztretman.getOznakaTretmana()).getNaziv() + "\t" +
                    ztretman.getCena());
        }

        //zakomentarisati prilikom pokretanja test scenaria

        program.getKozmetickiSalon().zapamtiTipoveTretmana();
        program.getKozmetickiSalon().zapamtiTretmane();
        program.getKozmetickiSalon().zapamtiZakazaneTretmane();
        program.getKozmetickiSalon().zapamtiObuke();

    }
    private static void testScenarioKT3(Program program)  {
        
        System.out.println("==== PODACI O SALONU === ");
        System.out.println();
        program.kozmetickiSalon.setNaziv("Moj salon");
        ArrayList<DayOfWeek> radniDani = new ArrayList<DayOfWeek>();
        radniDani.add(DayOfWeek.MONDAY);
        radniDani.add(DayOfWeek.TUESDAY);
        radniDani.add(DayOfWeek.THURSDAY);
        radniDani.add(DayOfWeek.FRIDAY);
        radniDani.add(DayOfWeek.SATURDAY);
        program.getKozmetickiSalon().setRadniDani(radniDani);
        program.getKozmetickiSalon().setRadnoVreme(7, 0, 16, 0);

        String nrv = "Kozmeticki salon: " + program.kozmetickiSalon.getNaziv() + "\n\n";
        nrv += "Radni dani i radno vreme:\n";
        for (DayOfWeek day : program.getKozmetickiSalon().getRadniDani()) {
            nrv += day + " ";
        }
        nrv += "\n";

        KozmetickiSalon.RadnoVreme rv = program.getKozmetickiSalon().getRadnoVreme();
        nrv += "Od " + rv.satOtvaranja + ": " + rv.minutOtvaranja + " do " + rv.satZatvaranja + ": " + rv.minutZatvaranja
                + "\n";
        System.out.println(nrv);

        // Korisnici

        Zaposleni nz = new Zaposleni();
        nz.setKorisnickoIme("Nikola");
        nz.setUloga(Korisnik.Uloga.MENADZER);
        nz.setLozinka("tajna");
        nz.setIme("Nikola");
        nz.setPrezime("Nikolic");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.VS);
        nz.setStaz(2.5);
        nz.setOsnova(40000.0);

        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Pera");
        nz.setUloga(Korisnik.Uloga.RECEPCIONER);
        nz.setLozinka("tajna");
        nz.setIme("Pera");
        nz.setPrezime("Peric");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.SSS);
        nz.setStaz(1.5);
        nz.setOsnova(30000.0);
        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Sima");
        nz.setUloga(Korisnik.Uloga.KOZMETICAR);
        nz.setLozinka("tajna");
        nz.setIme("Sima");
        nz.setPrezime("Simic");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.VSS);
        nz.setStaz(3.5);
        nz.setOsnova(25000.0);
        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Zika");
        nz.setUloga(Korisnik.Uloga.KOZMETICAR);
        nz.setLozinka("tajna");
        nz.setIme("Zika");
        nz.setPrezime("Zikic");
        nz.setPol(Korisnik.Pol.m);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.SSS);
        nz.setStaz(0.5);
        nz.setOsnova(25000.0);
        program.getKozmetickiSalon().getZaposleni().add(nz);

        nz = new Zaposleni();
        nz.setKorisnickoIme("Jadranka");
        nz.setUloga(Korisnik.Uloga.KOZMETICAR);
        nz.setLozinka("tajna");
        nz.setIme("Jadranka");
        nz.setPrezime("Jovanovic");
        nz.setPol(Korisnik.Pol.z);
        nz.setTelefon("011 000 000");
        nz.setAdresa("Neko Mesto bb");
        nz.setStrucnaSprema(Zaposleni.StrucnaSprema.VSS);
        nz.setStaz(2.5);
        nz.setOsnova(25000.0);
        program.getKozmetickiSalon().getZaposleni().add(nz);

        Klijent nk = new Klijent();
        nk.setKorisnickoIme("Milica");
        nk.setUloga(Korisnik.Uloga.KLIJENT);
        nk.setLozinka("tajna");
        nk.setIme("Milica");
        nk.setPrezime("Milic");
        nk.setPol(Korisnik.Pol.z);
        nk.setTelefon("011 000 000");
        nk.setAdresa("Neko Mesto bb");
        nk.setKarticaLojalnostiStanje(0.0);
        program.getKozmetickiSalon().getKlijenti().add(nk);

        nk = new Klijent();
        nk.setKorisnickoIme("Mika");
        nk.setUloga(Korisnik.Uloga.KLIJENT);
        nk.setLozinka("tajna");
        nk.setIme("Mika");
        nk.setPrezime("Mikic");
        nk.setPol(Korisnik.Pol.z);
        nk.setTelefon("011 000 000");
        nk.setAdresa("Neko Mesto bb");
        nk.setKarticaLojalnostiStanje(0.0);
        program.getKozmetickiSalon().getKlijenti().add(nk);

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");
        nz.setIme("Jovana");

        System.out.println();
        System.out.println("=== ZAPOSLENI === ");
        System.out.println();

        for (Zaposleni z : program.getKozmetickiSalon().getZaposleni()) {
            System.out.println(z.tocsv());
            
        }
        System.out.println();
        System.out.println("=== KLIJENTI === ");
        System.out.println();

        for (Klijent k : program.getKozmetickiSalon().getKlijenti()) {
            System.out.println(k.tocsv());
            
        }

        // Tipovi tretmana

        TipTretmana tt = new TipTretmana();
        tt.setOznaka("masaza");
        tt.setNaziv("masaza");
        program.getKozmetickiSalon().getTipoviTretmana().add(tt);

        tt = new TipTretmana();
        tt.setOznaka("manikir");
        tt.setNaziv("manikir");
        program.getKozmetickiSalon().getTipoviTretmana().add(tt);

        tt = new TipTretmana();
        tt.setOznaka("pedikir");
        tt.setNaziv("pedikir");
        program.getKozmetickiSalon().getTipoviTretmana().add(tt);


        // Tretmani 

        Tretman tr = new Tretman();
        tr.setOznaka("relaks masaza");
        tr.setNaziv("Relaks masaza");
        tr.setOznakaTipaTretmana("masaza");
        tr.setTrajanje(Duration.ofMinutes(45));
        tr.setCena(2000.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("sportska masaza");
        tr.setNaziv("Sportska masaza");
        tr.setOznakaTipaTretmana("masaza");
        tr.setTrajanje(Duration.ofMinutes(75));
        tr.setCena(2500.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("francuski manikir");
        tr.setNaziv("Francuski manikir");
        tr.setOznakaTipaTretmana("manikir");
        tr.setTrajanje(Duration.ofMinutes(50));
        tr.setCena(1500.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("gel lak");
        tr.setNaziv("Gel lak");
        tr.setOznakaTipaTretmana("manikir");
        tr.setTrajanje(Duration.ofMinutes(80));
        tr.setCena(1600.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("spa manikir");
        tr.setNaziv("Spa manikir");
        tr.setOznakaTipaTretmana("manikir");
        tr.setTrajanje(Duration.ofMinutes(90));
        tr.setCena(2000.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        tr = new Tretman();
        tr.setOznaka("spa pedikir");
        tr.setNaziv("Spa pedikir");
        tr.setOznakaTipaTretmana("pedikir");
        tr.setTrajanje(Duration.ofMinutes(45));
        tr.setCena(1600.0);
        program.getKozmetickiSalon().getTretmani().add(tr);

        System.out.println();
        System.out.println("=== TRETMANI === ");
        System.out.println();

        for (Tretman tretman : program.getKozmetickiSalon().getTretmani()) {
            System.out.println(tretman.tocsv());
        }


        // Sima Simic - masaza i manikir
        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Sima");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("masaza");
        Obuka ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("manikir");
        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        // Jovana Jovanovic  - masaza, manikir i pedikir

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("manikir");

        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        //Zika Zikic - manikir, masaza, pedikir
        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("manikir");

        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("pedikir");

        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);
        
        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");

        tt = null;
        tt = program.getKozmetickiSalon().getTipTretmanaByOznaka("masaza");

        ob = new Obuka();
        ob.setZaposleni(nz.getKorisnickoIme());
        ob.setTipTretmana(tt.getOznaka());
        program.getKozmetickiSalon().getObuke().add(ob);
        // Milica Milić vrši online zakazivanje francuskog manikira i bira kozmetičara 3 u terminu
        // 10.6.2023, 18:00h. Zatim, Milica Milić vrši zakazivanje spa pedikira preko recepcionera i bira
        // kozmetičara 2 u terminu 11.06.2023, 09:00h.

        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("francuski manikir");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        ZakazaniTretman zt = new ZakazaniTretman();
        zt.setOznaka("t1");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 10, 18, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);
        System.out.println();
        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());



        // Milica Milic - spa pedikir preko recepcionera
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("spa pedikir");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = new ZakazaniTretman();
        zt.setOznaka("t2");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 11, 9, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());

        // Mika Mikić vrši online zakazivanje sportske masaže i bira kozmetičara 1 u terminu 12.6.2023,
        // 08:00h. Zatim, Mika vrši zakazivanje relaks masaže preko recepcionera i bira kozmetičara 2 u
        // terminu 13.06.2023, 19:00h.
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("sportska masaza");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Sima");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Mika");

        zt = new ZakazaniTretman();
        zt.setOznaka("t3");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 12, 8, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());

        // Mika Mikic - preko recepcionera relaks masaza 
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("relaks masaza");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");
        zt = new ZakazaniTretman();
        zt.setOznaka("t4");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 13, 19, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());

        
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("francuski manikir");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Jadranka");

        Calendar noviTermin = new GregorianCalendar(2023, 5, 10, 18, 0);
        if (program.kozmetickiSalon.isSlobodanTermin(nz, noviTermin,program.kozmetickiSalon.getTretmanByOznaka("francuski manikir").getTrajanje())){
            zt = null;
            zt = new ZakazaniTretman();
            zt.setOznaka("t5");
            zt.setOznakaTretmana(tr.getOznaka());
            zt.setTermin(noviTermin);
            zt.setKozmeticar(nz.getKorisnickoIme());
            zt.setKlijent(nk.getKorisnickoIme());
            zt.setCena(tr.getCena());
            zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
            program.getKozmetickiSalon().getZakazaniTretmani().add(zt);
            System.out.println("Uspesno zakazan");
        } else{
            System.out.println();
            System.out.println("Neuspeno zakazan tretman " + tr.getNaziv() + " za korisnika " + nk.getIme() + " za termin " + ((GregorianCalendar)noviTermin).toZonedDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm")));
        }
        
        // prikaz svih tretmana za kozmeticara 2

        nz = null;
        nz = program.kozmetickiSalon.getZaposleniByKorisnickoIme("Zika");
        ArrayList<ZakazaniTretman> tretmaniKozmeticara = program.kozmetickiSalon.getTretmaniByKozmeticar(nz.getKorisnickoIme());
        
        
        //ispis 
        System.out.println();
        System.out.println("=== ZAKAZANI TRETMANI ZA KOZMETIČARA 2 ===");
        for(ZakazaniTretman zakazaniTretman: tretmaniKozmeticara){

            GregorianCalendar vreme = (GregorianCalendar) zakazaniTretman.getTermin();

            System.out.println(zakazaniTretman.getOznaka() + "-" + 
            program.kozmetickiSalon.getTretmanByOznaka(zakazaniTretman.getOznakaTretmana()).getNaziv() +
            "       " + vreme.toZonedDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm")));
        }


        //postavljanje vrednosti za karticu lojalnosti na 3500.0

        program.kozmetickiSalon.setLimitKarticeLojalnosti(3500.00);


        //t1 uspesno izvrsen i stanje na kl
        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = null;
        zt = program.kozmetickiSalon.getZakazaniTretmanByOznaka("t1");

        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.IZVRSEN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println();
        System.out.println("Tretman " + zt.getOznaka() + " je " + zt.getStanje() + " za klijenta " + nk.getIme() + " " + nk.getPrezime());
        System.out.println("Stanje na kartici lojanosti: " + nk.getKarticaLojalnostiStanje());
        System.out.println();


        //t2 otkazao klijent - klijent 1
        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = null;
        zt = program.kozmetickiSalon.getZakazaniTretmanByOznaka("t2");

        zt.setStanje(ZakazaniTretman.Stanje.OTKAZAO_KLIJENT);
        System.out.println();
        System.out.println("Tretman " + zt.getOznaka() + " je " + zt.getStanje() + " za klijenta " + nk.getIme() + " " + nk.getPrezime());
        System.out.println();
        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.OTKAZAO_KLIJENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Bilans salona: " + program.kozmetickiSalon.getBilans());


        //t3 otkazao salon
        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Mika");

        zt = null;
        zt = program.kozmetickiSalon.getZakazaniTretmanByOznaka("t3");

        zt.setStanje(ZakazaniTretman.Stanje.OTKAZAO_SALON);
        System.out.println();
        System.out.println("Tretman " + zt.getOznaka() + " je " + zt.getStanje() + " za klijenta " + nk.getIme() + " " + nk.getPrezime());
        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.OTKAZAO_SALON);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println();

        // nije se pojavio na t4
        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = null;
        zt = program.kozmetickiSalon.getZakazaniTretmanByOznaka("t4");

        zt.setStanje(ZakazaniTretman.Stanje.NIJE_SE_POJAVIO);
        System.out.println();
        System.out.println("Tretman " + zt.getOznaka() + " je " + zt.getStanje() + " za klijenta " + nk.getIme() + " " + nk.getPrezime());
        System.out.println();
        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.NIJE_SE_POJAVIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Bilans salona: " + program.kozmetickiSalon.getBilans());

        
        //milica milic zakazuje gel lak 
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("gel lak");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Sima");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = new ZakazaniTretman();
        zt.setOznaka("t6");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 14, 9, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);
        System.out.println();
        System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());

        // mika mikic online spa manikir i ne zeli da bira kozmeticara;
        // prikazati da je termin  uspesno izvrsen i stanje na kl i bilans na racunu salona

        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("spa manikir");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Mika");

        Calendar vremeZakazivanja = new GregorianCalendar(2023, 5, 14, 9, 0);

        nz = null;
        nz = program.kozmetickiSalon.izborKozmeticara(tr.getOznaka(), "manikir", vremeZakazivanja);
        zt = new ZakazaniTretman();
       
        if(nz == null){
            System.out.println("Nije pronadjen kozmetičar u tom terminu");
        }
        else{
           
            zt.setOznaka("t7");
            zt.setOznakaTretmana(tr.getOznaka());
            zt.setTermin(vremeZakazivanja);
            zt.setKozmeticar(nz.getKorisnickoIme());
            zt.setKlijent(nk.getKorisnickoIme());
            zt.setCena(tr.getCena());
            zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
            program.getKozmetickiSalon().getZakazaniTretmani().add(zt);
            System.out.println();
            System.out.println(nk.getIme() + " " + nk.getPrezime() + " je uspesno zakazala tretman: " + tr.getNaziv() + " kod kozmeticara " + nz.getIme() + " " + nz.getPrezime());
            System.out.println();
        }
        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.IZVRSEN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Tretman " + zt.getOznaka() + " je " + zt.getStanje() + " za klijenta " + nk.getIme() + " " + nk.getPrezime());

        System.out.println("Stanje na kartici lojanosti: " + nk.getKarticaLojalnostiStanje());
        System.out.println();

        System.out.println("Bilans salona: " + program.kozmetickiSalon.getBilans());
        System.out.println();

        // svi tretmani za Miku Mikica

        nk = null;
        nk = program.kozmetickiSalon.getKlijentByKorisnickoIme("Mika");
        ArrayList<ZakazaniTretman> tretmaniKlijenata = program.kozmetickiSalon.getTretmaniByKlijent(nk.getKorisnickoIme());
        System.out.println("=== TRETMANI MIKE MIKIC ===");
        for(ZakazaniTretman zakazaniTretman: tretmaniKlijenata){

            GregorianCalendar vreme = (GregorianCalendar) zakazaniTretman.getTermin();

            System.out.println(zakazaniTretman.getOznaka() + "-" + 
            program.kozmetickiSalon.getTretmanByOznaka(zakazaniTretman.getOznakaTretmana()).getNaziv() +
            "       " + vreme.toZonedDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm")));
        }
        System.out.println();

        program.kozmetickiSalon.setBonusLimit(1000.0);
        System.out.println("Menadžer zadaje limit bonusa na prihod zaposlenih.");
        
        //prihodi za 6 mesec == BILANS SALONA

        System.out.println("Prihodi za 6 mesec: " + program.kozmetickiSalon.getPrihodi(5));

        //rashodi za 6.mesec 
        Double rashodi = 0.0;
        for(Zaposleni zaposeni: program.kozmetickiSalon.getZaposleni()){
            rashodi += program.kozmetickiSalon.getPlata(zaposeni, 5);
        }


        System.out.println("Rashodi za 6 mesec: " + rashodi);
        
        // kozmeticar 2 dobija bonus 
        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("spa pedikir");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = new ZakazaniTretman();
        zt.setOznaka("t9");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 15, 9, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        //IZVRSI 
        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.IZVRSEN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        tr = null;
        tr = program.getKozmetickiSalon().getTretmanByOznaka("relaks masaza");

        nz = null;
        nz = program.getKozmetickiSalon().getZaposleniByKorisnickoIme("Zika");

        nk = null;
        nk = program.getKozmetickiSalon().getKlijentByKorisnickoIme("Milica");

        zt = new ZakazaniTretman();
        zt.setOznaka("t10");
        zt.setOznakaTretmana(tr.getOznaka());
        zt.setTermin(new GregorianCalendar(2023, 5, 25, 9, 0));
        zt.setKozmeticar(nz.getKorisnickoIme());
        zt.setKlijent(nk.getKorisnickoIme());
        zt.setCena(tr.getCena());
        zt.setStanje(ZakazaniTretman.Stanje.ZAKAZAN);
        program.getKozmetickiSalon().getZakazaniTretmani().add(zt);

        try {
			program.kozmetickiSalon.zavrsiZakazaniTretman(zt, ZakazaniTretman.Stanje.IZVRSEN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println();

        System.out.println("=== BONUSI ZA ZAPOSLENE === ");
        for(Zaposleni zaposleni: program.kozmetickiSalon.getZaposleni()){
            program.kozmetickiSalon.getBonus(zaposleni.getKorisnickoIme(), 5);
            System.out.println(zaposleni.getIme() + " " + zaposleni.getPrezime() + ": " 
            + program.kozmetickiSalon.getBonus(zaposleni.getKorisnickoIme(), 5));
        }
        System.out.println();

    
        program.getKozmetickiSalon().zapamtiZaposlene();
        program.getKozmetickiSalon().zapamtiKlijente();
        program.getKozmetickiSalon().zapamtiTipoveTretmana();
        program.getKozmetickiSalon().zapamtiTretmane();
        program.getKozmetickiSalon().zapamtiZakazaneTretmane();
        program.getKozmetickiSalon().zapamtiObuke();
        program.getKozmetickiSalon().zapamtiSalon();

    }
}
