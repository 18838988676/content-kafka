package com.example.storm_kafka.centos;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ToString
public class UserLog implements Serializable {
    //用户名
    private String username;
    // 用户id
    private Integer userid;
    //订单id
    private Integer orderId;
    //商品名称
    private String goodsName;
    //商品单价
//    private Double goodPrice;
    //商品数量
//    private Integer goodNums;
    //总价
    private String AllPrice;
    //订单状态
//    private String orderStatus;
    //交易时间
    private Long orderTime;

}