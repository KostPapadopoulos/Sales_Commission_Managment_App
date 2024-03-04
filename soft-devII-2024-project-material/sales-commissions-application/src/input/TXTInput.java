 package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class TXTInput extends Input{
	protected BufferedReader br = null;

	public TXTInput(File recieptFileTXT){
		this.inputFile = recieptFileTXT;
		inputFilePath =  inputFile.getAbsolutePath();

	}

	@Override
	public void openFile() {
		try (BufferedReader localBr = new BufferedReader(new FileReader(inputFilePath))) {
			this.br = localBr;
			readReceiptDataFromFile();
			// Xrhsimopoihsame try with opote otan teleiwnei h open kleinei ton BufferReader kai etsi den mporei na ton xrhsimopoihsei h readReceiptDataFromFile
			
		} catch (FileNotFoundException e1) {
				e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override //O pio aplos ALGORITHMIKA tropos
	public void readReceiptDataFromFile()  {
		String line = "";
	    try {
			line = this.br.readLine();
			name = (line.substring(line.indexOf(":") + 2).trim());
			line = br.readLine();
			afm = (line.substring(line.indexOf(":") + 1).trim());
			addReceiptManager();
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();

			while (line != null) {
				receiptID = (Integer.parseInt(line.substring(line.indexOf(":") + 1).trim()));	
				line = br.readLine();
				
				date = (line.substring(line.indexOf(":") + 1).trim());
				line = br.readLine();			

				kind = (line.substring(line.indexOf(":") + 1).trim());
				line = br.readLine();		
				
				sales = (Double.parseDouble(line.substring(line.indexOf(":") + 1).trim()));
				line = br.readLine();		

				items = (Integer.parseInt(line.substring(line.indexOf(":") + 1).trim()));
				line = br.readLine();				
			
				companyName = (line.substring(line.indexOf(":") + 1).trim());
				line = br.readLine();		

				companyCountry =  (line.substring(line.indexOf(":") + 1).trim());
				line = br.readLine();									
				
				companyCity =  (line.substring(line.indexOf(":") + 1).trim());
				line = br.readLine();		

				companyStreet =  (line.substring(line.indexOf(":") + 1).trim());				
				line = br.readLine();		

				companyStreetNumber =  (Integer.parseInt(line.substring(line.indexOf(":") + 1).trim()));
				addReceipt();
				line = br.readLine();		
				line = br.readLine();	
					

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 
	    try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	
	}
	
}
