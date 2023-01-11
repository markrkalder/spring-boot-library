package com.smit.library.controller;

import com.smit.library.modal.Book;
import com.smit.library.modal.Customer;
import com.smit.library.service.LibraryServiceImpl;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;

@RestController
public class LibraryController {

    @Autowired
    LibraryServiceImpl libraryServiceImpl;

    @GetMapping("/findallbooks")
    public HashSet<Book> findAllBooks() {
        return libraryServiceImpl.findAllBooks();
    }

    @PostMapping("/addbook")
    public void addBook(@RequestBody Book book) {
        libraryServiceImpl.addBook(book);
    }

    @GetMapping("/findbooksbytitle")
    public HashSet<Book> findBooksByTitle(@RequestBody String title) {
        return libraryServiceImpl.findBooksByTitle(title);
    }

    @GetMapping("/findbooksbyauthor")
    public HashSet<Book> findbooksByAuthor(@RequestBody String author) {
        return libraryServiceImpl.findBooksByAuthor(author);
    }

    @GetMapping("/findbooksbyreleasedate")
    public HashSet<Book> findbooksByReleaseDate(@RequestBody LocalDate releaseDate) {
        return libraryServiceImpl.findBooksByReleaseDate(releaseDate);
    }

    @GetMapping("/findbookbyid/{id}")
    public Book findBookById(@PathVariable long id) {
        return libraryServiceImpl.findBookById(id);
    }
    @GetMapping("/findbooklocationbyid/{id}")
    public String findBookLocationById(@PathVariable long id) {
        return libraryServiceImpl.findBookLocationById(id);
    }

    @GetMapping("/findbookquantitybyid/{id}")
    public int findBookQuantityById(@PathVariable long id) { return libraryServiceImpl.findBookQuantityById(id);
    }

    @PostMapping("/setbookquantitybyid/{id}")
    public void setBookQuantityById(@PathVariable long id, @RequestBody int quantity) {
        libraryServiceImpl.setBookQuantityById(id, quantity);
    }

    @PostMapping("/setbooklendperiodbyid/{id}")
    public void setBookLendPeriodById(@PathVariable long id, @RequestBody long lendperiod) {
        libraryServiceImpl.setBookLendPeriodById(id, lendperiod);
    }

    @GetMapping("/findbooklendperiodbyid/{id}")
    public long findLendPeriodById(@PathVariable long id) {
        return libraryServiceImpl.findLendPeriodById(id);
    }

    @DeleteMapping("/removebookbyid/{id}")
    public void removeBookById(@PathVariable long id) {
        libraryServiceImpl.removeBookById(id);
    }

    @PostMapping("/assignbooktocustomer")
    public void assignBookToCustomer(@RequestBody long bookId, long customerId) {
        libraryServiceImpl.assignBookToCustomer(bookId, customerId);
    }

    @PostMapping("/retrievebookfromcustomer")
    public void retrieveBookFromCustomer(@RequestBody long bookId, long customerId) {
        libraryServiceImpl.retrieveBookFromCustomer(bookId, customerId);
    }

    @GetMapping("/findallcustomers")
    public HashSet<Customer> findAllCustomers() {
        return libraryServiceImpl.findAllCustomers();
    }

    @GetMapping("/findcustomerbyid/{id}")
    public Customer findCustomerById(@PathVariable long id) {
        return libraryServiceImpl.findCustomerById(id);
    }

    @GetMapping("/findcustomerbyname")
    public HashSet<Customer> findCustomerByName(@RequestBody String name) {
        return libraryServiceImpl.findCustomerByName(name);
    }

    @GetMapping("/findoverduecustomers")
    public HashSet<Pair<Customer, HashSet<Pair<Book, LocalDate>>>> findOverdueCustomers() {
        return libraryServiceImpl.findOverdueCustomers();
    }

    @PostMapping("/addcustomer")
    public void addCustomer(@RequestBody Customer customer) {
        libraryServiceImpl.addCustomer(customer);
    }

    @DeleteMapping("/removecustomerbyid/{id}")
    public void removecustomerById(@PathVariable long id) {
        libraryServiceImpl.removeCustomerById(id);
    }
}
