package services;

import model.RoundedAggregateResponse;
import model.UserAuthResponse;
import model.UserRoundedTransaction;

import java.util.List;

public interface TransactionService {

    UserRoundedTransaction getUserRoundedTransactions(UserAuthResponse userAuthResponse);

    RoundedAggregateResponse getAggregateRoundedTransactions(List<UserAuthResponse> userAuthResponses, String since, String until);
}
