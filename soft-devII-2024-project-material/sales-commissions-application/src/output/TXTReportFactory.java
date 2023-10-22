package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import data.SalesRepManager;


public class TXTReportFactory extends ReportFactory{
    private BufferedWriter bufferedWriter = null;
    private String fullPathName;
	
	public TXTReportFactory(SalesRepManager a){
		receiptManager = a;
	}

    @Override
    public void createFile(){
        try{
        	fullPathName =  "\\C:\\Users\\papat\\Desktop\\Sales_Commission_Managment_App\\soft-devII-2024-project-material\\Reports\\" + receiptManager.getAfm() + "_SALES.txt";
        	bufferedWriter = new BufferedWriter(new FileWriter(new File(fullPathName)));
        }catch (IOException ex){
			JOptionPane.showMessageDialog(null,"The specified path: " + fullPathName + " is not valid!");

        }
    }
	
	@Override
	public void writeReportToFile() {
        try {
            
        	bufferedWriter.write("Name: " + receiptManager.getName()); 
            bufferedWriter.newLine();

            bufferedWriter.write("AFM: " + receiptManager.getAfm());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            
            bufferedWriter.write("Total Sales: " + receiptManager.calculateTotalSales());
            bufferedWriter.newLine();
 
            bufferedWriter.write("Trousers Sales: " + receiptManager.calculateSalesByKind("Trouser"));
            bufferedWriter.newLine();

            bufferedWriter.write("Skirts Sales: " + receiptManager.calculateSalesByKind("Skirt"));
            bufferedWriter.newLine();

            bufferedWriter.write("Shirts Sales: " + receiptManager.calculateSalesByKind("Shirt"));
            bufferedWriter.newLine();
            
            bufferedWriter.write("Coats Sales: " + receiptManager.calculateSalesByKind("Coat"));
            bufferedWriter.newLine();

            bufferedWriter.write("Commission: " + receiptManager.calculateCommission());
            
        	


        }catch (IOException ex){
			JOptionPane.showMessageDialog(null,"Exception occured while trying to write report to file!");

        }
	
	}

    @Override
    public void closeFile() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
