package common;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DateTimeInterval {

    private LocalDateTime from;
    private LocalDateTime to;

    public static DateTimeInterval of(LocalDateTime from, LocalDateTime to) {
        return new DateTimeInterval(from, to);
    }

    public static DateTimeInterval toMidnight(LocalDateTime from) {
        LocalDateTime to = LocalDateTime.of(from.toLocalDate(), LocalTime.of(23, 59, 59, 999_999_999));
        return new DateTimeInterval(from, to);
    }

    public static DateTimeInterval fromMidnight(LocalDateTime to) {
        LocalDateTime from = LocalDateTime.of(to.toLocalDate(), LocalTime.of(0 , 0));
        return new DateTimeInterval(from, to);
    }

    public static DateTimeInterval during(LocalDate localDate) {
        LocalDateTime from = LocalDateTime.of(localDate, LocalTime.of(0 , 0));
        LocalDateTime to = LocalDateTime.of(localDate, LocalTime.of(23, 59, 59, 999_999_999));
        return new DateTimeInterval(from, to);
    }

    public DateTimeInterval(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public Duration duration() {
        return Duration.between(from, to);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public List<DateTimeInterval> splitByDay() {
        long days = days();
        return days > 0 ? split(days) : List.of(this);
    }

    private List<DateTimeInterval> split(long days) {
        List<DateTimeInterval> result = new ArrayList<>();
        addFirstDay(result);
        addMiddleDays(result, days);
        addLastDays(result);
        return result;
    }

    private void addFirstDay(List<DateTimeInterval> result) {
        result.add(DateTimeInterval.toMidnight(from));
    }

    private void addMiddleDays(List<DateTimeInterval> result, long days) {
        for(int loop = 1; loop < days; loop++) {
            result.add(DateTimeInterval.during(from.toLocalDate().plusDays(loop)));
        }
    }

    private void addLastDays(List<DateTimeInterval> result) {
        result.add(DateTimeInterval.fromMidnight(to));
    }

    private long days() {
        return Duration.between(from.toLocalDate().atStartOfDay(), to.toLocalDate().atStartOfDay()).toDays();
    }

    public String toString() {
        return "[ " + from + " - " + to + " ]";
    }
}
