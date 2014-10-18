package exceptions;

/**
 *
 * @author RootSoft
 */
public class TeVeelStudentenException extends Exception {
    
    public TeVeelStudentenException() {
        super("Er zijn teveel studenten gekoppeld aan de promotor.");
    }
    
    public TeVeelStudentenException(String message) {
        super(message);
    }
}
