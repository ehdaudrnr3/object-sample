package phone.condition;

import common.DateTimeInterval;
import phone.Call;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class TimeOfDayFeeCondition implements FeeCondition {

    private LocalTime from;
    private LocalTime to;

    public TimeOfDayFeeCondition(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return call.getInterval()
                .splitByDay()
                .stream()
                .filter(each -> from(each).isBefore(to(each)))
                .map(each -> DateTimeInterval.of(
                        LocalDateTime.of(each.getFrom().toLocalDate(), from(each)),
                        LocalDateTime.of(each.getTo().toLocalDate(), to(each))
                ))
                .collect(Collectors.toList());
    }

    private LocalTime from(DateTimeInterval each) {
        LocalTime localTime = each.getFrom().toLocalTime();
        return localTime.isBefore(from) ? from : localTime;
    }

    private LocalTime to(DateTimeInterval each) {
        LocalTime localTime = each.getTo().toLocalTime();
        return localTime.isAfter(to) ? to : localTime;
    }
}
