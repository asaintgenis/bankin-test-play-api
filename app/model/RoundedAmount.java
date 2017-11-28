package model;

public class RoundedAmount {
    private double roundedAmount;
    private double dueAmount;

    public RoundedAmount(double roundedAmount, double dueAmount) {
        this.roundedAmount = roundedAmount;
        this.dueAmount = dueAmount;
    }

    public double getRoundedAmount() {
        return roundedAmount;
    }

    public double getDueAmount() {
        return dueAmount;
    }
}
