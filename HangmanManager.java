//The hangman ---- does not fill with the right input. 
//The number of guesses dont drop on wrong answer.
//At the end, when its cool and good, we wanna choose the other option when the user chooses one.

import java.util.*;

public class HangmanManager {
   private List<String> dictionaryList = new ArrayList<>();
   private Set<String> leftovers = new HashSet<>(); //This is so we can return the words that are being considered.
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
      int bestSize = 0;
      String bestSizeWord = "";
      guessedSet.add(guess);
      List<Integer> temp = new ArrayList<>();
      Map<String, Set<String>> theMap = new TreeMap();
      for(String x: leftovers) { //Since at start we say leftover is the same as Dictionary words
         temp.clear(); 
         if(x.indexOf(guess) > -1 && x.indexOf(guess) < x.length()) {
            for(int i = 0; i< x.length(); i++) {
               if(x.charAt(i) == guess) {
                  temp.add(i);
               }
            }
         } 
         
         String forMap = "!";
         for(int i = 0; i < temp.size(); i++) {
            forMap = forMap + temp.get(i);
         }
         
         if(theMap.containsKey(forMap)) {
            theMap.get(forMap).add(x);
         } else {
            Set<String> t = new TreeSet<>();
            t.add(x);
            theMap.put(forMap, t);
         }
         
      }
      
      System.out.println(theMap);
      //This gets the best size of the pattern.
      for(String z: theMap.keySet()) {
         if(theMap.get(z).size() > bestSize) {
            bestSize = theMap.get(z).size();
            bestSizeWord = z;
         }         
      }
      
      //Here we want to filter the theMap of excessive words that are not best size.
      Set<String> takePlace = new TreeSet<>();
      for(String z: theMap.keySet()) {
         if(!z.equals(bestSizeWord)) {
            takePlace.add(z);
         }
      }
      
      for(String z: takePlace) {
         theMap.remove(z);
      }
      
      //Here we try to reduce what leftovers is by setting it equal to only best size word.
      Set<String> temporary = new TreeSet<>();
      for(String z: theMap.get(bestSizeWord)) {
         temporary.add(z);
      }
      
      leftovers.retainAll(temporary);
      //
      
      //How many tells us how many of that characters there are in the largest pattern.
      int howMany = 0;
      for(String x : leftovers) {
         howMany = 0;
         for(int i = 0; i< x.length(); i++) {
            if(x.charAt(i) == guess) {
               howMany++;
            }
         }
      }  
      
      
      return howMany;
   }

}
