package td8.application;

import td8.domain.Book;
import td8.domain.Library;
import td8.domain.LibraryName;
import td8.domain.exceptions.LibraryNotFoundException;
import jakarta.inject.Inject;
import td8.domain.LibraryRepository;

import java.util.List;

public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookAssembler bookAssembler;

    @Inject
    public LibraryService(LibraryRepository libraryRepository, BookAssembler bookAssembler) {
        this.libraryRepository = libraryRepository;
        this.bookAssembler = bookAssembler;
    }

    public void addBook(String libraryName, BookDto bookDto) {
        Library library = libraryRepository.findByName(new LibraryName(libraryName));
        if (library == null) {
            throw new LibraryNotFoundException();
        }

        Book book = bookAssembler.fromDto(bookDto);
        library.addBook(book);
        libraryRepository.saveOrUpdate(library);
    }

    public List<BookDto> getAllBooks(String libraryName) {
        Library library = libraryRepository.findByName(new LibraryName(libraryName));
        if (library == null) {
            throw new LibraryNotFoundException();
        }

        List<Book> books = library.getBooks();
        return bookAssembler.toDtos(books);
    }
}
