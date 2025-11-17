package exceptions;

/**
 * checked exception to show difference between checked/unchecked
 */
public class NoAvailableSpotException extends Exception {
    public NoAvailableSpotException(String message) {
        super(message);
    }
}
