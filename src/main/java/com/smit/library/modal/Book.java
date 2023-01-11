package com.smit.library.modal;

import java.time.LocalDate;
import java.time.Period;

public class Book {
    private long id;
    private String author;
    private String title;
    private LocalDate releaseDate;
    private int quantity;
    private String location;
    private long lendPeriod;

    // For adding new books with JSON
    public Book(){}
    public Book(long id, String author, String title, LocalDate releaseDate, int quantity, String location) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.releaseDate = releaseDate;
        this.quantity = quantity;
        this.location = location;
        // The book has been released less than 3 months ago or the quantity is less than 5
        if (Period.between(releaseDate, LocalDate.now()).getMonths() < 3 || quantity < 5){
            this.lendPeriod = 1;
        }
        else {
            this.lendPeriod = 4;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 5 || Period.between(this.releaseDate, LocalDate.now()).getMonths() < 3){
            this.lendPeriod = 1;
        }
        else {
            this.lendPeriod = 4;
        }
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getLendPeriod() {
        return lendPeriod;
    }

    public void setLendPeriod(long lendPeriod) {
        this.lendPeriod = lendPeriod;
    }

    public long getId() {
        return id;
    }
}
