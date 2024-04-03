package kozmetika.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kozmetika.ProgramSwing;

@SuppressWarnings("serial")
public class PrijavaPanel extends KSPanel {
	private JTextField textFieldKorisnickoIme;
	private JPasswordField passwordFieldLozinka;
	private JLabel lblStatusPrijava;
	

	/**
	 * Create the panel.
	 */
	public PrijavaPanel(ProgramSwing program) {
		this.setProgram(program);
		setLayout(new BorderLayout(0, 0));
		
		JPanel naslovPanel = new JPanel();
		add(naslovPanel, BorderLayout.NORTH);
		naslovPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNaslov = new JLabel("Prijava korisnika");
		naslovPanel.add(lblNaslov);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JPanel kontrolePanel = new JPanel();
		panel.add(kontrolePanel);
		kontrolePanel.setLayout(new GridLayout(3, 2, 5, 5));
		
		JLabel lblKorisnickoIme = new JLabel("Korisničko ime");
		kontrolePanel.add(lblKorisnickoIme);
		lblKorisnickoIme.setLabelFor(textFieldKorisnickoIme);
		
		textFieldKorisnickoIme = new JTextField();
		textFieldKorisnickoIme.setColumns(10);
		kontrolePanel.add(textFieldKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		kontrolePanel.add(lblLozinka);
		lblLozinka.setLabelFor(passwordFieldLozinka);
		
		passwordFieldLozinka = new JPasswordField();
		kontrolePanel.add(passwordFieldLozinka);
		
		JButton btnGlavniPanel = new JButton("Povratak");
		btnGlavniPanel.setActionCommand("prikazi_glavni_meni");
		btnGlavniPanel.addActionListener(program);
		kontrolePanel.add(btnGlavniPanel);
		
		JButton btnAutorizacija = new JButton("Prijavi se");
		btnAutorizacija.setActionCommand("autorizuj_korisnika");
		btnAutorizacija.addActionListener(program);
		kontrolePanel.add(btnAutorizacija);	
		
		JPanel statusPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) statusPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(statusPanel, BorderLayout.SOUTH);
		
		lblStatusPrijava = new JLabel("");
		lblStatusPrijava.setForeground(new Color(255, 0, 0));
		statusPanel.add(lblStatusPrijava);
	}

	public JTextField getTextFieldKorisnickoIme() {
		return textFieldKorisnickoIme;
	}

	public void setTextFieldKorisnickoIme(JTextField textFieldKorisnickoIme) {
		this.textFieldKorisnickoIme = textFieldKorisnickoIme;
	}

	public JPasswordField getPasswordFieldLozinka() {
		return passwordFieldLozinka;
	}

	public void setPasswordFieldLozinka(JPasswordField passwordFieldLozinka) {
		this.passwordFieldLozinka = passwordFieldLozinka;
	}
	public JLabel getLblStatusPrijava() {
		return lblStatusPrijava;
	}
}
