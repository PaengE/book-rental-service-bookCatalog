package com.my.bookcatalog.cucumber;

import com.my.bookcatalog.BookCatalogApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BookCatalogApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
