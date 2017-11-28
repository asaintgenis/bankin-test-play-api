package model;

public class RoundedAggregate {
    private double aggRoundedAmount;
    private double aggDueAmount;

    public RoundedAggregate() {
        aggDueAmount = 0.0;
        aggRoundedAmount = 0.0;
    }

    public RoundedAggregate(double aggRoundedAmount, double aggDueAmount) {
        this.aggRoundedAmount = aggRoundedAmount;
        this.aggDueAmount = aggDueAmount;
    }

    public double getAggRoundedAmount() {
        return aggRoundedAmount;
    }

    public double getAggDueAmount() {
        return aggDueAmount;
    }

    public void addRoundedAmount(RoundedAmount roundedAmount) {
        aggRoundedAmount += roundedAmount.getRoundedAmount();
        aggDueAmount += roundedAmount.getDueAmount();
    }

    public void addAggregateAmount(RoundedAggregate roundedAggregate) {
        aggRoundedAmount += roundedAggregate.getAggRoundedAmount();
        aggDueAmount += roundedAggregate.getAggDueAmount();
    }
}
