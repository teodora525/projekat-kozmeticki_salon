package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class KozmeticarPanel extends KSPanel {

	/**
	 * Create the panel.
	 */
	public KozmeticarPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.NORTH);
		
		ZakazaniTretmaniPanel zakazaniTretmaniPanel = new ZakazaniTretmaniPanel(this.program);
		tabbedPane.addTab("Zakazani tretmani", null, zakazaniTretmaniPanel, null);
		
		
	}

}
