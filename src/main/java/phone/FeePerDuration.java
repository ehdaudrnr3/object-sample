package phone;

import common.DateTimeInterval;
import common.Money;

import java.time.Duration;

public class FeePerDuration {

    private Money fee;

    private Duration duration;

    public FeePerDuration(Money fee, Duration duration) {
        this.fee = fee;
        this.duration = duration;
    }

    public Money calculate(DateTimeInterval interval) {
        double seconds = Math.ceil(interval.duration().toNanos() / duration.toNanos());
        return fee.times(seconds);
    }
}
