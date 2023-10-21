 package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class TXTInput extends Input{

	public TXTInput(File recieptFileTXT){
		this.inputFile = recieptFileTXT;
		inputFilePath =  inputFile.getAbsolutePath();
		
	}
	
	@Override //O pio aplos ALGORITHMIKA tropos
	public void readFile()  {
		 BufferedReader br = null;
	    try {
	            	
			br = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e1) {
				e1.printStackTrace();
		}
		String line = "";
	    try {
			line = br.readLine();
			name = (line.substring(line.indexOf(":") + 2).trim());
			System.out.println(name);
			line = br.readLine();
			afm = (line.substring(line.indexOf(":") + 1).trim());
			System.out.println(afm);
			addReceiptManager();
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			System.out.println(line);

			while (line != null) {
				String valueString = (line.substring(line.indexOf(":") + 1).trim());
				receiptID = (Integer.parseInt(valueString));	
				System.out.println("Receipt ID " + receiptID);
				line = br.readLine();
				System.out.println("LINE GAMW 1 " + line);
				
				date = (line.substring(line.indexOf(":") + 1).trim());
				line = br.readLine();			
				System.out.println("LINE GAMW 2 " + line);

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
				System.out.println(line);
				if (line.trim().isEmpty()) {
					line = br.readLine();	
					
				}	

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
