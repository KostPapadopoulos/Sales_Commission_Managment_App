package tests;
import org.junit.Test;
import input.TXTInput;
import input.XMLInput;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XMLInputTest {

	@Test
    public void testOpenFileAndReadReceiptDataFromFile() throws IOException {
        File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFile.xml");
    	XMLInput testXMLInput = new XMLInput(testFile);
    	
    	testXMLInput.openFile();
    	assertNotNull(testXMLInput.getReceiptManager());
    	
    	assertEquals("Konstantinos Papathanasiou", testXMLInput.getReceiptManager().getName());
    	assertEquals("130456097", testXMLInput.getReceiptManager().getAfm());
    	assertEquals(1, testXMLInput.getReceiptManager().getReceipts().get(0).getReceiptID());
    	assertEquals("25/2/2014", testXMLInput.getReceiptManager().getReceipts().get(0).getDate());
    	assertEquals("Coat", testXMLInput.getReceiptManager().getReceipts().get(0).getKind());
    	assertEquals((double) 2000, testXMLInput.getReceiptManager().getReceipts().get(0).getSales(), 0.01);
    	assertEquals(10, testXMLInput.getReceiptManager().getReceipts().get(0).getItems());
    	assertEquals("Hand Made Clothes", testXMLInput.getReceiptManager().getReceipts().get(0).getCompany().getName());
    	assertEquals("Greece", testXMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getCountry());
    	assertEquals("Ioannina", testXMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getCity());
    	assertEquals("Kaloudi", testXMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getStreet());
    	assertEquals(10, testXMLInput.getReceiptManager().getReceipts().get(0).getCompany().getCompanyAddress().getStreetNumber());
    	
    	
    	assertEquals(2, testXMLInput.getReceiptManager().getReceipts().get(1).getReceiptID());
    	assertEquals("25/2/2014", testXMLInput.getReceiptManager().getReceipts().get(1).getDate());
    	assertEquals("Skirt", testXMLInput.getReceiptManager().getReceipts().get(1).getKind());
    	assertEquals((double) 4000, testXMLInput.getReceiptManager().getReceipts().get(1).getSales(), 0.01);
    	assertEquals(10, testXMLInput.getReceiptManager().getReceipts().get(1).getItems());
    	assertEquals("Hand Made Clothes", testXMLInput.getReceiptManager().getReceipts().get(1).getCompany().getName());
    	assertEquals("Greece", testXMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getCountry());
    	assertEquals("Ioannina", testXMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getCity());
    	assertEquals("Souliou", testXMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getStreet());
    	assertEquals(45, testXMLInput.getReceiptManager().getReceipts().get(1).getCompany().getCompanyAddress().getStreetNumber());
    }
}
