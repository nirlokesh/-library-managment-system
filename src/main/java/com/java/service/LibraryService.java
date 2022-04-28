package com.java.service;

import com.java.entity.Library;
import com.java.exception.NoBooksException;

public interface LibraryService {

	/**
	 * Load library.
	 *
	 * @param userName the user name
	 * @return the library
	 * @throws NoBooksException 
	 */
	Library loadLibrary(String userName) throws NoBooksException;
	
	/**
	 * Borrow book.
	 *
	 * @param userName the user name
	 * @param bookId the book id
	 * @return the library
	 * @throws NoBooksException 
	 */
	Library borrowBook(String userName, Integer bookId) throws NoBooksException;
	
	/**
	 * Submit book.
	 *
	 * @param userName the user name
	 * @param bookId the book id
	 * @return the library
	 * @throws NoBooksException 
	 */
	Library submitBook(String userName, Integer bookId) throws NoBooksException;
	
}
