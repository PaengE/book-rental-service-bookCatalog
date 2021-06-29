package com.my.bookcatalog.web.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.my.bookcatalog.domain.BookCatalog} entity.
 */
public class BookCatalogDTO implements Serializable {

    private String id;

    private String title;

    private String author;

    private String description;

    private LocalDate publicationDate;

    private String classification;

    private Boolean rented;

    private Long rentCnt;

    private Long bookId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Long getRentCnt() {
        return rentCnt;
    }

    public void setRentCnt(Long rentCnt) {
        this.rentCnt = rentCnt;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookCatalogDTO)) {
            return false;
        }

        BookCatalogDTO bookCatalogDTO = (BookCatalogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bookCatalogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookCatalogDTO{" +
            "id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", description='" + getDescription() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", classification='" + getClassification() + "'" +
            ", rented='" + getRented() + "'" +
            ", rentCnt=" + getRentCnt() +
            ", bookId=" + getBookId() +
            "}";
    }
}
