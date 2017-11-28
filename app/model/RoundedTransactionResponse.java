package model;

import java.util.List;

public class RoundedTransactionResponse {
    private User user;
    private List<RoundedTransaction> roundedTransactions;

    public RoundedTransactionResponse(User user, List<RoundedTransaction> roundedTransactions) {
        this.user = user;
        this.roundedTransactions = roundedTransactions;
    }

    public User getUser() {
        return user;
    }

    public List<RoundedTransaction> getRoundedTransactions() {
        return roundedTransactions;
    }
}
