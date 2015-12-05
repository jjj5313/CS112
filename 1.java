public class RemoveVowels {
  public static String noVowels(String s) {
     // base cases
    if (s == null || s.equals(""))
         return "";
     // recursive case
    String remove = noVowels(s.substring(1));
     char first = str.charAt(0);
        if (first == 'a' || first == 'e' || first == 'i'
          || first == 'o' || first == 'u' ||first == 'A' 
           || first == 'E' || first == 'I' || first == 'O'
           || first == 'U') {
            return remove;
        } else {
            return first + remove;
        }
  }
}
        
     
