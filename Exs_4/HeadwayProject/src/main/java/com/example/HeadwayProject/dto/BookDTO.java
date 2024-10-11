package com.example.HeadwayProject.dto;

public class BookDTO {
    private Long id;
    private String bookName;
    private String author;

    public String getBookName() {
        return bookName;
    }
    public BookDTO() {

    }
    public BookDTO(String bookName, String author , Long id) {
        this.bookName = bookName;
        this.author = author;
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
