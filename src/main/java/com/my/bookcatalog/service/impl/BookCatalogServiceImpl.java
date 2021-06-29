package com.my.bookcatalog.service.impl;

import com.my.bookcatalog.domain.BookCatalog;
import com.my.bookcatalog.repository.BookCatalogRepository;
import com.my.bookcatalog.service.BookCatalogService;
import com.my.bookcatalog.web.rest.mapper.BookCatalogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link BookCatalog}.
 */
@Service
public class BookCatalogServiceImpl implements BookCatalogService {

    private final Logger log = LoggerFactory.getLogger(BookCatalogServiceImpl.class);

    private final BookCatalogRepository bookCatalogRepository;

    private final BookCatalogMapper bookCatalogMapper;

    public BookCatalogServiceImpl(BookCatalogRepository bookCatalogRepository, BookCatalogMapper bookCatalogMapper) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.bookCatalogMapper = bookCatalogMapper;
    }

    @Override
    public BookCatalog save(BookCatalog bookCatalog) {
        log.debug("Request to save BookCatalog : {}", bookCatalog);
        return bookCatalogRepository.save(bookCatalog);
    }

    @Override
    public Page<BookCatalog> findAll(Pageable pageable) {
        log.debug("Request to get all BookCatalogs");
        return bookCatalogRepository.findAll(pageable);
    }

    @Override
    public Optional<BookCatalog> findOne(String id) {
        log.debug("Request to get BookCatalog : {}", id);
        return bookCatalogRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete BookCatalog : {}", id);
        bookCatalogRepository.deleteById(id);
    }
}
