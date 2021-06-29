package com.my.bookcatalog.service;

import com.my.bookcatalog.domain.BookCatalog;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.my.bookcatalog.domain.BookCatalog}.
 */
public interface BookCatalogService {
    /**
     * Save a bookCatalog.
     *
     * @param bookCatalogDTO the entity to save.
     * @return the persisted entity.
     */
    BookCatalog save(BookCatalog bookCatalog);

    /**
     * Get all the bookCatalogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookCatalog> findAll(Pageable pageable);

    /**
     * Get the "id" bookCatalog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookCatalog> findOne(String id);

    /**
     * Delete the "id" bookCatalog.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
