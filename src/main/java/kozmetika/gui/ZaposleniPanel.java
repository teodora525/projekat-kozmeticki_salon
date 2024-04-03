package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;

import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;

import kozmetika.stanje.Zaposleni.StrucnaSprema;
import kozmetika.stanje.Korisnik.Uloga;
import kozmetika.stanje.Zaposleni;
import kozmetika.stanje.Korisnik;
import kozmetika.stanje.Korisnik.Pol;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ZaposleniPanel extends KSPanel implements ActionListener, ListSelectionListener {
	private JTable table;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldKorisnickoIme;
	private JPasswordField passwordField;
	private JTextField textFieldAdresa;
	private JTextField textFieldTelefon;
	private JTextField textFieldOsnova;
	private JTextField textFieldStaz;
	private JComboBox cbPol;
	private JComboBox cbUloga;
	private JComboBox cbStrucnaSprema;
	private ZaposleniTableModel zaposleniTableModel;
	private Zaposleni selektovanZaposleni;


	/**
	 * Create the panel.
	 * @param program 
	 */
	public ZaposleniPanel(ProgramSwing program) {
		this.setProgram(program);
		
		
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JPanel panelSadrzaj = new JPanel();
		add(panelSadrzaj, BorderLayout.CENTER);
		panelSadrzaj.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTabela = new JPanel();
		panelSadrzaj.add(panelTabela, BorderLayout.EAST);
		panelTabela.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panelSadrzaj.add(panel, BorderLayout.CENTER);
		
		JPanel panelForma = new JPanel();
		panelForma.setBounds(0, 0, 450, 97);
		panel.add(panelForma);
		panelForma.setLayout(new GridLayout(7, 4, 5, 0));
		
		JLabel lblIme = new JLabel("Ime");
		panelForma.add(lblIme);
		
		textFieldIme = new JTextField();
		textFieldIme.setColumns(10);
		panelForma.add(textFieldIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		panelForma.add(lblPrezime);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setColumns(10);
		panelForma.add(textFieldPrezime);
		
		JLabel lblKorisnickoIme = new JLabel("Korisničko ime");
		panelForma.add(lblKorisnickoIme);
		
		textFieldKorisnickoIme = new JTextField();
		textFieldKorisnickoIme.setColumns(10);
		panelForma.add(textFieldKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		panelForma.add(lblLozinka);
		
		passwordField = new JPasswordField();
		panelForma.add(passwordField);
		
		JLabel lblPol = new JLabel("Pol");
		panelForma.add(lblPol);
		
		this.cbPol = new JComboBox();
		this.cbPol.setModel(new DefaultComboBoxModel(Pol.values()));
		panelForma.add(this.cbPol);
		
		JLabel lblAdresa = new JLabel("Adresa");
		panelForma.add(lblAdresa);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setColumns(10);
		panelForma.add(textFieldAdresa);
		
		JLabel lblTelefon = new JLabel("Telefon");
		panelForma.add(lblTelefon);
		
		textFieldTelefon = new JTextField();
		textFieldTelefon.setColumns(10);
		panelForma.add(textFieldTelefon);
		
		JLabel lblUloga = new JLabel("Uloga");
		panelForma.add(lblUloga);
		
		this.cbUloga = new JComboBox();
		this.cbUloga.setModel(new DefaultComboBoxModel(Uloga.values()));
		panelForma.add(this.cbUloga);
		
		JLabel lblOsnova = new JLabel("Osnova");
		panelForma.add(lblOsnova);
		
		textFieldOsnova = new JTextField();
		panelForma.add(textFieldOsnova);
		textFieldOsnova.setColumns(10);
		
		JLabel lblStaz = new JLabel("Staž");
		panelForma.add(lblStaz);
		
		textFieldStaz = new JTextField();
		panelForma.add(textFieldStaz);
		textFieldStaz.setColumns(10);
		
		JLabel lblStrucnaSprema = new JLabel("Stručna sprema");
		panelForma.add(lblStrucnaSprema);
		
		this.cbStrucnaSprema = new JComboBox();
		this.cbStrucnaSprema.setModel(new DefaultComboBoxModel(StrucnaSprema.values()));
		panelForma.add(this.cbStrucnaSprema);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setActionCommand("prikazi_glavni_meni");
		btnOdustani.addActionListener(program);
		panelForma.add(btnOdustani);
		
		JButton btnZapamti = new JButton("Zapamti");
		btnZapamti.setActionCommand("kreiraj_zaposlenog");
		btnZapamti.addActionListener(this);
		panelForma.add(btnZapamti);
		
		JButton btnObrisi = new JButton("Obriši");
		btnObrisi.setActionCommand("obrisi_zaposlenog");
		btnObrisi.addActionListener(this);
		panelForma.add(btnObrisi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 105, 665, 402);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		this.zaposleniTableModel = new ZaposleniTableModel();
		zaposleniTableModel.setZaposleni(this.getProgram().getKozmetickiSalon().getZaposleni());
		table.setModel(zaposleniTableModel);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		
		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        redSelektovan(e);
		    }
		});
		
		JPanel statusPanel = new JPanel();
		add(statusPanel, BorderLayout.WEST);
	}

	protected void redSelektovan(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
            return;
		
		 
		final DefaultListSelectionModel target = (DefaultListSelectionModel)e.getSource();
		
		selektovanZaposleni = this.getProgram().getKozmetickiSalon().getZaposleni().get( target.getAnchorSelectionIndex() );
		textFieldIme.setText(selektovanZaposleni.getIme());;
		textFieldPrezime.setText(selektovanZaposleni.getPrezime());
		textFieldKorisnickoIme.setText(selektovanZaposleni.getKorisnickoIme());
		textFieldAdresa.setText(selektovanZaposleni.getAdresa());
		textFieldTelefon.setText(selektovanZaposleni.getTelefon());
		textFieldOsnova.setText(selektovanZaposleni.getOsnova().toString());
		textFieldStaz.setText(selektovanZaposleni.getStaz().toString());
		cbPol.setSelectedItem(selektovanZaposleni.getPol());
		cbUloga.setSelectedItem(selektovanZaposleni.getUloga());
		cbStrucnaSprema.setSelectedItem(selektovanZaposleni.getStrucnaSprema());
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("kreiraj_zaposlenog".equals(e.getActionCommand())){
			Zaposleni zaposleni = program.getKozmetickiSalon().getZaposleniByKorisnickoIme(textFieldKorisnickoIme.getText());
			if (zaposleni == null) {
				String pol = cbPol.getSelectedItem().toString();
				String uloga = cbUloga.getSelectedItem().toString();
				String nivoStrucneSpreme = cbStrucnaSprema.getSelectedItem().toString();
				this.program.getKozmetickiSalon().getZaposleni().add(new Zaposleni(textFieldKorisnickoIme.getText(), Uloga.valueOf(uloga),new String(passwordField.getPassword()),  textFieldIme.getText(), textFieldPrezime.getText(), Pol.valueOf(pol),
						textFieldTelefon.getText(), textFieldAdresa.getText(), StrucnaSprema.valueOf(nivoStrucneSpreme), Double.parseDouble(textFieldStaz.getText()), Double.parseDouble(textFieldOsnova.getText())));
				
			} else {
				zaposleni.setIme(textFieldIme.getText());
				zaposleni.setPrezime(textFieldPrezime.getText());
				zaposleni.setKorisnickoIme(textFieldKorisnickoIme.getText());
				zaposleni.setAdresa( textFieldAdresa.getText());
				zaposleni.setTelefon( textFieldTelefon.getText());
				zaposleni.setOsnova(Double.parseDouble(textFieldOsnova.getText()));
				zaposleni.setStaz(Double.parseDouble(textFieldStaz.getText()));
				zaposleni.setPol((Korisnik.Pol) cbPol.getSelectedItem() );
				zaposleni.setUloga((Korisnik.Uloga)cbUloga.getSelectedItem());
				zaposleni.setStrucnaSprema((Zaposleni.StrucnaSprema) cbStrucnaSprema.getSelectedItem());
			}
			this.program.getKozmetickiSalon().zapamtiZaposlene();
			this.updateUI();
		} else if ("obrisi_zaposlenog".equals(e.getActionCommand())) {
			int index = this.table.getSelectedRow();
			program.getKozmetickiSalon().getZaposleni().remove(index);
			program.getKozmetickiSalon().zapamtiZaposlene();
			this.updateUI();
		}
	}
		
	
	@SuppressWarnings("serial")
	public class ZaposleniTableModel extends AbstractTableModel {

		private String[] columnNames = {"Korisnicko ime", "Ime", "Prezime", "Pol", "Adresa", "Telefon", 
				"Uloga", "Osnova", "Staž", "Stručna sprema", "Bonus", "Plata"};
		private ArrayList<Zaposleni> zaposleni;
		
		@Override
		public int getRowCount() {
			return getZaposleni().size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			Zaposleni zaposleni = getZaposleni().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return zaposleni.getKorisnickoIme();
			case 1:
				return zaposleni.getIme();
			case 2:
				return zaposleni.getPrezime();
			case 3:
				return zaposleni.getPol().toString();
			case 4:
				return zaposleni.getAdresa();
			case 5:
				return zaposleni.getTelefon();
			case 6:
				return zaposleni.getUloga().toString();
			case 7:
				return zaposleni.getOsnova().toString();
			case 8:
				return zaposleni.getStaz().toString();
			case 9:
				return zaposleni.getStrucnaSprema().toString();
			case 10:
				return "" + program.getKozmetickiSalon().getBonus(zaposleni.getKorisnickoIme(), LocalDate.now().getMonthValue());
			case 11:
				return "" + program.getKozmetickiSalon().getPlata(zaposleni, LocalDate.now().getMonthValue());
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

		public ArrayList<Zaposleni> getZaposleni() {
			return zaposleni;
		}

		public void setZaposleni(ArrayList<Zaposleni> zaposleni) {
			this.zaposleni = zaposleni;
		}

	}

}
