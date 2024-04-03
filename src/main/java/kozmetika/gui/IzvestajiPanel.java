package kozmetika.gui;

import javax.swing.JPanel;
import kozmetika.stanje.*;
import kozmetika.ProgramSwing;
import kozmetika.gui.KlijentiPanel.KlijentiTableModel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class IzvestajiPanel extends KSPanel implements ActionListener, ListSelectionListener {
	private ArrayList<Zaposleni> kozmeticari; 
	private ArrayList<Klijent> klijentiKLojalnosti; 
	private KozmeticariTableModel kozmeticariTableModel;
	private KlijentiTableModel klijentiTableModel;
	private JTextField textFieldMesec;
	private JTable table;
	private JTextField textFieldStanjeMesec;
	private JLabel lblTretmanaIzvrseno;
	private JLabel lblTretmanaNijeSePojavio;
	private JLabel lblTretmanaOtkazaoKlijent;
	private JLabel lblTretmanaOtkazaoSalon;
	private JTextField textFieldUslugaMesec;
	private JTable tabelaKlijentiKLojalnosti;
	private ArrayList<Tretman> tretmani;
	private DefaultComboBoxModel<String> tretmaniModel;
	private JLabel lblNazivUsluge2;
	private JComboBox comboBoxTretmanUsluga;
	private JLabel lblTipTretmana2;
	private JLabel lblBrojZakazanihTretmana2;
	private JLabel lblOstvareniPrihodi2;
	private JTextField textFieldMesecPrihodiIRashodi;
	private JLabel lblPrihodi;
	private JLabel lblRashodi;
	
	/**
	 * Create the panel.
	 * @param program 
	 */
	public IzvestajiPanel(ProgramSwing program) {
		this.program = program;
		
		
		kozmeticari = new ArrayList<Zaposleni>();
		
		for(Zaposleni kozmeticar: program.getKozmetickiSalon().getZaposleni()) {
			if("KOZMETICAR".equals(kozmeticar.getUloga().toString())){
				kozmeticari.add(kozmeticar);
			}	
		}
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Izveštaji po kozmetičaru", null, panel, null);
		this.kozmeticariTableModel = new KozmeticariTableModel();
		kozmeticariTableModel.setKozmeticari(kozmeticari);;
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Mesec");
		panel_5.add(lblNewLabel);
		
		textFieldMesec = new JTextField();
		textFieldMesec.setColumns(10);
		panel_5.add(textFieldMesec);
		
		JButton btnPrikazi = new JButton("Prikaži");
		btnPrikazi.setActionCommand("prikazi");
		btnPrikazi.addActionListener(this);
		panel_5.add(btnPrikazi);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);

		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(table);
		
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Izveštaji po razlozima", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblStanjeTretmanaMesec = new JLabel("Mesec");
		panel_6.add(lblStanjeTretmanaMesec);
		
		textFieldStanjeMesec = new JTextField();
		panel_6.add(textFieldStanjeMesec);
		textFieldStanjeMesec.setColumns(10);
		
		JButton btnPrikaziStanjeIzvestaj = new JButton("Prikaži");
		btnPrikaziStanjeIzvestaj.setActionCommand("prikazi_izvestaj_po_stanju");
		btnPrikaziStanjeIzvestaj.addActionListener(this);
		panel_6.add(btnPrikaziStanjeIzvestaj);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.CENTER);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		lblTretmanaIzvrseno = new JLabel("Tretmana izvršeno: ");
		GridBagConstraints gbc_lblTretmanaIzvrseno = new GridBagConstraints();
		gbc_lblTretmanaIzvrseno.insets = new Insets(0, 0, 5, 0);
		gbc_lblTretmanaIzvrseno.gridx = 0;
		gbc_lblTretmanaIzvrseno.gridy = 0;
		panel_7.add(lblTretmanaIzvrseno, gbc_lblTretmanaIzvrseno);
		
		lblTretmanaNijeSePojavio = new JLabel("Tretmana nije se pojavio: ");
		GridBagConstraints gbc_lblTretmanaNijeSePojavio = new GridBagConstraints();
		gbc_lblTretmanaNijeSePojavio.insets = new Insets(0, 0, 5, 0);
		gbc_lblTretmanaNijeSePojavio.gridx = 0;
		gbc_lblTretmanaNijeSePojavio.gridy = 1;
		panel_7.add(lblTretmanaNijeSePojavio, gbc_lblTretmanaNijeSePojavio);
		
		lblTretmanaOtkazaoKlijent = new JLabel("Tretmana otkazao klijent");
		GridBagConstraints gbc_lblTretmanaOtkazaoKlijent = new GridBagConstraints();
		gbc_lblTretmanaOtkazaoKlijent.insets = new Insets(0, 0, 5, 0);
		gbc_lblTretmanaOtkazaoKlijent.gridx = 0;
		gbc_lblTretmanaOtkazaoKlijent.gridy = 2;
		panel_7.add(lblTretmanaOtkazaoKlijent, gbc_lblTretmanaOtkazaoKlijent);
		
		lblTretmanaOtkazaoSalon = new JLabel("Tretmana otkazao salon");
		GridBagConstraints gbc_lblTretmanaOtkazaoSalon = new GridBagConstraints();
		gbc_lblTretmanaOtkazaoSalon.insets = new Insets(0, 0, 5, 0);
		gbc_lblTretmanaOtkazaoSalon.gridx = 0;
		gbc_lblTretmanaOtkazaoSalon.gridy = 3;
		panel_7.add(lblTretmanaOtkazaoSalon, gbc_lblTretmanaOtkazaoSalon);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Izveštaji o tretmanima", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblUsluga = new JLabel("Usluga");
		panel_8.add(lblUsluga);
		
		
		tretmani = program.getKozmetickiSalon().getTretmani();
		ArrayList<String> naziviTretmana = new ArrayList<String>();
		for (Tretman tretman: tretmani) {
			naziviTretmana.add(tretman.getNaziv());
		}
		
		tretmaniModel = new DefaultComboBoxModel<String>(naziviTretmana.toArray(new String[naziviTretmana.size()]));
		comboBoxTretmanUsluga = new JComboBox(tretmaniModel);
		panel_8.add(comboBoxTretmanUsluga);
		
		JLabel lblMesec = new JLabel("Mesec");
		panel_8.add(lblMesec);
		
		textFieldUslugaMesec = new JTextField();
		panel_8.add(textFieldUslugaMesec);
		textFieldUslugaMesec.setColumns(10);
		
		JButton btnUslugaPrikazi = new JButton("Prikaži");
		btnUslugaPrikazi.setActionCommand("prikazi_detalje_tretmana");
		btnUslugaPrikazi.addActionListener(this);
		panel_8.add(btnUslugaPrikazi);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblNazivUsluge1 = new JLabel("Naziv tretmana: ");
		panel_10.add(lblNazivUsluge1);
		
		lblNazivUsluge2 = new JLabel("");
		panel_10.add(lblNazivUsluge2);
		
		JLabel lblTipTretmana1 = new JLabel("Tip tretmana: ");
		panel_10.add(lblTipTretmana1);
		
		lblTipTretmana2 = new JLabel("");
		panel_10.add(lblTipTretmana2);
		
		JLabel lblBrojZakazanihTretmana1 = new JLabel("Broj zakazanih tretmana: ");
		panel_10.add(lblBrojZakazanihTretmana1);
		
		lblBrojZakazanihTretmana2 = new JLabel("");
		panel_10.add(lblBrojZakazanihTretmana2);
		
		JLabel lblOstvareniPrihodi1 = new JLabel("Ostvareni prihodi: ");
		panel_10.add(lblOstvareniPrihodi1);
		
		lblOstvareniPrihodi2 = new JLabel("");
		panel_10.add(lblOstvareniPrihodi2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Izveštaj o stanju kartice lojalnosti", null, panel_3, null);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblKlijentiKLojalnosti = new JLabel("Klijenti koji su ispunili uslov za karticu lojalnosti");
		panel_11.add(lblKlijentiKLojalnosti);

		this.klijentiTableModel = new KlijentiTableModel();
		this.klijentiKLojalnosti = new ArrayList<Klijent>();
		for(Klijent k: this.getProgram().getKozmetickiSalon().getKlijenti()) {
			if(k.getKarticaLojalnostiStanje() >= program.getKozmetickiSalon().getLimitKarticeLojalnosti())
				klijentiKLojalnosti.add(k);
		}
		
		klijentiTableModel.setKlijenti(klijentiKLojalnosti);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		tabelaKlijentiKLojalnosti = new JTable();
		scrollPane.setViewportView(tabelaKlijentiKLojalnosti);
		tabelaKlijentiKLojalnosti.setModel(klijentiTableModel);
		tabelaKlijentiKLojalnosti.setFillsViewportHeight(true);


		JPanel prihodiIRashodiPanel = new JPanel();
		prihodiIRashodiPanel.setLayout(new BorderLayout(0, 0));
		JPanel formaUpitaPanel = new JPanel();
		
		JLabel lblMesecPrihodiIRashodi = new JLabel("Mesec");
		textFieldMesecPrihodiIRashodi = new JTextField(10);
		JButton btnPrikaziPrihodiIRashodi = new JButton("Prikaži");
		btnPrikaziPrihodiIRashodi.setActionCommand("prikazi_prihode_i_rashode");
		btnPrikaziPrihodiIRashodi.addActionListener(this);
		 
		formaUpitaPanel.add(lblMesecPrihodiIRashodi);
		formaUpitaPanel.add(textFieldMesecPrihodiIRashodi);
		formaUpitaPanel.add(btnPrikaziPrihodiIRashodi);

		prihodiIRashodiPanel.add(formaUpitaPanel, BorderLayout.NORTH);

		JPanel prihodiIRashodi = new JPanel();
		prihodiIRashodi.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		prihodiIRashodiPanel.add(prihodiIRashodi);
		
		JPanel panel_4 = new JPanel();
		prihodiIRashodi.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblPrihodi = new JLabel("Prihodi");
		panel_4.add(lblPrihodi);
		
		lblRashodi = new JLabel("Rashodi");
		panel_4.add(lblRashodi);

		tabbedPane.addTab("Prihodi i rashodi", null, prihodiIRashodiPanel, null);
	}
	
	public int brojIzvrsenihTretmanaPoKozmeticaru(Zaposleni kozmeticar, int mesec) {
		ArrayList<ZakazaniTretman> zakazaniTretmani = program.getKozmetickiSalon().getTretmaniByKozmeticar(kozmeticar.getKorisnickoIme());
		int brojac = 0;
		for(ZakazaniTretman zakazaniTretman: zakazaniTretmani) {
			if(zakazaniTretman.getStanje().toString().equals("IZVRSEN") &&
				zakazaniTretman.getTermin().get(Calendar.MONTH) == mesec) {
				brojac += 1;
			}
		}
		return brojac;	
	}
		
	public Double prihodPoKozmeticaru(Zaposleni kozmeticar, int mesec) {
		ArrayList<ZakazaniTretman> zakazaniTretmani = program.getKozmetickiSalon().getTretmaniByKozmeticar(kozmeticar.getKorisnickoIme());
		Double prihod = 0.0;
		for(ZakazaniTretman zakazaniTretman: zakazaniTretmani) {
			if(zakazaniTretman.getStanje().toString().equals("IZVRSEN") &&
				zakazaniTretman.getTermin().get(Calendar.MONTH) == mesec) {
				prihod += zakazaniTretman.getCena();
			}
		}
		return prihod;
	}
	
	@SuppressWarnings("serial") 
	class KozmeticariTableModel extends AbstractTableModel {

		private String[] columnNames = {"Kozmetičar", "Broj tretmana", "Prihod"};
		private ArrayList<Zaposleni> kozmeticari;
		
		@Override
		public int getRowCount() {
			return kozmeticari.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			Zaposleni kozmeticar = getKozmeticari().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return kozmeticar.getKorisnickoIme();
			case 1:
				return brojIzvrsenihTretmanaPoKozmeticaru(kozmeticar, Integer.parseInt(textFieldMesec.getText()) -1) + "";
			case 2:
				return prihodPoKozmeticaru(kozmeticar, Integer.parseInt(textFieldMesec.getText()) - 1) + "";
			default:
				break;
			}
			return "";
		}
		
		public String getColumnName(int col) {
	        return columnNames[col];
	    }
		
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public ArrayList<Zaposleni> getKozmeticari() {
			return this.kozmeticari;
		}

		public void setKozmeticari(ArrayList<Zaposleni> kozmeticari) {
			this.kozmeticari = kozmeticari;
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if ("prikazi".equals(e.getActionCommand())) {
			KozmeticariTableModel kozmeticariTableModel = new KozmeticariTableModel();
			kozmeticariTableModel.setKozmeticari(kozmeticari);
			this.table.setModel(kozmeticariTableModel);
		} else if("prikazi_izvestaj_po_stanju".equals(e.getActionCommand())) {
			this.getLblTretmanaIzvrseno().setText(
					"Tretmana izvršeno: " + tretmanaPoStanjuUMesecu(program.getKozmetickiSalon().getZakazaniTretmani(),
							Integer.parseInt( this.getTextFieldStanjeMesec().getText() ) - 1, ZakazaniTretman.Stanje.valueOf("IZVRSEN")) );
			this.getLblTretmanaNijeSePojavio().setText(
					"Tretmana nije se pojavio: " + tretmanaPoStanjuUMesecu(program.getKozmetickiSalon().getZakazaniTretmani(),
							Integer.parseInt( this.getTextFieldStanjeMesec().getText() ) - 1, ZakazaniTretman.Stanje.valueOf("NIJE_SE_POJAVIO")) );
			this.getLblTretmanaOtkazaoKlijent().setText(
					"Tretmana otkazao klijent: " + tretmanaPoStanjuUMesecu(program.getKozmetickiSalon().getZakazaniTretmani(),
							Integer.parseInt( this.getTextFieldStanjeMesec().getText() ) - 1, ZakazaniTretman.Stanje.valueOf("OTKAZAO_KLIJENT")) );
			this.getLblTretmanaOtkazaoSalon().setText(
					"Tretmana otkazao salon: " + tretmanaPoStanjuUMesecu(program.getKozmetickiSalon().getZakazaniTretmani(),
							Integer.parseInt( this.getTextFieldStanjeMesec().getText() ) - 1, ZakazaniTretman.Stanje.valueOf("OTKAZAO_SALON")) );
		
		} else if("prikazi_detalje_tretmana".equals(e.getActionCommand())) {
			String nazivTretmana = (String)comboBoxTretmanUsluga.getSelectedItem();
			int mesec = Integer.parseInt( this.textFieldUslugaMesec.getText()) - 1;
			Tretman tretman = program.getKozmetickiSalon().getTretmanByOznaka(oznakaTretmanaByNaziv(nazivTretmana));
			lblNazivUsluge2.setText(tretman.getNaziv());
			lblTipTretmana2.setText( program.getKozmetickiSalon().getTipTretmanaByOznaka(tretman.getOznakaTipaTretmana()).getNaziv()  );
			lblBrojZakazanihTretmana2.setText("" + zakazanihTretmanaUMesecu(program.getKozmetickiSalon().getZakazaniTretmani(), mesec, tretman ));
			lblOstvareniPrihodi2.setText("" + prihodPoTretmanu(tretman, mesec));
		} else if("prikazi_prihode_i_rashode".equals(e.getActionCommand())) {
			int mesec = Integer.parseInt( this.textFieldMesecPrihodiIRashodi.getText()) - 1;

	        this.lblPrihodi.setText("Prihodi: " + program.getKozmetickiSalon().getPrihodi(mesec).toString());
	        
	        Double rashodi = 0.0;
	        for(Zaposleni zaposeni: program.getKozmetickiSalon().getZaposleni()){
	            rashodi += program.getKozmetickiSalon().getPlata(zaposeni, mesec);
	        }
	        this.lblRashodi.setText("Rashodi: " + rashodi.toString());

		}
	}

	public Double prihodPoTretmanu(Tretman tretman, int mesec) {
		ArrayList<ZakazaniTretman> zakazaniTretmani = program.getKozmetickiSalon().getZakazaniTretmani();
		Double prihod = 0.0;
		for(ZakazaniTretman zakazaniTretman: zakazaniTretmani) {
			if(zakazaniTretman.getStanje().toString().equals("IZVRSEN") &&
				zakazaniTretman.getTermin().get(Calendar.MONTH) == mesec &&
				zakazaniTretman.getOznakaTretmana().equals(tretman.getOznaka())) {
				prihod += zakazaniTretman.getCena();
			}
		}
		return prihod;
	}

	private int zakazanihTretmanaUMesecu(ArrayList<ZakazaniTretman> zakazaniTretmani, int mesec, Tretman tretman) {
		int brojac = 0;
		for(ZakazaniTretman zt: zakazaniTretmani)
			if ( zt.getOznakaTretmana().equals(tretman.getOznaka()) && 
					zt.getTermin().get(Calendar.MONTH) == mesec) brojac++;
		return brojac;
	}
	
	private String oznakaTretmanaByNaziv(String naziv) {
		for(Tretman t: tretmani) {
			if (t.getNaziv().equals(naziv)) return t.getOznaka();
		}
		return null;
	}

	private int tretmanaPoStanjuUMesecu(ArrayList<ZakazaniTretman> zakazaniTretmani, int mesec, ZakazaniTretman.Stanje stanje) {
		int brojac = 0;
		for(ZakazaniTretman zt: zakazaniTretmani)
			if (zt.getStanje() == stanje) brojac++;
		return brojac;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	protected JLabel getLblTretmanaIzvrseno() {
		return lblTretmanaIzvrseno;
	}
	protected JLabel getLblTretmanaNijeSePojavio() {
		return lblTretmanaNijeSePojavio;
	}
	protected JLabel getLblTretmanaOtkazaoKlijent() {
		return lblTretmanaOtkazaoKlijent;
	}
	protected JLabel getLblTretmanaOtkazaoSalon() {
		return lblTretmanaOtkazaoSalon;
	}
	protected JTextField getTextFieldStanjeMesec() {
		return textFieldStanjeMesec;
	}
	
	@SuppressWarnings("serial")
	public class KlijentiTableModel extends AbstractTableModel {

		private String[] columnNames = {"Korisnicko ime", "Ime", "Prezime", "Pol", "Adresa", "Telefon", "Stanje"};
		private ArrayList<Klijent> klijenti;
		
		@Override
		public int getRowCount() {
			return getKlijenti().size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			Klijent klijent = getKlijenti().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return klijent.getKorisnickoIme();
			case 1:
				return klijent.getIme();
			case 2:
				return klijent.getPrezime();
			case 3:
				return klijent.getPol().toString();
			case 4:
				return klijent.getAdresa();
			case 5:
				return klijent.getTelefon();
			case 6:
				return klijent.getKarticaLojalnostiStanje().toString();
				
			default:
				break;
			}
			
			return "";
		}
		
		public String getColumnName(int col) {
	        return columnNames[col];
	    }
		
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public ArrayList<Klijent> getKlijenti() {
			return klijenti;
		}

		public void setKlijenti(ArrayList<Klijent> klijenti) {
			this.klijenti = klijenti;
		}

	}
}





