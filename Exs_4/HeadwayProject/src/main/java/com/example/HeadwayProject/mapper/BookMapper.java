package com.example.HeadwayProject.mapper;


import com.example.HeadwayProject.dto.BookDTO;
import com.example.HeadwayProject.model.Book;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);
    List<BookDTO> toDtos(List<Book> books);
}
