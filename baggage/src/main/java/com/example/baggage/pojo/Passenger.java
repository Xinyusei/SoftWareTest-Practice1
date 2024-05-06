package com.example.baggage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: ZJB
 * @Description: 乘客类
 * @Date: 2024/4/8 15:51
 * @Version: 1.0
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger {
    private  Long id;

    private Integer passengerType;//乘客类型

    private Integer vip;//vip类型

    private Integer cabin;//座舱类型

    private Integer airLine;//航线类型

    private Double ticketPrice;//机票价格

    private List<Baggage> baggagelist; //行李列表

    private Double baggageWeight; //行李总重量

}
