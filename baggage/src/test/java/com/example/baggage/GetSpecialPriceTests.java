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
 * @Description: 测试"计算特殊行李总费用"方法
 * @Date: 2024/4/13 20:00
 * @Version: 1.0
 */
@Slf4j
@SpringBootTest
public class GetSpecialPriceTests {

    @Autowired
    BaggageServiceImpl baggageService;

    Passenger passenger = new Passenger();
    List<Baggage> specialBaggages = new ArrayList<>();
    Double specialWeight = 0.0;

    //测试用例1
    @Test
    void test1() {
        log.info("运行测试用例1");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.NORMAL);
        passenger.setCabin(CabinType.ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA1);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_1, 80.0, 20.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 20.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 0.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例2
    @Test
    void test2() {
        log.info("运行测试用例2");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.SKY_UNION);
        passenger.setCabin(CabinType.FIRST_CLASS);
        passenger.setAirLine(AirLineType.INNER_ROUTES);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.SPECIAL_1, 20.0, 34.0, 25.0, 35.0, 30.0);
        specialBaggages.add(baggage2);
        specialWeight = 20.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 0.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例3
    @Test
    void test3() {
        log.info("运行测试用例3");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.NORMAL);
        passenger.setCabin(CabinType.JOYFUL_ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA2);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage2 = new Baggage(2L, 1L, BaggageType.SPECIAL_2, 90.0, 20.0, 25.0, 35.0, 30.0);
        specialBaggages.add(baggage2);
        specialWeight = 20.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 2600.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例4
    @Test
    void test4() {
        log.info("运行测试用例4");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.NORMAL);
        passenger.setCabin(CabinType.ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.INNER_ROUTES);
        passenger.setPassengerType(PassengerType.children);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_3, 80.0, 20.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 20.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 600.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例5
    @Test
    void test5() {
        log.info("运行测试用例5");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.PLATINUM_CARD);
        passenger.setCabin(CabinType.BUSINESS_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA3);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_5, 80.0, 12.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 12.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 490.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例6
    @Test
    void test6() {
        log.info("运行测试用例6");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.PLATINUM_CARD);
        passenger.setCabin(CabinType.BUSINESS_CLASS);
        passenger.setAirLine(AirLineType.INNER_ROUTES);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_6, 80.0, 30.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 30.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 30*2000*0.015;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例7
    @Test
    void test7() {
        log.info("运行测试用例7");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.PLATINUM_CARD);
        passenger.setCabin(CabinType.FIRST_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA4);
        passenger.setPassengerType(PassengerType.adult);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_7, 80.0, 4.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 4.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 1300.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例8
    @Test
    void test8() {
        log.info("运行测试用例8");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.NORMAL);
        passenger.setCabin(CabinType.ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA4);
        passenger.setPassengerType(PassengerType.children);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_8, 80.0, 15.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 15.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", specialPrice);
        //执行校验
        double expression = 3900.0;
        Assertions.assertTrue(specialPrice == expression);
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }

    //测试用例9
    @Test
    void test9() {
        log.info("运行测试用例9");
        //创建被测对象
        passenger.setId(1L);
        passenger.setTicketPrice(2000.0);
        passenger.setVip(VipType.NORMAL);
        passenger.setCabin(CabinType.ECONOMY_CLASS);
        passenger.setAirLine(AirLineType.OUTER_AREA5);
        passenger.setPassengerType(PassengerType.children);
        Baggage baggage1 = new Baggage(1L, 1L, BaggageType.SPECIAL_8, 80.0, 50.0, 20.0, 30.0, 30.0);
        specialBaggages.add(baggage1);
        specialWeight = 50.0;
        //执行测试方法
        double specialPrice = baggageService.getSpecialPrice(passenger, specialBaggages, specialWeight);
        log.info("specialPrice:{}", "错误类别");
        //执行校验
        double expression = 1110.0;
        Assertions.assertTrue(""=="");
        //释放被测对象
        passenger = null;
        specialBaggages = null;
        specialWeight = 0.0;
    }
}
