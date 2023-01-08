import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * TestFunctions Class
 *
 * Test various aspects of the user and recipe classes. Objects created in
 * main method for these test cases.
 *
 * @author Ayo Arulogun
 *
 * @version January 4, 2023
 */
public class TestFunctions {

    public static void main(String[] args) {
        /*
        ArrayList<String> forCookies = new ArrayList<>();
        forCookies.add("1 cup peanut butter");
        forCookies.add("2/3 cup sugar");
        Recipe one = new Recipe("Margaret's PB Cookies", "USA", 10, 15, forCookies);
        System.out.println(one.toString());
        try {
            one.saveRecipe();
        } catch (IOException e) {
            System.out.println("Error saving Margaret's PB Cookies!");
            e.printStackTrace();
        }

        ArrayList<String> forBrownies = new ArrayList<>();
        forBrownies.add("1 cup melted chocolate chips");
        forBrownies.add("1/4 cup honey");
        forBrownies.add("2 cups flour");
        forBrownies.add("2 eggs");
        Recipe two = new Recipe("Brown Bear Brownies", "Mexico", 15, 20, forBrownies);
        System.out.println(two.toString());
        try {
            two.saveRecipe();
        } catch (IOException e) {
            System.out.println("Error saving Brown Bear Brownies!");
            e.printStackTrace();
        }
        */

        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;

        do {
            System.out.println("1. Log in\n2. Sign up");
            int loginNav = sc.nextInt();
            sc.nextLine();
            if (loginNav == 1) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                try {
                    User user = (User) User.loadUser(username, password);
                    System.out.println("User was found!");
                    System.out.printf("User %s's ID is %s", user.getUsername(), user.getUserID());
                    break;
                } catch (WrongPasswordException e) {
                    System.out.println("Incorrect password!");
                } catch (FileNotFoundException e) {
                    System.out.println("This user does not exist!");
                }
            } else if (loginNav == 2) {
                System.out.print("Enter a username: ");
                String username = sc.nextLine();
                System.out.print("Enter a password: ");
                String password = sc.nextLine();
                User newUser = new User (username, password);
                try {
                    newUser.saveUser();
                } catch (IOException e) {
                    System.out.println("Could not create account :(");
                }
            }


        } while (true);

        System.out.println("\nUsers counted in hashtable: " + User.loadUserCount());
    }

    Recipe pbCookies = (Recipe) Recipe.loadRecipe("R1");

    @Test
    public void testLoadCookies() {
        assertEquals("Correct recipe name", "Margaret's PB Cookies", pbCookies.getName());
        assertEquals("Correct recipe country", "America", pbCookies.getCountry());
        assertEquals("Correct recipe prep time", 10, pbCookies.getPrepTime());
        assertEquals("Correct recipe cook time", 15, pbCookies.getCookTime());
        assertEquals("Correct recipe ID", "R1", pbCookies.getRecipeID());
    }

}
