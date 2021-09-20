package movie.policy;

import common.Money;
import movie.Screening;
import movie.condition.DiscountCondition;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {

    private Money dicountAmount;

    public AmountDiscountPolicy(Money dicountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.dicountAmount = dicountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return dicountAmount;
    }
}
