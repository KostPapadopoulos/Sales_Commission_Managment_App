package data;


import java.util.ArrayList;

public class Agent {
	private String name;
	private String afm;
	private ArrayList<Receipt> allReceipts;
	private FileAppender fileAppender;
	private final double LOWER_THRESHOLD = 6000;
	private final double MIDDLE_THRESHOLD = 10000;
	private final double UPPER_THRESHOLD = 40000;
	private final double LOWER_COMMISSION_RATE = 0.1;
	private final double MIDDLE_COMMISSION_RATE = 0.15;
	private final double UPPER_COMMISSION_RATE = 0.2;

	
	
	public Agent(){
		allReceipts = new ArrayList<Receipt>();
	}
	
	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			fileAppender = new FileAppenderTXT();
		}	
		else{
			fileAppender = new FileAppenderXML();
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
	
	// Replace magic number with symbolic constant 
	public double calculateCommission(){
		double commission = 0;
		double totalSales = this.calculateTotalSales();

		if( totalSales > LOWER_THRESHOLD  && totalSales <= MIDDLE_THRESHOLD){
			commission = LOWER_COMMISSION_RATE * (totalSales - LOWER_THRESHOLD);
		}
		else if(totalSales > MIDDLE_THRESHOLD && totalSales <= UPPER_THRESHOLD ){
			commission = (((totalSales - MIDDLE_THRESHOLD) * MIDDLE_COMMISSION_RATE) + (MIDDLE_THRESHOLD * LOWER_COMMISSION_RATE));			
		}
		else if(totalSales > UPPER_THRESHOLD ) {
			commission = ((MIDDLE_THRESHOLD * LOWER_COMMISSION_RATE + 30000 * MIDDLE_COMMISSION_RATE) + (totalSales - UPPER_THRESHOLD) * UPPER_COMMISSION_RATE);			
		}
		return commission;
	}


	public FileAppender getFileAppender() {
		return fileAppender;
	}


}
