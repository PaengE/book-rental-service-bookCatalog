package com.my.bookcatalog.service.impl;

import com.my.bookcatalog.domain.BookCatalog;
import com.my.bookcatalog.domain.BookChanged;
import com.my.bookcatalog.repository.BookCatalogRepository;
import com.my.bookcatalog.service.BookCatalogService;
import com.my.bookcatalog.web.rest.mapper.BookCatalogMapper;
import java.util.List;
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

    // 도서 카탈로그 등록
    private BookCatalog registerNewBook(BookChanged bookChanged) {
        BookCatalog bookCatalog = BookCatalog.registerNewBookCatalog(bookChanged);
        bookCatalog = bookCatalogRepository.save(bookCatalog);
        return bookCatalog;
    }

    // 도서 카탈로그 대출 상태 수정
    private BookCatalog updateBookStatus(BookChanged bookChanged) {
        BookCatalog bookCatalog = bookCatalogRepository.findByBookId(bookChanged.getBookId());
        if (bookChanged.getEventType().equals("RENT_BOOK")) {
            bookCatalog = bookCatalog.rentBook();
        } else if (bookChanged.getEventType().equals("RETURN_BOOK")) {
            bookCatalog = bookCatalog.returnBook();
        }
        bookCatalog = bookCatalogRepository.save(bookCatalog);
        return bookCatalog;
    }

    // 도서 카탈로그 정보 수정
    private BookCatalog updateBookInfo(BookChanged bookChanged) {
        BookCatalog bookCatalog = bookCatalogRepository.findByBookId(bookChanged.getBookId());
        bookCatalog = bookCatalog.updateBookCatalogInfo(bookChanged);
        bookCatalog = bookCatalogRepository.save(bookCatalog);
        return bookCatalog;
    }

    // 도서 카탈로그 삭제
    private void deleteBook(BookChanged bookChanged) {
        bookCatalogRepository.deleteByBookId(bookChanged.getBookId());
    }

    @Override
    public Page<BookCatalog> findBookByTitle(String title, Pageable pageable) {
        return bookCatalogRepository.findByTitleContaining(title, pageable);
    }

    // 이벤트 종류별 분기 처리
    @Override
    public void processCatalogChanged(BookChanged bookChanged) {
        String eventType = bookChanged.getEventType();
        switch (eventType) {
            case "NEW_BOOK":
                registerNewBook(bookChanged);
                break;
            case "DELETE_BOOK":
                deleteBook(bookChanged);
                break;
            case "RENT_BOOK":
            case "RETURN_BOOK":
                updateBookStatus(bookChanged);
                break;
            case "UPDATE_BOOK":
                updateBookInfo(bookChanged);
                break;
        }
    }

    @Override
    public List<BookCatalog> loadTop10() {
        return bookCatalogRepository.findTop10ByOrderByRentCntDesc();
    }
}
