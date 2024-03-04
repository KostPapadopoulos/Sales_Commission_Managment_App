package tests;

import org.junit.Before;
import org.junit.Test;

import data.Receipt;
import input.TXTInput;
import output.ReceiptAppender;
import output.ReceiptAppenderTXT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ReceiptAppenderTXTTest {
	
	@Test
    public void testOpenFileAndAddReceiptDataToFile() throws IOException {
        File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFile.txt");
        ReceiptAppenderTXT testReceiptAppenderTXT = new ReceiptAppenderTXT();
        testReceiptAppenderTXT.setFileToAppend(testFile);
    	Receipt testReceipt = new Receipt("Skirt");
    	testReceipt.setDate("12/12/2023");
    	testReceipt.setReceiptID(50);
    	testReceipt.setSales(5000);
    	testReceipt.setItems(10);
    	testReceipt.getCompany().setName("Test Company");
    	testReceipt.getCompany().getCompanyAddress().setCity("Test City");
    	testReceipt.getCompany().getCompanyAddress().setCountry("Test Country");
    	testReceipt.getCompany().getCompanyAddress().setStreet("Test Street");
    	testReceipt.getCompany().getCompanyAddress().setStreetNumber(85);
    	
    	testReceiptAppenderTXT.setReceipt(testReceipt);
    	testReceiptAppenderTXT.appendFile();
   
    	
    	TXTInput testInput = new TXTInput(testFile);
    	testInput.readFile();
    	
    	assertNotNull(testInput.getReceiptManager());
    	
    	
    	assertEquals(50, testInput.getReceiptManager().getReceipts().get(2).getReceiptID());
    	assertEquals("12/12/2023", testInput.getReceiptManager().getReceipts().get(2).getDate());
    	assertEquals("Skirt", testInput.getReceiptManager().getReceipts().get(2).getKind());
    	assertEquals((double) 5000, testInput.getReceiptManager().getReceipts().get(2).getSales(), 0.01);
    	assertEquals(10, testInput.getReceiptManager().getReceipts().get(2).getItems());
    	assertEquals("Test Company", testInput.getReceiptManager().getReceipts().get(2).getCompany().getName());
    	assertEquals("Test Country", testInput.getReceiptManager().getReceipts().get(2).getCompany().getCompanyAddress().getCountry());
    	assertEquals("Test City", testInput.getReceiptManager().getReceipts().get(2).getCompany().getCompanyAddress().getCity());
    	assertEquals("Test Street", testInput.getReceiptManager().getReceipts().get(2).getCompany().getCompanyAddress().getStreet());
    	assertEquals(85, testInput.getReceiptManager().getReceipts().get(2).getCompany().getCompanyAddress().getStreetNumber());
	
	}
}

