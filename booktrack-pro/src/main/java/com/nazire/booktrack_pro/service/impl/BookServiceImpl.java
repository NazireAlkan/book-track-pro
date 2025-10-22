package com.nazire.booktrack_pro.service.impl;

import com.nazire.booktrack_pro.domain.BookEntity;
import com.nazire.booktrack_pro.dto.BookResponse;
import com.nazire.booktrack_pro.dto.CreateBookRequest;
import com.nazire.booktrack_pro.dto.UpdateBookRequest;
import com.nazire.booktrack_pro.exception.BadRequestException;
import com.nazire.booktrack_pro.exception.NotFoundException;
import com.nazire.booktrack_pro.mapper.BookMapper;
import com.nazire.booktrack_pro.repository.BookRepository;
import com.nazire.booktrack_pro.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookResponse create(CreateBookRequest createBookRequest) {
        if(bookRepository.existsByIsbn(createBookRequest.getIsbn())){
            throw new BadRequestException("ISBN already exit");
        }
        BookEntity bookEntity = bookRepository.save(BookMapper.toEntity(createBookRequest));
        return BookMapper.toResponse(bookEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse get(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        return BookMapper.toResponse(bookEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponse> list(Pageable pageable) {
        return bookRepository.findAll(pageable).map(BookMapper::toResponse);
    }

    @Override
    @Transactional
    public BookResponse update(Long id, UpdateBookRequest updateBookRequest) {//10
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found!"));
        //ISBN değişiyorsa çakışmayı kontrol et
        if(!bookEntity.getIsbn().equals(updateBookRequest.getIsbn()) && bookRepository.existsByIsbn(updateBookRequest.getIsbn())){
            throw new BadRequestException("ISBN already exception");
        }
        BookMapper.updateEntity(bookEntity, updateBookRequest);

        BookEntity saved = bookRepository.save(bookEntity);
        return BookMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found!"));
        bookRepository.delete(bookEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponse> searchBook(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable).map(BookMapper::toResponse);
    }

    @Override
    public Page<BookResponse> searchAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable).map(BookMapper::toResponse);
    }

    /*
    Jack london geldi : yazar
    findByAuthorContainingIgnoreCase metodu bookrepository'de arıyor
     */


}
