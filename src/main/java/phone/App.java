package phone;

import common.Money;
import phone.condition.FeeCondition;
import phone.condition.FixedFeeCondition;
import phone.policy.BasicRatePolicy;
import phone.policy.RatePolicy;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class App {
    public static void main(String[] args) {

        Call[] calls = {
                new Call(
                        LocalDateTime.of(LocalDate.of(2021, 9, 2), LocalTime.of(11, 30, 20, 3000)),
                        LocalDateTime.of(LocalDate.of(2021, 9, 2), LocalTime.of(11, 35, 20, 3000))
                ),
                new Call(
                        LocalDateTime.of(LocalDate.of(2021, 9, 2), LocalTime.of(11, 30, 20, 3000)),
                        LocalDateTime.of(LocalDate.of(2021, 9, 2), LocalTime.of(11, 32, 20, 3000))
                ),
                new Call(
                        LocalDateTime.of(LocalDate.of(2021, 9, 2), LocalTime.of(11, 30, 20, 3000)),
                        LocalDateTime.of(LocalDate.of(2021, 9, 2), LocalTime.of(11, 33, 20, 3000))
                )
        };

        fixedFeeAndBasicPolicy(calls);
    }

    private static void fixedFeeAndBasicPolicy(Call[] calls) {
        FeePerDuration feePerDuration = new FeePerDuration(Money.wons(100), Duration.ofSeconds(10));
        FeeCondition feeCondition = new FixedFeeCondition();
        FeeRule feeRule = new FeeRule(feeCondition, feePerDuration);
        RatePolicy ratePolicy = new BasicRatePolicy(feeRule);
        Phone phone = new Phone(ratePolicy);
        phone.addCalls(calls);

        System.out.println("fixedFee and basicPolicy calculate = " + phone.publishBill());
    }
}
