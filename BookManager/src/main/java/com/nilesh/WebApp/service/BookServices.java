package com.nilesh.WebApp.service;

import com.nilesh.WebApp.Exception.BookNotFoundException;
import com.nilesh.WebApp.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookServices {

    List <Book> books = new ArrayList<>(Arrays.asList(new Book(533,"CPP","Charles"),
            new Book(343,"DSA","C.A.Lan"),
            new Book(45,"Java","James")));

    public List<Book> getBooks()
    {
        return books;
    }

    public Book getBookById(int id)
    {
        return books.stream().
                filter(p->p.getId()==id)
                .findFirst().orElseThrow(() -> new BookNotFoundException(id));
    }

    public void adBook(Book bk)
    {
        books.add(bk);
    }


    public Book updateBook(Book prod) {

        Book pr= books.stream().filter(p->p.getId()==prod.getId()).findFirst().get();
        pr.setAuthor(prod.getAuthor());
        pr.setTitle(prod.getTitle());

        return pr;
    }

    public String deleteBook(int id) {

        if(books.removeIf(item->item.getId()==id))
            return "Book Removed";
        else
            return "Book Unavailable";

    }


}
