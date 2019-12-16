package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    
    InvoiceService invoiceGenerator=null;
    @Before
    
    @Test
    public void givendistance_And_TimeShould_ReturnTotalFare() throws CustomException {
        InvoiceService invoiceGenerator=new InvoiceService(InvoiceService.CabRidesType.NORMAL_RIDES);
        double distance = 2.0;
        int time = 5;
        double fare=invoiceGenerator.calculateFare(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void  givenLessDistanceOrTime_shouldReturnMinFare() throws CustomException {

        InvoiceService invoiceGenerator=new InvoiceService(InvoiceService.CabRidesType.NORMAL_RIDES);
        double distance = 0.1;
        int time = 1;
        double fare=invoiceGenerator.calculateFare(distance,time);
        Assert.assertEquals(5,fare,0.0);

    }


    @Test
    public void givenMultipleRide_shouldReturnInvoiceSummary() throws CustomException {
        InvoiceService invoiceGenerator = new InvoiceService(InvoiceService.CabRidesType.NORMAL_RIDES);
        Ride[] rides= { new Ride(2.0, 5),
                        new Ride(0.1, 1)
                      };
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,invoiceSummary);
    }

    @Test
    public void givenUserIDAndRides_shouldReturnInvoiceSummary() throws CustomException {
        InvoiceService invoiceGenerator = new InvoiceService(InvoiceService.CabRidesType.NORMAL_RIDES);
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
    public void givenUserId_WithPremiumRides_ShouldReturnSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService(InvoiceService.CabRidesType.PREMIUM_RIDES);
            String userId = "abc@gmail.com";
            double distance = 15;
            int time = 2;
            double fare = 0;
            fare = invoiceService.calculateFare(distance, time);
            Assert.assertEquals(229.0, fare, 0.0);
        } catch (CustomException e) {
        }
    }

    @Test
    public void givenUserId_WithPremiumWithMultipleRides_ShouldReturnInvoiceSummary() {
        try {
            InvoiceService invoiceService = new InvoiceService(InvoiceService.CabRidesType.PREMIUM_RIDES);
            String userId = "a@b.com";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
            };
            invoiceService.addRides(userId, rides);
            InvoiceSummary summary = null;
            summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
            Assert.assertEquals(expectedInvoiceSummary, summary);
        } catch (CustomException e) {
        }
    }
}
