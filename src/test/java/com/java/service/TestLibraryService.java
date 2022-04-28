package com.java.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.entity.Book;
import com.java.entity.BorrowList;
import com.java.entity.Library;
import com.java.exception.LibraryException;
import com.java.repository.BorrowListRepository;
import com.java.repository.LibraryRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestLibraryService {

	private static final String USER_NAME = "roche";

	private static Optional<Book> bookOp;

	@Mock
	private LibraryRepository libraryRepository;

	@Mock
	private BorrowListRepository borrowListRepository;

	@InjectMocks
	private LibraryServiceImpl libraryService;

	@BeforeAll
	public static void initialize() {
		Book book = new Book();
		book.setCount(3);
		bookOp = Optional.of(book);
	}

	@Test
	@DisplayName("Test Method just to display test case description instead of method name.")
	public void testLoadLibraryWithNoBooks() {
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(LibraryException.class, () -> this.libraryService.loadLibrary(USER_NAME));

	}

	@ParameterizedTest
	@ValueSource(strings = { "roche", "2ndUserName" })
	public void testLoadLibraryWithBooks(String userName) {
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Arrays.asList(new Book()));
		Library loadLibrary = this.libraryService.loadLibrary(userName);
		Assert.assertFalse("Library will contain book list", loadLibrary.getBookList().isEmpty());

	}

	@Test
	public void testBorrowBookOutOfLimitBorrow() {
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.borrowListRepository.findAll()).thenReturn(getBorrowListWithOutOfLimitBook());
		assertThrows(LibraryException.class, () -> this.libraryService.borrowBook(USER_NAME, 3));
	}

	@Test
	public void testBorrowBook() {
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.borrowListRepository.findAll()).thenReturn(getBorrowList());
		Mockito.when(this.borrowListRepository.save(Mockito.any())).thenReturn(new BorrowList());
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Arrays.asList(new Book()));
		Library borrowBook = this.libraryService.borrowBook(USER_NAME, 3);
		Assert.assertFalse("Borrow list will not be empty", borrowBook.getBorrowList().isEmpty());
	}

	@Test
	public void testSubmitBook() {
		Mockito.when(this.libraryRepository.findById(Mockito.anyInt())).thenReturn(bookOp);
		Mockito.when(this.libraryRepository.save(Mockito.any())).thenReturn(new Book());
		Mockito.when(this.libraryRepository.findAll()).thenReturn(Arrays.asList(new Book()));
		Library submitBook = this.libraryService.submitBook(USER_NAME, 2);
		Assert.assertFalse("Book will no longer be in borrow list",
				submitBook.getBorrowList().contains(new BorrowList(USER_NAME, 2)));
	}

	private List<BorrowList> getBorrowList() {
		return Arrays.asList(new BorrowList(USER_NAME, 1));
	}

	private List<BorrowList> getBorrowListWithOutOfLimitBook() {
		return Arrays.asList(new BorrowList(USER_NAME, 1), new BorrowList(USER_NAME, 2));
	}

}
