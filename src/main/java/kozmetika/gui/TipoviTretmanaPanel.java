package kozmetika.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import kozmetika.ProgramSwing;
import kozmetika.gui.KlijentiPanel.KlijentiTableModel;
import kozmetika.stanje.Klijent;
import kozmetika.stanje.TipTretmana;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class TipoviTretmanaPanel extends KSPanel implements ActionListener  {
	private JTextField textFieldOznaka;
	private JTextField textFieldNaziv;
	private JTable table;
	private TipoviTretmanaModel tipoviTretmanaModel;
	private TipTretmana selektovaniTipTretmana;
	private JTable table_1;

	/**
	 * Create the panel.
	 * @param program 
	 */
	
	
	
	public TipoviTretmanaPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 4, 0, 0));
		
		JLabel lblOznaka = new JLabel("Oznaka\r\n");
		panel.add(lblOznaka);
		
		textFieldOznaka = new JTextField();
		panel.add(textFieldOznaka);
		textFieldOznaka.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Naziv");
		panel.add(lblNewLabel);
		
		textFieldNaziv = new JTextField();
		panel.add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setActionCommand("ocisti");
		btnOdustani.addActionListener(this);
		panel.add(btnOdustani);
		
		JButton btnZapamti = new JButton("Zapamti");
		btnZapamti.setActionCommand("kreiraj_tip_tretmana");
		btnZapamti.addActionListener(this);
		panel.add(btnZapamti);
		
		JButton btnObrisi = new JButton("Obriši");
		btnObrisi.setActionCommand("obrisi_tip_tretmana");
		btnObrisi.addActionListener(this);
		panel.add(btnObrisi);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		
		
		table = new JTable();
		this.tipoviTretmanaModel = new TipoviTretmanaModel();
		tipoviTretmanaModel.setTipoviTretmana(this.getProgram().getKozmetickiSalon().getTipoviTretmana());
		panel_1.setLayout(new BorderLayout(0, 0));
		table.setModel(tipoviTretmanaModel);
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
		
		TipTretmana tipTretmana = this.getProgram().getKozmetickiSalon().getTipoviTretmana().get( target.getAnchorSelectionIndex() );
		
		textFieldOznaka.setText(tipTretmana.getOznaka());
		textFieldNaziv.setText(tipTretmana.getNaziv());

		
	}

	@SuppressWarnings("serial")
	public class TipoviTretmanaModel extends AbstractTableModel {

		private String[] columnNames = {"Oznaka", "Naziv"};
		private ArrayList<TipTretmana> tipoviTretmana;
		
		@Override
		public int getRowCount() {
			return tipoviTretmana.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			TipTretmana tipTretmana = getTipoviTretmana().get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				return tipTretmana.getOznaka();
			case 1:
				return tipTretmana.getNaziv();
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

		public ArrayList<TipTretmana> getTipoviTretmana() {
			return tipoviTretmana;
		}

		public void setTipoviTretmana(ArrayList<TipTretmana> tipoviTretmana) {
			this.tipoviTretmana = tipoviTretmana;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if("kreiraj_tip_tretmana".equals(e.getActionCommand())) {
			TipTretmana tipTretmana = program.getKozmetickiSalon().getTipTretmanaByOznaka(textFieldOznaka.getText());
			if (tipTretmana == null) {
				this.program.getKozmetickiSalon().getTipoviTretmana().add(new TipTretmana(textFieldOznaka.getText(), textFieldNaziv.getText()));
			} else {
				tipTretmana.setNaziv(textFieldNaziv.getText());
			}
			this.program.getKozmetickiSalon().zapamtiTipoveTretmana();
			this.updateUI();
		} else if("ocisti".equals(e.getActionCommand())) {
			ocisti();
		} else if ("obrisi_tip_tretmana".equals(e.getActionCommand())) {
			TipTretmana tipTretmana = program.getKozmetickiSalon().getTipTretmanaByOznaka(textFieldOznaka.getText());
			program.getKozmetickiSalon().getTipoviTretmana().remove(tipTretmana);
			this.program.getKozmetickiSalon().zapamtiTipoveTretmana();
			this.updateUI();
		}
		
	}

	private void ocisti() {
		this.textFieldNaziv.setText("");
		this.textFieldOznaka.setText("");
		
	}

}
