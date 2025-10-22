package com.nazire.booktrack_pro.dto;

import jakarta.validation.constraints.*;

/*
kullanıcı yeni bir kayıt oluşturmak istediğinde
frontend'den gelen isteği karşılayan sınıf
 */
public class CreateBookRequest {
    @NotBlank @Size(max = 200)
    private String title;

    @NotBlank @Size(max = 120)
    private String author;

    @NotBlank @Pattern(regexp = "^[0-9-] {10,17}$", message = "Invalid ISBN")
    private String isbn;

    @Min(1450) @Max(2025)
    private Integer publishedYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
