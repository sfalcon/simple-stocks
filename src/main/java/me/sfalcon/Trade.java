package me.sfalcon;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.IllegalFormatCodePointException;
import java.util.function.Predicate;

/**
 * Created by sfalcon on 3/6/2016.
 */
public class Trade {


    private ZonedDateTime timestamp;
    private int shares;
    private Indicator indicator;
    private double price;

    public Trade(){}

    public Trade(ZonedDateTime timestamp, int shares, Indicator indicator, double price) {
        this.timestamp = timestamp;
        this.setShares(shares);
        this.indicator = indicator;
        this.setPrice(price);
    }

    public boolean madeWithinLast15Min(){
        ZonedDateTime fifteenMinAgo =  ZonedDateTime.now().minus(15, ChronoUnit.MINUTES);
        return this.timestamp.isAfter(fifteenMinAgo);
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        if (shares <= 0){
            throw new IllegalArgumentException("Can't set zero or negative price");
        }
        this.shares = shares;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException("Can't set zero or negative price");
        }
        this.price = price;
    }
}
