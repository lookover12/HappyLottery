package com.project.happylottery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 中奖号码表
 * </p>
 *
 * @author lookover
 * @since 2024-06-04
 */
public class TLotteryWinInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 彩票名称
     */
    private String name;

    /**
     * 开奖日期
     */
    private String openDate;

    /**
     * 星期
     */
    private String week;

    /**
     * 开奖红球号码
     */
    private String red;

    /**
     * 开奖篮球号码
     */
    private String blue;

    /**
     * 当日销售额
     */
    private Integer sales;

    /**
     * 奖池
     */
    private Double poolMoney;

    /**
     * 期号
     */
    private String code;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getPoolMoney() {
        return poolMoney;
    }

    public void setPoolMoney(Double poolMoney) {
        this.poolMoney = poolMoney;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TLotteryWinInfo{" +
        "id=" + id +
        ", name=" + name +
        ", openDate=" + openDate +
        ", week=" + week +
        ", red=" + red +
        ", blue=" + blue +
        ", sales=" + sales +
        ", poolMoney=" + poolMoney +
        ", code=" + code +
        "}";
    }
}
