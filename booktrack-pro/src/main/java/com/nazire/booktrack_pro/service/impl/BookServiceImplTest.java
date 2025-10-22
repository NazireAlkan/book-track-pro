package com.nazire.booktrack_pro.service.impl;

import com.nazire.booktrack_pro.domain.BookEntity;
import com.nazire.booktrack_pro.dto.CreateBookRequest;
import com.nazire.booktrack_pro.exception.BadRequestException;
import com.nazire.booktrack_pro.exception.NotFoundException;
import com.nazire.booktrack_pro.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BookServiceImplTest {

    private static BookRepository repo;
    private static BookServiceImpl service;

    // Her seferinde mock(sahte) bir nesne oluşturacak
    @BeforeEach
    void setup(){
        repo = Mockito.mock(BookRepository.class);
        service = new BookServiceImpl(repo);
    }

    @Test
    void create_ok(){
        CreateBookRequest request = new CreateBookRequest();
        request.setTitle("Clean Code");
        request.setAuthor("Uncle Bob");
        request.setIsbn("978-0132350884");
        request.setPublishedYear(2008);

        when(repo.existsByIsbn(request.getIsbn())).thenReturn(false);
        //veritabanına kaydetme
        BookEntity saved = new BookEntity();
        saved.setId(1L);
        saved.setTitle(request.getTitle());
        saved.setAuthor(request.getAuthor());
        saved.setIsbn(request.getIsbn());
        saved.setPublishedYear(request.getPublishedYear());
        when(repo.save(any(BookEntity.class))).thenReturn(saved);

        var response = service.create(request);
        assertEquals(1L, response.getId());
        verify(repo).save(any(BookEntity.class));
    }

    @Test
    void create_duplicate_isbn(){
        CreateBookRequest request = new CreateBookRequest();
        request.setTitle("X");
        request.setAuthor("Y");
        request.setIsbn("111-1111111111");

        when(repo.existsByIsbn("111-1111111111")).thenReturn(true);
        assertThrows(BadRequestException.class, () -> service.create(request));
    }

    @Test
    void get_not_found(){
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.get(99L));
    }
}
