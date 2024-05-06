package com.example.baggage.service;

import com.example.baggage.constans.*;
import com.example.baggage.pojo.Baggage;
import com.example.baggage.pojo.Passenger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: ZJB
 * @Description: 行李业务类
 * @Date: 2024/4/8 16:12
 * @Version: 1.0
 */
@Slf4j
@Service
public class BaggageServiceImpl {
    private static final Double rate = 0.015;


    /**
     * 计算行李托运总价格
     *
     * @param passenger 乘客信息
     * @return 托运行李价格
     */
    public Double sum(Passenger passenger)  {
        //普通行李列表
        List<Baggage> normalBagList = new ArrayList<>();
        //特殊行李列表
        List<Baggage> specialBagList = new ArrayList<>();
        //普通行李总重量
        Double normalWeight = 0.0;
        //特殊行李总重量
        Double specialWeight = 0.0;
        List<Baggage> baggage = passenger.getBaggagelist();
        for (Baggage b : baggage) {
            //计算每个行李的尺寸 (长 + 宽 + 高)
            b.setSize(b.getLength() + b.getWidth() + b.getHeight());
            //先分成普通行李 和 特殊行李
            if (b.getSpecial().equals(BaggageType.NORMAL)
                    || b.getSpecial().equals(BaggageType.SPECIAL_1)
                    || b.getSpecial().equals(BaggageType.SPECIAL_4)) {
                normalBagList.add(b);
                normalWeight += b.getWeight();
            } else {
                specialBagList.add(b);
                specialWeight += b.getWeight();
            }
        }
        //将两个列表按照重量大小从大到小排序,如果重量相同则按照尺寸大小从大到小排序
        normalBagList.sort((o1, o2) -> {
            if (!o1.getWeight().equals(o2.getWeight())) {
                return o2.getWeight().compareTo(o1.getWeight());
            } else
                return o2.getSize().compareTo(o1.getSize());
        });
        specialBagList.sort((o1, o2) -> {
            if (!o1.getWeight().equals(o2.getWeight())) {
                return o2.getWeight().compareTo(o1.getWeight());
            } else
                return o2.getSize().compareTo(o1.getSize());
        });
        return getNormalPrice(passenger, normalBagList, normalWeight) + getSpecialPrice(passenger, specialBagList, specialWeight);
    }


