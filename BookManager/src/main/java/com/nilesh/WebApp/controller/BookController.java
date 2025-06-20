package com.nilesh.WebApp.controller;

import com.nilesh.WebApp.model.Book;
import com.nilesh.WebApp.service.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BookController {
    @Autowired
    private BookServices service;

    @GetMapping("/books")
    public List<Book> getbooks()
    {
        return service.getBooks();
    }

    @GetMapping("/book/{bookid}")
    public Book getBookById(@PathVariable int bookid)
    {
        return service.getBookById(bookid);
    }


    @PostMapping("/addbook")
    public  void addBook(@RequestBody Book bk)
    {
        service.adBook(bk);

    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book bk)
    {
        return service.updateBook(bk);
    }

    @DeleteMapping("/delete/{id}")
    public  String  deleteProduct(@PathVariable int id)
    {
        return service.deleteBook(id);
    }
}


