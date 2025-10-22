package com.nazire.booktrack_pro.service;

import com.nazire.booktrack_pro.dto.BookResponse;
import com.nazire.booktrack_pro.dto.CreateBookRequest;
import com.nazire.booktrack_pro.dto.UpdateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    /*
    Kitap oluşturma
    İd'ye göre kitap getirme
    Kitapların listesini döndürme
    İd'ye göre güncelleme
    id'ye göre silme
    arama(title)
     */
    //! Pageable dönmek için sayfa listleme ya da arama(kısaca sayfa dönenler)

    BookResponse create(CreateBookRequest createBookRequest);
    BookResponse get(Long id);
    Page<BookResponse> list(Pageable pageable);
    BookResponse update(Long id, UpdateBookRequest updateBookRequest);
    void delete(Long id);
    Page<BookResponse> searchBook(String title, Pageable pageable);
    Page<BookResponse> searchAuthor(String author, Pageable pageable);
}
