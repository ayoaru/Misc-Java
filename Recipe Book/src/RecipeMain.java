import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * RecipeMain class
 * <p>
 * Class is for running the main functions of this program w/o the terminal.
 *
 * @author Shola Arulogun
 *
 * @version March 12, 2022
 */

public class RecipeMain {
    public static final String BORDER = "--------------------------------------------------"; //Purely for aesthetics
    public static final String INPUT_ERROR = "Please select a valid menu option!";
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        User user;
        do { //Initial menu, for user account creation, log in, and closing program
            System.out.println("1. Create account\n2.Log in\n3. Exit");
            try {
                if (scan.nextInt() == 1) { //Account creation
                    System.out.println("Enter a username: "); //TODO: Check that it's unique
                    scan.nextLine(); //Consume newline char from scan.nextInt()
                    String username = scan.nextLine();
                    System.out.println("Enter a password: "); //TODO: Create password standards for acc security
                    String password = scan.nextLine();
                    User newUser = new User(username, password);
                    try {
                        newUser.saveUser();
                    } catch (IOException e) {
                        System.out.println("ERROR: Could not create new account. Please try again later.");
                    }
                    break;
                }
                else if (scan.nextInt() == 2) { //Account login
                    System.out.print("Username: ");
                    scan.nextLine(); //Consume newline char from scan.nextInt()
                    String username = scan.nextLine();
                    System.out.print("Password: ");
                    String password = scan.nextLine();
                    try {
                        user = User.loadUser(username, password);
                    } catch (WrongPasswordException e) {
                        System.out.println(e);
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                } else if (scan.nextInt() == 3) { //Close program
                    System.out.println("Thank you for using Cooklection. We'll see you soon!");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(INPUT_ERROR);
            }
        } while(true);
    }


}