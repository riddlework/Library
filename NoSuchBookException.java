/**
 * An exception to be thrown when no book of a given criterion (or no criteria) exists
 */
class NoSuchBookException extends Exception {
    // constructor that accepts a message
    public NoSuchBookException (String message) {
        super(message);
    }
}
