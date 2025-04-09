package td8.domain.exceptions;

public class LibraryNotFoundException extends RuntimeException {

    public LibraryNotFoundException() {
        super("Library not found");
    }
}