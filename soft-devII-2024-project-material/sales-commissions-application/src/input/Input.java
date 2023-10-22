package input;


import java.io.File;

import data.SalesRepManager;
import data.Receipt;

public abstract class Input {
	
	protected SalesRepManager receiptManager;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected int receiptID;
	protected String date;
	protected String kind;
	protected double sales;
	protected int items;
	protected String companyName;
	protected String companyCountry;
	protected String companyCity;
	protected String companyStreet;
	protected int companyStreetNumber;

	public void readFile(){
		openFile();
	}

	
	public Input() {
		receiptManager = new SalesRepManager();
		kind  = new String("");
	}
	

	
	public void addReceiptManager() {
		receiptManager.setName(name);
		receiptManager.setAfm(afm);
	}
	
	public void addReceipt( ){
		Receipt receipt;
		// Mallon kai edw den xreiazetai to if else opws sthn Selection Window			
			if(kind.equals("Shirts")) {
				receipt= new Receipt();
				receipt.setKind("Shirt");

			}
			else if (kind.equals("Skirts")) {
				receipt = new Receipt();
				receipt.setKind("Skirt");

			}
			else if (kind.equals("Trousers")) {
				receipt = new Receipt();
				receipt.setKind("Trouser");

			}
			else {
				receipt = new Receipt();
				receipt.setKind("Coat");
			}
			
			receipt.setReceiptID(receiptID);			
			receipt.setDate(date);
			receipt.setSales(sales);
			receipt.setItems(items);
			receipt.getCompany().setName(companyName);
			receipt.getCompany().getCompanyAddress().setCountry(companyCountry);
			receipt.getCompany().getCompanyAddress().setCity(companyCity);
			receipt.getCompany().getCompanyAddress().setStreet(companyStreet);
			receipt.getCompany().getCompanyAddress().setStreetNumber(companyStreetNumber);
			receiptManager.getReceipts().add(receipt);
	}
	public SalesRepManager getReceiptManager() {
		return receiptManager;
	}

	public abstract void openFile();
	public abstract void readReceiptDataFromFile();
	
}
