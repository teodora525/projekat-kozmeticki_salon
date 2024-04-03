
package kozmetika.stanje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * SmestajPodataka
 */
public class SmestajPodataka {

	private static String putanjaFajla = "";
	private File tk = null;


	public SmestajPodataka(String putanjaFajla) {

		this.tk = new File(putanjaFajla);
		// Ako fajl ne postoji, kreiraj ga
		if (!tk.exists()) {
			try {
				tk.createNewFile();
			} catch (IOException e) {
				System.out.println("Greška prilikom kreiranja fajla " + putanjaFajla);
				e.printStackTrace();
			}
		}
	}

    	public ArrayList<String> vratiPodatke() {
		ArrayList<String> linije = new ArrayList<String>();
		
		try {
			Scanner tkScanner = new Scanner(tk);
		      while (tkScanner.hasNextLine()) {
			  linije.add(tkScanner.nextLine());
		      }
		      tkScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Greška prilikom čitanja fajla " + putanjaFajla);
			e.printStackTrace();
		}
		
		return linije;
	}

	public void zapamtiPodatke(ArrayList<String> csvLista) {
	    tk.delete();
		String lista = "";
		try {
			tk.createNewFile();
		} catch (IOException e) {
			System.out.println("Greška prilikom kreiranja fajla " + putanjaFajla);
			e.printStackTrace();
		}

		for (String linija : csvLista) {
			lista += linija + "\n";
		}

		try (FileWriter fileWriter = new FileWriter(this.tk, true)) {
			fileWriter.write(String.format("%s", lista));
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl " + putanjaFajla);
			e.printStackTrace();
		}
	}

    
}
