package kozmetika.gui;

import kozmetika.ProgramSwing;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kozmetika.stanje.Klijent;
import kozmetika.stanje.Korisnik;
import kozmetika.stanje.Obuka;
import kozmetika.stanje.TipTretmana;
import kozmetika.stanje.Tretman;
import kozmetika.stanje.Zaposleni;

public class ZakaziPanel  extends KSPanel implements ActionListener {

	
	private JTextField textFieldVreme;
	private JTextField textFieldDatum;
	private JComboBox cbTretman;
	private JComboBox cbKozmeticar;
	private JComboBox cbOdaberiKlijenta;
	private JPanel panelZakazi;
	private JPanel panelZakazi1;
	private JPanel panelZakazi2;
	private JPanel panelZakazi3;
	
	
	private ArrayList<TipTretmana> tipoviTretmana;
	private ArrayList<Tretman> tretmani;
	private ArrayList<Zaposleni> kozmeticari;
	private ArrayList<Klijent> klijenti;
	private ArrayList<Obuka> obuke;
	DefaultComboBoxModel<String> tipoviTretmanaModel;
	DefaultComboBoxModel<String> tretmaniModel;
	DefaultComboBoxModel<String> kozmeticariModel;
	DefaultComboBoxModel<String> klijentiModel;
	
