package otto;

/**
 * Represents an exception specific to Otto.
 */
public class OttoException extends Exception {
    /** Message to be displayed when exception is thrown. */
    private String msg;

    public OttoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
