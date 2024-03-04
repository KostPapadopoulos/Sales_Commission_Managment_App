package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import data.Receipt;
import data.SalesRepManager;
import output.ReceiptAppenderHTML;
import output.ReceiptAppenderTXT;
import output.ReceiptAppenderXML;

class SalesRepManagerTest {

	@Test
    public void testSetFileTypeWithFileTypeTXT() {
        SalesRepManager salesRepManager = new SalesRepManager();
        salesRepManager.setFileType("TXT");
        assertTrue(salesRepManager.getReceiptAppender() instanceof ReceiptAppenderTXT);
    }

    @Test
    public void testSetFileTypeWithFileTypeXML() {
        SalesRepManager salesRepManager = new SalesRepManager();
        salesRepManager.setFileType("XML");
        assertTrue(salesRepManager.getReceiptAppender() instanceof ReceiptAppenderXML);
    }

    @Test
    public void testSetFileTypeWithFileTypeHTML() {
        SalesRepManager salesRepManager = new SalesRepManager();
        salesRepManager.setFileType("HTML");
        assertTrue(salesRepManager.getReceiptAppender() instanceof ReceiptAppenderHTML);
    }

    @Test
    public void testSetFileTypeWithNullFileType() {
        SalesRepManager salesRepManager = new SalesRepManager();
        assertThrows(NullPointerException.class, () -> salesRepManager.setFileType(null));
    }

    @Test
    public void testSetFileTypeWithEmptyFileType() {
        SalesRepManager salesRepManager = new SalesRepManager();
        assertThrows(IllegalArgumentException.class, () -> salesRepManager.setFileType(""));
    }

    @Test
    public void testSetFileTypeWithInvalidFileType() {
        SalesRepManager salesRepManager = new SalesRepManager();
        assertThrows(IllegalArgumentException.class, () -> salesRepManager.setFileType("PDF"));
    }

    @Test
    public void testSetFileTypeOverwriteFileAppender() {
        SalesRepManager salesRepManager = new SalesRepManager();
        salesRepManager.setFileType("TXT");
        assertTrue(salesRepManager.getReceiptAppender() instanceof ReceiptAppenderTXT);
        salesRepManager.setFileType("XML");
        assertTrue(salesRepManager.getReceiptAppender() instanceof ReceiptAppenderXML);
    }

    @Test
    public void testGetReceiptsReturnsReceiptsWithAtLeastOneReceipt() {
        SalesRepManager manager = new SalesRepManager();
        ArrayList<Receipt> result = manager.getReceipts();
    
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Receipt receipt = new Receipt();
        manager.getReceipts().add(receipt);
        result = manager.getReceipts();
    
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(receipt, result.get(0));
    }

    @Test
    public void testTotalSalesReturnsZeroWhenNoReceipts()
    {
        SalesRepManager manager = new SalesRepManager();
        double totalSales = manager.calculateTotalSales();
        assertEquals(0, totalSales, 0.001);

    }
    @Test
    public void testReturnsTotalSales() {
        SalesRepManager manager = new SalesRepManager();
        
        Receipt rec1 = new Receipt();
        rec1.setSales(500.0);
        manager.getReceipts().add(rec1);
        double totalSales = manager.calculateTotalSales();
        assertEquals(500.0, totalSales, 0.001);
    }

    @Test
    public void testReturnsTotalItems() {
        SalesRepManager manager = new SalesRepManager();
        Receipt receipt1 = new Receipt("Coats");
        Receipt receipt2 = new Receipt("Trousers");
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
        manager.getReceipts().get(0).setItems(10);
        manager.getReceipts().get(1).setItems(10);
        int result = manager.calculateTotalItems();
        assertEquals(20, result);
    }
    @Test
    public void testReturnZeroIfNoReceiptsOfGivenKind() {
        SalesRepManager manager = new SalesRepManager();
        float result = manager.calculateSalesByKind("Coat");
        assertEquals(0, result, 0.001);
        

    }

    @Test
    public void testReturnsSalesBySpecificKind() {
        SalesRepManager manager = new SalesRepManager();
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(5);
        manager.getReceipts().add(receipt1);
        float result = manager.calculateSalesByKind("Coat");
        assertEquals(5, result, 0.001);
    }


    @Test
    public void tesComissionWithNoReceipts() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
    
        double commission = manager.calculateCommission();
    
        assertEquals(0, commission, 0.001);
    }

    @Test
    public void testFirstCommissionCategory() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(5000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(2000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
        double commission = manager.calculateCommission();
    
        assertEquals(100, commission, 0.001);
    }

    @Test
    public void testSecondCommissionCategory() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(8000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(3000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
        double commission = manager.calculateCommission();
    
        assertEquals(1150, commission, 0.001);
    }

    @Test
    public void testThirdCommissionCategory() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(20000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(25000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
        double commission = manager.calculateCommission();
    
        assertEquals(6500, commission, 0.001);
    }
    @Test
    public void testTotalSalesLessThanOrEqualToLowerThreshold() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(3000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(3000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
        boolean result = manager.isInFirstCommissionCategory();
    
        assertFalse(result);
    }      

    @Test
    public void testTotalSalesGreaterThanLowerAndLessThanOrEqualToMiddle() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(3000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(5000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
    
        boolean result = manager.isInFirstCommissionCategory();
    
        assertTrue(result);
    }


    @Test
    public void testTotalSalesGreaterThanMiddle() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(6000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(5000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
        boolean result = manager.isInFirstCommissionCategory();
    
        assertFalse(result);

        boolean result2 = manager.isInSecondCommissionCategory();

        assertTrue(result2);
    }

    @Test
    public void testTotalSalesGreaterThanMiddleAndLessThanOrEqualToUpper() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(1000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(15000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
    
        boolean result = manager.isInSecondCommissionCategory();
    
        assertTrue(result);
    }

    @Test
    public void testTotalSalesGreaterThanUpper() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(30000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(15000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
    
        boolean result = manager.isInSecondCommissionCategory();
    
        assertFalse(result);

        boolean result2 = manager.isInThirdCommissionCategory();
    
        assertTrue(result2);
    }

    @Test
    public void testTotalSalesEqualToMiddle() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(5000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(5000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
        
        
        boolean result = manager.isInFirstCommissionCategory();
    
        assertTrue(result);

        boolean result2 = manager.isInSecondCommissionCategory();
    
        assertFalse(result2);
    }

    @Test
    public void testTotalSalesEqualToUpper() {
        SalesRepManager manager = new SalesRepManager();
        manager.setAfm("123456789");
        manager.setName("Konstantinos Zarras");
        Receipt receipt1 = new Receipt("Coat");
        receipt1.setItems(10);
        receipt1.setSales(20000);
        Receipt receipt2 = new Receipt("Trouser");
        receipt2.setItems(5);
        receipt2.setSales(20000);
        manager.getReceipts().add(receipt1);
        manager.getReceipts().add(receipt2);
        
        
        boolean result = manager.isInSecondCommissionCategory();
    
        assertTrue(result);

        boolean result2 = manager.isInThirdCommissionCategory();
        
        assertFalse(result2);
    }


}
