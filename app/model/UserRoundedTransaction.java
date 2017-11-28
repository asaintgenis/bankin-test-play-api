package model;

import java.util.List;

public class UserRoundedTransaction extends User {

    private List<RoundedTransaction> roundedTransactions;

    public UserRoundedTransaction(String uuid, String email, String resource_uri, String resource_type, List<RoundedTransaction> roundedTransactions) {
        super(uuid, email, resource_uri, resource_type);
        this.roundedTransactions = roundedTransactions;
    }

    public List<RoundedTransaction> getRoundedTransactions() {
        return roundedTransactions;
    }
}
