package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;
import kozmetika.stanje.KozmetickiSalon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class IzmenaKozmetickogSalonaPanel extends KSPanel implements ActionListener {
	private JTextField textFieldImeSalona;
	private JTextField textFieldLimitKarticeLojalnosti;
	private JTextField textFieldBonusLimit;
	private JTextField textFieldRVOd;
	private JTextField textFieldRVDo;
	private JList list;

	/**
	 * Create the panel.
	 * @param program 
	 */
	public IzmenaKozmetickogSalonaPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelGrid = new JPanel();
		panel.add(panelGrid, BorderLayout.WEST);
		panelGrid.setLayout(new GridLayout(6, 2, 5, 5));
		
		JLabel lblImeSalona = new JLabel("Ime salona");
		panelGrid.add(lblImeSalona);
		
		textFieldImeSalona = new JTextField();
		textFieldImeSalona.setText(this.getProgram().getKozmetickiSalon().getNaziv());
		panelGrid.add(textFieldImeSalona);
		textFieldImeSalona.setColumns(10);
		
		JLabel lblRadniDani = new JLabel("Radni dani");
		panelGrid.add(lblRadniDani);
		
		JScrollPane scrollPane = new JScrollPane();
		panelGrid.add(scrollPane);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"ponedeljak", "utorak", "sreda", "četvrtak", "petak", "subota ", "nedelja"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		ArrayList<DayOfWeek> radniDani = this.getProgram().getKozmetickiSalon().getRadniDani();
		ArrayList<Integer> indeksi = new ArrayList<Integer>();
		for(DayOfWeek rd: radniDani) {
			switch (rd.toString()) {
			case "MONDAY":
				indeksi.add(0);
				break;
			case "TUESDAY":
				indeksi.add(1);
				break;
			case "WEDNESDAY":
				indeksi.add(2);
				break;
			case "THURSDAY":
				indeksi.add(3);
				break;
			case "FRIDAY":
				indeksi.add(4);
				break;
			case "SATURDAY":
				indeksi.add(5);
				break;
			case "SUNDAY":
				indeksi.add(6);
				break;
			default:
				break;
			}
		}
		
		
		list.setSelectedIndices(Arrays.stream(indeksi.toArray()).mapToInt(o -> (int)o).toArray());
		scrollPane.setViewportView(list);
		
		JLabel lblRadnoVreme = new JLabel("Radno vreme");
		panelGrid.add(lblRadnoVreme);
		
		JPanel panelRV = new JPanel();
		panelGrid.add(panelRV);
		
		textFieldRVOd = new JTextField();
		textFieldRVOd.setText(this.getProgram().getKozmetickiSalon().getRadnoVreme().satOtvaranja + "");
		panelRV.add(textFieldRVOd);
		textFieldRVOd.setColumns(10);
		
		JLabel lblDo = new JLabel("do");
		panelRV.add(lblDo);
		
		textFieldRVDo = new JTextField();
		textFieldRVDo.setText(this.getProgram().getKozmetickiSalon().getRadnoVreme().satZatvaranja + "");
		panelRV.add(textFieldRVDo);
		textFieldRVDo.setColumns(10);
		
		JLabel lblLimit = new JLabel("Limit kartice lojalnosti");
		panelGrid.add(lblLimit);
		
		textFieldLimitKarticeLojalnosti = new JTextField();
		textFieldLimitKarticeLojalnosti.setText(this.getProgram().getKozmetickiSalon().getLimitKarticeLojalnosti() + "");
		panelGrid.add(textFieldLimitKarticeLojalnosti);
		
		JLabel lblBonusLimit = new JLabel("Bonus limit");
		panelGrid.add(lblBonusLimit);
		
		textFieldBonusLimit = new JTextField();
		textFieldBonusLimit.setText(this.getProgram().getKozmetickiSalon().getBonusLimit() + "");
		panelGrid.add(textFieldBonusLimit);
		
		JButton btnZapamtiSalon = new JButton("Zapamti");
		btnZapamtiSalon.setActionCommand("zapamti_salon");
		btnZapamtiSalon.addActionListener(this);
		panelGrid.add(btnZapamtiSalon);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("zapamti_salon".equals(e.getActionCommand())) {
			program.getKozmetickiSalon().setNaziv(textFieldImeSalona.getText());
			int[] listaDana = list.getSelectedIndices();
			ArrayList<DayOfWeek> radniDani = new ArrayList<DayOfWeek>();
			for(int dan: listaDana) {
				switch (dan) {
				case 0:
					radniDani.add(DayOfWeek.MONDAY);
					break;
				case 1:
					radniDani.add(DayOfWeek.TUESDAY);
					break;
				case 2:
					radniDani.add(DayOfWeek.WEDNESDAY);
					break;
				case 3:
					radniDani.add(DayOfWeek.THURSDAY);
					break;
				case 4:
					radniDani.add(DayOfWeek.FRIDAY);
					break;
				case 5:
					radniDani.add(DayOfWeek.SATURDAY);
					break;
				case 6:
					radniDani.add(DayOfWeek.SUNDAY);
					break;
				default:
					break;
				}
			}
			
			program.getKozmetickiSalon().setRadniDani(radniDani);
			program.getKozmetickiSalon().setRadnoVreme(Integer.parseInt(textFieldRVOd.getText()), 0, Integer.parseInt(textFieldRVDo.getText()), 0);
			program.getKozmetickiSalon().setLimitKarticeLojalnosti(Double.parseDouble(textFieldLimitKarticeLojalnosti.getText()));
			program.getKozmetickiSalon().setBonusLimit(Double.parseDouble(textFieldBonusLimit.getText()));

			program.getKozmetickiSalon().zapamtiSalon();
		}
		
	}

}
