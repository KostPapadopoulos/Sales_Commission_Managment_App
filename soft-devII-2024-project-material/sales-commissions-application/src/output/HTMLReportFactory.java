package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

import data.SalesRepManager;

public class HTMLReportFactory extends ReportFactory {
    private BufferedWriter bufferedWriter;

    public HTMLReportFactory(SalesRepManager a) {
        receiptManager = a;
    }

    @Override
    public void createFile(File file) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "The specified path is not valid!");
        }
    }

    @Override
    public void writeReportToFile() {
        try {
            // Write HTML header
            bufferedWriter.write("<html><head><title>Receipt Report</title></head><body>");
            
            // Write report content
            bufferedWriter.write("<Name>Name: " + receiptManager.getName() + "</Name>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<AFM>AFM: " + receiptManager.getAfm() + "</AFM>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<TotalSales>Total Sales: " + receiptManager.calculateTotalSales() + "</TotalSales>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<TrousersSales>Trousers Sales: " + receiptManager.calculateSalesByKind("Trouser") + "</TrousersSales>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<SkirtsSales>Skirts Sales: " + receiptManager.calculateSalesByKind("Skirt") + "</SkirtsSales>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<ShirtsSales>Shirts Sales: " + receiptManager.calculateSalesByKind("Shirt") + "</ShirtsSales>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<CoatsSales>Coats Sales: " + receiptManager.calculateSalesByKind("Coat") + "</CoatsSales>");
            bufferedWriter.write("<br>");
            bufferedWriter.write("<Commission>Commission: " + receiptManager.calculateCommission() + "</Commission>");
            
            // Write HTML footer
            bufferedWriter.write("</body></html>");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Exception occurred while trying to write the report to the file!");
        }
    }

    @Override
    public void closeFile(File file) {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
