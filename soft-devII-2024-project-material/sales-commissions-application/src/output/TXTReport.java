package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import data.ReceiptManager;


public class TXTReport extends Report{

	
	public TXTReport(ReceiptManager a){
		receiptManager = a;
	}
	
	
	public void saveFile() {
        BufferedWriter bufferedWriter = null;
        try{
        	String fullPathName =  "/users/Nick/Desktop/Reports/" + receiptManager.getAfm() + "_SALES.txt";
        	bufferedWriter = new BufferedWriter(new FileWriter(new File(fullPathName)));
            
        	bufferedWriter.write("Name: " + receiptManager.getName()); 
            bufferedWriter.newLine();

            bufferedWriter.write("AFM: " + receiptManager.getAfm());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            
            bufferedWriter.write("Total Sales: " + receiptManager.calculateTotalSales());
            bufferedWriter.newLine();
 
            bufferedWriter.write("Trousers Sales: " + receiptManager.calculateSalesByKind("Trousers"));
            bufferedWriter.newLine();

            bufferedWriter.write("Skirts Sales: " + receiptManager.calculateSalesByKind("Skirts"));
            bufferedWriter.newLine();

            bufferedWriter.write("Shirts Sales: " + receiptManager.calculateSalesByKind("Shirts"));
            bufferedWriter.newLine();
            
            bufferedWriter.write("Coats Sales: " + receiptManager.calculateSalesByKind("Coats"));
            bufferedWriter.newLine();

            bufferedWriter.write("Commission: " + receiptManager.calculateCommission());
            
        	bufferedWriter.close();


        }catch (IOException ex){
			JOptionPane.showMessageDialog(null,"������ ������ �������� ���� ��� ���������� ��� �������");

        }
		
	}

}
