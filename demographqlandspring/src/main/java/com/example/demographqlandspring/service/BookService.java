package com.example.demographqlandspring.service;



import com.example.demographqlandspring.model.Book;
import com.example.demographqlandspring.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class BookService {


    private final BookRepository repo;


    public BookService(BookRepository repo) {
        this.repo = repo;
    }


    public List<Book> findAll() { return repo.findAll(); }


    public Optional<Book> findById(Long id) { return repo.findById(id); }


    public Book save(Book book) { return repo.save(book); }


    public void deleteById(Long id) { repo.deleteById(id); }
}