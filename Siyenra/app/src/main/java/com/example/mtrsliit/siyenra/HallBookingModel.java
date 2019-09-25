package com.example.mtrsliit.siyenra;

import java.io.Serializable;

public class HallBookingModel implements Serializable {
    private String hall;
    private String checkindate;
    private String checkoutdate;
    private String time;
    private int id;

    public void setHall(String hallType) {
        this.hall = hallType;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHall() {
        return hall;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public String getTime() {
        return time;
    }




}