    //计算特殊行李总费用
    public Double getSpecialPrice(Passenger passenger, List<Baggage> specialBaggage, Double weightnums) {
        Double sum = 0.0;
        for (Baggage bag : specialBaggage) {
            if (bag.getSpecial().equals(BaggageType.SPECIAL_0)) {
                sum += 0.0;
            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_1)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += 0.0;
                } else {//国际
                    sum += 0.0;
                }

            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_2)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += normalBagExPriceWithInnerAir(weightnums, 0.0, 0.015, passenger.getTicketPrice());

                } else {//国际
                    if (bag.getWeight() <= 23 && bag.getWeight() >= 2)
                        sum += 2600;
                    if (bag.getWeight() <= 32 && bag.getWeight() > 23)
                        sum += 3900;
                    if (bag.getWeight() <= 45 && bag.getWeight() > 32)
                        sum += 5200;
                }
            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_3)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += normalBagExPriceWithInnerAir(weightnums, 0.0, 0.015, passenger.getTicketPrice());
                } else {//国际
                    if (bag.getWeight() <= 23 && bag.getWeight() >= 2)
                        sum += 1300;
                    if (bag.getWeight() <= 32 && bag.getWeight() > 23)
                        sum += 2600;
                    if (bag.getWeight() <= 45 && bag.getWeight() > 32)
                        sum += 3900;
                }

            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_4)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += 0.0;
                } else {//国际
                    sum += 0.0;
                }

            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_5)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += normalBagExPriceWithInnerAir(weightnums, 0.0, 0.015, passenger.getTicketPrice());
                } else {//国际
                    if (bag.getWeight() <= 23 && bag.getWeight() >= 2)
                        sum += 490;
                    if (bag.getWeight() <= 32 && bag.getWeight() > 23)
                        sum += 3900;


                }

            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_6)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += normalBagExPriceWithInnerAir(weightnums, 0.0, 0.015, passenger.getTicketPrice());
                } else {//国际
                    if (bag.getWeight() <= 23 && bag.getWeight() >= 2)
                        sum += 1300;
                    if (bag.getWeight() <= 32 && bag.getWeight() > 23)
                        sum += 2600;

                }

            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_7)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += normalBagExPriceWithInnerAir(weightnums, 0.0, 0.015, passenger.getTicketPrice());
                } else {//国际
                    if (bag.getWeight() <= 5 && bag.getWeight() >= 2)
                        sum += 1300;

                }

            } else if (bag.getSpecial().equals(BaggageType.SPECIAL_8)) {
                if (passenger.getAirLine().equals(AirLineType.INNER_ROUTES)) {//国内
                    sum += normalBagExPriceWithInnerAir(weightnums, 0.0, 0.015, passenger.getTicketPrice());
                } else {//国际
                    if (bag.getWeight() <= 23 && bag.getWeight() >= 2)
                        sum += 3900;
                    if (bag.getWeight() <= 32 && bag.getWeight() > 23)
                        sum += 5200;
                    if (bag.getWeight() <= 45 && bag.getWeight() > 32)
                        sum += 7800;

                }
            }

        }

        return sum;
    }


    /**
     * 计算普通行李托运价格
     *
     * @param passenger     乘客
     * @param normalBagList 普通行李列表
     * @param normalWeight  普通行李总重量
     * @return 行李价格
     */
    public Double getNormalPrice(Passenger passenger, List<Baggage> normalBagList, Double normalWeight) {
        //乘客舱位等级
        Integer cabin = passenger.getCabin();
        //乘客的会员等级
        Integer vip = passenger.getVip();
        //乘客航班类型
        Integer airLine = passenger.getAirLine();
        //经济舱的机票价格
        Double ticketPrice = passenger.getTicketPrice();
        //乘客的类型
        Integer passengerType = passenger.getPassengerType();
        //记件条件
        TreeMap<Double, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        //成人/儿童票
        if (PassengerType.adult.equals(passengerType) || PassengerType.children.equals(passengerType)) {
            //头等舱
            if (CabinType.FIRST_CLASS.equals(cabin)) {
                //普通会员
                if (VipType.NORMAL.equals(vip)) {
                    //国内记重-≤40KG免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 40.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 40.0, rate, ticketPrice);
                        //国际计件-免费托运 2件 32KG
                    else {
                        map.put(32.0, 2);
                    }
                }
                //星空联盟会员
                else if (VipType.SKY_UNION.equals(vip)) {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤60KG免费
                        return normalWeight <= 60.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 60.0, rate, ticketPrice);
                    else {
                        //可免费托运2件32KG以内+1件23KG以内
                        map.put(32.0, 2);
                        map.put(23.0, 1);
                    }
                }
                //凤凰知音金卡、银卡
                else if (VipType.GOLD_CARD.equals(vip) || VipType.SILVER_CARD.equals(vip)) {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤60KG免费
                        return normalWeight <= 60.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 60.0, rate, ticketPrice);
                    else {
                        //免费托运 3件 32KG
                        map.put(32.0, 3);
                    }
                } else {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤70KG免费
                        return normalWeight <= 70.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 70.0, rate, ticketPrice);
                    else {
                        //免费托运 3件 32KG
                        map.put(32.0, 3);
                    }
                }
            }
            //公务舱
            else if (CabinType.BUSINESS_CLASS.equals(cabin)) {
                //普通会员
                if (VipType.NORMAL.equals(vip)) {
                    //≤30KG免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 30.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 30.0, rate, ticketPrice);
                        //可免费托运2件32KG以内
                    else {
                        map.put(32.0, 2);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                }
                //星空联盟会员
                else if (VipType.SKY_UNION.equals(vip)) {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤50KG免费
                        return normalWeight <= 50.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 50.0, rate, ticketPrice);
                    else {
                        //可免费托运2件32KG以内+1件23KG以内
                        map.put(32.0, 2);
                        map.put(23.0, 1);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                }
                //凤凰知音金卡、银卡
                else if (VipType.GOLD_CARD.equals(vip) || VipType.SILVER_CARD.equals(vip)) {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤50KG免费
                        return normalWeight <= 50.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 50.0, rate, ticketPrice);
                    else {
                        //免费托运 3件 32KG
                        map.put(32.0, 3);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                } else {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤60KG免费
                        return normalWeight <= 60.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 60.0, rate, ticketPrice);
                    else {
                        //免费托运 3件 32KG
                        map.put(32.0, 3);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                }
            }
            //悦享经济舱/超级经济舱
            else if (CabinType.JOYFUL_ECONOMY_CLASS.equals(cabin) || CabinType.SUPER_ECONOMY_CLASS.equals(cabin)) {
                //普通会员
                if (VipType.NORMAL.equals(vip)) {
                    //≤20KG免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 20.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 20.0, rate, ticketPrice);
                        //可免费托运2件32KG以内
                    else {
                        map.put(32.0, 2);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                }
                //凤凰知音金卡、银卡/星空联盟金卡
                else if (VipType.GOLD_CARD.equals(vip) || VipType.SILVER_CARD.equals(vip) || VipType.SKY_UNION.equals(vip)) {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤40KG免费
                        return normalWeight <= 40.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 40.0, rate, ticketPrice);
                    else {
                        //可免费托运3件32KG以内
                        map.put(32.0, 3);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                }
                //凤凰知音终身白金卡、白金卡
                else {
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        //≤50KG免费
                        return normalWeight <= 50.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 50.0, rate, ticketPrice);
                    else {
                        //免费托运 3件 32KG
                        map.put(32.0, 3);
                        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                    }
                }
            }
            //经济舱
            //分区域1和区域2
            else if (CabinType.ECONOMY_CLASS.equals(vip)) {
                //国内
                if (AirLineType.INNER_ROUTES.equals(airLine)) {
                    //普通会员
                    if (VipType.NORMAL.equals(vip))
                        //≤20KG免费
                        return normalWeight <= 20.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 20.0, rate, ticketPrice);
                    else if (VipType.SKY_UNION.equals(vip) || VipType.SILVER_CARD.equals(vip) || VipType.GOLD_CARD.equals(vip)) {
                        //≤40KG免费
                        return normalWeight <= 40.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 40.0, rate, ticketPrice);
                    } else
                        //≤50KG免费
                        return normalWeight <= 50.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 50.0, rate, ticketPrice);
                }
                //国外区域
                else {
                    //区域1 + 2 + 3
                    if (AirLineType.OUTER_AREA1.equals(airLine) || AirLineType.OUTER_AREA2.equals(airLine) || AirLineType.OUTER_AREA3.equals(airLine)) {
                        //普通用户
                        if (VipType.NORMAL.equals(vip))
                            map.put(23.0, 1);
                            //其他
                        else
                            map.put(23.0, 2);
                    }
                    //区域 4 + 5
                    else {
                        //普通用户
                        if (VipType.NORMAL.equals(vip))
                            map.put(23.0, 2);
                            //其他
                        else
                            map.put(23.0, 3);
                    }
                    return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
                }
            }
        }
        //婴儿票
        else if (PassengerType.baby.equals(passenger.getPassengerType())) {
            //头等舱/公务舱
            if (CabinType.FIRST_CLASS.equals(cabin) || CabinType.BUSINESS_CLASS.equals(cabin)) {
                //普通会员
                if (VipType.NORMAL.equals(vip)) {
                    //国内计重制 <=10kg-免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 10.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 10.0, rate, ticketPrice);
                        //国际计件制 托运1件23KG以内 - 免费
                    else
                        map.put(23.0, 1);
                }
                //星空联盟金卡
                else if (VipType.SKY_UNION.equals(vip)) {
                    //国内-计重 小于30kg免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 30.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 30.0, rate, ticketPrice);
                        //国际计件制 2件23KG以内 - 免费
                    else
                        map.put(23.0, 2);
                }
                //凤凰知音卡、银卡
                else if (VipType.GOLD_CARD.equals(vip) || VipType.SILVER_CARD.equals(vip)) {
                    //国内-计重 小于30kg免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 30.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 30.0, rate, ticketPrice);
                        //国际计件制 1件32KG以内，1件23KG以内 - 免费
                    else {
                        map.put(32.0, 1);
                        map.put(23.0, 1);
                    }
                }
                //凤凰知音终身白金卡、白金卡
                else if (VipType.LIFETIME_PLATINUM_CARD.equals(vip) || VipType.PLATINUM_CARD.equals(vip)) {
                    //国内-计重 小于40kg免费
                    if (AirLineType.INNER_ROUTES.equals(airLine)) {
                        return normalWeight <= 40.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 40.0, rate, ticketPrice);
                    }
                    //国际 1件32KG以内，1件23KG以内免费
                    else {
                        map.put(32.0, 1);
                        map.put(23.0, 1);
                    }
                }
            }
            //悦享经济舱/超级经济舱/经济舱
            else {
                //普通会员
                if (VipType.NORMAL.equals(vip)) {
                    //国内 - 10kg以下免费
                    if (AirLineType.INNER_ROUTES.equals(airLine))
                        return normalWeight <= 10.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 10.0, rate, ticketPrice);
                    else
                        //国际 - 1件23KG以下免费
                        map.put(23.0, 1);
                }
                //凤凰知音金卡、银卡/星空联盟金卡
                else if (VipType.SKY_UNION.equals(vip) || VipType.SILVER_CARD.equals(vip) || VipType.GOLD_CARD.equals(vip)) {
                    //国内 - 30KG以下免费
                    if (AirLineType.INNER_ROUTES.equals(airLine)) {
                        return normalWeight <= 30.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 30.0, rate, ticketPrice);
                    }
                    //国际 - 2件23KG以内免费
                    else
                        map.put(23.0, 2);
                } else if (VipType.LIFETIME_PLATINUM_CARD.equals(vip) || VipType.PLATINUM_CARD.equals(vip)) {
                    //国内-计重 小于40kg免费
                    if (AirLineType.INNER_ROUTES.equals(airLine)) {
                        return normalWeight <= 40.0 ? 0.0 : normalBagExPriceWithInnerAir(normalWeight, 40.0, rate, ticketPrice);
                    }
                    //国际-2件23KG以内免费
                    else
                        map.put(23.0, 2);
                }
            }
        }
        return iternationalExcessFees(passenger, calculateBaggageList(normalBagList, map).get(0), calculateBaggageList(normalBagList, map).get(1));
    }

    /**
     * 国内计重制 - 普通行李超重收费
     *
     * @param normalBagWeight 普通行李总重量
     * @param freeWeight      最大免费托运重量
     * @param rate            百分比
     * @param airLinePrice    经济舱普通票价
     * @return 国内-普通行李超重收费
     */
    public Double normalBagExPriceWithInnerAir(Double normalBagWeight, Double freeWeight, Double rate, Double airLinePrice) {
        return rate * airLinePrice * (normalBagWeight - freeWeight);
    }


    private int[][] overstandard = {
            {},
            {380, 980, 980, 1400, 1400, 2000, 3000},
            {280, 690, 690, 1100, 1100, 1100, 1590},
            {520, 520, 520, 520, 1170, 1170, 1590},
            {690, 1040, 1040, 2050, 1380, 1380, 1590},
            {210, 520, 520, 830, 830, 1100, 1590}
    };

    //国际航线：计算单件超额行李的费用
    public Double overweightOrOversize(Integer airlinetype, Baggage bag) {
        Double sum = 0.0;
        if ((bag.getWeight() > 23 && bag.getWeight() <= 28) && (bag.getSize() >= 60 && bag.getSize() <= 158))
            sum += overstandard[airlinetype][0];
        else if ((bag.getWeight() > 28 && bag.getWeight() <= 32) && (bag.getSize() >= 60 && bag.getSize() <= 158))
            sum += overstandard[airlinetype][1];
        else if ((bag.getWeight() >= 2 && bag.getWeight() <= 23) && (bag.getSize() > 158 && bag.getSize() <= 203))
            sum += overstandard[airlinetype][2];
        else if ((bag.getWeight() > 23 && bag.getWeight() <= 32) && (bag.getSize() > 158 && bag.getSize() <= 203))
            sum += overstandard[airlinetype][3];
        return sum;
    }

    //国际航线：计算乘客所有超额行李的总费用
    public Double iternationalExcessFees(Passenger passenger, List<Baggage> overweightoroversize, List<Baggage> overquantity) {
        Double sum = 0.0;
        if (overweightoroversize != null && !overweightoroversize.isEmpty()) {
            for (Baggage bag : overweightoroversize) {//超重或超尺寸
                sum += overweightOrOversize(passenger.getAirLine(), bag);
            }
        }
        if (overquantity != null && !overquantity.isEmpty()) {
            //超件数
            for (int i = 0; i < overquantity.size(); i++) {
                sum = sum + overstandard[passenger.getAirLine()][Math.min(4 + i, 6)] + overweightOrOversize(passenger.getAirLine(), overquantity.get(i));
            }
        }
        return sum;
    }

    /**
     * 获取 普通行李的超重/超尺寸、超件列表
     *
     * @param normalBagList 普通行李列表
     * @param map           条件
     * @return List.get(0)为超重列表, List.get(1)为超件列表
     */
    private List<List<Baggage>> calculateBaggageList(List<Baggage> normalBagList, TreeMap<Double, Integer> map) {
        int bagNum = normalBagList.size();
        List<List<Baggage>> res = new ArrayList<>();
        //超重行李集合
        List<Baggage> exWeightBagList = new ArrayList<>();
        //超件行李集合
        List<Baggage> exNumBagList = new ArrayList<>();
        //符合条件的行李的索引
        Set<Integer> eligibleBagListIdx = new HashSet<>();
        for (Map.Entry<Double, Integer> entry : map.entrySet()) {
            Double limitWeight = entry.getKey();
            Integer limitNum = entry.getValue();
            for (int i = 0; i < bagNum; i++) {
                if (limitNum > 0 && !eligibleBagListIdx.contains(i) && normalBagList.get(i).getWeight() <= limitWeight && normalBagList.get(i).getSize() <= 158.0) {
                    eligibleBagListIdx.add(i);
                    limitNum--;
                } else {
                    if (limitNum == 0)
                        exNumBagList.add(normalBagList.get(i));
                    else
                        exWeightBagList.add(normalBagList.get(i));
                }
            }
        }
        res.add(exWeightBagList);
        res.add(exNumBagList);

        return res;
    }

}