	private Tretman izabranTretman;
	private Zaposleni izabranKozmeticar;
	private Klijent izabranKlijent;
	
	
	/**
	 * Create the panel.
	 */
	public ZakaziPanel(ProgramSwing program) {
		this.setProgram(program);
		
		panelZakazi = new JPanel();
		panelZakazi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.add(panelZakazi);
		
		panelZakazi1 = new JPanel();
		panelZakazi.add(panelZakazi1);
		panelZakazi1.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblOdaberiKlijenta = new JLabel("Odaberi klijenta");
		//panelZakazi1.add(lblOdaberiKlijenta);
		
		
		klijenti = this.program.getKozmetickiSalon().getKlijenti();
		ArrayList<String> korisnickaImenaKlijenata = new ArrayList<String>();
		for(Klijent k: klijenti) {
			korisnickaImenaKlijenata.add(k.getKorisnickoIme());
		}
		String[] korisniciCombo = korisnickaImenaKlijenata.toArray(new String[korisnickaImenaKlijenata.size()]);
		klijentiModel = new DefaultComboBoxModel<String>(korisniciCombo);
		
		cbOdaberiKlijenta = new JComboBox(klijentiModel);
		//panelZakazi1.add(cbOdaberiKlijenta);
		
		if(this.program.getKorisnik().getUloga() == Korisnik.Uloga.RECEPCIONER) {
			panelZakazi1.add(lblOdaberiKlijenta);
			panelZakazi1.add(cbOdaberiKlijenta);
		}
		
		JLabel lblOdaberiVrstuTretmana = new JLabel("Odaberi vrstu tretmana");
		panelZakazi1.add(lblOdaberiVrstuTretmana);
		

		tipoviTretmana = program.getKozmetickiSalon().getTipoviTretmana();
		ArrayList<String> naziviTipovaTretmana = new ArrayList<String>();
		for(TipTretmana tt: tipoviTretmana) {
			naziviTipovaTretmana.add(tt.getNaziv());
		}
		String[] tipoviTretmanaCombo = naziviTipovaTretmana.toArray(new String[naziviTipovaTretmana.size()]);
		tipoviTretmanaModel = new DefaultComboBoxModel<String>(tipoviTretmanaCombo);
		JComboBox cbVrstaTretmana = new JComboBox(tipoviTretmanaModel);
		cbVrstaTretmana.setActionCommand("odabrana_vrsta_tretmana");
		cbVrstaTretmana.addActionListener(this);
		panelZakazi1.add(cbVrstaTretmana);
		
		JLabel lblOdaberiTretman = new JLabel("Odaberi tretman");
		panelZakazi1.add(lblOdaberiTretman);
		
		tretmani = program.getKozmetickiSalon().getTretmani();
		ArrayList<String> naziviTretmana = new ArrayList<String>();
		for (Tretman tretman: tretmani) {
			if(tretman.getOznakaTipaTretmana().equals(oznakaTipaTretmanaByNaziv((String)cbVrstaTretmana.getSelectedItem()))) 
				naziviTretmana.add(tretman.getNaziv());
		}
		
		tretmaniModel = new DefaultComboBoxModel<String>(naziviTretmana.toArray(new String[naziviTretmana.size()]));
		cbTretman = new JComboBox(tretmaniModel);
		panelZakazi1.add(cbTretman);
		
		JButton btnOdabirKozmeticara = new JButton("Odaberi kozmetičara");
		btnOdabirKozmeticara.setActionCommand("odaberi_kozmeticara");
		btnOdabirKozmeticara.addActionListener(this);
		panelZakazi1.add(btnOdabirKozmeticara);
		
		panelZakazi2 = new JPanel();
		//add(panelZakazi2, BorderLayout.CENTER);
		panelZakazi2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panelZakazi2.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblOdaberiKozmeticara = new JLabel("Odaberi kozmetičara");
		panel.add(lblOdaberiKozmeticara);
		
		kozmeticari = new ArrayList<Zaposleni>();
		obuke = program.getKozmetickiSalon().getObuke();
		
		if(izabranTretman != null) {
			for(Zaposleni z: program.getKozmetickiSalon().getZaposleni())
				if (z.getUloga().toString().equals("KOZMETICAR")) {

					for(Obuka o: obuke)
						if (z.getKorisnickoIme().equals(o.getZaposleni()) &&
							izabranTretman.getOznakaTipaTretmana().equals(o.getTipTretmana()))
							kozmeticari.add(z);
			}
		}
		
		ArrayList<String> kozmeticariKorisnickoIme = new ArrayList<String>();
		for(Zaposleni z: kozmeticari) kozmeticariKorisnickoIme.add(z.getKorisnickoIme());
		
		kozmeticariModel = new DefaultComboBoxModel<String>(kozmeticariKorisnickoIme.toArray(new String[kozmeticariKorisnickoIme.size()]));
		
		cbKozmeticar = new JComboBox(kozmeticariModel);
		
		panel.add(cbKozmeticar);
		
		JButton btnNazadTretman = new JButton("Nazad na odabir tretmana");
		btnNazadTretman.setActionCommand("nazad_na_odabir_tretmana");
		btnNazadTretman.addActionListener(this);
		panel.add(btnNazadTretman);
		
		JButton btnIzaberiTermin = new JButton("Izaberi termin");
		btnIzaberiTermin.setActionCommand("izaberi_termin");
		btnIzaberiTermin.addActionListener(this);
		panel.add(btnIzaberiTermin);
		
		panelZakazi3 = new JPanel();
		//add(panelZakazi3, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panelZakazi3.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 5, 5));	
		
		JLabel lblDatum = new JLabel("Datum");
		panel_1.add(lblDatum);
		
		textFieldDatum = new JTextField();
		panel_1.add(textFieldDatum);
		textFieldDatum.setColumns(10);
		
		JLabel lblVreme = new JLabel("Vreme");
		panel_1.add(lblVreme);
		
		textFieldVreme = new JTextField();
		panel_1.add(textFieldVreme);
		textFieldVreme.setColumns(10);
		
		JButton btnNazadOdabirKoz = new JButton("Nazad na odabir kozmetičara");
		btnNazadOdabirKoz.setActionCommand("nazad_na_odabir_kozmeticara");
		btnNazadOdabirKoz.addActionListener(this);
		panel_1.add(btnNazadOdabirKoz);
		
		JButton btnZakazi = new JButton("Zakaži");
		btnZakazi.setActionCommand("zakazi_termin");
		btnZakazi.addActionListener(this);
		panel_1.add(btnZakazi);
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("odabrana_vrsta_tretmana".equals(e.getActionCommand())) {
			JComboBox cb = (JComboBox)e.getSource();
	        String nazivTipaTretmana = (String)cb.getSelectedItem();
	        
	        tretmaniModel.removeAllElements();
	        ArrayList<String> tretmaniNaziv = new ArrayList<String>();
	        for(Tretman tretman: this.tretmani) {
	        	if(tretman.getOznakaTipaTretmana().equals(oznakaTipaTretmanaByNaziv(nazivTipaTretmana))) 
	        		this.tretmaniModel.addElement(tretman.getNaziv());

	        }
	        this.updateUI();
		} else if ("odaberi_kozmeticara".equals(e.getActionCommand())) {
			
			if(this.program.getKorisnik().getUloga() == Korisnik.Uloga.RECEPCIONER) {
				Klijent klijent = program.getKozmetickiSalon().getKlijentByKorisnickoIme((String) this.cbOdaberiKlijenta.getSelectedItem() );
				this.izabranKlijent = klijent;
			}
			
			for(Tretman tretman: tretmani)
				if(tretman.getNaziv().equals((String)this.cbTretman.getSelectedItem())) 
						this.izabranTretman = program.getKozmetickiSalon().getTretmanByOznaka(oznakaTretmanaByNaziv(tretman.getNaziv()));
			kozmeticariModel.removeAllElements();
			kozmeticari.clear();
			if(izabranTretman != null) {
				for(Zaposleni z: program.getKozmetickiSalon().getZaposleni())
					if (z.getUloga().toString().equals("KOZMETICAR")) {

						for(Obuka o: obuke)
							if (z.getKorisnickoIme().equals(o.getZaposleni()) &&
								izabranTretman.getOznakaTipaTretmana().equals(o.getTipTretmana()))
								kozmeticari.add(z);
				}
			}
			
			ArrayList<String> kozmeticariKorisnickoIme = new ArrayList<String>();
			for(Zaposleni z: kozmeticari) kozmeticariKorisnickoIme.add(z.getKorisnickoIme());
			
			for(String kozm: kozmeticariKorisnickoIme)
				kozmeticariModel.addElement(kozm);
			
			this.panelZakazi.remove(panelZakazi1);
			this.panelZakazi.add(panelZakazi2);
			this.panelZakazi.updateUI();
		} else if ("nazad_na_odabir_tretmana".equals(e.getActionCommand())) {
			this.panelZakazi.remove(panelZakazi2);
			this.panelZakazi.add(panelZakazi1);
			this.panelZakazi.updateUI();
		} else if ("izaberi_termin".equals(e.getActionCommand())) {
			izabranKozmeticar = program.getKozmetickiSalon().getZaposleniByKorisnickoIme((String)cbKozmeticar.getSelectedItem());
			this.panelZakazi.remove(panelZakazi2);
			this.panelZakazi.add(panelZakazi3);
			this.panelZakazi.updateUI();
			System.out.println(izabranKozmeticar.getKorisnickoIme());
		} else if ("nazad_na_odabir_kozmeticara".equals(e.getActionCommand())) {
			this.panelZakazi.remove(panelZakazi3);
			this.panelZakazi.add(panelZakazi2);
			this.panelZakazi.updateUI();
		} else if ("zakazi_termin".equals(e.getActionCommand()) ) {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;;
			try {
				date = df.parse(textFieldDatum.getText());
			} catch (ParseException e1) {
				System.err.println("Datum nije prepoznat!\n" + e1.getMessage());
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt( textFieldVreme.getText()));
			
			try {
				if(this.program.getKorisnik().getUloga() == Korisnik.Uloga.RECEPCIONER) {
					program.getKozmetickiSalon().zakaziTretman(izabranKlijent, izabranKozmeticar, izabranTretman, cal);
				} else if(this.program.getKorisnik().getUloga() == Korisnik.Uloga.KLIJENT) {
					program.getKozmetickiSalon().zakaziTretman((Klijent)program.getKorisnik(), izabranKozmeticar, izabranTretman, cal);
				}
				program.getKozmetickiSalon().zapamtiZakazaneTretmane();
				this.panelZakazi.remove(panelZakazi3);
				this.panelZakazi.add(panelZakazi1);
				this.updateUI();
			} catch (Exception e1) {
				//System.err.println("Zakazivanje tretmana nije uspelo!\n" + e1.getMessage());
				JOptionPane.showMessageDialog(this,
					    e1.getMessage(),
					    "Zakazivanje tretmana nije uspelo!",
					    JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
		}
		
	}
	
	private String oznakaTipaTretmanaByNaziv(String naziv) {
		for(TipTretmana tt: tipoviTretmana) {
			if (tt.getNaziv().equals(naziv)) return tt.getOznaka();
		}
		return null;
	}
	
	private String oznakaTretmanaByNaziv(String naziv) {
		for(Tretman t: tretmani) {
			if (t.getNaziv().equals(naziv)) return t.getOznaka();
		}
		return null;
	}
}
