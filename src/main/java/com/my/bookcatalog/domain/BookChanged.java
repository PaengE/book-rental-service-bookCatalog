package com.my.bookcatalog.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookChanged {

    private String title;

    private String description;

    private String author;

    private String publicationDate;

    private String classification;

    private Boolean rented;

    private Long rentCnt;

    private String eventType;

    private Long bookId;
}
