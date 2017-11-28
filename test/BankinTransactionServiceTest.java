import com.fasterxml.jackson.databind.JsonNode;
import model.*;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.libs.Json;
import repositories.TransactionRepository;
import services.BankinTransactionService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankinTransactionServiceTest {

    List<Transaction> transactions;
    BankinTransactionService bankinTransactionService;

    @Before
    public void initTest() {
        String content = "";
        try {
            content = new Scanner(new File("./public/jsonData/transactions.json")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            Logger.error("File not found");
        }
        JsonNode jsonNode = Json.parse(content);
        TransactionResponse transactionResponse = Json.fromJson(jsonNode,TransactionResponse.class);
        transactions = transactionResponse.getResources();

        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        when(transactionRepository.getTransactionsFromUser("",null,null)).thenReturn(transactions);
        when(transactionRepository.getTransactionsFromUser("","2016-04-06","2016-04-06")).thenReturn(transactions);

        bankinTransactionService = new BankinTransactionService(transactionRepository);
    }

    @Test
    public void getUserRoundedTransactionsTest() {
        UserAuthResponse userAuthResponse = new UserAuthResponse(new User(),"","");
        UserRoundedTransaction userRoundedTransaction = bankinTransactionService.getUserRoundedTransactions(userAuthResponse);
        List<RoundedTransaction> roundedTransactions = userRoundedTransaction.getRoundedTransactions();

        assertEquals(-10,roundedTransactions.get(0).getRoundedAmount().getRoundedAmount(),0.0);
        assertEquals(-60,roundedTransactions.get(1).getRoundedAmount().getRoundedAmount(),0.0);
        assertEquals(-60,roundedTransactions.get(2).getRoundedAmount().getRoundedAmount(),0.0);
    }

    @Test
    public void getAggregateRoundedTransactionsTest() {

        List<UserAuthResponse> userAuthResponses = new ArrayList<>();
        userAuthResponses.add(new UserAuthResponse(new User(),"",""));
        userAuthResponses.add(new UserAuthResponse(new User(),"",""));
        userAuthResponses.add(new UserAuthResponse(new User(),"",""));
        RoundedAggregateResponse roundedAggregateResponse = bankinTransactionService.getAggregateRoundedTransactions(userAuthResponses,"2016-04-06","2016-04-06");

        assertEquals(-390,roundedAggregateResponse.getRoundedAggregate().getAggRoundedAmount(),0.0);
    }

}
