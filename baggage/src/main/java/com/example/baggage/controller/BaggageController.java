package com.example.baggage.controller;

import com.example.baggage.pojo.Baggage;
import com.example.baggage.pojo.Passenger;
import com.example.baggage.service.BaggageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZJB
 * @Description: 行李controller类
 * @Date: 2024/4/8 16:10
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/baggage")
@CrossOrigin(origins = "*")//允许跨域请求
public class BaggageController {
    
    @Autowired
    private BaggageServiceImpl baggageService;

    /**
     * 计算行李价格
     * @param passenger
     * @return
     */
    @PostMapping("/sum")
    public String sum(@RequestBody Passenger passenger){
        //TODO 参数提前校验
        System.out.println("---------------------------------------------");
        log.info("passenger{}",passenger.toString());
        Double sum = baggageService.sum(passenger);
        log.info("行李托运价格为：{}",sum);
        return sum.toString();
    }

}
