package com.smit.library.service;

import com.smit.library.modal.Book;
import com.smit.library.modal.Customer;
import org.javatuples.Pair;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public interface LibraryService {
    HashSet<Book> findAllBooks();
    HashSet<Book> findBooksByTitle(String title);
    HashSet<Book> findBooksByAuthor(String author);
    HashSet<Book> findBooksByReleaseDate(LocalDate releaseDate);
    Book findBookById(long id);
    String findBookLocationById(long id);
    int findBookQuantityById(long id);
    void setBookQuantityById(long id, int quantity);
    void setBookLendPeriodById(long id, long lendperiod);
    long findLendPeriodById(long id);
    void addBook(Book b);
    void removeBookById(long id);
    void assignBookToCustomer(long bookId, long customerId);
    void retrieveBookFromCustomer(long bookId, long customerId);
    HashSet<Customer> findAllCustomers();
    Customer findCustomerById(long id);
    HashSet<Customer> findCustomerByName(String name);
    HashSet<Pair<Customer, HashSet<Pair<Book, LocalDate>>>> findOverdueCustomers();
    void addCustomer(Customer c);
    void removeCustomerById(long id);
}
