package tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.Receipt;
import data.SalesRepManager;
import output.XMLReportFactory;

public class XMLReportFactoryTest {

	@Test
	public void testCreateAndWriteReportToFile() {
		File testFile = new File("C:\\Users\\kostas\\Documents\\Sxoli\\5oEtos\\9th_Semester\\Soft_Dev_II\\Project\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\sales-commissions-application\\src\\tests\\testFileReport.xml");
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
		
		XMLReportFactory testFactory = new XMLReportFactory(testManager);
		testFactory.saveFile(testFile);
		
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(testFile.getAbsolutePath()));

            Element root = document.getDocumentElement();

            assertEquals("Ilias Papadopoulos", getElementTextContent(root, "Name"));
            assertEquals("666877769", getElementTextContent(root, "AFM"));
            assertEquals("5000.0", getElementTextContent(root, "TotalSales"));
            assertEquals("0.0", getElementTextContent(root, "TrouserSales"));
            assertEquals("10.0", getElementTextContent(root, "SkirtsSales"));
            assertEquals("0.0", getElementTextContent(root, "ShirtsSales"));
            assertEquals("0.0", getElementTextContent(root, "CoatsSales"));
            assertEquals("0.0", getElementTextContent(root, "Commission"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
   private String getElementTextContent(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }

}
