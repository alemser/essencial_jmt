package essentialjmt.cap3.ex2;

public class ConsumerException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String name;
    
    public ConsumerException(String name, String message) {
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
