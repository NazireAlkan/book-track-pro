package com.nazire.booktrack_pro.mapper;

import com.nazire.booktrack_pro.domain.BookEntity;
import com.nazire.booktrack_pro.dto.BookResponse;
import com.nazire.booktrack_pro.dto.CreateBookRequest;
import com.nazire.booktrack_pro.dto.UpdateBookRequest;

public class BookMapper {
    //create book
    public static BookEntity toEntity(CreateBookRequest request){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setIsbn(request.getIsbn());
        bookEntity.setPublishedYear(request.getPublishedYear());
        return bookEntity;
    }
    // update book
    public static void updateEntity(BookEntity bookEntity, UpdateBookRequest request){
        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setIsbn(request.getIsbn());
        bookEntity.setPublishedYear(request.getPublishedYear());
    }
    public static BookResponse toResponse(BookEntity bookEntity){
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(bookEntity.getId());
        bookResponse.setTitle(bookEntity.getTitle());
        bookResponse.setAuthor(bookEntity.getAuthor());
        bookResponse.setIsbn(bookEntity.getIsbn());
        bookResponse.setPublishedYear(bookEntity.getPublishedYear());
        return bookResponse;
    }
}
