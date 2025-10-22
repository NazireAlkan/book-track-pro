package com.nazire.booktrack_pro.controller;

import com.nazire.booktrack_pro.dto.BookResponse;
import com.nazire.booktrack_pro.dto.CreateBookRequest;
import com.nazire.booktrack_pro.dto.UpdateBookRequest;
import com.nazire.booktrack_pro.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Operation(description = "This method creates a new book and return 200")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        BookResponse created= bookService.create(createBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

        /*
        public BookResponse createBook(@RequestBody CreateBookRequest createBookRequest)
        { return bookService.create(createBookRequest);
        }
         */
    }

  //api/books/10
    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id){
        return bookService.get(id);
    }

    @GetMapping
    public Page<BookResponse> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return bookService.list(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody UpdateBookRequest updateBookRequest){
       BookResponse updated = bookService.update(id, updateBookRequest);
       return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }

    @GetMapping("/search")
    public Page<BookResponse> searchBook(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        return bookService.searchBook(title, PageRequest.of(page, size));
        /*
             PageRequest extends AbstractPageRequest
             AbstractPageRequest extends implements Pageable, Serializable
             yani BookServiceImpl sınıfındaki search metodunun beklediği Pageable parametresi bu şekilde karşılanıyor

         */
    }

    @GetMapping("/search/author")
    public Page<BookResponse> searchAuthor(
            @RequestParam(name = "name") String authorName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        return bookService.searchAuthor(authorName, PageRequest.of(page, size));
    }
}
