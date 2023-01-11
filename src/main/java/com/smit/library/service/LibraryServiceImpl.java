package com.smit.library.service;

import com.smit.library.modal.Book;
import com.smit.library.modal.Customer;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

@Service
public class LibraryServiceImpl implements LibraryService {

    HashSet<Book> bookList;
    HashSet<Customer> customerList;

    {
        try {
            bookList = new HashSet<>(initializeBookList());
            customerList = new HashSet<>(initializeCustomerList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<Book> initializeBookList() throws FileNotFoundException {
        HashSet<Book> initialBookList = new HashSet<>();
        Scanner input = new Scanner(new File("./src/main/resources/static/books.txt"));
        input.useDelimiter("\n");
        while(input.hasNext()){
            long id = Long.parseLong(input.nextLine());
            String author = input.nextLine();
            String title = input.nextLine();
            String releaseDateLine = input.nextLine();
            String[] splitReleaseDate = releaseDateLine.split(" ");
            LocalDate releaseDate = LocalDate.of(Integer.parseInt(splitReleaseDate[0]),
                    Integer.parseInt(splitReleaseDate[1]), Integer.parseInt(splitReleaseDate[2]));
            int quantity = Integer.parseInt(input.nextLine());
            String location = input.nextLine();
            System.out.println(" id: "+ id + " author: "+ author + " title: "+ title + " releaseDate: " + releaseDate + " quantity: " + quantity + " location: " + location);
            initialBookList.add(new Book(id, author, title, releaseDate,
                    quantity, location));

            if (input.hasNext()) {
                input.nextLine();
            }
            else break;
        }
        return initialBookList;
    }

    public HashSet<Customer> initializeCustomerList() throws FileNotFoundException {
        HashSet<Customer> initialCustomerList = new HashSet<>();
        Scanner input = new Scanner(new File("./src/main/resources/static/customers.txt"));
        input.useDelimiter("\n");
        while(input.hasNext()){
            long id = Long.parseLong(input.nextLine());
            String name = input.nextLine();
            initialCustomerList.add(new Customer(id, name));

            if (input.hasNext()) {
                input.nextLine();
            }
            else break;
        }
        return initialCustomerList;
    }

    @Override
    public HashSet<Book> findAllBooks() {
        for (Book book : bookList) {
            System.out.println(book.getId());
            System.out.println(book.getAuthor());
        }
        if (bookList.isEmpty()) return null;
        else return bookList;
    }

    @Override
    public HashSet<Book> findBooksByTitle(String title) {
        HashSet<Book> matchingResults = new HashSet<>();
        for (Book book : bookList) {
            if (book.getTitle().equals(title)){
                matchingResults.add(book);
            }
        }
        if (matchingResults.isEmpty()) return null;
        else return matchingResults;
    }

    @Override
    public HashSet<Book> findBooksByAuthor(String author) {
        HashSet<Book> matchingResults = new HashSet<>();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)){
                matchingResults.add(book);
            }
        }
        if (matchingResults.isEmpty()) return null;
        else return matchingResults;
    }

    @Override
    public HashSet<Book> findBooksByReleaseDate(LocalDate releaseDate) {
        HashSet<Book> matchingResults = new HashSet<>();
        for (Book book : bookList) {
            if (book.getReleaseDate().equals(releaseDate)){
                matchingResults.add(book);
            }
        }
        if (matchingResults.isEmpty()) return null;
        else return matchingResults;
    }

    @Override
    public Book findBookById(long id) {
        System.out.println("Finding the book with id " + id);
        for (Book book : bookList) {
            if (book.getId() == id){
                System.out.println("Book with id " + id + " exists");
                return book;
            }
        }
        System.out.println("A book with this id was not found in the system");
        return null;
    }

    @Override
    public String findBookLocationById(long id) {
        System.out.println("Starting to find books location by id of " + id);
        Book book = findBookById(id);
        if (!Objects.isNull(book)) {
            System.out.println("Book location is: " + book.getLocation());
            return book.getLocation();
        }
        else return null;
    }

    @Override
    public int findBookQuantityById(long id) {
        System.out.println("Starting to find books quantity by id of " + id);
        Book book = findBookById(id);
        if (!Objects.isNull(book)) {
            System.out.println("Books quantity is: " + book.getQuantity());
            return book.getQuantity();
        }
        return 0;
    }

    @Override
    public void setBookQuantityById(long id, int quantity) {
        System.out.println("Starting to set the quantity of " +
                quantity + " to the book with the id of " + id);
        Book book = findBookById(id);
        if (!Objects.isNull(book)) {
            book.setQuantity(quantity);
            System.out.println("Books new quantity is set ");
        }
    }

    @Override
    public void setBookLendPeriodById(long id, long lendperiod) {
        System.out.println("Starting to set the lend period of " +
                lendperiod + " to the book with the id of " + id);
        Book book = findBookById(id);
        if (!Objects.isNull(book)) {
            book.setLendPeriod(lendperiod);
            System.out.println("Books new quantity is set ");
        }
    }

    @Override
    public long findLendPeriodById(long id) {
        System.out.println("Starting to find books lend period by id of " + id);
        Book book = findBookById(id);
        if (!Objects.isNull(book)) {
            System.out.println("Books lend period is: " + book.getLendPeriod());
            return book.getLendPeriod();
        }
        return 0;
    }


