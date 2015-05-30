import java.io.*;
import java.util.ArrayList;

public class StreamsAndArrayLists {

	public static void main(String[] args) {
		
		/** some code to read in a file of famous places and then 
		  * prompt the user to sort them into places they've been
		  * and places they have not visited
		  */

		// declarations of the File type and the string we'll need to do some file io
		// note that input.txt needs to be in the StreamsAndArrayLists project folder
		File file = new File("input.txt");
		String line = "";
		
		// declarations of ArrayList<String> object(s) for the visited and not-visited places
		
	    /* 
	     * if a method throws an error, you can enclose it in 
		 * try{ // possibly error-throwing code } 
		 * catch(Exception exName) { // conditionally executed code } 
		 */
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
		    // loop over file input.  keep reading while file line isn't null (empty)
		    while ((line = br.readLine()) != null) {
		    	// code to process input.
		    }
		    
		    // clean up after yourself!
		    br.close();
		    
		} catch (Exception ex) {
			System.out.printf("Error: %s", ex);
		} 
		
		// once your ArrayLists are constructed
		
		// output to screen (via System.out.println()) Ooooor try....
		
		// output to file: 
		/* try{
		 * 		PrintWriter output = new PrintWriter("output.txt");
		 * 		output.println("this string shows up in file");
		 * 			// code
		 * 		output.close();
		 * } Catch (IOException ex2) {
		 * 		System.out.printf("Error: %s", ex2);
		 * }
		 */

	}
	}


