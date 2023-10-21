package output;

import java.io.File;
import java.io.IOException;

import data.Receipt;

public abstract class ReceiptAppender {

	protected File fileToAppend;
	protected Receipt receipt;
	protected String country;
	protected String city;
	protected String street;
	protected String number;
		
	public void appendFile() {
		try {
			openFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addReceiptDataToFile();
		try {
			closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFileToAppend(File fileToAppend) {
		this.fileToAppend = fileToAppend;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public abstract void openFile() throws IOException;
	public abstract void closeFile() throws IOException;
	public abstract void addReceiptDataToFile();
	
	public abstract void setCountry(String country); 
	public abstract void setCity(String city);
	public abstract void setStreet(String street);
	public abstract void setNumber(String number);

}

