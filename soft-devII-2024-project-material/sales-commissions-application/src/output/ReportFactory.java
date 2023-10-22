package output;

import java.io.File;

import data.SalesRepManager;

public abstract class ReportFactory {

	protected SalesRepManager receiptManager;
	
	public void saveFile(File file){
		createFile(file);
		writeReportToFile();
		closeFile(file);
	}

	public abstract void createFile(File file);
	public abstract void writeReportToFile();
	public abstract void closeFile(File file);
}
