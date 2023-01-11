package com.smit.library.modal;

import org.javatuples.Pair;

import java.time.LocalDate;
import java.util.HashSet;

public class Customer {
    private long id;
    private String name;
    private HashSet<Pair<Book, LocalDate>> bookList;

    // For creating a new customer through the API
    public Customer(){}

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
        this.bookList = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public HashSet<Pair<Book, LocalDate>> getBookList() {
        return bookList;
    }
}
