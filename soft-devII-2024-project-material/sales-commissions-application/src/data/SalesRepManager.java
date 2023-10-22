package data;


import java.util.ArrayList;

import output.ReceiptAppender;
import output.ReceiptAppenderTXT;
import output.ReceiptAppenderXML;

public class SalesRepManager {
	private String name;
	private String afm;
	private ArrayList<Receipt> allReceipts;
	private ReceiptAppender fileAppender;
	private boolean res = false;
	
	private final double LOWER_SALES_THRESHOLD = 6000;
	private final double MIDDLE_SALES_THRESHOLD = 10000;
	private final double UPPER_SALES_THRESHOLD = 40000;
	private final double LOWER_COMMISSION_RATE = 0.1;
	private final double MIDDLE_COMMISSION_RATE = 0.15;
	private final double UPPER_COMMISSION_RATE = 0.2;

	
	
	public SalesRepManager(){
		allReceipts = new ArrayList<Receipt>();
	}
	
	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			fileAppender = new ReceiptAppenderTXT();
		}	
		else{
			fileAppender = new ReceiptAppenderXML();
		}	
	}
	public ArrayList<Receipt> getReceipts(){
		return allReceipts;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAfm() {
		return afm;
	}
	public void setAfm(String afm) {
		this.afm = afm;
	}

	public double calculateTotalSales(){
		double sumSales = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumSales += allReceipts.get(i).getSales();
		}
		return sumSales;
	}
	

	public int calculateTotalItems(){
		int sumItems = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumItems += allReceipts.get(i).getItems();
		}
		return sumItems;
	}
	

	// Parametarize Method
	public float calculateSalesByKind(String kind) {
        float sum = 0;
        for (Receipt receipt : allReceipts) {
            if (receipt.getKind().equals(kind)) {
                sum += receipt.getItems();
            }
        }
        return sum;
    }

	public boolean isInFirstCommissionCategory(){
		double totalSales = this.calculateTotalSales();

		if ( totalSales > LOWER_SALES_THRESHOLD  && totalSales <= MIDDLE_SALES_THRESHOLD){
			res = true;
		}
		return res;
	}

	public boolean isInSecondCommissionCategory() {
		double totalSales = this.calculateTotalSales();
		
		if(totalSales > MIDDLE_SALES_THRESHOLD && totalSales <= UPPER_SALES_THRESHOLD ){
			res = true;
		}
		return res;
	}

	public boolean isInThirdCommissionCategory() {
		double totalSales = this.calculateTotalSales();

		if(totalSales > UPPER_SALES_THRESHOLD ) {
			res = true;
		}
		return res;
	}
	
	// Replace magic number with symbolic constant 
	public double calculateCommission(){
		double commission = 0;
		double totalSales = this.calculateTotalSales();

		if(isInFirstCommissionCategory()){
			commission = LOWER_COMMISSION_RATE * (totalSales - LOWER_SALES_THRESHOLD);
		}
		else if(isInSecondCommissionCategory()){
			commission = (((totalSales - MIDDLE_SALES_THRESHOLD) * MIDDLE_COMMISSION_RATE) + (MIDDLE_SALES_THRESHOLD * LOWER_COMMISSION_RATE));			
		}
		else if(isInThirdCommissionCategory()) {
			commission = ((MIDDLE_SALES_THRESHOLD * LOWER_COMMISSION_RATE + 30000 * MIDDLE_COMMISSION_RATE) + (totalSales - UPPER_SALES_THRESHOLD) * UPPER_COMMISSION_RATE);			
		}
		return commission;
	}


	public ReceiptAppender getReceiptAppender() {
		return fileAppender;
	}


}
