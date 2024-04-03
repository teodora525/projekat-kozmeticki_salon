
package kozmetika.stanje;

/**
 * Icsvpersistable
 */
public interface Icsvpersistable {
    
	public String tocsv();
	public Icsvpersistable fromcsv(String csvString);
}
