package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ReceiptAppenderHTML extends ReceiptAppender {
    private Writer writer;

    @Override
    public void openFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(fileToAppend, true));
    }

    @Override
    public void addReceiptDataToFile() {
        try {
            
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<title>Receipt Data</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            writer.write("<Receipt>Receipt");
            writer.write("<ReceiptID>" + (int) receipt.getReceiptID() + "</ReceiptID>\n");
            writer.write("<Date>" + receipt.getDate()+ "</Date>\n");
            writer.write("<Kind>" + receipt.getKind() + "s</Kind>\n");
            writer.write("<Sales>" + receipt.getSales() + "</Sales>\n");
            writer.write("<Items>" + receipt.getItems() + "</Items>\n");
            writer.write("<Company>" +receipt.getCompany().getName() + "</Company>\n");
            writer.write("<Country>" +receipt.getCompany().getCompanyAddress().getCountry() + "</Country>\n");
            writer.write("<City>" + receipt.getCompany().getCompanyAddress().getCity() + "</City>\n");
            writer.write("<Street>" + receipt.getCompany().getCompanyAddress().getStreet() + "</Street>\n");
            writer.write("<Number>" + (int) receipt.getCompany().getCompanyAddress().getStreetNumber() + "</Number>\n");
            writer.write("</Receipt>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeFile() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}
