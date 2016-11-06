
import java.util.List;

public interface FastSearchInterface {
	
	/*
	 * Converts the Results HashMap to a List of the Terms ranked by weight.
	 */
	public abstract List<String> getSubstringList(String prefix);

    // Returns the highest weighted matching term, or null if no matching term.
    public String bestMatch(String prefix);
    
    /*
     * Checks if the term entered matches any term in the hashMap map 
     * and if it does it returns the weight of the term.
     */
	public double weightOf(String term);

	/*
	 * Allows the user to input a term and a corresponding weight and 
	 * adds this to the hashMap map.
	 */
	public void addEntry(String term, Double weight);
}