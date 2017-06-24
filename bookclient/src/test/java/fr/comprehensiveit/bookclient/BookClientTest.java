package fr.comprehensiveit.bookclient;

import org.junit.Ignore;
import org.junit.Test;

public class BookClientTest {

	@Ignore
	@Test
	public void testGetBook() {
		BookClient bc = new BookClient();
		String result = bc.getBook();
		System.out.println(result);
	}
	
	@Test
	public void testGetBookJersey() {
		BookClient bc = new BookClient();
		String result = bc.getBookJersey();
		System.out.println(result);
	}
}
