package kozmetika.gui;

import kozmetika.ProgramSwing;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class KorisnickiPanel extends KSPanel {

	private KSPanel aktivniPanel;
	
	/**
	 * Create the panel.
	 */
	public KorisnickiPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnOdjaviSe = new JButton("Odjavi se");
		btnOdjaviSe.setActionCommand("odjavi_se");
		btnOdjaviSe.addActionListener(program);
		toolBar.add(btnOdjaviSe);
		
		if("MENADZER".equals(this.program.getKorisnik().getUloga().toString())) {
			ManagerPanel managerPanel = new ManagerPanel(this.program);
			setAktivniPanel(managerPanel);
			add(managerPanel, BorderLayout.CENTER);
		} else if("KOZMETICAR".equals(this.program.getKorisnik().getUloga().toString())) {
			KozmeticarPanel kozmeticarPanel = new KozmeticarPanel(this.program);
			setAktivniPanel(kozmeticarPanel);
			add(kozmeticarPanel, BorderLayout.CENTER);
		} else if("RECEPCIONER".equals(this.program.getKorisnik().getUloga().toString())) {
			RecepcionerPanel recepcionerPanel = new RecepcionerPanel(this.program);
			setAktivniPanel(recepcionerPanel);
			add(recepcionerPanel, BorderLayout.CENTER);
		} else if("KLIJENT".equals(this.program.getKorisnik().getUloga().toString())) {
			KlijentPanel klijentPanel = new KlijentPanel(this.program);
			setAktivniPanel(klijentPanel);
			add(klijentPanel, BorderLayout.CENTER);
		}
		
		
	}

	public KSPanel getAktivniPanel() {
		return aktivniPanel;
	}

	public void setAktivniPanel(KSPanel aktivniPanel) {
		this.aktivniPanel = aktivniPanel;
	}

}
