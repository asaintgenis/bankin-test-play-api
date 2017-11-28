package repositories;

import model.Transaction;
import model.TransactionResponse;
import org.apache.commons.lang3.StringUtils;
import play.Configuration;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
public class TransactionRepository {

    WSClient wsClient;
    Configuration configuration;

    private String END_POINT = "/v2/transactions";

    @Inject
    public TransactionRepository(WSClient wsClient, Configuration configuration) {
        this.wsClient = wsClient;
        this.configuration = configuration;
    }

    public List<Transaction> getTransactionsFromUser(String token, String since, String until) {
        WSRequest wsRequest = wsClient.url(configuration.underlying().getString("bankin.base_url") + END_POINT);

        setBasicRequestParameter(token, since, until, wsRequest,configuration.underlying().getString("bankin.limit_rate"));

        TransactionResponse transactionResponse = wsRequest.get().thenApply(wsResponse ->
                Json.fromJson(wsResponse.asJson(), TransactionResponse.class)
        ).toCompletableFuture().join();

        List<Transaction> transactions = Collections.synchronizedList(transactionResponse.getResources());

        while(transactionResponse.getPagination().getNext_uri() != null) {
            wsRequest = wsClient.url(configuration.underlying().getString("bankin.base_url") + END_POINT);
            setBasicRequestParameter(token,since,until,wsRequest,configuration.underlying().getString("bankin.limit_rate"));
            setAfterQueryParameter(wsRequest, parseNextUri(transactionResponse.getPagination().getNext_uri()));
            transactionResponse = wsRequest.get().thenApply(wsResponse ->
                    Json.fromJson(wsResponse.asJson(), TransactionResponse.class)
            ).toCompletableFuture().join();
            transactions.addAll(transactionResponse.getResources());
        }
        return transactions;
    }

    private void setAfterQueryParameter(WSRequest wsRequest, String after) {
        wsRequest.setQueryParameter("after", after);
    }

    private String parseNextUri(String next_uri) {
        String[] params = StringUtils.split(next_uri,'?');
        params = StringUtils.split(params[1],'&');

        for(String param : params) {
            if(param.contains("after")) {
                return StringUtils.replace(StringUtils.split(param,'=')[1],"%3D","=");
            }
        }
        return "";
    }

    private void setBasicRequestParameter(String token, String since, String until, WSRequest wsRequest,String limit) {
        wsRequest.setHeader("Bankin-version",configuration.underlying().getString("bankin.api_version"));
        wsRequest.setHeader("Authorization","Bearer " + token);
        wsRequest.setQueryParameter("client_id",configuration.underlying().getString("bankin.client_id"));
        wsRequest.setQueryParameter("client_secret",configuration.underlying().getString("bankin.client_secret"));
        wsRequest.setQueryParameter("limit", limit);

        if(since != null) {
            wsRequest.setQueryParameter("since",since);
        }
        if(until != null) {
            wsRequest.setQueryParameter("until", until);
        }
    }
}