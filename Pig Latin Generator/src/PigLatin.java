import java.util.*;
/**
 * Pig Latin Generator
 * <p>
 * A simple program that takes in user input as a String, then translates it to pig latin!
 * Users can view rules for pig latin under the menu they're greeted with at program launch.
 *
 * @author Ayo Arulogun on GitHub
 *
 * @version January 1, 2023
 */
public class PigLatin {
    public static final String BORDER = "==========================="; //Just for aesthetics
    public static final String RULE_ONE = "1. If the word begins with a constant, move the constant to the end " +
            "of the word and add 'ay'. Example: \"Pig latin\" becomes \"Igpay atinlay\".";
    public static final String RULE_TWO = "2. If the word begins with a digraph (th, sh, ch, etc.) move the digraph " +
            "to the end of the word and add 'ay'. Example: \"This is pig latin\" becomes \"Isthay isway igpay atinlay\".";
    public static final String RULE_THREE = "3. If the word begins with a vowel, add 'way' to the end of the word. " +
            "Example: \"Is this an apple?\" becomes \"Isway isthay anway appleway?\"";

    public static String pigLatinIt(String s) {
        StringBuilder pigS = new StringBuilder(); //Prior to warnings, this line read String pigS = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') { //Prior to warnings used pigS +=, appending is cleaner remember to use it!
                if (isVowel(s.substring(0, 1))){ //Must come to avoid errors w/ an 'a' by itself in String input
                    pigS.append(s, 0, i).append("way ");
                } else if (isDigraph(s.substring(0, 2))) {
                    pigS.append(s, 2, i).append(s, 0, 2).append("ay ");
                } else {
                    pigS.append(s, 1, i).append(s.charAt(0)).append("ay ");
                }

                s = s.substring(i + 1);
                i = 0;
            } else if (!s.contains(" ")) {
                if (isVowel(s.substring(0, 1))){
                    pigS.append(s).append("way");
                } else if (isDigraph(s.substring(0, 2))) {
                    pigS.append(s.substring(2)).append(s, 0, 2).append("ay");
                } else {
                    pigS.append(s.substring(1)).append(s.charAt(0)).append("ay");
                }
                s = "";
            }
        }
        return pigS.toString().toLowerCase();
    }

    public static boolean isDigraph(String s) {
        return (s.equals("ch") ||s.equals("sh") || s.equals("th") || s.equals("wh") || s.equals("ph"));
    }

    public static boolean isVowel(String s) {
        return (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u"));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Greet user
        System.out.println("Welcome to the Pig Latin Converter!\nWhat would you like to do?");

        do {
            try {
                System.out.println("1. Convert text to pig latin\n2. Explain pig latin\n3. Exit program");
                int select = sc.nextInt();
                if (select == 1) {
                    System.out.println(BORDER + "\nWhat is the text you would like to convert? Only use letters!");
                    sc.nextLine();
                    String convert = sc.nextLine();
                    System.out.print("Your original text was: \"" + convert + "\". \nIn pig latin it is: ");
                    System.out.println(pigLatinIt(convert) + "\n" + BORDER);
                } else if (select == 2) {
                    System.out.println(BORDER + "\nPig latin is a language formed from English. The rules are below:");
                    System.out.println(RULE_ONE + "\n" + RULE_TWO + "\n" + RULE_THREE + "\n" + BORDER);
                } else if (select == 3) {
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter a valid number.");
                sc.next();
            }

        } while (true);

        System.out.println(BORDER + "\nThank you for using the Pig Latin Converter. Goodbye!");
    }
}