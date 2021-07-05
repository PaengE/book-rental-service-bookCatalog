package com.my.bookcatalog.web.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the {@link com.my.bookcatalog.domain.BookCatalog} entity.
 */
@Getter
@Setter
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

    public Boolean isRented() {
        return rented;
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
