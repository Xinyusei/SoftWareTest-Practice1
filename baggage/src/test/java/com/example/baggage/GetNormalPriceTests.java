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
import java.util.List;

/**
 * @Author: ZJB
 * @Description: 测试“计算普通行李托运价格”方法
 * @Date: 2024/4/13 19:10
 * @Version: 1.0
 */
@Slf4j
@SpringBootTest
public class GetNormalPriceTests {

    @Autowired
    BaggageServiceImpl baggageService;

    Passenger passenger = new Passenger();
    List<Baggage> normalBagList = new ArrayList<>();
    Double normalWeight = 0.0;

    //测试用例1
    @Test
    void test1() {
        log.info("运行测试用例1");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.NORMAL);
        passenger.setCabin(CabinType.ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.INNER_ROUTES);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 10.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 18.0, 25.0, 35.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalWeight = 28.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 240.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }

    //测试用例2
    @Test
    void test2() {
        log.info("运行测试用例2");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.SKY_UNION);
        passenger.setCabin(CabinType.BUSINESS_CLASS);
        passenger.setAirLine(AirLineType.INNER_ROUTES);
        passenger.setPassengerType(PassengerType.children);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 30.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 25.0, 25.0, 35.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalWeight = 55.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 150.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }

    //测试用例3
    @Test
    void test3() {
        log.info("运行测试用例3");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.SILVER_CARD);
        passenger.setCabin(CabinType.JOYFUL_ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.INNER_ROUTES);
        passenger.setPassengerType(PassengerType.children);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 10.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 25.0, 25.0, 35.0, 30.0);
        Baggage baggage3 = new Baggage(3L, 1L, BaggageType.NORMAL, 80.0, 15.0, 20.0, 30.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalBagList.add(baggage3);
        normalWeight = 50.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 300.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }

    //测试用例4
    @Test
    void test4() {
        log.info("运行测试用例4");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.GOLD_CARD);
        passenger.setCabin(CabinType.BUSINESS_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA1);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 15.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 25.0, 25.0, 35.0, 30.0);
        Baggage baggage3 = new Baggage(3L, 1L, BaggageType.NORMAL, 90.0, 31.0, 25.0, 35.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalBagList.add(baggage3);
        normalWeight = 71.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 0.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }

    //测试用例5
    @Test
    void test5() {
        log.info("运行测试用例5");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.PLATINUM_CARD);
        passenger.setCabin(CabinType.SUPER_ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA2);
        passenger.setPassengerType(PassengerType.children);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 24.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 30.0, 25.0, 35.0, 30.0);
        Baggage baggage3 = new Baggage(3L, 1L, BaggageType.NORMAL, 80.0, 10.0, 20.0, 30.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalWeight = 64.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 0.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }

    //测试用例6
    @Test
    void test6() {
        log.info("运行测试用例6");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.LIFETIME_PLATINUM_CARD);
        passenger.setCabin(CabinType.FIRST_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA3);
        passenger.setPassengerType(PassengerType.baby);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 23.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 32.0, 25.0, 35.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalWeight = 55.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 2210.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }

    //测试用例7
    @Test
    void test7() {
        log.info("运行测试用例7");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.LIFETIME_PLATINUM_CARD);
        passenger.setCabin(CabinType.FIRST_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA4);
        passenger.setPassengerType(PassengerType.baby);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.NORMAL, 80.0, 23.0, 20.0, 30.0, 30.0);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.NORMAL, 90.0, 32.0, 25.0, 35.0, 30.0);
        Baggage baggage3 = new Baggage(3L, 1L, BaggageType.NORMAL, 90.0, 10.0, 25.0, 35.0, 30.0);
        normalBagList.add(baggage1);
        normalBagList.add(baggage2);
        normalBagList.add(baggage3);
        normalWeight = 65.0;
        //执行测试方法
        double normalPrice = baggageService.getNormalPrice(passenger, normalBagList, normalWeight);
        log.info("normalPrice:{}", normalPrice);
        //执行校验
        double expression = 4840.0;
        Assertions.assertTrue(normalPrice == expression);
        //释放被测对象
        passenger = null;
        normalBagList = null;
        normalWeight = 0.0;
    }


}
