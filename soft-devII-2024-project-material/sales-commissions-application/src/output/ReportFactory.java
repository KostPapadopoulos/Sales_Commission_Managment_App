package output;

import data.SalesRepresentativeManager;

public abstract class ReportFactory {

	protected SalesRepresentativeManager receiptManager;
	
	public void saveFile(){
		createFile();
		writeReportToFile();
		closeFile();
	}

	public abstract void createFile();
	public abstract void writeReportToFile();
	public abstract void closeFile();
}
