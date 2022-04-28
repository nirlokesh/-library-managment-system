package com.java.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.java.exception.NoBooksException;
import com.java.response.LibraryResponse;
import com.java.service.LibraryServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class TestLibraryManagmentController {

	@InjectMocks
	private LibraryManagmentController libraryManagmentController;
	
	@Mock
	private LibraryServiceImpl libraryService;

	@Test
	public void testGetLibraryWithEmptyLibrary() throws NoBooksException  {
		Mockito.when(this.libraryService.loadLibrary("roche")).thenReturn(null);
		LibraryResponse library = this.libraryManagmentController.getLibrary("roche");
		Assert.assertNull("The library object will be null when there is no books available in the library.", library.getLibrary());
	}
	
	@Test
	public void testGetLibraryWithBooks() throws NoBooksException  {
		Mockito.when(this.libraryService.loadLibrary("roche")).thenReturn(getLibrary());
		LibraryResponse library = this.libraryManagmentController.getLibrary("roche");
		Assert.assertEquals("Library is having two books", 2, library.getLibrary().getBookList().size());
	}
	
	@Test
	public void testSubmitBook() throws NoBooksException{
		 List<BorrowList> borrowList = new ArrayList<>();
		 borrowList.add(new BorrowList("roche", 2));
			Library library = new Library();
		 library.setBorrowList(new ArrayList<BorrowList>());
		 Mockito.when(this.libraryService.submitBook(Mockito.anyString(), Mockito.anyInt())).thenReturn(library);
		 LibraryResponse libraryResponse = this.libraryManagmentController.submitBook("roche", 2);
		 Assert.assertNotEquals("Borrow list will get reduced with one entry",borrowList.size(), libraryResponse.getLibrary().getBorrowList().size());
	}
	
	private static Library getLibrary() {
		Library library = new Library();
		List<Book> bookList = new ArrayList<>();
		Book book = new Book();
		book.setName("Java head first");
		book.setISBN(1);
		book.setDescription("Devid T.");
		book.setCount(2);
		bookList.add(book);
		Book book2 = new Book();
		book2.setName("Spring head first");
		book2.setISBN(2);
		book2.setDescription("John T.");
		book2.setCount(4);
		bookList.add(book2);
		library.setBookList(bookList);
		return library;
	}

}