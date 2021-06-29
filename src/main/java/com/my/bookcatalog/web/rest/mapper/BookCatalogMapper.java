package com.my.bookcatalog.web.rest.mapper;

import com.my.bookcatalog.domain.*;
import com.my.bookcatalog.web.rest.dto.BookCatalogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BookCatalog} and its DTO {@link BookCatalogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookCatalogMapper extends EntityMapper<BookCatalogDTO, BookCatalog> {}
