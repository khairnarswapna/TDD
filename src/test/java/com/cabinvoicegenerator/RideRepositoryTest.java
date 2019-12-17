package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest {

    @Test
    public void givenMultipleRide_shouldReturnSummary() throws CustomException {

        RideRepository rideRepository=new RideRepository();
        String userId = "a@gmail.com";
        Ride[] rides= { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        rideRepository.addRide(userId,rides);
        Ride[] summary = new Ride[0];
        summary = rideRepository.getRides(userId);
        Assert.assertEquals(rides[0],summary[0]);
    }

    @Test
    public void givenDistanceAndTime_IfNullUserId_ShouldThrowExcption() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId = "";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
            };
            rideRepository.addRide(userId, rides);
            Ride[] summary = new Ride[0];
            summary = rideRepository.getRides(userId);
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.NULL_USER_ID,e.type);
        }
    }






}
