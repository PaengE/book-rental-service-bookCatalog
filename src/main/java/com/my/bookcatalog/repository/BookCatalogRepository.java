package com.my.bookcatalog.repository;

import com.my.bookcatalog.domain.BookCatalog;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the BookCatalog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookCatalogRepository extends MongoRepository<BookCatalog, String> {
    Page<BookCatalog> findByTitleContaining(String title, Pageable pageable);

    BookCatalog findByBookId(Long bookId);

    void deleteByBookId(Long bookId);

    List<BookCatalog> findTop10ByOrderByRentCntDesc();
}
