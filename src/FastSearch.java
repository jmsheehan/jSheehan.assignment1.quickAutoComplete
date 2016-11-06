import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class FastSearch implements FastSearchInterface {
	private Map<String, Double> map;
	private TreeMap<String, Double> treeMap;  
	private Scanner search = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		FastSearch words = new FastSearch();
		words.run();
	}
	/*
     * menu() - This method displays the menu for the application, 
     * reads the menu option that the user entered handles any exceptions
     *  and returns an acceptable input.
     * 
     * @return     the users menu choice
     */
	  private int menu(){
		    System.out.println("Main Menu");
	        System.out.println("---------------------------");
	        System.out.println("  1) Top Match.");
	        System.out.println("---------------------------");
	        System.out.println("  2) All Matches.");
	        System.out.println("---------------------------");
	        System.out.println("  3) List of k Matches.");
	        System.out.println("---------------------------");
	        System.out.println("  4) Weight of Term.");
	        System.out.println("---------------------------");
	        System.out.println("  5) Add a Term.");
	        System.out.println("---------------------------");
	        System.out.println("  0) Exit");
	        System.out.print("=============>>>>>>>>>>>>>");
	        boolean goodInput=false;
	        int option = 0;
	        do{
	        	try{
	        		System.out.println("Choose Option:");
	        		option = search.nextInt();
	        		goodInput = true;
	        	}
	        	catch(Exception e){
	        		String throwOut = search.nextLine();
	        		System.out.println("Int expected - but not received");
	        	}
	        }while(!goodInput);
	        return option;
	    }
	     
	  /**
	     * This is the method that controls the menu loop
	     * Using input from the menu() method to link to the
	     * choosen method.
	     */
	     private void run()
	        {
	    	 	String s = null;
	    	 	System.out.println("");
	            int option = menu();
	            if(option==1||option==2||option==3||option==4)
	            {
	   	         boolean goodInput=false;
	   		        do{
	   		        	try{
	   		   	    	    System.out.println("\n Please enter a String to search for ");
	   		        		s = search.next();
	   		        		goodInput = true;
	   		        	}
	   		        	catch(Exception e){
	   		        		String throwOut = search.nextLine();
	   		        		System.out.println("Int expected - but not received");
	   		        	}
	   		        }while(!goodInput);
	   		    }
	            while (option != 0)
	            {
	                switch (option)
	                {
	                case 1:
	                    System.out.println("The best Match for the Term Provided is"+bestMatch(s));
	                    break;
	                case 2:
	                    search(s);
	                    break;
	                case 3:
	                    kMatches(s);
	                    break;
	                case 4:
	                	System.out.println("The weight of the Term provided is:	"+
	                	weightOf(s));
	                	break;
	                case 5:
	                	String term = null;
	                	Double weight = 0.0;
	                	boolean goodInput=false;
	                	boolean goodInput1=false;
	                	
		   		        do{
		   		        	try{
		   		        		System.out.println("\n Please enter a Term to add ");
		   		        		term = search.next();
		   		        		goodInput = true;
		   		        	}
		   		        	catch(Exception e){
		   		        		String throwOut = search.nextLine();
		   		        		System.out.println("Int expected - but not received");
		   		        	}
		   		        }while(!goodInput);
		   		        
		   		        do{
		   		        	try{
			       	    	    System.out.println("\n Please enter the Weight of the Term ");
		   		        		weight = search.nextDouble();
		   		        		goodInput1 = true;
		   		        	}
		   		        	catch(Exception e){
		   		        		String throwOut = search.nextLine();
		   		        		System.out.println("Int expected - but not received");
		   		        	}
		   		        }while(!goodInput1);
	                	addEntry(term,weight);
	                	break;
	                default:
	                    System.out.println("Invalid option selected.");
	                    break;
	                }
	      
	                //pause the program so that the user can read what we just printed to the terminal window
	                System.out.println("\nPress any key to continue...");
	                search.nextLine();
	                search.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
	    			run();
	            }
	            System.out.println("Exiting... bye");
	            System.exit(0);
	        }
	     
	     /*
	      * Constructs the map hashMap containing the terms from database.txt
	      * and the corresponding weight of the terms.
	      */	public FastSearch() throws Exception{
		map = new HashMap<String, Double>();
		//wordList = new ArrayList<String>();
		File usersFile = new File("./database.txt");
		  Scanner inUsers = new Scanner(usersFile);
		//each field in the file is separated(delimited) by whitespace.
		  String delims = "[\\s+]";
		  System.out.println(inUsers.nextLine());
		  while (inUsers.hasNextLine()) {
			//eliminates leading and trailing spaces from the line.
		    String userDetails =  inUsers.nextLine().trim();
		    /*
		     * Splits the weight and term by the delims and constructs an array of type 
		     * String[].
		     */
		    String[] userTokens = userDetails.split(delims);
		    /*
		     * Ensures that there are only two entries per each array of userTokens.
		     */
		    if (userTokens.length == 2) {
		      Double value = Double.valueOf(userTokens[0]);
		      String key = userTokens[1];
		      map.put(key, value);

		    }else
		    	
		    {
		      inUsers.close();
		      throw new Exception("Invalid member length: "+userTokens.length);
		    }
		}
		  /*
		   * Converts the Map to a treeMap hence allowing for a much faster
		   * search as the entries have been sorted alphabetically by terms.
		   */
		  treeMap = new TreeMap<String,Double>(map);
		  inUsers.close();
	}
	
	      /*
	  	 * Constructs a Map of the Terms that start with the String entered by
	  	 * the user.
	  	 */
	public Map<String, Double> getResults(String subString){
		boolean foundFirst =false;
		Map<String, Double> results = new HashMap<String, Double>();
		if (subString != null) {
			for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();
				
				/*
				 * if the foundFirst boolean has been set to true and an entry
				 * that does not start with the subString entered is found 
				 * the search function is ended and the loop is broken.
				 */
				if (foundFirst == true){
					if (!key.startsWith(subString)){
						break;
						}
					}
				if (key.startsWith(subString)){
					results.put(key,value);
					/* indicates that the first term which starts with 
					 * the substring has been found
					 */
					foundFirst = true;
				}
			}
			
		}
		return results; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see FastSearchInterface#getSubstringList(java.lang.String)
	 */
	@Override
	public List<String> getSubstringList(String subString) {
		List<String> sorted = getResults(subString).entrySet().stream()
                .sorted(reverseOrder(comparing(Entry::getValue)))
                .map(Entry::getKey)
                .collect(toList());
		return sorted;

	}
	
	/*
	 * Prints out each entry the List sorted along with its ranking in the list
	 * based on in weight unless the list is empty.
	 */
	public void search(String s){
		int index = 1;
		if(getSubstringList(s).size()>0)
		{
			for(String string:getSubstringList(s))
			{
				System.out.println("Match "+index+": "+string);	
				index++;
			}
		}
		else
		{
			System.out.println("There is no match for the String provided");	
		}
	  
	    	
	}
	
	/*
	 * Allows the user to choose how many matches are output.
	 */
	public void kMatches(String s){
		if(getSubstringList(s).size()>0){
			boolean goodInput=false;
			int k = 0;
			do{
	        	try{
	        		System.out.println("\n Please Choose number of matches to be returned ");
	        		k= search.nextInt();
	        		goodInput = true;
	        	}
	        	catch(Exception e){
	        		String throwOut = search.nextLine();
	        		System.out.println("Int expected - but not received");
	        	}
	        }while(!goodInput);
	        if(k>0&&k<getSubstringList(s).size()){
		        for(int i = 0;i<k;i++){
		        	System.out.println("Match "+(i+1)+": "+getSubstringList(s).get(i));	
		        }
	        }
	        else if(k>getSubstringList(s).size())
	        {
	        	System.out.println("There are only "+getSubstringList(s).size()+" matches"
	        			+ " for the String provided.");
	        	search(s);
	        }
	        else
	        {
	        	System.out.println("Please choose a greater number of mathces.");
	        	kMatches(s);
	        }
		}
		else{
			System.out.println("There is no match for the String provided");	
			}
	}
	/*
	 * (non-Javadoc)
	 * @see FastSearchInterface#weightOf(java.lang.String)
	 */
	@Override
	public double weightOf(String term) {
		double weight = 0;
		for (Entry<String, Double> entry : getResults(term).entrySet()) {
			String key = entry.getKey();
			if(key.equals(term)){
				System.out.println("A Match Was Found for the String entered.");
				weight = entry.getValue();
				break;
			}
			else{
				weight = 0.0;
			}
		}
		if(weight == 0.0){
			System.out.println("A Match Was not Found for the String entered."+"\n");
		}
		return weight;
	}
	
	/*
	 * (non-Javadoc)
	 * @see FastSearchInterface#bestMatch(java.lang.String)
	 */
	@Override
	public String bestMatch(String prefix) {
		String topMatch = null;
		if(getSubstringList(prefix).size()>0){
			topMatch=getSubstringList(prefix).get(0);
		}
		else{
			topMatch="There is no match for the String provided";
		}
		return topMatch;
	}
	
	/*
	 * (non-Javadoc)
	 * @see FastSearchInterface#addEntry(java.lang.String, java.lang.Double)
	 */
	@Override
	public void addEntry(String term, Double weight) {

		if (term != null && term.trim().length() > 0&& weight >0)
			treeMap.put(term.trim(), weight);

	}
}