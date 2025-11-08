package com.example.demographqlandspring.controller;


import com.example.demographqlandspring.model.Book;
import com.example.demographqlandspring.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.util.List;


@Controller
public class GraphqlController {


    private final BookService service;


    public GraphqlController(BookService service) {
        this.service = service;
    }


    @QueryMapping
    public List<Book> books() {
        return service.findAll();
    }


    @QueryMapping
    public Book bookById(@Argument Long id) {
        return service.findById(id).orElse(null);
    }


    @MutationMapping
    public Book createBook(@Argument String title, @Argument String author) {
        Book b = new Book(title, author);
        return service.save(b);
    }


    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument String title, @Argument String author) {
        return service.findById(id).map(book -> {
            if (title != null) book.setTitle(title);
            if (author != null) book.setAuthor(author);
            return service.save(book);
        }).orElse(null);
    }


    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return true;
        }
        return false;
    }
}