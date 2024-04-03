package kozmetika;

import kozmetika.gui.*;
import kozmetika.stanje.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * ProgramSwing
 */
@SuppressWarnings("serial")
public class ProgramSwing extends JFrame implements ActionListener {

    private KozmetickiSalon kozmetickiSalon;
    private ProgramSwing program;
    private Korisnik korisnik = null;
    
    private JPanel panel;

    private PrijavaPanel prijavaPanel;
    private KorisnickiPanel korisnickiPanel;
    
    public ProgramSwing() {

        this.program = this;
        this.kozmetickiSalon = new KozmetickiSalon();

        if ("".equals(this.getKozmetickiSalon().getNaziv())) this.kozmetickiSalon.setNaziv("Moj salon");
        
        //ako korisnik nije ulogovan;
        if (this.korisnik == null) {
            this.panel = getGlavniPanel();
        } else {
        	this.panel = this.getKorisnickiPanel();
        }
    }

    public KozmetickiSalon getKozmetickiSalon() {
        return kozmetickiSalon;
    }

    public void setKozmetickiSalon(KozmetickiSalon kozmetickiSalon) {
        this.kozmetickiSalon = kozmetickiSalon;
    }

    public ProgramSwing getProgram() {
        return program;
    }

    public void setProgram(ProgramSwing program) {
        this.program = program;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    private JPanel getGlavniPanel() {
        JPanel panel = new GlavniPanel(this);
        return panel;
    }

	public KorisnickiPanel getKorisnickiPanel() {
		if (this.korisnickiPanel == null) {
			this.korisnickiPanel = new KorisnickiPanel(program);
		}
		
		return this.korisnickiPanel;
	}
    
    private RegistracijaPanel getRegistracijaPanel() {
    	return new RegistracijaPanel(this);
    	
    }

    private PrijavaPanel getPrijavaPanel() {
        if(this.prijavaPanel == null) this.prijavaPanel = new PrijavaPanel(this);
        return this.prijavaPanel;
    }

    
    private static void kreirajIPrikaziGUI() {
        ProgramSwing ps = new ProgramSwing();
        ps.setTitle("Kozmeticki salon " + ps.getKozmetickiSalon().getNaziv());
        ps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ps.setSize(800, 600);
        ps.render();

        ps.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if ("prijavi_se".equals(e.getActionCommand())) {
            this.remove(this.panel);
            this.panel = getPrijavaPanel();
            render();
        } else if ("registruj_se".equals(e.getActionCommand())) {
            this.remove(this.panel);
            this.panel = getRegistracijaPanel();
            render();
        } else if ("prikazi_glavni_meni".equals(e.getActionCommand())) {
            this.remove(this.panel);
            this.panel = getGlavniPanel();
            render();
        } else if ("odjavi_se".equals(e.getActionCommand())) {
        	this.setKorisnik(null);
            this.remove(this.panel);
            this.panel = getGlavniPanel();
            render();
            getPrijavaPanel().getTextFieldKorisnickoIme().setText("");
            getPrijavaPanel().getPasswordFieldLozinka().setText("");
            this.panel.updateUI();
        }else if ("autorizuj_korisnika".equals(e.getActionCommand())) {

            if (autorizujKorisnika(this.prijavaPanel.getTextFieldKorisnickoIme().getText(), new String(this.prijavaPanel.getPasswordFieldLozinka().getPassword()))) {
            	this.remove(this.panel);
            	this.korisnickiPanel = null;
            	this.panel = this.getKorisnickiPanel();
            }
            render();
        }else if ("zapamti_novog_klijenta".equals(e.getActionCommand())) {
            render();
        }
    }

    private void render() {
    	
    	Dimension d = this.getSize();
    	
    			
        this.getProgram().add(this.getPanel(), BorderLayout.CENTER);
        this.pack();
        this.setSize(d.width, d.height);
    }

    private Boolean autorizujKorisnika(String korisnickoIme, String lozinka) { 

    	this.korisnik = this.getKozmetickiSalon().getKlijentByKorisnickoIme(korisnickoIme);
    	if (this.korisnik == null) this.korisnik = this.getKozmetickiSalon().getZaposleniByKorisnickoIme(korisnickoIme);
    	
    	if (this.korisnik == null) {
        	this.prijavaPanel.getLblStatusPrijava().setText("Pogrešno korisničko ime ili lozinka!");
    		return false;
    	} else {
    		if (this.korisnik.getLozinka().equals(lozinka)) {
    			return true;
    		} else {
    			this.prijavaPanel.getLblStatusPrijava().setText("Pogrešno korisničko ime ili lozinka!");
    			return false;
    		}
    	}
    }
    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                kreirajIPrikaziGUI();
            }
        });
    }

}
