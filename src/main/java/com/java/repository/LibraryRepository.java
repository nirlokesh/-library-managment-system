package com.java.repository;

import org.springframework.data.repository.CrudRepository;

import com.java.entity.Book;
public interface LibraryRepository extends CrudRepository<Book, Integer>  
{  
}
