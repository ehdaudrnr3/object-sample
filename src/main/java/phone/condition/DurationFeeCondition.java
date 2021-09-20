package phone.condition;

import common.DateTimeInterval;
import phone.Call;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * 초당 요금 조건
 */
public class DurationFeeCondition implements FeeCondition {
    private Duration from;

    private Duration to;

    public DurationFeeCondition(Duration from, Duration to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        if(call.getInterval().duration().compareTo(from) < 0) {
            return Collections.emptyList();
        }

        DateTimeInterval interval = call.getInterval();
        LocalDateTime startDateTime = interval.getFrom().plus(from);
        LocalDateTime endDateTime = interval.duration().compareTo(to) > 0 ? interval.getFrom().plus(to) : interval.getTo();
        return List.of(DateTimeInterval.of(startDateTime, endDateTime));
    }
}
