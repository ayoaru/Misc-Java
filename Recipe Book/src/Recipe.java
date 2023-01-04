import java.io.*;
import java.util.ArrayList;
/**
 * Recipe Class
 * <p>
 * Class that handles the creation of Recipe objects. Implements serializable
 * for data persistence, allows Recipe objects to be read from a file.
 *
 * @author Ayo Arulogun
 *
 * @version January 4, 2023
 */
public class Recipe implements Serializable {

    @Serial
    private static final long serialVersionUID = -8042716088127048291L;
    private final String recipeID;
    private String name;
    private String author;
    private String country;
    private int prepTime;
    private int cookTime;
    private ArrayList<String> ingredients;
    private static int recipeCount = 0;

    /*
    TODO: Add a tagging system? GUI can use checkboxes...
    - breakfast, lunch, dinner, dessert, snack, meal, drink
    - country/region tags??
    - should probably implement a search system too...
    */

    //Constructor for new recipes
    public Recipe(String name, String country, int prepTime, int cookTime, ArrayList<String> ingredients) {
        this.name = name;
        this.country = country;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.ingredients = ingredients;
        recipeCount++;
        recipeID = "R" + recipeCount;
    }


    //Saving and loading Recipe objects, no need for try-catch since exception thrown in method head
    public void saveRecipe() throws IOException {
        try {
            File f = new File("./Recipe Book/src/recipes/" + recipeID);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f)); //Output obj to a file
            oos.writeObject(this);
            oos.flush(); //Clear the writer of obj that may or may not be there
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateRecipe() throws IOException {
        String path = "./Recipe Book/src/recipes/" + this.recipeID;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)); //Output obj to a file
        oos.writeObject(this);
        oos.flush(); //Clear the writer of obj that may or may not be there
        oos.close();
    }

    public static Recipe loadRecipe(String id) { //static so can be called w/o formally creating a Recipe object
        String path = "./Recipe Book/src/recipes/" + id;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            return (Recipe) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading!");
            return null;
        }
    }

    //Getters & Setters
    //Getters
    public String getRecipeID() {
        return recipeID;
    }
    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public int getPrepTime() {
        return prepTime;
    }
    public int getCookTime() {
        return cookTime;
    }
    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    //Other methods
    @Override
    public String toString() {
        return String.format("Name: %s\nCountry: %s\nPrep Time: %d min\nCook Time: %d min\nRecipe ID: %s",
                this.name, this.country, this.prepTime, this.cookTime, this.recipeID);
    }
}