    @Override
    public void addBook(Book book) {
        if (findBookById(book.getId()) == null) {
            bookList.add(book);
            System.out.println("New book with the id of " + book.getId()
                    + " has been added to the system");
        }

        System.out.println("Book with this id already exists in the system");
    }

    @Override
    public void removeBookById(long id) {
        System.out.println("Starting to remove a book with the id of " +
                id + " from the system");
        Book bookToRemove = findBookById(id);
        if (bookToRemove != null) {
            bookList.remove(bookToRemove);
            System.out.println("Removed a book with the id of " + id + " from the system");
        }
    }

    @Override
    public void assignBookToCustomer(long bookId, long customerId) {
        System.out.println("Starting to assign a book with id " +
                bookId + " to customer with the id " + customerId);
        Book bookToAssign = findBookById(bookId);
        if (bookToAssign.getQuantity() < 1) {
            System.out.println("A book with insufficient quantity is being tried to assign to a customer");
            return;
        };
        Customer customerToAssign =  findCustomerById(customerId);
        LocalDate rentPeriodEnd = LocalDate.now().plusDays(bookToAssign.getLendPeriod());
        customerToAssign.getBookList().add(Pair.with(bookToAssign, rentPeriodEnd));
        bookToAssign.setQuantity(bookToAssign.getQuantity() - 1);
        System.out.println("Book with the id " + bookId +
                " has been assigned to the customer with the id "+ customerId);
    }

    @Override
    public void retrieveBookFromCustomer(long bookId, long customerId) {
        //TODO: logging
        //TODO: quantity add
        Book bookToRetrieve = findBookById(bookId);
        Customer customerToRetrieve =  findCustomerById(customerId);
        HashSet<Pair<Book, LocalDate>> customerBookList = customerToRetrieve.getBookList();
        customerBookList.removeIf(pair -> pair.getValue0().equals(bookToRetrieve));

    }

    @Override
    public HashSet<Customer> findAllCustomers() {
        System.out.println("Finding all customers");
        System.out.println("All customers with id and name:");
        for (Customer customer : customerList) {
            System.out.println("id: " + customer.getId()
                    + "name: " + customer.getName());
        }
        return customerList;
    }

    @Override
    public Customer findCustomerById(long id) {
        System.out.println("Finding the customer with id " + id);
        for (Customer customer : customerList) {
            if (customer.getId() == id){
                System.out.println("The customer with this id exists");
                return customer;
            }
        }
        System.out.println("A customer with this id was not found in the system");
        return null;
    }

    @Override
    public HashSet<Customer> findCustomerByName(String name) {
        System.out.println("Finding customers with the given name of " + name);
        HashSet<Customer> matchingResults = new HashSet<>();
        for (Customer customer : customerList) {
            if (customer.getName().equals(name)){
                System.out.println("Found a customer with the id of " +
                        customer.getId() + " with the matching name");
                matchingResults.add(customer);
            }
        }
        System.out.println("All customers with the matching name found:");
        for (Customer matchingResult : matchingResults) {
            System.out.println("id: " + matchingResult.getId() + " name: " + matchingResult.getName());
        }
        if (matchingResults.isEmpty()) return null;
        else return matchingResults;
    }

    @Override
    public HashSet<Pair<Customer, HashSet<Pair<Book, LocalDate>>>> findOverdueCustomers() {
        System.out.println("Finding all customers with overdue books");
        HashSet<Pair<Customer, HashSet<Pair<Book, LocalDate>>>> overdueCustomers = new HashSet<>();
        for (Customer customer : customerList ) {
            HashSet<Pair<Book, LocalDate>> customersOverdueBooks = new HashSet<>();
            for (Pair<Book, LocalDate> bookLocalDatePair : customer.getBookList()) {
                // The rent period end is before the current date
                if (bookLocalDatePair.getValue1().isBefore(LocalDate.now())){
                    customersOverdueBooks.add(Pair.with(bookLocalDatePair.getValue0(), bookLocalDatePair.getValue1()));
                }
            }
            if (!customersOverdueBooks.isEmpty()){
                overdueCustomers.add(Pair.with(customer, customersOverdueBooks));
            }
        }
        if (overdueCustomers.isEmpty()) {
            System.out.println("No overdue customers were found");
            return null;
        }
        System.out.println("All customers with an overdue book:");
        for (Pair<Customer, HashSet<Pair<Book, LocalDate>>> overdueCustomer : overdueCustomers) {
            System.out.println("Customer id: " + overdueCustomer.getValue0().getId() +
                    " customer name: " + overdueCustomer.getValue0().getName());
            for (Pair<Book, LocalDate> pair : overdueCustomer.getValue1()) {
                System.out.println("book id: " + pair.getValue0().getId()
                        + " book name: " + pair.getValue0().getTitle());
            }
        }
        return overdueCustomers;
    }

    @Override
    public void addCustomer(Customer c) {
        System.out.println("A customer with the id of "
                + c.getId() + "has been created in the system");
        customerList.add(c);
    }

    @Override
    public void removeCustomerById(long id) {
        System.out.println("Removing the customer with the id of " + id);
        Customer customerToRemove = findCustomerById(id);
        customerList.remove(customerToRemove);
        System.out.println("Customer with the id of " + id
                + " has been removed from the system");
    }
}
