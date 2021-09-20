package phone.condition;

import common.DateTimeInterval;
import phone.Call;

import java.util.List;

public interface FeeCondition {
    List<DateTimeInterval> findTimeIntervals(Call call);
}
