package input;


import java.io.File;

import data.ReceiptManager;
import data.Coat;
import data.Receipt;
import data.Shirt;
import data.Skirt;
import data.Trouser;

public abstract class Input {
	
	protected ReceiptManager receiptManager;
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

	public abstract void readFile();

	
	public Input() {
		receiptManager = new ReceiptManager();
		kind  = new String("");
	}
	

	
	public void addAgent() {
		receiptManager.setName(name);
		receiptManager.setAfm(afm);
	}
	
	public void addReceipt( ){
		Receipt receipt;			
			if(kind.equals("Shirts")) {
				receipt= new Shirt();

			}
			else if (kind.equals("Skirts")) {
				receipt = new Skirt();

			}
			else if (kind.equals("Trousers")) {
				receipt = new Trouser();

			}
			else {
				receipt = new Coat();
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
	public ReceiptManager getReceiptManager() {
		return receiptManager;
	}

	
}
