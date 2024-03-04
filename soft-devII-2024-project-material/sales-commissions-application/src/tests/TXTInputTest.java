package tests;

import input.TXTInput;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;




public class TXTInputTest {

  
    @Test
    public void testOpenFileAndReadReceiptDataFromFile() throws IOException {
        File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFile.txt");
    	TXTInput testTXTInput = new TXTInput(testFile);
    	
    	testTXTInput.openFile();
    	assertNotNull(testTXTInput.getReceiptManager());
    	
    	assertEquals("Konstantinos Papathanasiou", testTXTInput.getReceiptManager().getName());
    	assertEquals("130456093", testTXTInput.getReceiptManager().getAfm());
    	assertEquals(1, testTXTInput.getReceiptManager().getReceipts().get(0).getReceiptID());
    	assertEquals("25/2/2014", testTXTInput.getReceiptManager().getReceipts().get(0).getDate());
    	assertEquals("Coat", testTXTInput.getReceiptManager().getReceipts().get(0).getKind());
    	assertEquals((double) 2000, testTXTInput.getReceiptManager().getReceipts().get(0).getSales(), 0.01);
    	assertEquals(10, testTXTInput.getReceiptManager().getReceipts().get(0).getItems());
    	assertEquals("Hand Made Clothes", testTXTInput.getReceiptManager().getReceipts().get(0).getCompany().getName());
    	assertEquals("Greece", testTXTInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getCountry());
    	assertEquals("Ioannina", testTXTInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getCity());
    	assertEquals("Kaloudi", testTXTInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getStreet());
    	assertEquals(10, testTXTInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getStreetNumber());
    	
    	
    	assertEquals(2, testTXTInput.getReceiptManager().getReceipts().get(1).getReceiptID());
    	assertEquals("25/2/2014", testTXTInput.getReceiptManager().getReceipts().get(1).getDate());
    	assertEquals("Skirt", testTXTInput.getReceiptManager().getReceipts().get(1).getKind());
    	assertEquals((double) 4000, testTXTInput.getReceiptManager().getReceipts().get(1).getSales(), 0.01);
    	assertEquals(10, testTXTInput.getReceiptManager().getReceipts().get(1).getItems());
    	assertEquals("Hand Made Clothes", testTXTInput.getReceiptManager().getReceipts().get(1).getCompany().getName());
    	assertEquals("Greece", testTXTInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getCountry());
    	assertEquals("Ioannina", testTXTInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getCity());
    	assertEquals("Souliou", testTXTInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getStreet());
    	assertEquals(45, testTXTInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getStreetNumber());
    }
}