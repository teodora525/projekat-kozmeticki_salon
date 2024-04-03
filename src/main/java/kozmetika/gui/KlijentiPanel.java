package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;

import kozmetika.stanje.Klijent;
import kozmetika.stanje.Korisnik;
import kozmetika.stanje.Korisnik.Pol;
import kozmetika.stanje.Korisnik.Uloga;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class KlijentiPanel extends KSPanel implements ActionListener//, ListSelectionListener 
{
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldKorisnickoIme;
	private JPasswordField passwordField;
	private JTextField textFieldAdresa;
	private JTextField textFieldTelefon;
	private JComboBox cbPol;
	private JTable table;
	private KlijentiTableModel klijentiTableModel;
	private Klijent selektovanKlijent;


	/**
	 * Create the panel.
	 */
	public KlijentiPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		

		this.klijentiTableModel = new KlijentiTableModel();
		klijentiTableModel.setKlijenti(this.getProgram().getKozmetickiSalon().getKlijenti());
		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panelForma = new JPanel();
		panel.add(panelForma);
		panelForma.setLayout(new GridLayout(5, 4, 5, 0));
		
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
		
		JLabel lblKLStanje = new JLabel("Stanje");
		panelForma.add(lblKLStanje);
		
		JLabel lblKLStanjeVrednost = new JLabel("");
		panelForma.add(lblKLStanjeVrednost);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setActionCommand("odustani");
		btnOdustani.addActionListener(this);
		panelForma.add(btnOdustani);
		
		JButton btnZapamti = new JButton("Zapamti");
		btnZapamti.setActionCommand("kreiraj_klijenta");
		btnZapamti.addActionListener(this);
		panelForma.add(btnZapamti);
		
		JButton btnObrisi = new JButton("Obriši");
		btnObrisi.setActionCommand("obrisi_klijenta");
		btnObrisi.addActionListener(this);
		panelForma.add(btnObrisi);
		
		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane);
		
		table = new JTable();
		table.setModel(klijentiTableModel);
		table.setFillsViewportHeight(true);
		//table.getSelectionModel().addListSelectionListener(this);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		
		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        redSelektovan(e);
		    }
		});
		
		scrollPane.setViewportView(table);
		
		
		
	}

	protected void redSelektovan(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;
		
		 
		final DefaultListSelectionModel target = (DefaultListSelectionModel)e.getSource();
		
		selektovanKlijent = this.getProgram().getKozmetickiSalon().getKlijenti().get( target.getAnchorSelectionIndex() );
		
		textFieldKorisnickoIme.setText(selektovanKlijent.getKorisnickoIme());
		textFieldIme.setText(selektovanKlijent.getIme());
		textFieldPrezime.setText(selektovanKlijent.getPrezime());
		cbPol.setSelectedItem(selektovanKlijent.getPol());
		textFieldAdresa.setText(selektovanKlijent.getAdresa());
		textFieldTelefon.setText(selektovanKlijent.getTelefon());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("novi_klijent".equals(e.getActionCommand())){
			ocistiFormu();
		} else if("kreiraj_klijenta".equals(e.getActionCommand())) {
			String pol = cbPol.getSelectedItem().toString();
			Klijent klijent = program.getKozmetickiSalon().getKlijentByKorisnickoIme(textFieldKorisnickoIme.getText());
			if(klijent == null) {
				this.program.getKozmetickiSalon().getKlijenti().add(new Klijent(textFieldKorisnickoIme.getText(), Uloga.KLIJENT,new String(passwordField.getPassword()),  textFieldIme.getText(), textFieldPrezime.getText(), Pol.valueOf(pol),
						textFieldTelefon.getText(), textFieldAdresa.getText(),  0.0));
				this.program.getKozmetickiSalon().zapamtiKlijente();
				selektovanKlijent = null;
			} else {
				klijent.setKorisnickoIme(textFieldKorisnickoIme.getText());
				klijent.setLozinka(new String( passwordField.getPassword()));
				klijent.setIme(textFieldIme.getText());
				klijent.setPrezime(textFieldPrezime.getText());
				klijent.setPol((Korisnik.Pol)cbPol.getSelectedItem());
				klijent.setUloga(Korisnik.Uloga.valueOf("KLIJENT"));
				klijent.setAdresa(textFieldAdresa.getText());
				klijent.setTelefon(textFieldTelefon.getText());
				
				program.getKozmetickiSalon().zapamtiKlijente();
				selektovanKlijent = null;
			}
			this.updateUI();
		}else if("odustani".equals(e.getActionCommand())) {
			ocistiFormu();
			selektovanKlijent = null;
			
		} else if("obrisi_klijenta".equals(e.getActionCommand())) {
			int index = this.table.getSelectedRow();
			program.getKozmetickiSalon().getKlijenti().remove(index);
			program.getKozmetickiSalon().zapamtiKlijente();
			selektovanKlijent = null;
			this.updateUI();
		}
		
	}
	
	private void ocistiFormu() {
		textFieldIme.setText("");
		textFieldPrezime.setText("");
		textFieldKorisnickoIme.setText("");
		passwordField.setText("");
		textFieldAdresa.setText("");
		textFieldTelefon.setText("");
	
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
