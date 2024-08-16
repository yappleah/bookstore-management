package com.jac.bookstoreProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jac.bookstoreProject.model.Book;

@Service
public interface BookService {
	
	List<Book> findAll();
	
	Book findById(int theId);
	
	void save(Book theBook);
	
	void deleteById(int theId);
}
