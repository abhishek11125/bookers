package com.bookers.repository;

import com.bookers.exception.AuthorException;
import com.bookers.exception.BookException;
import com.bookers.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {

    public List<Book> findByTitle(String title)throws BookException;

    public List<Book> findByAuthorName(String name)throws AuthorException;

}
