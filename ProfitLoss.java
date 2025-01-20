package org.example;

public class ProfitLoss {
    private long id;                      // Unique identifier
    private String market;                // Market name (e.g., Stock, Cryptocurrency)
    private double buyInTotal = 0;            // Total amount spent on buying
    private double quantityBought = 0;        // Quantity bought
    private double buyChargesInINR = 0;       // Charges for buying in INR
    private double buyTdsInAmount = 0;        // TDS on buy in amount
    private double buyTdsInINR = 0;           // TDS on buy in INR
    private double sellInTotal = 0;           // Total amount received from selling
    private double quantitySold = 0;          // Quantity sold
    private double sellChargesInINR = 0;      // Charges for selling in INR
    private double sellTdsInAmount = 0;       // TDS on sell in amount
    private double sellTdsInINR = 0;          // TDS on sell in INR
    private double profitOrLoss = 0;          // Profit or loss from the transaction
    private double totalCharges = 0;          // Total charges incurred
    private double totalTdsInINR = 0;         // Total TDS in INR
    private double totalChargesAndTax = 0;    // Combined total of charges and taxes
    private double netProfitOrLoss = 0;       // Net profit or loss
    private double quantityInReserve = 0;     // Remaining quantity in reserve
    private double valueInINR = 0 ;            // Current value in INR
    private String status;                // Status of the transaction (e.g., Completed, Pending)

    // Constructor
    public ProfitLoss(){

    }
    public ProfitLoss(long id, String market, double buyInTotal, double quantityBought, double buyChargesInINR,
                    double buyTdsInAmount, double buyTdsInINR, double sellInTotal, double quantitySold,
                    double sellChargesInINR, double sellTdsInAmount, double sellTdsInINR, double profitOrLoss,
                    double totalCharges, double totalTdsInINR, double totalChargesAndTax, double netProfitOrLoss,
                    double quantityInReserve, double valueInINR, String status) {
        this.id = id;
        this.market = market;
        this.buyInTotal = buyInTotal;
        this.quantityBought = quantityBought;
        this.buyChargesInINR = buyChargesInINR;
        this.buyTdsInAmount = buyTdsInAmount;
        this.buyTdsInINR = buyTdsInINR;
        this.sellInTotal = sellInTotal;
        this.quantitySold = quantitySold;
        this.sellChargesInINR = sellChargesInINR;
        this.sellTdsInAmount = sellTdsInAmount;
        this.sellTdsInINR = sellTdsInINR;
        this.profitOrLoss = profitOrLoss;
        this.totalCharges = totalCharges;
        this.totalTdsInINR = totalTdsInINR;
        this.totalChargesAndTax = totalChargesAndTax;
        this.netProfitOrLoss = netProfitOrLoss;
        this.quantityInReserve = quantityInReserve;
        this.valueInINR = valueInINR;
        this.status = status;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getBuyInTotal() {
        return buyInTotal;
    }

    public void setBuyInTotal(double buyInTotal) {
        this.buyInTotal = buyInTotal;
    }

    public double getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(double quantityBought) {
        this.quantityBought = quantityBought;
    }

    public double getBuyChargesInINR() {
        return buyChargesInINR;
    }

    public void setBuyChargesInINR(double buyChargesInINR) {
        this.buyChargesInINR = buyChargesInINR;
    }

    public double getBuyTdsInAmount() {
        return buyTdsInAmount;
    }

    public void setBuyTdsInAmount(double buyTdsInAmount) {
        this.buyTdsInAmount = buyTdsInAmount;
    }

    public double getBuyTdsInINR() {
        return buyTdsInINR;
    }

    public void setBuyTdsInINR(double buyTdsInINR) {
        this.buyTdsInINR = buyTdsInINR;
    }

    public double getSellInTotal() {
        return sellInTotal;
    }

    public void setSellInTotal(double sellInTotal) {
        this.sellInTotal = sellInTotal;
    }

    public double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getSellChargesInINR() {
        return sellChargesInINR;
    }

    public void setSellChargesInINR(double sellChargesInINR) {
        this.sellChargesInINR = sellChargesInINR;
    }

    public double getSellTdsInAmount() {
        return sellTdsInAmount;
    }

    public void setSellTdsInAmount(double sellTdsInAmount) {
        this.sellTdsInAmount = sellTdsInAmount;
    }

    public double getSellTdsInINR() {
        return sellTdsInINR;
    }

    public void setSellTdsInINR(double sellTdsInINR) {
        this.sellTdsInINR = sellTdsInINR;
    }

    public double getProfitOrLoss() {
        profitOrLoss = sellInTotal - buyInTotal;
        return profitOrLoss;
    }

    public void setProfitOrLoss(double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public double getTotalCharges() {
        totalCharges = buyChargesInINR + sellChargesInINR;
        return totalCharges;
    }

    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public double getTotalTdsInINR() {
        totalTdsInINR =  buyTdsInINR + sellTdsInINR;
        return totalTdsInINR;
    }

    public void setTotalTdsInINR(double totalTdsInINR) {
        this.totalTdsInINR = totalTdsInINR;
    }

    public double getTotalChargesAndTax() {
        totalChargesAndTax = totalCharges + totalTdsInINR;
        return totalChargesAndTax;
    }

    public void setTotalChargesAndTax(double totalChargesAndTax) {
        this.totalChargesAndTax = totalChargesAndTax;
    }

    public double getNetProfitOrLoss() {
        netProfitOrLoss = profitOrLoss - totalChargesAndTax;
        return netProfitOrLoss;
    }

    public void setNetProfitOrLoss(double netProfitOrLoss) {
        this.netProfitOrLoss = netProfitOrLoss;
    }

    public double getQuantityInReserve() {
        quantityInReserve = quantityBought - quantitySold;
        return quantityInReserve;
    }

    public void setQuantityInReserve(double quantityInReserve) {
        this.quantityInReserve = quantityInReserve;
    }

    public double getValueInINR() {
        return valueInINR;
    }

    public void setValueInINR(double valueInINR) {
        this.valueInINR = valueInINR;
    }

    public String getStatus() {
        status = quantityBought - quantitySold > 0 ? "Holding" : "Sold Out";
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfitOrLossString(){
        return netProfitOrLoss > 0 ? "Profit" : "Loss";
    }

    @Override
    public String toString() {
        return "PLObject{" +
                "id=" + id +
                ", market='" + market + '\'' +
                ", buyInTotal=" + buyInTotal +
                ", quantityBought=" + quantityBought +
                ", buyChargesInINR=" + buyChargesInINR +
                ", buyTdsInAmount=" + buyTdsInAmount +
                ", buyTdsInINR=" + buyTdsInINR +
                ", sellInTotal=" + sellInTotal +
                ", quantitySold=" + quantitySold +
                ", sellChargesInINR=" + sellChargesInINR +
                ", sellTdsInAmount=" + sellTdsInAmount +
                ", sellTdsInINR=" + sellTdsInINR +
                ", profitOrLoss=" + profitOrLoss +
                ", totalCharges=" + totalCharges +
                ", totalTdsInINR=" + totalTdsInINR +
                ", totalChargesAndTax=" + totalChargesAndTax +
                ", netProfitOrLoss=" + netProfitOrLoss +
                ", quantityInReserve=" + quantityInReserve +
                ", valueInINR=" + valueInINR +
                ", status='" + status + '\'' +
                '}';
    }
}
