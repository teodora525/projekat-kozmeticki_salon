package kozmetika.gui;

import javax.swing.JPanel;

import kozmetika.ProgramSwing;
import kozmetika.stanje.KozmetickiSalon;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class GlavniPanel extends KSPanel {

	/**
	 * Create the panel.
	 */
	public GlavniPanel(ProgramSwing program) {
		
		this.setProgram(program);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel naslovPanel = new JPanel();
		add(naslovPanel, BorderLayout.NORTH);
		
		JLabel lblNaslov = new JLabel("Dobrodošli u " + program.getKozmetickiSalon().getNaziv());
		lblNaslov.setVerticalAlignment(SwingConstants.TOP);
		naslovPanel.add(lblNaslov);
		
		JPanel kontrolePanel = new JPanel();
		add(kontrolePanel, BorderLayout.CENTER);
		kontrolePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnPrijaviSe = new JButton("Prijavi se");
		btnPrijaviSe.setActionCommand("prijavi_se");
		btnPrijaviSe.addActionListener(program);
		kontrolePanel.add(btnPrijaviSe);
		
		JButton btnRegistrujSe = new JButton("Registruj se");
		btnRegistrujSe.setActionCommand("registruj_se");
		btnRegistrujSe.addActionListener(program);
		kontrolePanel.add(btnRegistrujSe);
		
	}

}
