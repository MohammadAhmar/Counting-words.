import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Z0000000<X extends Comparable<X>, Y> implements Counter<X, Y> {
	public static void main(String args[]) throws FileNotFoundException {
		Counter<String, String> x = new Z0000000<String, String>();
		Utility.run(x);
	}

	public String get(X word) {
		Repeat wordRepeat = map.get(word);
		if(wordRepeat == null)
			return "";
		else {
			String s = "total count " + wordRepeat.count + "," ;
			for(Y b : wordRepeat.unique)
				s += " " + b + ",";
			return s;
		}
	}
	public int getCount(X word) {
		Repeat wordRepeat = map.get(word);
		if(wordRepeat == null)
			return 0;
		else
			return wordRepeat.count;
	}
	
	public Set<X> keySet() {
		return map.keySet();
	}
	
	public void put(X keyWord, Y word) {
		Repeat wordRepeat = map.get(keyWord);
		if(wordRepeat == null) {
			wordRepeat = new Repeat(word);
			map.put(keyWord, wordRepeat);
		}
		else {
			wordRepeat.count++;
			wordRepeat.unique.add(word);
		}
	}
	// declare any required instance variables or helpful auxiliary types and methods here
	class Repeat {
		int count;
		HashSet<Y> unique;
		
		Repeat(Y b) {
			count = 1;
			unique = new HashSet<Y>();
			unique.add(b);
		}

	}
		HashMap<X, Repeat> map = new HashMap<X, Repeat>();
}

// ------- cut here. Place your new code above this line and submit only the ------
// ------- code above this line as your homework. Do not make any code changes ----
// ------- below this line. ---------

interface Counter<X extends Comparable<X>, Y> {
	   int getCount(X word);
	   Set<X> keySet();
	   void put(X keyWord, Y word);
	   String get(X word);
	}

	class Utility {
	   public static void run(Counter<String, String> words) throws FileNotFoundException{
	      Scanner terminal = new Scanner(System.in);
	      System.out.println("Give the name of a file for analysis:");
	      String fileName = terminal.next();
	      Scanner input = new Scanner(new File(
	            "C:\\Documents and Settings\\alex\\Desktop\\" + fileName));
	      input.useDelimiter("\\W+");

	      while (input.hasNext()) {
	         String word = input.next();
	         String keyWord = Utility.dropS(word);
	         words.put(keyWord, word);
	      }

	      for (String word : words.keySet())
	         if (words.getCount(word) >= 10)
	            System.out.println(word +":\t" + words.get(word));

	      input.close();
	      terminal.close();
	   }
	   
	   static String dropS(String word) {
	      String ans = word.toLowerCase();
	      if (ans.endsWith("s"))
	         return ans.substring(0, ans.length() - 1);
	      return ans;
	   }
	}