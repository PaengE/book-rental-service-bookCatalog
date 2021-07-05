package com.my.bookcatalog.service.mapper;

import com.my.bookcatalog.web.rest.mapper.BookCatalogMapper;
import com.my.bookcatalog.web.rest.mapper.BookCatalogMapperImpl;
import org.junit.jupiter.api.BeforeEach;

class BookCatalogMapperTest {

    private BookCatalogMapper bookCatalogMapper;

    @BeforeEach
    public void setUp() {
        bookCatalogMapper = new BookCatalogMapperImpl();
    }
}
