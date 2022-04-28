package com.java.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.entity.Book;
import com.java.entity.BorrowList;
import com.java.entity.Library;
import com.java.exception.LibraryException;
import com.java.exception.NoBooksException;
import com.java.repository.BorrowListRepository;
import com.java.repository.LibraryRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestLibraryService {
	
	private static final String USER_NAME = "roche";

	@Mock
	private LibraryRepository libraryRepository;

	@Mock
	private BorrowListRepository borrowListRepository;
	
	@InjectMocks
	private LibraryServiceImpl libraryService;
	
	@Test(expected=NoBooksException.class)
	public void testLoadLibraryWithNoBooks() throws NoBooksException {
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Collections.emptyList());
		this.libraryService.loadLibrary(USER_NAME);
	}
	
	@Test
	public void testLoadLibraryWithBooks() throws NoBooksException {
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Arrays.asList(new Book()));
		Library loadLibrary = this.libraryService.loadLibrary(USER_NAME);
		Assert.assertTrue("Library will contain book list", !loadLibrary.getBookList().isEmpty());
		
	}
	
	@Test(expected=LibraryException.class)
	public void testBorrowBookNoBookAvailable() throws NoBooksException {
		Book book = new Book();
		book.setCount(0);
		Optional<Book> bookOp = Optional.of(book);
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		this.libraryService.borrowBook(USER_NAME, 1);
	}
	
	@Test(expected=LibraryException.class)
	public void testBorrowBookAlredyBorrowed() throws NoBooksException {
		Book book = new Book();
		book.setCount(3);
		Optional<Book> bookOp = Optional.of(book);
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.borrowListRepository.findAll()).thenReturn(getBorrowList());
		this.libraryService.borrowBook(USER_NAME, 1);
	}
	
	@Test(expected=LibraryException.class)
	public void testBorrowBookOutOfLimitBorrow() throws NoBooksException {
		Book book = new Book();
		book.setCount(3);
		Optional<Book> bookOp = Optional.of(book);
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.borrowListRepository.findAll()).thenReturn(getBorrowListWithOutOfLimitBook());
		this.libraryService.borrowBook(USER_NAME, 3);
	}
	
	@Test
	public void testBorrowBook() throws NoBooksException {
		Book book = new Book();
		book.setCount(3);
		Optional<Book> bookOp = Optional.of(book);
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.borrowListRepository.findAll()).thenReturn(getBorrowList());
		Mockito.when(this.borrowListRepository.save(Mockito.any())).thenReturn(new BorrowList());
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Arrays.asList(new Book()));
		Library borrowBook = this.libraryService.borrowBook(USER_NAME, 3);
		Assert.assertFalse("Borrow list will not be empty", borrowBook.getBorrowList().isEmpty());
	}
	
	@Test
	public void testSubmitBook() throws NoBooksException{
		Book book = new Book();
		book.setCount(3);
		Optional<Book> bookOp = Optional.of(book);
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.libraryRepository.save(Mockito.any())).thenReturn(new Book());
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Arrays.asList(new Book()));
		Library submitBook = this.libraryService.submitBook(USER_NAME, 2);
		Assert.assertFalse("Book will no longer be in borrow list", submitBook.getBorrowList().contains(new BorrowList(USER_NAME, 2)));
	}

	private List<BorrowList> getBorrowList() {
		return Arrays.asList(new BorrowList(USER_NAME, 1));
	}
	private List<BorrowList> getBorrowListWithOutOfLimitBook() {
		return Arrays.asList(new BorrowList(USER_NAME, 1),new BorrowList(USER_NAME, 2));
	}

}
