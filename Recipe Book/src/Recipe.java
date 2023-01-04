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

    private final String fileID;
    private String name;
    private String country;
    private int prepTime;
    private int cookTime;
    private ArrayList<String> ingredients;
    private static int recipeCount = 0;

    //Constructor
    public Recipe(String name, String country, int prepTime, int cookTime, ArrayList<String> ingredients) {
        this.name = name;
        this.country = country;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.ingredients = ingredients;
        recipeCount++;
        this.fileID = "R" + recipeCount;
    }

    //Saving and loading Recipe objects, no need for try-catch since exception thrown in method head
    public void saveRecipe() throws IOException {
        String path = "./recipes/" + fileID; //Name the file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)); //Output obj to a file
        oos.writeObject(this);
        oos.flush(); //Clear the writer of obj that may or may not be there
        oos.close();
    }

    public Recipe loadRecipe(String fileID) throws IOException, ClassNotFoundException {
        String path = "./recipes/" + fileID;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        return (Recipe) ois.readObject();
    }

    //Getters & Setters
    //Getters
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

}