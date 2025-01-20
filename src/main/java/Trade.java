package org.example;
import java.util.Date;

public class Trade {
    private Long id;
    private Date date;                // Date of the trade
    private String market;            // Market name (e.g., Stock, Cryptocurrency)
    private double price;             // Price of the asset
    private double volume;            // Volume of the trade
    private double total;             // Total trade amount
    private String tradeType;         // Type of trade (Buy/Sell)
    private String feePaidIn;         // Currency in which fee was paid
    private double feeAmount;         // Amount of fee paid
    private String tdsPaidIn;         // Currency in which TDS was paid
    private double tdsAmount;         // TDS amount
    private double tdsInINR;          // TDS amount in INR (Indian Rupees)

    // Constructor
    public Trade(Long id, Date date, String market, double price, double volume, double total,
                         String tradeType, String feePaidIn, double feeAmount,
                         String tdsPaidIn, double tdsAmount, double tdsInINR) {
        this.id = id;
        this.date = date;
        this.market = market;
        this.price = price;
        this.volume = volume;
        this.total = total;
        this.tradeType = tradeType;
        this.feePaidIn = feePaidIn;
        this.feeAmount = feeAmount;
        this.tdsPaidIn = tdsPaidIn;
        this.tdsAmount = tdsAmount;
        this.tdsInINR = tdsInINR;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getFeePaidIn() {
        return feePaidIn;
    }

    public void setFeePaidIn(String feePaidIn) {
        this.feePaidIn = feePaidIn;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getTdsPaidIn() {
        return tdsPaidIn;
    }

    public void setTdsPaidIn(String tdsPaidIn) {
        this.tdsPaidIn = tdsPaidIn;
    }

    public double getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(double tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public double getTdsInINR() {
        return tdsInINR;
    }

    public void setTdsInINR(double tdsInINR) {
        this.tdsInINR = tdsInINR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfitAndLoss{" +
                "id=" + id +
                "date=" + date +
                ", market='" + market + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", total=" + total +
                ", tradeType='" + tradeType + '\'' +
                ", feePaidIn='" + feePaidIn + '\'' +
                ", feeAmount=" + feeAmount +
                ", tdsPaidIn='" + tdsPaidIn + '\'' +
                ", tdsAmount=" + tdsAmount +
                ", tdsInINR=" + tdsInINR +
                '}';
    }
}
