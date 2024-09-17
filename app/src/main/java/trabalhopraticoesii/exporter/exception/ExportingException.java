package trabalhopraticoesii.exporter.exception;

public class ExportingException extends Exception {

    /**
     * Constructs a new FormattingException with no detail message.
     */
    public ExportingException() {
    }

    /**
     * Constructs a new FormattingException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ExportingException(String message) {
        super(message);
    }

    /**
     * Constructs a new FormattingException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public ExportingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new FormattingException with the specified cause and a detail message
     * of (cause==null ? null : cause.toString()) (which typically contains the class
     * and detail message of cause).
     *
     * @param cause The cause of the exception.
     */
    public ExportingException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new FormattingException with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
     *
     * @param message            The detail message.
     * @param cause              The cause of the exception.
     * @param enableSuppression  Whether or not suppression is enabled or disabled.
     * @param writableStackTrace Whether or not the stack trace should be writable.
     */
    public ExportingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}