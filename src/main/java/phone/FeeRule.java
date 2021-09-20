package phone;

import common.Money;
import phone.condition.FeeCondition;

public class FeeRule {

    private FeeCondition feeCondition;

    private FeePerDuration feePerDuration;

    public FeeRule(FeeCondition feeCondition, FeePerDuration feePerDuration) {
        this.feeCondition = feeCondition;
        this.feePerDuration = feePerDuration;
    }

    public Money calculateFee(Call call) {
        return feeCondition.findTimeIntervals(call)
                .stream()
                .map(interval -> feePerDuration.calculate(interval))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
