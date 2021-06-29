package com.my.bookcatalog.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.bookcatalog.web.rest.mapper.BookCatalogMapper;
import org.junit.jupiter.api.BeforeEach;

class BookCatalogMapperTest {

    private BookCatalogMapper bookCatalogMapper;

    @BeforeEach
    public void setUp() {
        bookCatalogMapper = new BookCatalogMapperImpl();
    }
}
