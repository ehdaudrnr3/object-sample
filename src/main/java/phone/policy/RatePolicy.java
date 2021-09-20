package phone.policy;

import common.Money;
import phone.Phone;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
