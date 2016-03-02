package com.lihuanyu.repairsystem.model;

import javax.persistence.*;

/**
 * Created by echao on 2016/2/28.
 */
@Entity
@Table(name = "repairList")
public class RepairList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int yibanid;
    private String yibanname;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYibanid() {
        return yibanid;
    }

    public void setYibanid(int yibanid) {
        this.yibanid = yibanid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }
    public String getYibanname() {
        return yibanname;
    }

    public void setYibanname(String yibanname) {
        this.yibanname = yibanname;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state;
}
