package com.hexad.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hexad.service.LibraryServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class TestLibraryManagmentController {

	@InjectMocks
	private LibraryManagmentController libraryManagmentController;
	
	@Mock
	private LibraryServiceImpl libraryService;

	@Test
	public void testGetLibraryWithEmptyLibrary()  {}
	
	@Test
	public void testGetLibraryWithBooks()  {}
	
	@Test
	public void testSubmitBook(){}
	

}