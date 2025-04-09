package td8.application;

import td8.domain.Author;
import td8.domain.Book;
import td8.domain.BookName;

import java.util.List;

public class BookAssembler {

    public Book fromDto(BookDto bookDto)
    {
        BookName bookName = new BookName(bookDto.title);
        Author author = new Author(bookDto.author);
        return new Book(bookName, author);
    }

    public List<BookDto> toDtos(List<Book> books) {
        return books.stream().map(book -> new BookDto(book.getName().toString(), book.getAuthor().getName())).toList();
    }
}
