package SpringMVC.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
    public PersonNotFoundException(Throwable throwable){
        super(throwable);
    }
}
