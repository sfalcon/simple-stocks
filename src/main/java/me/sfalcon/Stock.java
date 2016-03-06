package me.sfalcon;

import java.util.Stack;

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
