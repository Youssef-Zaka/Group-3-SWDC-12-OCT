package com.example.HeadwayProject.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.HeadwayProject.dto.BookDTO;
import com.example.HeadwayProject.exception.BookNotFoundException;
import com.example.HeadwayProject.mapper.BookMapper;
import com.example.HeadwayProject.model.Book;
import com.example.HeadwayProject.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookServiceDiffblueTest {
    @MockBean
    private BookMapper bookMapper;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    /**
     * Method under test: {@link BookService#getAllBooks()}
     */
    @Test
    void testGetAllBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<BookDTO> bookDTOList = new ArrayList<>();
        when(bookMapper.toDtos(Mockito.<List<Book>>any())).thenReturn(bookDTOList);

        // Act
        List<BookDTO> actualAllBooks = bookService.getAllBooks();

        // Assert
        verify(bookMapper).toDtos(isA(List.class));
        verify(bookRepository).findAll();
        assertTrue(actualAllBooks.isEmpty());
        assertSame(bookDTOList, actualAllBooks);
    }

    /**
     * Method under test: {@link BookService#getAllBooks()}
     */
    @Test
    void testGetAllBooks2() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        when(bookMapper.toDtos(Mockito.<List<Book>>any())).thenThrow(new BookNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getAllBooks());
        verify(bookMapper).toDtos(isA(List.class));
        verify(bookRepository).findAll();
    }

    /**
     * Method under test: {@link BookService#getBookById(Long)}
     */
    @Test
    void testGetBookById() {
        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookName("Book Name");
        book.setId(1L);
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        BookDTO bookDTO = new BookDTO();
        when(bookMapper.toDto(Mockito.<Book>any())).thenReturn(bookDTO);

        // Act
        BookDTO actualBookById = bookService.getBookById(1L);

        // Assert
        verify(bookMapper).toDto(isA(Book.class));
        verify(bookRepository).findById(eq(1L));
        assertSame(bookDTO, actualBookById);
    }

    /**
     * Method under test: {@link BookService#getBookById(Long)}
     */
    @Test
    void testGetBookById2() {
        // Arrange
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
        verify(bookRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link BookService#addBook(BookDTO)}
     */
    @Test
    void testAddBook() {
        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookName("Book Name");
        book.setId(1L);
        when(bookRepository.save(Mockito.<Book>any())).thenReturn(book);

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setBookName("Book Name");
        book2.setId(1L);
        BookDTO bookDTO = new BookDTO();
        when(bookMapper.toDto(Mockito.<Book>any())).thenReturn(bookDTO);
        when(bookMapper.toEntity(Mockito.<BookDTO>any())).thenReturn(book2);

        // Act
        BookDTO actualAddBookResult = bookService.addBook(new BookDTO());

        // Assert
        verify(bookMapper).toDto(isA(Book.class));
        verify(bookMapper).toEntity(isA(BookDTO.class));
        verify(bookRepository).save(isA(Book.class));
        assertSame(bookDTO, actualAddBookResult);
    }

    /**
     * Method under test: {@link BookService#addBook(BookDTO)}
     */
    @Test
    void testAddBook2() {
        // Arrange
        when(bookMapper.toEntity(Mockito.<BookDTO>any())).thenThrow(new BookNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.addBook(new BookDTO()));
        verify(bookMapper).toEntity(isA(BookDTO.class));
    }

    /**
     * Method under test: {@link BookService#updateBook(Long, BookDTO)}
     */
    @Test
    void testUpdateBook() {
        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookName("Book Name");
        book.setId(1L);
        when(bookRepository.save(Mockito.<Book>any())).thenReturn(book);
        when(bookRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setBookName("Book Name");
        book2.setId(1L);
        BookDTO bookDTO = new BookDTO();
        when(bookMapper.toDto(Mockito.<Book>any())).thenReturn(bookDTO);
        when(bookMapper.toEntity(Mockito.<BookDTO>any())).thenReturn(book2);

        // Act
        BookDTO actualUpdateBookResult = bookService.updateBook(1L, new BookDTO());

        // Assert
        verify(bookMapper).toDto(isA(Book.class));
        verify(bookMapper).toEntity(isA(BookDTO.class));
        verify(bookRepository).existsById(eq(1L));
        verify(bookRepository).save(isA(Book.class));
        assertSame(bookDTO, actualUpdateBookResult);
    }

    /**
     * Method under test: {@link BookService#updateBook(Long, BookDTO)}
     */
    @Test
    void testUpdateBook2() {
        // Arrange
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookName("Book Name");
        book.setId(1L);
        when(bookRepository.save(Mockito.<Book>any())).thenReturn(book);
        when(bookRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setBookName("Book Name");
        book2.setId(1L);
        when(bookMapper.toDto(Mockito.<Book>any())).thenThrow(new BookNotFoundException("An error occurred"));
        when(bookMapper.toEntity(Mockito.<BookDTO>any())).thenReturn(book2);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, new BookDTO()));
        verify(bookMapper).toDto(isA(Book.class));
        verify(bookMapper).toEntity(isA(BookDTO.class));
        verify(bookRepository).existsById(eq(1L));
        verify(bookRepository).save(isA(Book.class));
    }

    /**
     * Method under test: {@link BookService#updateBook(Long, BookDTO)}
     */
    @Test
    void testUpdateBook3() {
        // Arrange
        when(bookRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, new BookDTO()));
        verify(bookRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link BookService#deleteBook(Long)}
     */
    @Test
    void testDeleteBook() {
        // Arrange
        doNothing().when(bookRepository).deleteById(Mockito.<Long>any());
        when(bookRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act
        bookService.deleteBook(1L);

        // Assert that nothing has changed
        verify(bookRepository).deleteById(eq(1L));
        verify(bookRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link BookService#deleteBook(Long)}
     */
    @Test
    void testDeleteBook2() {
        // Arrange
        doThrow(new BookNotFoundException("An error occurred")).when(bookRepository).deleteById(Mockito.<Long>any());
        when(bookRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository).deleteById(eq(1L));
        verify(bookRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link BookService#deleteBook(Long)}
     */
    @Test
    void testDeleteBook3() {
        // Arrange
        when(bookRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository).existsById(eq(1L));
    }
}
