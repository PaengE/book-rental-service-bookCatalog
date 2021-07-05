package com.my.bookcatalog.web.rest.mapper;

import org.junit.jupiter.api.BeforeEach;

class BookCatalogMapperTest {

    private BookCatalogMapper bookCatalogMapper;

    @BeforeEach
    public void setUp() {
        bookCatalogMapper = new BookCatalogMapperImpl();
    }
}
