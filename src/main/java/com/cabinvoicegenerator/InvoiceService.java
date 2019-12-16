package com.cabinvoicegenerator;

public class InvoiceService {
    private static final double MIN_COST_PER_KILOMETER =10 ;
    private static final int COST_PER_TIME = 1;
    private static final int MINIMUM_FARE = 5;
    private final RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository=new RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalAmount = distance * MIN_COST_PER_KILOMETER + time * COST_PER_TIME;
        if( totalAmount < MINIMUM_FARE)
            return MINIMUM_FARE;
        return totalAmount;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare=0;
        for(Ride ride: rides){
            totalFare += this.calculateFare(ride.distance,ride.time);

        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}
