package repositories;

import com.fasterxml.jackson.databind.JsonNode;
import exception.UserNotFoundException;
import model.InternalUser;
import model.InternalUserCache;
import model.UserAuthResponse;
import play.Configuration;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {

    Configuration configuration;
    WSClient wsClient;
    List<InternalUser> internalUsers;

    private String BANKIN_END_POINT = "/v2/authenticate";

    @Inject
    public UserRepository(WSClient wsClient, Configuration configuration) {
        this.wsClient = wsClient;
        this.configuration = configuration;

        // Load Internal User
        String content = "";
        try {
            content = new Scanner(new File("./public/jsonData/internalUser.json")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            Logger.error("File not found");
        }
        JsonNode jsonNode = Json.parse(content);
        InternalUserCache internalUserCache = Json.fromJson(jsonNode, InternalUserCache.class);
        internalUsers = internalUserCache.getResources();
    }

    public UserAuthResponse getUser(String mail) throws UserNotFoundException {

        InternalUser internalUser = getInternalUser(mail);
        return getBankinUser(internalUser);
    }

    private UserAuthResponse getBankinUser(InternalUser internalUser) {
        WSRequest wsRequest = wsClient.url(configuration.underlying().getString("bankin.base_url") + BANKIN_END_POINT);

        setBankinRequestParameter(internalUser, wsRequest);

        return wsRequest.post("").thenApply(wsResponse ->
                Json.fromJson(wsResponse.asJson(),UserAuthResponse.class)
            ).toCompletableFuture().join();
    }

    private void setBankinRequestParameter(InternalUser internalUser, WSRequest wsRequest) {
        wsRequest.setHeader("Bankin-version",configuration.underlying().getString("bankin.api_version"));
        wsRequest.setMethod("POST");
        wsRequest.setQueryParameter("email",internalUser.getMail());
        wsRequest.setQueryParameter("password",internalUser.getPassword());
        wsRequest.setQueryParameter("client_id",configuration.underlying().getString("bankin.client_id"));
        wsRequest.setQueryParameter("client_secret",configuration.underlying().getString("bankin.client_secret"));
    }

    private InternalUser getInternalUser(String mail) throws UserNotFoundException {
        for(InternalUser internalUser : internalUsers) {
            if(internalUser.getMail().equals(mail)) {
                return internalUser;
            }
        }
        throw new UserNotFoundException(mail);
    }

    public List<UserAuthResponse> getAllUsers() {
        List<UserAuthResponse> userAuthResponses = new ArrayList<>();
        for(InternalUser internalUser : internalUsers) {
            userAuthResponses.add(getBankinUser(internalUser));
        }
        return userAuthResponses;
    }
}
