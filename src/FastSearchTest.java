

import static org.junit.Assert.*;
import java.util.List;


import org.junit.Test;

public class FastSearchTest {
	/*
	 * Ensure that the getSubStringList() and hence the addEntry() method
	 * functions correctly by adding an entry "kryptonite" with weight "10.0"
	 * and then searching for a match for "kryptonite" and ensuring there is
	 * a result.
	 */
	@Test
	public void testGetSubstringList() throws Exception {
		FastSearchInterface wordList;
		String s = "kryptonite";
		Double t = 10.0;
		wordList = new FastSearch();
		
		wordList.addEntry(s,t);
		
		List<String> resultList = wordList.getSubstringList(s);

		// check that the search returned some result(i.e. there is a match
		// in the list)
		assertTrue(resultList.size() > 0);
	}
	
	/*
	 * Ensures that the BruteForce constructs a bruteForce object which
	 *  is not null.
	 */
	@Test
	public void testWordList() throws Exception {
		FastSearchInterface wordList;

		wordList = new FastSearch();
		assertNotNull(wordList);

	}
	
	/*
	 * Ensures the weightOf() method operates correctly by adding an entry
	 * and testing that the result of entering this entries term into the
	 * weightOf() method returns the Double t.
	 */
	@Test
	public void testWeightOf() throws Exception {
		FastSearchInterface wordList;
		String s = "kryptonite";
		Double t = 10.0;
		wordList = new FastSearch();
		
		wordList.addEntry(s,t);
		
		double results = wordList.weightOf(s);

		// check that the search returned some result(i.e. there is a match
		// in the list)
		assertTrue(results==10.0);
	}
	
	/*
	 * Ensures the weightOf() method operates correctly by adding an entry
	 * and testing that the result of entering this entries term into the
	 * weightOf() method returns the Double t.
	 */
	@Test
	public void testBestMatch() throws Exception {
		FastSearchInterface wordList;
		String s = "t";
		wordList = new FastSearch();
		
		
		String bestMatch = wordList.bestMatch(s);

		// check that the search returned some result(i.e. there is a match
		// in the list)
		assertTrue(bestMatch.equals("the"));
	}
}
