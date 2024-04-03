package kozmetika.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import kozmetika.ProgramSwing;
import kozmetika.gui.TipoviTretmanaPanel.TipoviTretmanaModel;
import kozmetika.stanje.TipTretmana;
import kozmetika.stanje.Tretman;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.GridLayout;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class TretmaniPanel extends KSPanel implements ActionListener {
	private JTextField textFieldOznaka;
	private JTextField textFieldNaziv;
	private JTextField textFieldOznakaTipaTretmana;
	private JTextField textFieldTrajanje;
	private JTextField textFieldCena;
	private JTable table;
	private TretmanModel tretmanModel;

	/**
	 * Create the panel.
	 * @param program 
	 */
	public TretmaniPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(4, 4, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Oznaka\r\n");
		panel.add(lblNewLabel);
		
		textFieldOznaka = new JTextField();
		panel.add(textFieldOznaka);
		textFieldOznaka.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Naziv\r\n");
		panel.add(lblNewLabel_1);
		
		textFieldNaziv = new JTextField();
		panel.add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Oznaka tipa tretmana");
		panel.add(lblNewLabel_2);
		
		textFieldOznakaTipaTretmana = new JTextField();
		panel.add(textFieldOznakaTipaTretmana);
		textFieldOznakaTipaTretmana.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Trajanje");
		panel.add(lblNewLabel_3);
		
		textFieldTrajanje = new JTextField();
		panel.add(textFieldTrajanje);
		textFieldTrajanje.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Cena");
		panel.add(lblNewLabel_4);
		
		textFieldCena = new JTextField();
		panel.add(textFieldCena);
		textFieldCena.setColumns(10);
		
		JButton btnZapamti = new JButton("Zapamti");
		btnZapamti.setActionCommand("kreiraj_tretman");
		btnZapamti.addActionListener(this);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		panel.add(lblNewLabel_6);
		panel.add(btnZapamti);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setActionCommand("ocisti");
		btnOdustani.addActionListener(this);
		panel.add(btnOdustani);
		
		JButton btnObrisi = new JButton("Obriši");
		btnObrisi.setActionCommand("obrisi");
		btnObrisi.addActionListener(this);
		panel.add(btnObrisi);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		table = new JTable();
		this.tretmanModel = new TretmanModel();
		tretmanModel.setTretmani(this.getProgram().getKozmetickiSalon().getTretmani());
		panel_1.setLayout(new BorderLayout(0, 0));
		table.setModel(tretmanModel);
		table.setFillsViewportHeight(true);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		
		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        redSelektovan(e);
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		scrollPane.setViewportView(table);
	}


	protected void redSelektovan(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
            return;
		
		 
		final DefaultListSelectionModel target = (DefaultListSelectionModel)e.getSource();
		
		Tretman selektovanTretman = this.getProgram().getKozmetickiSalon().getTretmani().get( target.getAnchorSelectionIndex() );
		
		//String oznaka;
		textFieldOznaka.setText(selektovanTretman.getOznaka());
	    //String naziv;
		textFieldNaziv.setText(selektovanTretman.getNaziv());
	    //String oznakaTipaTretmana;
		textFieldOznakaTipaTretmana.setText(selektovanTretman.getOznakaTipaTretmana());
	    //Duration trajanje;
		textFieldTrajanje.setText(selektovanTretman.getTrajanje().toString());
	    //Double cena;
		textFieldCena.setText(selektovanTretman.getCena().toString());
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if("kreiraj_tretman".equals(e.getActionCommand())) {
			Tretman tretman = program.getKozmetickiSalon().getTretmanByOznaka(textFieldOznaka.getText());
			if (tretman == null) {
				this.program.getKozmetickiSalon().getTretmani().add(new Tretman(textFieldOznaka.getText(), textFieldNaziv.getText(), textFieldOznakaTipaTretmana.getText(), Duration.parse(textFieldTrajanje.getText()), Double.parseDouble(textFieldCena.getText())));
			} else {
				tretman.setNaziv(textFieldNaziv.getText());
				tretman.setOznakaTipaTretmana(textFieldOznakaTipaTretmana.getText());
				tretman.setTrajanje(Duration.parse(textFieldTrajanje.getText()));
				tretman.setCena(Double.parseDouble( textFieldCena.getText()));
			}
			
			this.program.getKozmetickiSalon().zapamtiTretmane();
			this.updateUI();
		} else if("ocisti".equals(e.getActionCommand())) {
			ocisti();
		} else if ("obrisi".equals(e.getActionCommand())) {
			int index = this.table.getSelectedRow();
			program.getKozmetickiSalon().getTretmani().remove(index);
			this.program.getKozmetickiSalon().zapamtiTretmane();
			this.updateUI();
		}
		
	}
	
	private void ocisti() {
		this.textFieldNaziv.setText("");
		this.textFieldOznaka.setText("");
		this.textFieldOznakaTipaTretmana.setText("");
		this.textFieldTrajanje.setText("");
		this.textFieldCena.setText("");		
	}

	public class TretmanModel extends AbstractTableModel {

		private String[] columnNames = {"Oznaka", "Naziv","Tip tretmana", "Trajanje", "Cena"};
		private ArrayList<Tretman> tretmani;
		
		@Override
		public int getRowCount() {
			return tretmani.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			Tretman tretman = getTretmani().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return tretman.getOznaka();
			case 1:
				return tretman.getNaziv();
			case 2:
				return tretman.getOznakaTipaTretmana();
			case 3:
				return tretman.getTrajanje().toString();
			case 4:
				return tretman.getCena().toString();
				
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

		public ArrayList<Tretman> getTretmani() {
			return tretmani;
		}

		public void setTretmani(ArrayList<Tretman> tretmani) {
			this.tretmani = tretmani;
		}
	}

}
