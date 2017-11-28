package model;

public class RoundedTransaction {

    private Transaction transaction;
    private RoundedAmount roundedAmount;

    public RoundedTransaction() {
        super();
    }

    public RoundedTransaction(Transaction transaction, RoundedAmount roundedAmount) {
        this.transaction = transaction;
        this.roundedAmount = roundedAmount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public RoundedAmount getRoundedAmount() {
        return roundedAmount;
    }
}
