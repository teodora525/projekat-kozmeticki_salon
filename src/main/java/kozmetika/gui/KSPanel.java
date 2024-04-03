package kozmetika.gui;


import kozmetika.ProgramSwing;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KSPanel extends JPanel {

	protected ProgramSwing program;
	
	/**
	 * Create the panel.
	 */
	public KSPanel() {

	}

	public ProgramSwing getProgram() {
		return program;
	}

	public void setProgram(ProgramSwing program) {
		this.program = program;
	}

}
