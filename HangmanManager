//If word size is greater than the words of length text it still runs.


import java.util.*;

public class HangmanManager {
   private List<String> dictionaryList = new ArrayList<>();
   private Set<String> leftovers = new TreeSet<>(); //This is so we can return the words that are being considered.
   private int length;
   private int maxWrong;
   private Set<Character> guessedSet = new TreeSet<>(); //To see what char guesses have bene aded
   
   
   public HangmanManager(List<String> dictionary, int givenLength, int gMaxWrong) {  
      length = givenLength;
      maxWrong = gMaxWrong; 
      if(givenLength < 1) {
         throw new IllegalArgumentException("Invalid input");
      }
      
      if(gMaxWrong < 0) {
         throw new IllegalArgumentException("Max Wrongs can't be less than 0");
      }
      
      for(int i = 0; i < dictionary.size(); i++) {
         if(dictionary.get(i).length() == length) {
            dictionaryList.add(dictionary.get(i));
            leftovers.add(dictionaryList.get(i));
         } 
      }   
      
   }
   
   public Set<String> words() {
      return leftovers;
   }
   
   public int guessesLeft() {
      return maxWrong;
   }
   
   public Set<Character> guesses() {      
      return guessedSet;
   }
   
   public String pattern() {
      String output = "";
      for(int i = 0; i < length; i++) {
         output = output + "-";
      }
      
      
      return output;
   }
   
   public int record(char guess) {
      guessedSet.add(guess);
      Map<Integer, Set<String>> temporary = new TreeMap<>();
      for(String x: dictionaryList) {
         int count = 0; //Count helps us organize theMap
         for(int i = 0; i < x.length(); i++) { //If character count of the guess character is equal to 1/2/3/ etc.
            if(x.charAt(i) == guess) {
               count++;
            }
         }
         
         //If theMap contains anything in that key prior then just add to it.
         if(temporary.containsKey(count)) {
            temporary.get(count).add(x);
         } else { //Otherwise create a set and put these things in.
            Set<String> t = new TreeSet<>();
            t.add(x);
            temporary.put(count, t);
         }
         
            
      }
            
      //Here we check if the size of any other key is greater than the case where there is no such character so we can cut a guess.
      for(int z : temporary.keySet()) {
         if(temporary.containsKey(0)) {
            if(temporary.get(z).size() > temporary.get(0).size()) {
               maxWrong--;
            }
         }
      }
      
      int bestSize = 0;
      
      for(int z: temporary.keySet()) {
         if(temporary.get(z).size() > bestSize) {
            bestSize = z;
         }         
      }
      
      //Test
      for(int z: temporary.keySet()) {
         if(!temporary.get(z).equals(temporary.get(bestSize))) {
            temporary.remove(z);
         }
      }
      
      for(int x: temporary.keySet()) {
         if(!leftovers.contains(temporary.get(x))) {
            leftovers.remove(temporary.get(x));
         }
      }

      
      return bestSize;
   }

}
