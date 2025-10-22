package com.nazire.booktrack_pro.repository;

import com.nazire.booktrack_pro.domain.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired private BookRepository repository;

    @Test
    void search_by_title(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("Spring in Action");
        bookEntity.setAuthor("Craig");
        bookEntity.setIsbn("123-1234567890");
        bookEntity.setPublishedYear(2021);
        repository.save(bookEntity);

        var page = repository.findByTitleContainingIgnoreCase("spring", PageRequest.of(0, 10));
        assertEquals(1, page.getTotalElements());
    }
}
