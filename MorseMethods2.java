public class MorseMethods2 {
   //separate methods
   /**
    * separates the method calls and calls the next method in translation
    * 
    * @param input The string to be translated 
    * @param startLang The language of starting translation (calls to 
    * translation method)
    * 
    * @author CaseyJayne
    */
   public void parseTranslation(String input, String startLang) {
      if (startLang.equals("E")) {
         englishToMorse(input);
      }
      else {
         morseToEnglish(input);
      }
   }
   
   /**
    *translates an english input without punctuation into morse code  by calling
    * to returnMatch()
    * 
    * @param userInput takes string English input to be translated
    * 
    * precondition: input must be a string without punctuation (numbers okay)
    * precondition: input must be in English
    * postcondition: output the string and the morse translation
    * 
    * @author: Casey Jayne
    */
   public void englishToMorse(String userInput) {
      StringBuffer translation = new StringBuffer();
      //loop through characters one by one
      for (int i = 0; i<userInput.length(); i++) {
       //separate the input by character
         char unitCharacter = userInput.charAt(i);
         //send the input to returnMatch method & append the match to output
         translation = translation.append(String.valueOf
               (returnMatch("&&", unitCharacter)));
         if (i == (userInput.length()-1)) { //when at end of input
            System.out.println(userInput + " : " + translation); 
         }
      }
      
   }
   /**
    * translates a morse code input without punctuation to English by calling
    * to returnMatch()
    * 
    * @param userInput String of Morse characters to translate
    * 
    * precondition: each Morse character must be separated by a space
    * precondition: each Morse word must be separated by '|'
    * postcondition: outputs the morse input and English translation in all caps
    * 
    * @author CaseyJayne
    */
   public void morseToEnglish(String userInput) {
      StringBuffer translation = new StringBuffer();
      //group letters of input
      //if null character used, Morse to English
      //add a space to end of string so that loop functions for last unit
      userInput = userInput+' ';
      //loop to separate Morse units by spaces
      int begin = 0;
      while(begin<userInput.length()) {
         //find the next space and save as end
         int end = userInput.indexOf(' ', begin);
         String morseUnit = 
               userInput.substring(begin,end);
         //send with 'default' input for charIn
         translation = translation.append
               (returnMatch(morseUnit, '#'));
         begin = end+1;
         if (begin == userInput.length()) {
            //reached end of translation unit
            System.out.println(userInput + " : " + translation);
         }
      }
   }
   
   /**
    * Called by the translation method to loop through String and Character 
    * arrays of Morse and English, returning the match with the charIn or
    * StringIn
    * 
    * @param stringIn if coming from English, the default is '&&'.  If 
    * translating from Morse the stringIn is the first character of the Morse 
    * code
    * @param characterIn if coming from Morse code, the default character is '#'
    * if translating from English, the character in is the next character within 
    * input to be translated
    * @return returns the String translation which matches the input
    * 
    * @author CaseyJayne
    */
   public String returnMatch(String stringIn, char characterIn) {
      //Create 2, 1D arrays
        //index English array: letters=0-25, space=26, 0-9=27-36
        //index of Morse array matches the array of English
        String [] morse= {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", 
              "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", 
              ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", 
              "-.-", "-.--", "--..", "|", "-----", ".----", "..---", 
              "...--", "....-", ".....", "-....", "--...", "---..", "----."};
        char [] english = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
              'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
              'W','X', 'Y', 'Z', ' ', '0', '1', '2', '3', '4', '5', '6', '7',
              '8', '9'};
        String match = new String();
        nest: { if (characterIn == '#') {//Morse to English
         //search substring in array
           for (int tempInd=0; tempInd<37; tempInd++) {
              if (stringIn.equals(String.valueOf(morse[tempInd]))) {
                 match = String.valueOf(english[tempInd]);
                 break nest;
              }
           }
        }
        else {//english to Morse
           //convert Character to String
           String tempChar = new String();
           tempChar = String.valueOf(characterIn);
           for (int tempInd=0; tempInd<37; tempInd++) {
              //if (characterIn == english[tempInd])
              if (tempChar.equals(String.valueOf(english[tempInd])))
              {
                 match = String.valueOf(morse[tempInd]);
                 break nest;
              }
           }}
        }//end nest
        
        return match; //return morse value of same index
        }
}
