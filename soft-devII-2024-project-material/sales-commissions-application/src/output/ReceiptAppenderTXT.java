package output;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptAppenderTXT extends ReceiptAppender{

	private FileWriter fileWriter;
	
	@Override
	public void openFile() throws IOException {
		this.fileWriter = new FileWriter(fileToAppend,true);		
	}

	@Override
	public void addReceiptDataToFile(){
		System.out.println("Mpike sto TXT");
		System.out.println(fileToAppend.getAbsolutePath());

		try {
			
			fileWriter.write("\n");
			fileWriter.write("Receipt ID: ");
			fileWriter.write(String.valueOf(receipt.getReceiptID()));
			fileWriter.write("\n");

			fileWriter.write("Date: ");
			fileWriter.write(receipt.getDate());
			fileWriter.write("\n");

			fileWriter.write("Kind: ");
			fileWriter.write(receipt.getKind());
			System.out.println(receipt.getKind());
			fileWriter.write("\n");

			fileWriter.write("Sales: ");
			fileWriter.write(String.valueOf((int) receipt.getSales()));
			fileWriter.write("\n");

			fileWriter.write("Items: ");
			fileWriter.write(String.valueOf((int)receipt.getItems()));
			fileWriter.write("\n");

			fileWriter.write("Company: ");
			fileWriter.write(receipt.getCompany().getName());
			fileWriter.write("\n");


			fileWriter.write("Country: ");
			fileWriter.write(receipt.getCompany().getCompanyAddress().getCountry());
			fileWriter.write("\n");
			
			fileWriter.write("City: ");
			fileWriter.write(receipt.getCompany().getCompanyAddress().getCity());
			fileWriter.write("\n");

			fileWriter.write("Street: ");
			fileWriter.write(receipt.getCompany().getCompanyAddress().getStreet());
			fileWriter.write("\n");

			fileWriter.write("Number: ");
			fileWriter.write(String.valueOf((int) receipt.getCompany().getCompanyAddress().getStreetNumber()));
			fileWriter.write("\n");

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void closeFile() throws IOException {
		this.fileWriter.close();

	}

	public void setCountry(String country) {
		this.country = country;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
