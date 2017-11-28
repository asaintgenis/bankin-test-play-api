package model;

import java.util.ArrayList;
import java.util.List;

public class RoundedAggregateResponse {
    private List<UserAgg> userAggs = new ArrayList<>();
    private RoundedAggregate roundedAggregate;

    public RoundedAggregateResponse() {
    }

    public RoundedAggregateResponse(List<UserAgg> userAggs, RoundedAggregate roundedAggregate) {
        this.userAggs = userAggs;
        this.roundedAggregate = roundedAggregate;
    }

    public List<UserAgg> getUserAggs() {
        return userAggs;
    }

    public void setUserAggs(UserAgg userAgg) {
        userAggs.add(userAgg);
    }

    public RoundedAggregate getRoundedAggregate() {
        return roundedAggregate;
    }

    public void addRoundedAggregate(RoundedAggregate roundedAggregate) {
        if(this.roundedAggregate == null) {
            this.roundedAggregate = new RoundedAggregate(0,0);
        }
        this.roundedAggregate.addAggregateAmount(roundedAggregate);
    }
}
