package kozmetika.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import kozmetika.ProgramSwing;

@SuppressWarnings("serial")
public class KlijentPanel extends KSPanel {

	
	/**
	 * Create the panel.
	 */
	public KlijentPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.NORTH);
		
		ZakazaniTretmaniPanel zakazaniTretmaniPanel = new ZakazaniTretmaniPanel(program);
		tabbedPane.addTab("Zakazani tretmani", null, zakazaniTretmaniPanel, null);
		
		ZakaziPanel zakaziPanel = new ZakaziPanel(program);
		tabbedPane.addTab("Zakaži", null, zakaziPanel, null);

		JPanel stanjeLojalnosti = new JPanel();
		JLabel lblStanjeNaKarticiLojanosti = new JLabel("Stanje na kartici lojalnosti: " + program.getKozmetickiSalon().getKlijentByKorisnickoIme(program.getKorisnik().getKorisnickoIme()).getKarticaLojalnostiStanje());
		stanjeLojalnosti.add(lblStanjeNaKarticiLojanosti);
		tabbedPane.addTab("Stanje na kartici lojalnosti", null, stanjeLojalnosti, null);
		
	}

}
