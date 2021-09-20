package phone;

import common.Money;
import phone.policy.RatePolicy;

import java.util.ArrayList;
import java.util.List;

public class Phone {

    private RatePolicy policy;
    private List<Call> calls = new ArrayList<>();

    public Phone(RatePolicy policy) {
        this.policy = policy;
    }

    public void call(Call call) {
        calls.add(call);
    }

    public void addCalls(Call... call) {
        calls.addAll(List.of(call));
    }

    public List<Call> getCalls() {
        return calls;
    }

    public Money calculateFee() {
        return policy.calculateFee(this);
    }

    public Bill publishBill() {
        Money money = policy.calculateFee(this);
        return new Bill(this, money);
    }
}
