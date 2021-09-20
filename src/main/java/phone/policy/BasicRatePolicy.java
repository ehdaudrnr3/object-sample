package phone.policy;

import common.Money;
import phone.Call;
import phone.FeeRule;
import phone.Phone;

import java.util.ArrayList;
import java.util.List;

public final class BasicRatePolicy implements RatePolicy {

    private List<FeeRule> feeRules = new ArrayList<>();

    public BasicRatePolicy(FeeRule... feeRules) {
        this.feeRules = List.of(feeRules);
    }

    @Override
    public Money calculateFee(Phone phone) {
        return phone.getCalls()
                .stream()
                .map(this::calculate)
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }

    private Money calculate(Call call) {
        return feeRules.stream()
                .map(feeRule -> feeRule.calculateFee(call))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
