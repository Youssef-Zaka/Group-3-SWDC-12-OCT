package com.example.HeadwayProject.controller;

import com.example.HeadwayProject.Service.BookService;
import com.example.HeadwayProject.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return  new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") Long id){
        BookDTO bookDTO = bookService.getBookById(id);
        return new ResponseEntity<>(bookDTO , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        return new ResponseEntity<>( bookService.addBook(bookDTO),HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddResponse> deleteBook(@PathVariable(value = "id") Long id){
        AddResponse response=new AddResponse();
         bookService.deleteBook(id);
         response.setMsg("book deleted successfully");
        return new ResponseEntity<AddResponse>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(value = "id") Long id , @RequestBody BookDTO bookDTO){
        BookDTO updatedBookDTO = bookService.updateBook(id , bookDTO );
        return new ResponseEntity<>(updatedBookDTO , HttpStatus.OK);
    }



}
