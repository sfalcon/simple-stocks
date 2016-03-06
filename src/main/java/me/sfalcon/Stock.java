package me.sfalcon;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by sfalcon on 3/6/2016.
 */
public class Stock {

    protected String symbol;

    protected double lastDividend = 0;
    protected double tickerPrice = 0;
    protected double parValue = 0;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static double geometricMean(List<Stock> stocks) {

        double price = 0;
        for (Iterator<Stock> iterator = stocks.iterator(); iterator.hasNext(); ) {
            Stock next =  iterator.next();
            if (price == 0){
                price = next.getTickerPrice();
            }else {
                price *= next.getTickerPrice();
            }
        }

        return Math.pow(Math.E, Math.log(price)/stocks.size());
    }
}
