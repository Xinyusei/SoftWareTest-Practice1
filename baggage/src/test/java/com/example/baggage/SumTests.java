package com.example.baggage;

import com.example.baggage.constans.*;
import com.example.baggage.pojo.Baggage;
import com.example.baggage.pojo.Passenger;
import com.example.baggage.service.BaggageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @Author: ZJB
 * @Description: 测试“计算行李托运总价格”方法
 * @Date: 2024/4/13 20:32
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class SumTests {

    @Autowired
    BaggageServiceImpl baggageService;
    Passenger passenger = new Passenger();

    //测试用例1
    @Test
    void test1() {
        log.info("运行测试用例1");
        //创建被测对象
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

        //执行测试方法
        double sum = baggageService.sum(passenger);
        log.info("sum:{}", sum);
        //执行校验
        double expression = 3580.0;
        Assertions.assertTrue(sum == expression);
        //释放被测对象
        passenger = null;
    }
}
