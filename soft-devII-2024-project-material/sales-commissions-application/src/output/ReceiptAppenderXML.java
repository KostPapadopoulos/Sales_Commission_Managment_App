package output;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ReceiptAppenderXML  extends ReceiptAppender{

	DocumentBuilderFactory docFactory;
	DocumentBuilder docBuilder;
	Document doc;
	Node agent;

	@Override
	public void openFile() throws IOException {
		this.docFactory = DocumentBuilderFactory.newInstance();
				System.out.println(doc);

			try {
				this.docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			try {
				this.doc = docBuilder.parse(fileToAppend);
			} catch (SAXException e) {
				e.printStackTrace();
			}
		
			this.agent = doc.getFirstChild();

	}


	@Override
	public void addReceiptDataToFile(){

		try{
		
			
			Element receiptElem = doc.createElement("Receipt");
			agent.appendChild(receiptElem);		
		
			Element receiptIDElem = doc.createElement("ReceiptID");
			receiptIDElem.appendChild(doc.createTextNode(String.valueOf(receipt.getReceiptID())));
			receiptElem.appendChild(receiptIDElem);

	       	Element dateElem = doc.createElement("Date");
	       	dateElem.appendChild(doc.createTextNode(receipt.getDate()));
	       	receiptElem.appendChild(dateElem);
       	
	       	Element kindElem = doc.createElement("Kind");
	       	kindElem.appendChild(doc.createTextNode(receipt.getKind()+ "s"));
	       	receiptElem.appendChild(kindElem);
	       	
	       	Element salesElem = doc.createElement("Sales");
	       	salesElem.appendChild(doc.createTextNode(String.valueOf(receipt.getSales())));
	       	receiptElem.appendChild(salesElem);
	       	
	       	Element itemsElem = doc.createElement("Items");
	       	itemsElem.appendChild(doc.createTextNode(String.valueOf(receipt.getItems())));
	       	receiptElem.appendChild(itemsElem);
	       	
	       	Element companyElem = doc.createElement("Company");
			companyElem.appendChild(doc.createTextNode(receipt.getCompany().getName()));
			receiptElem.appendChild(companyElem);
	       	
	       	Element countryElem = doc.createElement("Country");
	       	countryElem.appendChild(doc.createTextNode(receipt.getCompany().getCompanyAddress().getCountry()));
	       	receiptElem.appendChild(countryElem);
	       	
	       	Element cityElem = doc.createElement("City");
	       	cityElem.appendChild(doc.createTextNode(receipt.getCompany().getCompanyAddress().getCity()));
	       	receiptElem.appendChild(cityElem);
	       	
	       	Element streetElem = doc.createElement("Street");
	       	streetElem.appendChild(doc.createTextNode(receipt.getCompany().getCompanyAddress().getStreet()));
	       	receiptElem.appendChild(streetElem);
	       	
	       	Element numberElem = doc.createElement("Number");
	       	numberElem.appendChild(doc.createTextNode(String.valueOf((int) receipt.getCompany().getCompanyAddress().getStreetNumber())));
	       	receiptElem.appendChild(numberElem);

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void closeFile() {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(fileToAppend);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}


	/*
	public void setCountry(String country) {
		this.country = country;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	*/

}
