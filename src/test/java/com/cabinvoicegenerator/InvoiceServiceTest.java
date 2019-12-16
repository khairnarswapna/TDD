package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    
    InvoiceService invoiceGenerator=null;
    @Before
    
    @Test
    public void givendistance_And_TimeShould_ReturnTotalFare() {
        InvoiceService invoiceGenerator=new InvoiceService();
        double distance = 2.0;
        int time = 5;
        double fare=invoiceGenerator.calculateFare(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void  givenLessDistanceOrTime_shouldReturnMinFare() {

        InvoiceService invoiceGenerator=new InvoiceService();
        double distance = 0.1;
        int time = 1;
        double fare=invoiceGenerator.calculateFare(distance,time);
        Assert.assertEquals(5,fare,0.0);

    }


    @Test
    public void givenMultipleRide_shouldReturnInvoiceSummary() {
        InvoiceService invoiceGenerator = new InvoiceService();
        Ride[] rides= { new Ride(2.0, 5),
                        new Ride(0.1, 1)
                      };
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,invoiceSummary);
    }

    @Test
    public void givenUserIDAndRides_shouldReturnInvoiceSummary() {
        InvoiceService invoiceGenerator = new InvoiceService();
        String userId="a@b.com";
        Ride[] rides= { new Ride(2.0, 5),
                        new Ride(0.1, 1)
                    };
        invoiceGenerator.addRides(userId,rides);
        InvoiceSummary summary=invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void name() {
    }
}
