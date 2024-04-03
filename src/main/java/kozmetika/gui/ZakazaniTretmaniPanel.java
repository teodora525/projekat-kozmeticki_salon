package kozmetika.gui;

import java.awt.BorderLayout;
import java.util.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.format.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import kozmetika.ProgramSwing;
import kozmetika.gui.TretmaniPanel.TretmanModel;
import kozmetika.stanje.Korisnik.Uloga;
import kozmetika.stanje.Tretman;
import kozmetika.stanje.ZakazaniTretman;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import kozmetika.stanje.ZakazaniTretman.Stanje;

@SuppressWarnings("serial")
public class ZakazaniTretmaniPanel extends KSPanel implements ActionListener {
	private ZakazaniTretman zakazaniTretmani;
	private JTable table;
	private JComboBox cbStanjeZakazanogTretmana;
	private ZakazaniTretmaniModel zakazaniTretmaniModel;
	/**
	 * Create the panel.
	 * @param program 
	 */
	public ZakazaniTretmaniPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		if (program.getKorisnik().getUloga().toString().equals("MENADZER") || 
				program.getKorisnik().getUloga().toString().equals("RECEPCIONER")) {
			JPanel panel_2 = new JPanel();
			panel.add(panel_2);
			panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
			cbStanjeZakazanogTretmana = new JComboBox();
			cbStanjeZakazanogTretmana.setModel(new DefaultComboBoxModel(Stanje.values()));
			panel_2.add(cbStanjeZakazanogTretmana);
		
			JButton btnPromeniStanje = new JButton("Promeni stanje");
			btnPromeniStanje.setActionCommand("promeni_stanje_tretmana");
			btnPromeniStanje.addActionListener(this);
			panel_2.add(btnPromeniStanje);
		} else {	
			JPanel panel_3 = new JPanel();
			panel.add(panel_3, BorderLayout.NORTH);
		
			JButton btnOtkaziTretman = new JButton("Otkaži tretman");
			btnOtkaziTretman.setActionCommand("otkazi_tretman");
			btnOtkaziTretman.addActionListener(this);
			panel_3.add(btnOtkaziTretman);
		}
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		table = new JTable();
		this.zakazaniTretmaniModel = new ZakazaniTretmaniModel();
		if (program.getKorisnik().getUloga().toString().equals("MENADZER") || program.getKorisnik().getUloga().toString().equals("RECEPCIONER")) {
			zakazaniTretmaniModel.setZakazaniTretmani(this.getProgram().getKozmetickiSalon().getZakazaniTretmani());
		} else if (program.getKorisnik().getUloga().toString().equals("KLIJENT")) {
			zakazaniTretmaniModel.setZakazaniTretmani(this.getProgram().getKozmetickiSalon().getTretmaniByKlijent(program.getKorisnik().getKorisnickoIme()));
		} else if (program.getKorisnik().getUloga().toString().equals("KOZMETICAR")) {
			zakazaniTretmaniModel.setZakazaniTretmani(this.getProgram().getKozmetickiSalon().getTretmaniByKozmeticar(program.getKorisnik().getKorisnickoIme()));
		}
		panel_1.setLayout(new BorderLayout(0, 0));
		table.setModel(zakazaniTretmaniModel);
		table.setFillsViewportHeight(true);
		
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		scrollPane.setViewportView(table);
	}

	public class ZakazaniTretmaniModel extends AbstractTableModel {

		private String[] columnNames = {"Oznaka", "Oznaka tretmana" ,"Termin", "Kozmetičar", "Klijent", "Cena", "Stanje"};
		private ArrayList<ZakazaniTretman> zakazaniTretmani;
		
		@Override
		public int getRowCount() {
			return zakazaniTretmani.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			ZakazaniTretman zakazaniTretman = getZakazaniTretmani().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return zakazaniTretman.getOznaka();
			case 1:
				return zakazaniTretman.getOznakaTretmana();
			case 2:
				GregorianCalendar vreme = (GregorianCalendar)zakazaniTretman.getTermin();
				return vreme.toZonedDateTime().format(DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm"));
			case 3:
				return zakazaniTretman.getKozmeticar();
			case 4:
				return zakazaniTretman.getKlijent();
			case 5:
				return zakazaniTretman.getCena().toString();
			case 6:
				return zakazaniTretman.getStanje().toString();
				
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

		public ArrayList<ZakazaniTretman> getZakazaniTretmani() {
			return zakazaniTretmani;
		}

		public void setZakazaniTretmani(ArrayList<ZakazaniTretman> zakazaniTretmani) {
			this.zakazaniTretmani = zakazaniTretmani;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("otkazi_tretman".equals(e.getActionCommand())) {
			ZakazaniTretman zakazaniTretman = zakazaniTretmaniModel.getZakazaniTretmani().get(table.getSelectedRow());
			try {
				ZakazaniTretman.Stanje novoStanje = null;
				if(program.getKorisnik().getUloga() == Uloga.KLIJENT) {
					novoStanje = ZakazaniTretman.Stanje.OTKAZAO_KLIJENT; 
				} else if(program.getKorisnik().getUloga() == Uloga.KOZMETICAR) {
					novoStanje = ZakazaniTretman.Stanje.OTKAZAO_SALON;
				}
				
				program.getKozmetickiSalon().zavrsiZakazaniTretman(zakazaniTretman, novoStanje);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this,
					    e1.getMessage(),
					    "Otkakazivanje tretmana nije uspelo!",
					    JOptionPane.ERROR_MESSAGE);
			}
			program.getKozmetickiSalon().zapamtiZakazaneTretmane();
			this.updateUI();
		} else if ("promeni_stanje_tretmana".equals(e.getActionCommand())) {
			ZakazaniTretman zakazaniTretman = zakazaniTretmaniModel.getZakazaniTretmani().get(table.getSelectedRow());
			try {
				program.getKozmetickiSalon().zavrsiZakazaniTretman(zakazaniTretman, (ZakazaniTretman.Stanje) cbStanjeZakazanogTretmana.getSelectedItem());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this,
					    e1.getMessage(),
					    "Otkazivanje tretmana nije uspelo!",
					    JOptionPane.ERROR_MESSAGE);
			}
			program.getKozmetickiSalon().zapamtiZakazaneTretmane();
			this.updateUI();
		}
		
	}

}
