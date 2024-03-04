package tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import data.Receipt;
import data.SalesRepManager;
import output.HTMLReportFactory;

import static org.junit.Assert.assertEquals;

public class HTMLReportFactoryTest {

	@Test
	public void testCreateAndWriteReportToFile() {
		File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFileReport.html");
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
		
		HTMLReportFactory testFactory = new HTMLReportFactory(testManager);
		testFactory.saveFile(testFile);
		try {
            Document document = Jsoup.parse(new File(testFile.getAbsolutePath()), "UTF-8");

            Element body = document.body();

            assertEquals("Name: Ilias Papadopoulos", getHtmlElementText(body, "Name"));
            assertEquals("AFM: 666877769", getHtmlElementText(body, "AFM"));
            assertEquals("Total Sales: 5000.0", getHtmlElementText(body, "TotalSales"));
            assertEquals("Trousers Sales: 0.0", getHtmlElementText(body, "TrousersSales"));
            assertEquals("Skirts Sales: 10.0", getHtmlElementText(body, "SkirtsSales"));
            assertEquals("Shirts Sales: 0.0", getHtmlElementText(body, "ShirtsSales"));
            assertEquals("Coats Sales: 0.0", getHtmlElementText(body, "CoatsSales"));
            assertEquals("Commission: 0.0", getHtmlElementText(body, "Commission"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getHtmlElementText(Element parentElement, String tagName) {
        Element element = parentElement.select(tagName).first();
        return element != null ? element.text().trim() : "";
    }
 
}

