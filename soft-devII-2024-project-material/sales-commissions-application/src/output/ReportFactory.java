package output;

import data.SalesRepManager;

public abstract class ReportFactory {

	protected SalesRepManager receiptManager;
	
	public void saveFile(){
		createFile();
		writeReportToFile();
		closeFile();
	}

	public abstract void createFile();
	public abstract void writeReportToFile();
	public abstract void closeFile();
}
