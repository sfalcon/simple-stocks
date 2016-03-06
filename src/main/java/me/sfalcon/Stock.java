package me.sfalcon;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by sfalcon on 3/6/2016.
 */
public class Stock {

    private double lastDividend = 0;
    private double tickerPrice = 0;
    private double parValue = 0;

    private Stack<Trade> trades = new Stack<>();

    public Stock (){}

    public Stock (double lastDividend, double parValue){
        this.lastDividend = lastDividend;
        this.parValue = parValue;
        this.tickerPrice = parValue; //assuming tickerPrice is the same as parValue when defined
    }

    public double dividendYield(){
        if (tickerPrice == 0) return tickerPrice;
        return lastDividend/tickerPrice;
    }

    public double priceEarningRatio(){
        if (lastDividend == 0) return lastDividend;
        return tickerPrice/lastDividend;
    }

    public void trade(Trade trade){
        this.tickerPrice = trade.getPrice();
        this.trades.push(trade);
    }

    public double price(){
        double sumPrice = 0;
        double sumQuantity = 0;
        for (Iterator<Trade> iterator = trades.iterator(); iterator.hasNext(); ) {
            Trade next =  iterator.next();
            if (next.madeWithinLast15Min()){
                sumPrice += next.getPrice() * next.getShares();
                sumQuantity += next.getShares();
            }
        }

        return sumPrice/sumQuantity;
    }

    public double getTickerPrice() {
        return tickerPrice;
    }

    public void setTickerPrice(double tickerPrice) {
        this.tickerPrice = tickerPrice;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        this.parValue = parValue;
    }
}
