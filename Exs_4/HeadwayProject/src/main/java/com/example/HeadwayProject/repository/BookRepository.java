package com.example.HeadwayProject.repository;

import com.example.HeadwayProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
