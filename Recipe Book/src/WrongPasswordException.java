/**
 * Exception to be thrown when the user attempts to log in to
 * an accoutn with the incorrect password.
 *
 * @author SHola Arulogun
 *
 * @version January 6, 2023
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException(String message) {
        super(message);
    }
}
