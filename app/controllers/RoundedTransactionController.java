package controllers;

import exception.ExceptionResponse;
import exception.NotFoundResponse;
import exception.UserNotFoundException;
import model.RoundedAggregateResponse;
import model.UserAuthResponse;
import model.UserRoundedTransaction;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.TransactionService;
import services.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RoundedTransactionController extends Controller {

    private TransactionService transactionService;
    private UserService userService;

    @Inject
    public RoundedTransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    public Result roundedTransaction(String mail) {
        UserAuthResponse userAuthResponse;
        try {
            userAuthResponse = userService.authenticate(mail);
            UserRoundedTransaction response = transactionService.getUserRoundedTransactions(userAuthResponse);
            return ok(Json.toJson(response));
        } catch (UserNotFoundException e) {
            return notFound(Json.toJson(new NotFoundResponse("user not found",e.getMail())));
        } catch (Exception e) {
            return internalServerError(Json.toJson(new ExceptionResponse(e.getMessage())));
        }
    }

    public Result aggregateRoundedAmount(String since, String until) {
        RoundedAggregateResponse response;
        try {
            response = transactionService.getAggregateRoundedTransactions(userService.getAllUsers(), since, until);
        } catch(Exception e) {
            return internalServerError(Json.toJson(new ExceptionResponse(e.getMessage())));
        }

        return ok(Json.toJson(response));
    }
}
