package otto;

public class OttoException extends Exception {
    private String msg;

    public OttoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
