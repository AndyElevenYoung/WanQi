package com.wanqi.andy.bean;

/**
 *
 * @author xc
 * @date 2018/3/29
 */

public class Meal {
    private String value;
    private String cost;

    public Meal(String value, String cost) {
        this.value = value;
        this.cost = cost;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
