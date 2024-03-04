package tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import input.HTMLInput;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HTMLInputTest {
	@Test
    public void testOpenFileAndReadReceiptDataFromFile() throws IOException {
		File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFile.html");
		HTMLInput testHTMLInput = new HTMLInput(testFile);
		
		testHTMLInput.openFile();
		assertNotNull(testHTMLInput.getReceiptManager());
		
		assertEquals("Konstantinos Papathanasiou", testHTMLInput.getReceiptManager().getName());
		assertEquals("130456097", testHTMLInput.getReceiptManager().getAfm());
		assertEquals(1, testHTMLInput.getReceiptManager().getReceipts().get(0).getReceiptID());
		assertEquals("25/2/2014", testHTMLInput.getReceiptManager().getReceipts().get(0).getDate());
		assertEquals("Coat", testHTMLInput.getReceiptManager().getReceipts().get(0).getKind());
		assertEquals((double) 2000, testHTMLInput.getReceiptManager().getReceipts().get(0).getSales(), 0.01);
		assertEquals(10, testHTMLInput.getReceiptManager().getReceipts().get(0).getItems());
		assertEquals("Hand Made Clothes", testHTMLInput.getReceiptManager().getReceipts().get(0).getCompany().getName());
		assertEquals("Greece", testHTMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getCountry());
		assertEquals("Ioannina", testHTMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getCity());
		assertEquals("Kaloudi", testHTMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getStreet());
		assertEquals(10, testHTMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getStreetNumber());
		
		
		assertEquals(2, testHTMLInput.getReceiptManager().getReceipts().get(1).getReceiptID());
		assertEquals("15/10/23", testHTMLInput.getReceiptManager().getReceipts().get(1).getDate());
		assertEquals("Coat", testHTMLInput.getReceiptManager().getReceipts().get(1).getKind());
		assertEquals((double) 75, testHTMLInput.getReceiptManager().getReceipts().get(1).getSales(), 0.01);
		assertEquals(3, testHTMLInput.getReceiptManager().getReceipts().get(1).getItems());
		assertEquals("Hand Made Clothes", testHTMLInput.getReceiptManager().getReceipts().get(1).getCompany().getName());
		assertEquals("Greece", testHTMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getCountry());
		assertEquals("Ioannina", testHTMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getCity());
		assertEquals("Souliou", testHTMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getStreet());
		assertEquals(45, testHTMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getStreetNumber());
	}

}

