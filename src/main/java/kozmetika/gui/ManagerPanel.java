package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class ManagerPanel extends KSPanel {

	/**
	 * Create the panel.
	 */
	public ManagerPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		add(tabbedPane);
		
		IzmenaKozmetickogSalonaPanel izmenaKozmetickogSalonaPanel = new IzmenaKozmetickogSalonaPanel(this.program);
		tabbedPane.addTab("Salon", null, izmenaKozmetickogSalonaPanel, null);
		
		KlijentiPanel klijentiPanel = new KlijentiPanel(this.program);
		tabbedPane.addTab("Klijenti", null, klijentiPanel, null);
		
		ZaposleniPanel zaposleniPanel = new ZaposleniPanel(this.program);
		tabbedPane.addTab("Zaposleni", null, zaposleniPanel, null);
		
		ObukePanel obukePanel = new ObukePanel(this.program);
		tabbedPane.addTab("Obuke", null, obukePanel, null);
		
		TipoviTretmanaPanel tipoviTretmanaPanel = new TipoviTretmanaPanel(this.program);
		tabbedPane.addTab("Tipovi tretmana", null, tipoviTretmanaPanel, null);
		
		TretmaniPanel tretmaniPanel = new TretmaniPanel(this.program);
		tabbedPane.addTab("Tretmani", null, tretmaniPanel, null);
		
		ZakazaniTretmaniPanel zakazaniTretmaniPanel = new ZakazaniTretmaniPanel(this.program);
		tabbedPane.addTab("Zakazani tretmani", null, zakazaniTretmaniPanel, null);
		
		IzvestajiPanel izvestajiPanel = new IzvestajiPanel(this.program);
		tabbedPane.addTab("Izveštaji", null, izvestajiPanel, null);
	}

}
