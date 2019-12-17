package com.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {

    Map<String, ArrayList<Ride>> userRides=null;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }
    public void addRide(String userId, Ride[] rides) throws CustomException {
            if(rides != null)
                this.userRides.put(userId, new ArrayList<>(Arrays.asList(rides)));
            throw new CustomException("Rides are Empty",CustomException.ExceptionType.RIDE_NOT_FOUND);
    }
    public Ride[] getRides(String userId) throws CustomException {
        if (userId != "")
            return this.userRides.get(userId).toArray(new Ride[0]);
        else
            throw new CustomException("User not available",CustomException.ExceptionType.NULL_USER_ID);
    }
}
