package com.hexad.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hexad.exception.LibraryException;
import com.hexad.repository.BorrowListRepository;
import com.hexad.repository.LibraryRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestLibraryService {
	
	private static final String USER_NAME = "hexad";

	@Mock
	private LibraryRepository libraryRepository;

	@Mock
	private BorrowListRepository borrowListRepository;
	
	@InjectMocks
	private LibraryServiceImpl libraryService;
	
	@Test(expected=LibraryException.class)
	public void testGetAllBooksWithNoBooks() {}
	
	@Test
	public void testGetAllBooksWithBooks() {}
	
	@Test(expected=LibraryException.class)
	public void testBorrowBookNoBookAvailable() {}
	
	@Test(expected=LibraryException.class)
	public void testBorrowBookAlredyBorrowed() {}
	
	@Test(expected=LibraryException.class)
	public void testBorrowBookOutOfLimitBorrow() {}
	
	@Test
	public void testBorrowBook() {}
	
	@Test
	public void testSubmitBook(){}

}
