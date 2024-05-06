package com.example.baggage;

import com.example.baggage.constans.*;
import com.example.baggage.pojo.Baggage;
import com.example.baggage.pojo.Passenger;
import com.example.baggage.service.BaggageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@Slf4j
@SpringBootTest
class BaggageApplicationTests {

    @Autowired
    BaggageServiceImpl baggageService;

    /**
     *
     *
     */
    @Test
    void test1() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setPassengerType(PassengerType.adult);
        passenger.setCabin(CabinType.ECONOMY_CLASS);
        passenger.setVip(VipType.NORMAL);
        passenger.setAirLine(AirLineType.OUTER_AREA1);
        passenger.setTicketPrice(2000.0);

        Baggage baggage1 = new Baggage(1L,1L,BaggageType.NORMAL,50.0,20.0,20.0,15.0,15.0);
        Baggage baggage2 = new Baggage(2L,1L,BaggageType.NORMAL,90.0,30.0,30.0,30.0,30.0);
        Baggage baggage3 = new Baggage(3L, 1L, BaggageType.SPECIAL_2, 60.0, 21.0, 20.0, 20.0, 20.0);

        ArrayList<Baggage> baggages = new ArrayList<>();
        baggages.add(baggage1);
        baggages.add(baggage2);
        baggages.add(baggage3);

        passenger.setBaggagelist(baggages);

        Double normalPrice = baggageService.sum(passenger);
        log.info("normalPrice:{}",normalPrice);
    }

}
