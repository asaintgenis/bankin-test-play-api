package services;

import model.*;
import repositories.TransactionRepository;
import utils.MathUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class BankinTransactionService implements TransactionService  {

    private TransactionRepository transactionRepository;

    @Inject
    public BankinTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public UserRoundedTransaction getUserRoundedTransactions(UserAuthResponse userAuthResponse) {
        List<Transaction> transactions = transactionRepository.getTransactionsFromUser(userAuthResponse.getAccess_token(),null,null);
        List<RoundedTransaction> roundTransactions = roundTransactions(transactions);

        UserRoundedTransaction user = new UserRoundedTransaction(userAuthResponse.getUser().getUuid(),userAuthResponse.getUser().getEmail(),userAuthResponse.getUser().getResource_uri(),userAuthResponse.getUser().getResource_type(), roundTransactions);
        return user;
    }

    @Override
    public RoundedAggregateResponse getAggregateRoundedTransactions(List<UserAuthResponse> userAuthResponses, String since, String until) {

        RoundedAggregateResponse roundedAggregateResponse= new RoundedAggregateResponse();

        for(UserAuthResponse userAuthResponse : userAuthResponses) {
            List<Transaction> transactions = transactionRepository.getTransactionsFromUser(userAuthResponse.getAccess_token(), since, until);
            RoundedAggregate roundedAggregate = addTransactions(transactions);
            roundedAggregateResponse.setUserAggs(new UserAgg(userAuthResponse.getUser().getUuid(),userAuthResponse.getUser().getEmail(),userAuthResponse.getUser().getResource_uri(),userAuthResponse.getUser().getResource_type(),roundedAggregate));
            roundedAggregateResponse.addRoundedAggregate(roundedAggregate);
        }

        return roundedAggregateResponse;
    }

    private RoundedAggregate addTransactions(List<Transaction> transactions) {
        RoundedAggregate roundedAggregate = new RoundedAggregate();

        for(Transaction transaction : transactions) {
            // We round only the debit
            if(transaction.getAmount()< 0.0) {
                roundedAggregate.addRoundedAmount(getRoundedAmount(transaction.getAmount()));
            }
        }
        return roundedAggregate;
    }

    private List<RoundedTransaction> roundTransactions(List<Transaction> transactions) {
        List<RoundedTransaction> roundedTransactions = new ArrayList<>();

        for(Transaction transaction : transactions) {
            // We round only the debit
            if(transaction.getAmount()< 0.0) {
                RoundedTransaction roundedTransaction = new RoundedTransaction(transaction, getRoundedAmount(transaction.getAmount()));
                roundedTransactions.add(roundedTransaction);
            }
        }
        return roundedTransactions;
    }

    private RoundedAmount getRoundedAmount(double amount) {
        double roundedAmount = amount;

        amount = MathUtils.revertDouble(amount);
        roundedAmount = MathUtils.revertDouble(roundedAmount);

        roundedAmount = MathUtils.roundToNextTen(roundedAmount);

        double diffAmount = roundedAmount - amount;

        roundedAmount = MathUtils.revertDouble(roundedAmount);
        diffAmount = MathUtils.revertDouble(diffAmount);

        return new RoundedAmount(roundedAmount,MathUtils.toScale(diffAmount,2));
    }
}