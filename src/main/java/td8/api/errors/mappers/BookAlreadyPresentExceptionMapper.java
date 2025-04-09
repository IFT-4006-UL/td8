package td8.api.errors.mappers;

import td8.api.errors.ErrorResponse;
import td8.domain.exceptions.BookAlreadyPresentException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookAlreadyPresentExceptionMapper implements ExceptionMapper<BookAlreadyPresentException> {

    @Override
    public Response toResponse(BookAlreadyPresentException exception) {
        ErrorResponse error = new ErrorResponse("BOOK_ALREADY_PRESENT", exception.getMessage());
        return Response.status(400).entity(error).build();
    }
}
