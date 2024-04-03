package kozmetika.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;

import kozmetika.ProgramSwing;
import kozmetika.stanje.Korisnik.Pol;
import kozmetika.stanje.Korisnik.Uloga;
import kozmetika.stanje.Klijent;
import kozmetika.stanje.Zaposleni;

@SuppressWarnings("serial")
public class RegistracijaPanel extends KSPanel implements ActionListener {
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldKorisnickoIme;
	private JPasswordField passwordField;
	private JTextField textFieldAdresa;
	private JTextField textFieldTelefon;
	private JComboBox cbPol;

	/**
	 * Create the panel.
	 * @param program 
	 */
	public RegistracijaPanel(ProgramSwing program) {
		this.program = program;
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelForma = new JPanel();
		panel.add(panelForma);
		panelForma.setLayout(new GridLayout(5, 4, 5, 0));
		
		JLabel lblIme = new JLabel("Ime");
		panelForma.add(lblIme);
		
		textFieldIme = new JTextField();
		textFieldIme.setColumns(10);
		panelForma.add(textFieldIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		panelForma.add(lblPrezime);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setColumns(10);
		panelForma.add(textFieldPrezime);
		
		JLabel lblKorisnickoIme = new JLabel("Korisničko ime");
		panelForma.add(lblKorisnickoIme);
		
		textFieldKorisnickoIme = new JTextField();
		textFieldKorisnickoIme.setColumns(10);
		panelForma.add(textFieldKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		panelForma.add(lblLozinka);
		
		passwordField = new JPasswordField();
		panelForma.add(passwordField);
		
		JLabel lblPol = new JLabel("Pol");
		panelForma.add(lblPol);
		
		this.cbPol = new JComboBox();
		this.cbPol.setModel(new DefaultComboBoxModel(Pol.values()));
		panelForma.add(this.cbPol);
		
		JLabel lblAdresa = new JLabel("Adresa");
		panelForma.add(lblAdresa);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setColumns(10);
		panelForma.add(textFieldAdresa);
		
		JLabel lblTelefon = new JLabel("Telefon");
		panelForma.add(lblTelefon);
		
		textFieldTelefon = new JTextField();
		textFieldTelefon.setColumns(10);
		panelForma.add(textFieldTelefon);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setActionCommand("prikazi_glavni_meni");
		btnOdustani.addActionListener(program);
		panelForma.add(btnOdustani);
		
		JButton btnZapamti = new JButton("Zapamti");
		btnZapamti.setActionCommand("kreiraj_klijenta");
		btnZapamti.addActionListener(this);
		panelForma.add(btnZapamti);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if("kreiraj_klijenta".equals(e.getActionCommand())){
			String pol = cbPol.getSelectedItem().toString();
			this.program.getKozmetickiSalon().getKlijenti().add((new Klijent(textFieldKorisnickoIme.getText(), Uloga.KLIJENT,new String(passwordField.getPassword()),  textFieldIme.getText(), textFieldPrezime.getText(), Pol.valueOf(pol),
             textFieldTelefon.getText(), textFieldAdresa.getText(),  0.0)));
			this.program.getKozmetickiSalon().zapamtiKlijente();

		}
	}

}
