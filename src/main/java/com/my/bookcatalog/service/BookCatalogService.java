package com.my.bookcatalog.service;

import com.my.bookcatalog.domain.BookCatalog;
import com.my.bookcatalog.domain.BookChanged;
import java.util.List;
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
     * @param bookCatalog the entity to save.
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

    // 제목으로 도서 검색
    Page<BookCatalog> findBookByTitle(String title, Pageable pageable);

    // kafka 이벤트 종류별 카테고라이징 처리
    void processCatalogChanged(BookChanged bookChanged);

    // 인기 도서 목록 조히
    List<BookCatalog> loadTop10();
}
