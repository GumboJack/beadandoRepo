package exception;

public class ObjectDoesNotExistException extends Throwable{
    public ObjectDoesNotExistException(String message) {
        super(message);
    }
}
