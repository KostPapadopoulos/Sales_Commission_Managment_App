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
            bufferedWriter.write("<h1>Name: " + receiptManager.getName() + "</h1>");
            bufferedWriter.write("<h2>AFM: " + receiptManager.getAfm() + "</h2>");
            
            bufferedWriter.write("<h3>Total Sales: " + receiptManager.calculateTotalSales() + "</h3>");
            bufferedWriter.write("<h3>Trousers Sales: " + receiptManager.calculateSalesByKind("Trouser") + "</h3>");
            bufferedWriter.write("<h3>Skirts Sales: " + receiptManager.calculateSalesByKind("Skirt") + "</h3>");
            bufferedWriter.write("<h3>Shirts Sales: " + receiptManager.calculateSalesByKind("Shirt") + "</h3>");
            bufferedWriter.write("<h3>Coats Sales: " + receiptManager.calculateSalesByKind("Coat") + "</h3>");
            bufferedWriter.write("<h3>Commission: " + receiptManager.calculateCommission() + "</h3>");
            
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
