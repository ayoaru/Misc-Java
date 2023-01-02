import java.util.*;

public class PigLatin {
    public static final String BORDER = "===========================";
    public static final String RULE_ONE = "1. If the word begins with a constant, move the constant to the end " +
            "of the word and add 'ay'. Example: \"Pig latin\" becomes \"Igpay atinlay\".";
    public static final String RULE_TWO = "2. If the word begins with a digraph (th, sh, ch, etc.) move the digraph " +
            "to the end of the word and add 'ay'. Example: \"This is pig latin\" becomes \"Isthay isway igpay atinlay\".";
    public static final String RULE_THREE = "3. If the word begins with a vowel, add 'way' to the end of the word. " +
            "Example: \"Is this an apple?\" becomes \"Isway isthay anway appleway?\"";

    public static String pigLatinIt(String s) { //FIXME: Not working properly, see debugger
        String pigS = "";
        int firstLetter = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).equals(" ")) {
                if (isDigraph(s.substring(firstLetter, firstLetter + 2))) {
                    pigS += s.substring(firstLetter + 2, i) + s.substring(firstLetter, firstLetter + 2) + "ay ";
                } else if (isVowel(s.substring(firstLetter, firstLetter + 1))){
                    pigS += s.substring(firstLetter, i) + "way ";
                } else {
                    pigS += s.substring(firstLetter + 1, i) + s.substring(firstLetter, firstLetter + 1) + "ay";
                }
                firstLetter = i + 1;
            } else if ( i == s.length() - 1 && pigS.equals("")) {
                pigS += ""; //TODO: Finish this part of the method, checking for if only one word
            }
        }
        return pigS;
    }

    public static boolean isDigraph(String s) {
        if (s.equals("ch") ||s.equals("sh") || s.equals("th") || s.equals("wh") || s.equals("ph"))
            return true;
        return false;
    }

    public static boolean isVowel(String s) {
        if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u"))
            return true;
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Greet user
        System.out.println("Welcome to the Pig Latin Converter!");
        System.out.println("What would you like to do?");

        do {
            System.out.println("1. Convert text to pig latin\n2. Explain pig latin\n3. Exit program");
            try {
                int select = sc.nextInt();
                if (select == 1) {
                    System.out.println(BORDER + "\nWhat is the text you would like to convert?");
                    String convert = sc.next();
                    System.out.println("Your original text was: " + convert + ". In pig latin it is:\n");
                    System.out.println(pigLatinIt(convert));
                } else if (select == 2) {
                    System.out.println(BORDER + "\nPig latin is a sort of fake language that moves the first letter of " +
                            "a word to the end of the word and adds 'ay' to the end. The rules are below:");
                    System.out.println(RULE_ONE + "\n" + RULE_TWO + "\n" + RULE_THREE + "\n" + BORDER);
                } else if (select == 3) {
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number!");
            }

        } while (true);

        System.out.println(BORDER + "\nThank you for using the Pig Latin Converter. Goodbye!");
    }
}