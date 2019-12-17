package com.cabinvoicegenerator;

public class InvoiceService {
    
    private static final double MINIMUM_NORMAL_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_PREMIUM_COST_PER_KILOMETER = 15;
    private static final int NORMAL_COST_PER_TIME = 1;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double MINIMUM_NORMAL_FARE = 5;
    private static final double MINIMUM_PREMIUM_FARE = 20;
    private final CabRidesType cabRidesType;

    RideRepository rideRepository=null;

    public enum CabRidesType {
        NORMAL_RIDES, PREMIUM_RIDES
    };

    public InvoiceService(CabRidesType cabRidesType) {
        this.rideRepository=new RideRepository();
        this.cabRidesType=cabRidesType;
    }

    public double calculateFare(double distance, int time) throws CustomException {
        double totalFare = 0.0;
        if (this.cabRidesType.equals(CabRidesType.NORMAL_RIDES)) {
            totalFare = distance * MINIMUM_NORMAL_COST_PER_KILOMETER + time * NORMAL_COST_PER_TIME;
            return Math.max(totalFare, MINIMUM_NORMAL_FARE);
        }
        if (this.cabRidesType.equals(CabRidesType.PREMIUM_RIDES)) {
            totalFare = distance * MINIMUM_PREMIUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
            return Math.max(totalFare, MINIMUM_PREMIUM_FARE);
        }
        throw new CustomException(CustomException.ExceptionType.INVALID_CAB_RIDE_TYPE, "Invalid Cab Ride type");
    }

    public InvoiceSummary calculateFare(Ride[] rides) throws CustomException {
        double totalFare=0;
        for(Ride ride: rides){
            totalFare += this.calculateFare(ride.distance,ride.time);
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) throws CustomException {
        rideRepository.addRide(userId,rides);
    }
    public InvoiceSummary getInvoiceSummary(String userId) throws CustomException {
        Ride[] rides = rideRepository.getRides(userId);
        return this.calculateFare(rides);
    }
}
