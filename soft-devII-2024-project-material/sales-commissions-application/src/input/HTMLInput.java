package input;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLInput extends Input{
	
	private Document doc;

	public HTMLInput(File recieptFileHTML){
		this.inputFile = recieptFileHTML;
		inputFilePath =  inputFile.getAbsolutePath();
		
	}

	@Override
	public void openFile() {
	    try {
	        // Read the HTML file using J soup
	        //File input = new File(inputFilePath);
	        doc = Jsoup.parse(inputFile, "UTF-8");

	        // Call your method to parse and process the HTML data
	        readReceiptDataFromFile(inputFilePath);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
	

	
	public void readReceiptDataFromFile(String htmlFilePath) {
	
	        // Assuming that your HTML structure is similar to your XML structure
	        name = doc.select("Name").text().trim();
	        afm = doc.select("AFM").text().trim();
	        addReceiptManager();
	
	        Elements receipts = doc.select("Receipt");
	        for (Element receipt : receipts) {
	            receiptID = Integer.parseInt(receipt.select("ReceiptID").text().trim());
	            date = receipt.select("Date").text().trim();
	            kind = receipt.select("Kind").text().trim();
	            System.out.println(kind);
	            sales = Double.parseDouble(receipt.select("Sales").text().trim());
	            items = Integer.parseInt(receipt.select("Items").text().trim());
	            companyName = receipt.select("Company").text().trim();
	            companyCountry = receipt.select("Country").text().trim();
	            companyCity = receipt.select("City").text().trim();
	            companyStreet = receipt.select("Street").text().trim();
	            companyStreetNumber = Integer.parseInt(receipt.select("Number").text().trim());
	            addReceipt();
	        }
	    
	}

	@Override
	public void readReceiptDataFromFile() {
		// TODO Auto-generated method stub
		
	}
}
