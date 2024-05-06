package com.example.baggage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ZJB
 * @Description: 行李类
 * @Date: 2024/4/8 15:54
 * @Version: 1.0
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Baggage {

    private Long id;

    private Long passengerId;
    /**
     * 行李类型
     */
    private Integer special;//行李类型

    private Double size;//尺寸  长+宽+高

    private Double weight; //重量

    //private Integer type; //表示超出件数/重量的行李件数

    private Double length;//长

    private Double width;//宽

    private Double height;//高

}
