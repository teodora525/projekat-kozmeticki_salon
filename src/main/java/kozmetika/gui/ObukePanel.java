package kozmetika.gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import kozmetika.ProgramSwing;
import kozmetika.gui.KlijentiPanel.KlijentiTableModel;
import kozmetika.stanje.Klijent;
import kozmetika.stanje.Korisnik;
import kozmetika.stanje.Obuka;
import kozmetika.stanje.TipTretmana;
import kozmetika.stanje.Tretman;
import kozmetika.stanje.Zaposleni;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class ObukePanel extends KSPanel implements ActionListener {
	private JTable table;
	private JComboBox cbTipTretmana;
	private JComboBox cbKozmeticar;
	
	private ArrayList<String> oznakeTipovaTretmana;
	private ArrayList<String> kozmeticariKorisnickaImena;
	
	private DefaultComboBoxModel<String> tipoviTretmanaModel;
	private DefaultComboBoxModel<String> kozmeticariModel;
	
	private ObukeTableModel obukeTableModel;
	private Obuka selektovanaObuka;
	
	
	/**
	 * Create the panel.
	 */
	public ObukePanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblTipTretmana = new JLabel("Tip tretmana");
		panel.add(lblTipTretmana);
		

		oznakeTipovaTretmana = new ArrayList<String>();
		for (TipTretmana tt:program.getKozmetickiSalon().getTipoviTretmana()) {
			oznakeTipovaTretmana.add(tt.getOznaka());
		}
		
		tipoviTretmanaModel = new DefaultComboBoxModel<String>(oznakeTipovaTretmana.toArray(new String[oznakeTipovaTretmana.size()]));
		cbTipTretmana = new JComboBox(tipoviTretmanaModel);
		panel.add(cbTipTretmana);
		
		JLabel lblKozmeticar = new JLabel("Kozmeticar");
		panel.add(lblKozmeticar);
		
		kozmeticariKorisnickaImena = new ArrayList<String>();
		for (Zaposleni z: program.getKozmetickiSalon().getZaposleni()) {
			if (z.getUloga() == Korisnik.Uloga.KOZMETICAR) kozmeticariKorisnickaImena.add(z.getKorisnickoIme());
		}
		
		kozmeticariModel =  new DefaultComboBoxModel<String>(kozmeticariKorisnickaImena.toArray(new String[kozmeticariKorisnickaImena.size()]));
		cbKozmeticar = new JComboBox(kozmeticariModel);
		panel.add(cbKozmeticar);
		
		JButton btnObrisi = new JButton("Obriši");
		btnObrisi.setActionCommand("obrisi_obuku");
		btnObrisi.addActionListener(this);
		panel.add(btnObrisi);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setActionCommand("dodaj_obuku");
		btnDodaj.addActionListener(this);
		panel.add(btnDodaj);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		this.obukeTableModel = new ObukeTableModel();
		this.obukeTableModel.setObuke(this.getProgram().getKozmetickiSalon().getObuke());
		
		table = new JTable();
		table.setModel(obukeTableModel);
		table.setFillsViewportHeight(true);
		
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
		
		selektovanaObuka = this.getProgram().getKozmetickiSalon().getObuke().get( target.getAnchorSelectionIndex() );
		
		cbTipTretmana.setSelectedItem(selektovanaObuka.getTipTretmana());
		cbKozmeticar.setSelectedItem(selektovanaObuka.getZaposleni());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("obrisi_obuku".equals(e.getActionCommand())){
			table.getSelectedRow();
			program.getKozmetickiSalon().getObuke().remove(table.getSelectedRow());
			program.getKozmetickiSalon().zapamtiObuke();
			this.updateUI();
		} else if("dodaj_obuku".equals(e.getActionCommand())) {
			Obuka obuka = new Obuka();
			obuka.setTipTretmana((String) cbTipTretmana.getSelectedItem());
			obuka.setZaposleni((String) cbKozmeticar.getSelectedItem());
			program.getKozmetickiSalon().getObuke().add(obuka);
			program.getKozmetickiSalon().zapamtiObuke();
			this.updateUI();
		}
		
	}
	
	@SuppressWarnings("serial")
	public class ObukeTableModel extends AbstractTableModel {

		private String[] columnNames = {"Obuka", "Kozmetičar"};
		private ArrayList<Obuka> obuke;
		

		@Override
		public int getRowCount() {
			return getObuke().size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			Obuka obuka = getObuke().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return obuka.getTipTretmana();
			case 1:
				return obuka.getZaposleni();
				
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
		
		public ArrayList<Obuka> getObuke() {
			return obuke;
		}

		public void setObuke(ArrayList<Obuka> obuke) {
			this.obuke = obuke;
		}

	}

}
