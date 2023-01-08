import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User Class
 * <p>
 * Class that handles User objects, so that people can create an account,
 * login, and save recipes
 *
 */
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -6144568660874957421L;
    private static int userCount = User.loadUserCount();
    private final String userID;
    private String username;
    private String password;
    private ArrayList<Recipe> myRecipes;
    private ArrayList<Recipe> starredRecipes;



    //Constructor for completely new users
    public User (String username, String password) {
        this.username = username;
        this.password = password;
        myRecipes = new ArrayList<>();
        starredRecipes = new ArrayList<>();
        userCount++;
        userID = "U" + userCount;
        updateUserCount();
    }


    //Saving and loading users for data persistence
    public void saveUser() throws IOException {
        String path = "./Recipe Book/src/users/" + this.username;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)); //Output obj to a file
        oos.writeObject(this);
        oos.flush(); //Clear the writer of obj that may or may not be there
        oos.close();
    }

    public void updateUser() throws IOException { //TODO: Why did I create this it's literally the same as saveUser lol?
        String path = "./Recipe Book/src/users/" + this.username;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)); //Output obj to a file
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    public static User loadUser(String username, String password) throws FileNotFoundException, WrongPasswordException {
        String path = "./Recipe Book/src/users/" + username;
        File f = new File(path);
        if (!f.exists()) {
            throw new FileNotFoundException("This user does not exist!");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            User u = (User) ois.readObject();
            if (u.getPassword().equals(password))
                return u;
            else
                throw new WrongPasswordException();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading!");
            return null;
        }
    }


    //User count persistence for user ID
    public static void updateUserCount() { //Updates Hashtable file
        Hashtable counter; //Local variable
        System.out.println("The current count is " + userCount);
        try {
            String path = "./Recipe Book/src/data";
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            counter = (Hashtable) ois.readObject(); //Pull the hashtable from file and store locally
            ois.close();
            counter.put("Users", userCount); //Update locally
            System.out.println("The count written to the file is: " + counter.get("Users"));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(counter); //Overwrite file w/ local hashtable updated
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Error updating user count in hashtable file.");
        }
    }
    public static int loadUserCount() { //For initializing userCount variable
        Hashtable counter = new Hashtable<>(2);
        try {
            String path = "./Recipe Book/src/data";
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            counter = (Hashtable) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error reading counter hashtable.");
            e.printStackTrace();
        }
        return (int) counter.get("Users");
    }


    //Getters & Setters
    //Getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Recipe> getMyRecipes() {
        return myRecipes;
    }
    public ArrayList<Recipe> getStarredRecipes() {
        return starredRecipes;
    }
    public String getUserID() {
        return userID;
    }
    public int getUserCount() {
        return userCount;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }


    //Recipe list management
    public void addRecipe(Recipe recipe) {
        myRecipes.add(recipe);
    }
    public void removeRecipe(int recipeIndex) {
        myRecipes.remove(recipeIndex);
    }
    public void addStarredRecipe(Recipe recipe) {
        starredRecipes.add(recipe);
    }
    public void removeStarredRecipe(int recipeIndex) {
        starredRecipes.remove(recipeIndex);
    }
}
