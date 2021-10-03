package familyTree.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String irException) {
        super(irException);
    }
}
