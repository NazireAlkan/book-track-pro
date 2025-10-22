package com.nazire.booktrack_pro.repository;

import com.nazire.booktrack_pro.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// veritabanıyla iletişim kuran sınıf
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    //ISBN kontrolü var mı yok mu
    boolean existsByIsbn(String isbn);

    //başlığa göre(case-insensitive) arama + sayfalama
    Page<BookEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    //yazara göre arama
    Page<BookEntity> findByAuthorContainingIgnoreCase(String authorName, Pageable pageable);
}
