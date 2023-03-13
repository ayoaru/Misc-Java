import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * RecipesGUI
 *
 * GUI interface for users to view, edit, and add recipes for themselves
 * or others to view.
 *
 * @author Shola Arulogun
 *
 * @version January 4, 2023
 */
public class RecipesGUI extends JComponent implements Runnable {

    JPanel login;
    JPanel home;
    JMenuBar mb;
    JMenu recipes, settings;

    public void run() {
        JFrame frame = new JFrame("Recipe");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        mb = new JMenuBar();
        recipes = new JMenu("Recipes");
        recipes.setMnemonic(KeyEvent.VK_A);
        recipes.getAccessibleContext().setAccessibleDescription("idk lol");

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new RecipesGUI());
    }
}
