package com.my.bookcatalog.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A BookCatalog.
 */
@Getter
@Setter
@Document(collection = "book_catalog")
public class BookCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("author")
    private String author;

    @Field("description")
    private String description;

    @Field("publication_date")
    private LocalDate publicationDate;

    @Field("classification")
    private String classification;

    @Field("rented")
    private Boolean rented;

    @Field("rent_cnt")
    private Long rentCnt;

    @Field("book_id")
    private Long bookId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public BookCatalog id(String id) {
        this.id = id;
        return this;
    }

    public BookCatalog title(String title) {
        this.title = title;
        return this;
    }

    public BookCatalog author(String author) {
        this.author = author;
        return this;
    }

    public BookCatalog description(String description) {
        this.description = description;
        return this;
    }

    public BookCatalog publicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public BookCatalog classification(String classification) {
        this.classification = classification;
        return this;
    }

    public BookCatalog rented(Boolean rented) {
        this.rented = rented;
        return this;
    }

    public BookCatalog rentCnt(Long rentCnt) {
        this.rentCnt = rentCnt;
        return this;
    }

    public BookCatalog bookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    // 신규 도서 카탈로그 생성
    public static BookCatalog registerNewBookCatalog(BookChanged bookChanged) {
        BookCatalog bookCatalog = new BookCatalog();
        bookCatalog.setBookId(bookChanged.getBookId());
        bookCatalog.setAuthor(bookChanged.getAuthor());
        bookCatalog.setClassification(bookChanged.getClassification());
        bookCatalog.setDescription(bookChanged.getDescription());
        bookCatalog.setPublicationDate(LocalDate.parse(bookChanged.getPublicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        bookCatalog.setRented(bookChanged.getRented());
        bookCatalog.setTitle(bookChanged.getTitle());
        bookCatalog.setRentCnt(bookChanged.getRentCnt());
        return bookCatalog;
    }

    // 도서 대출 상태 수정. '대출 중' 으로 수정
    public BookCatalog rentBook() {
        this.setRentCnt(this.getRentCnt() + (long) 1);
        this.setRented(true);
        return this;
    }

    // 도서 대출 상태 수정. '대출 가능' 으로 수정
    public BookCatalog returnBook() {
        this.setRented(false);
        return this;
    }

    // 도서 카탈로그 정보 수정
    public BookCatalog updateBookCatalogInfo(BookChanged bookChanged) {
        this.setAuthor(bookChanged.getAuthor());
        this.setClassification(bookChanged.getClassification());
        this.setDescription(bookChanged.getDescription());
        this.setPublicationDate(LocalDate.parse(bookChanged.getPublicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.setRented(bookChanged.getRented());
        this.setTitle(bookChanged.getTitle());
        this.setRentCnt(bookChanged.getRentCnt());
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookCatalog)) {
            return false;
        }
        return id != null && id.equals(((BookCatalog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookCatalog{" +
            "id=" + getId() +
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
