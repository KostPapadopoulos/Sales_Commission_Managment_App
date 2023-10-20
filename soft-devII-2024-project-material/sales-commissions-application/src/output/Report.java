package output;

import data.ReceiptManager;

public abstract class Report {

	protected ReceiptManager receiptManager;
	
	public abstract void saveFile();
}
