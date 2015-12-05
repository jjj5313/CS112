public class RemoveVowels {
  public static void main(String[] args) {
    String a = "be";
    String b = "bop";
    String s = "beware of beetles";
    replace(a, b, s);
  }
public static String replace(String str, char oldChar, char newChar) { 
if (str.length() == 0) 
return ""; 
else if (str.startsWith(Character.toString(oldCh... 
return newChar + replace(str.substring(1), oldChar, newChar); 
else 
return str.charAt(0) + replace(str.substring(1), oldChar, newChar); 
} 
}
