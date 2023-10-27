package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ReceiptAppenderHTML extends ReceiptAppender {
    private Writer writer;

    @Override
    public void openFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(fileToAppend));
    }

    @Override
    public void addReceiptDataToFile() {
        try {
            // You can customize the HTML format according to your needs
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<title>Receipt Data</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            writer.write("<h1>Receipt</h1>\n");
            writer.write("<ul>\n");
            writer.write("<li>Receipt ID: " + receipt.getReceiptID() + "</li>\n");
            writer.write("<li>Date: " + receipt.getDate() + "</li>\n");
            writer.write("<li>Kind: " + receipt.getKind() + "</li>\n");
            writer.write("<li>Sales: " + receipt.getSales() + "</li>\n");
            writer.write("<li>Items: " + receipt.getItems() + "</li>\n");
            writer.write("<li>Company: " + receipt.getCompany().getName() + "</li>\n");
            writer.write("<li>Country: " + receipt.getCompany().getCompanyAddress().getCountry() + "</li>\n");
            writer.write("<li>City: " + receipt.getCompany().getCompanyAddress().getCity() + "</li>\n");
            writer.write("<li>Street: " + receipt.getCompany().getCompanyAddress().getStreet() + "</li>\n");
            writer.write("<li>Number: " + (int) receipt.getCompany().getCompanyAddress().getStreetNumber() + "</li>\n");
            writer.write("</ul>\n");
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
