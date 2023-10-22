package input;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XMLInput extends Input {
	private DocumentBuilder docBuilder;
	private Document doc = null;
	private NodeList nodeLst = null;
 
	public XMLInput(File receiptFileXML ){
		inputFile = receiptFileXML;
		
	}

	@Override
	public void openFile(){
		DocumentBuilderFactory docBuilderFactory 
			= DocumentBuilderFactory.newInstance();
        	
			try {
				docBuilder = docBuilderFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			try {
				doc = docBuilder.parse(inputFile);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
        	 
        	doc.getDocumentElement().normalize();
            nodeLst = doc.getElementsByTagName("Agent");
			readReceiptDataFromFile();
			// Xrhsimopoihsame try with opote otan teleiwnei h open kleinei ton BufferReader kai etsi den mporei na ton xrhsimopoihsei h readReceiptDataFromFile

	}
    public void readReceiptDataFromFile() {
        try {
        	
        	name = ((Element) nodeLst.item(0)).getElementsByTagName("Name").
			item(0).getChildNodes().item(0).getNodeValue().trim();
			
        	afm = ((Element) nodeLst.item(0)).getElementsByTagName("AFM").
			item(0).getChildNodes().item(0).getNodeValue().trim();
        	addReceiptManager();
        	NodeList receiptsNodeList = ((Element) nodeLst.
			item(0)).getElementsByTagName("Receipt");
			
            int size = receiptsNodeList.getLength();
            for(int i=0; i<size; i++){
            	receiptID = Integer.parseInt(((Element) receiptsNodeList.item(i)).
				getElementsByTagName("ReceiptID").item(0).getChildNodes().item(0).getNodeValue().trim());
            	
            	date = ((Element) receiptsNodeList.item(i)).
				getElementsByTagName("Date").item(0).getChildNodes().item(0).getNodeValue().trim();
				
            	kind = ((Element) receiptsNodeList.item(i))
				.getElementsByTagName("Kind").item(0).getChildNodes().item(0).getNodeValue().trim();
				
            	sales = Double.parseDouble(((Element) receiptsNodeList.item(i)).
				getElementsByTagName("Sales").item(0).getChildNodes().item(0).getNodeValue().trim());
            	
				items = Integer.parseInt(((Element) receiptsNodeList.item(i))
				.getElementsByTagName("Items").item(0).getChildNodes().item(0).getNodeValue().trim());
            	
				companyName = ((Element) receiptsNodeList.item(i)).
				getElementsByTagName("Company").item(0).getChildNodes().item(0).getNodeValue().trim();
            	
				companyCountry = ((Element) receiptsNodeList.item(i)).
				getElementsByTagName("Country").item(0).getChildNodes().item(0).getNodeValue().trim();
            	
				companyCity = ((Element) receiptsNodeList.item(i)).
				getElementsByTagName("City").item(0).getChildNodes().item(0).getNodeValue().trim();
            	
				companyStreet = ((Element) receiptsNodeList.item(i)).
				getElementsByTagName("Street").item(0).getChildNodes().item(0).getNodeValue().trim();
            	
				companyStreetNumber = Integer.parseInt(((Element) receiptsNodeList.item(i)).
				getElementsByTagName("Number").item(0).getChildNodes().item(0).getNodeValue().trim());
            	
				addReceipt();
            }

        	
            
        
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog
			(null,"�������� ������ �������� ���� �� �������� ��� �������");
		} 
    }
    
}


