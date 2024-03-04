package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import data.Receipt;
import data.SalesRepManager;
import output.ReceiptAppenderTXT;
import output.TXTReportFactory;

public class TXTReportFactoryTest {
	
	@Test
	public void testCreateAndWriteReportToFile() {
		
		File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFileReport.txt");
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
    	
    	SalesRepManager testManager = new SalesRepManager();
    	testManager.setName("Ilias Papadopoulos");
    	testManager.setAfm("666877769");
    	testManager.getReceipts().add(testReceipt);
    	
    	TXTReportFactory testFactory = new TXTReportFactory(testManager);
    	testFactory.saveFile(testFile);
    	
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(testFile.getAbsolutePath()))) {

            // Read and verify Name
            assertEquals("Name: " + testManager.getName(), bufferedReader.readLine());
            
            // Read and verify AFM
            assertEquals("AFM: " + testManager.getAfm(), bufferedReader.readLine());

            // Skip empty lines
            bufferedReader.readLine();
            bufferedReader.readLine();

            assertEquals("Total Sales: 5000.0", bufferedReader.readLine());
            
            assertEquals("Trousers Sales: 0.0", bufferedReader.readLine());
            
            assertEquals("Skirts Sales: 10.0", bufferedReader.readLine());
            
            assertEquals("Shirts Sales: 0.0", bufferedReader.readLine());
            
            assertEquals("Coats Sales: 0.0", bufferedReader.readLine());
            
            assertEquals("Commission: 0.0", bufferedReader.readLine());
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
}
