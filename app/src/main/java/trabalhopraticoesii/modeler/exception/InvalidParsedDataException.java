package trabalhopraticoesii.modeler.exception;

public class InvalidParsedDataException extends Exception{
    /**
     * Constructs a new InvalidParsedDataException with no specified detail message.
     */
    public InvalidParsedDataException() {
    }

    /**
     * Constructs a new InvalidParsedDataException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidParsedDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidParsedDataException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public InvalidParsedDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidParsedDataException with the specified cause and a detail message
     * of (cause==null ? null : cause.toString()) (which typically contains the class and
     * detail message of cause).
     *
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public InvalidParsedDataException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidParsedDataException with the specified detail message, cause,
     * suppression enabled or disabled, and writable stack trace enabled or disabled.
     *
     * @param message            The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause              The cause (which is saved for later retrieval by the getCause() method).
     * @param enableSuppression  Whether or not suppression is enabled or disabled.
     * @param writableStackTrace Whether or not the stack trace should be writable.
     */
    public InvalidParsedDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
