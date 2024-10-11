package com.example.HeadwayProject.Service;

import com.example.HeadwayProject.dto.BookDTO;
import com.example.HeadwayProject.exception.BookAlreadyExistsException;
import com.example.HeadwayProject.exception.BookNotFoundException;
import com.example.HeadwayProject.mapper.BookMapper;
import com.example.HeadwayProject.model.Book;
import com.example.HeadwayProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBooks(){
        List<Book> books =bookRepository.findAll();
        return bookMapper.toDtos(books);
    }

    public BookDTO getBookById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found."));
    }

    public BookDTO addBook(BookDTO bookDTO){

        Book savedBook = bookRepository.save(bookMapper.toEntity(bookDTO));
        return bookMapper.toDto(savedBook);
    }

    public BookDTO updateBook(Long id , BookDTO bookDTO ){
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        Book bookToUpdate = bookMapper.toEntity(bookDTO);
        bookToUpdate.setId(id);

        Book updatedBook = bookRepository.save(bookToUpdate);

        return bookMapper.toDto(updatedBook);
    }

    public void deleteBook(Long id){
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }
}
