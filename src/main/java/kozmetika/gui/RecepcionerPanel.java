package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class RecepcionerPanel extends KSPanel {

	/**
	 * Create the panel.
	 */
	public RecepcionerPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		add(tabbedPane);
		
		KlijentiPanel klijentiPanel = new KlijentiPanel(this.program);
		tabbedPane.addTab("Klijenti", null, klijentiPanel, null);
		
		ZakazaniTretmaniPanel zakazaniTretmaniPanel = new ZakazaniTretmaniPanel(this.program);
		tabbedPane.addTab("Zakazani tretmani", null, zakazaniTretmaniPanel, null);
		
		ZakaziPanel zakaziPanel = new ZakaziPanel(this.program);
		tabbedPane.addTab("Zakaži", null, zakaziPanel, null);
		
		
	}

}
