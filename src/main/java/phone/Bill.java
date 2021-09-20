package phone;

import common.Money;

public class Bill {

    private Phone phone;
    private Money money;

    public Bill(Phone phone, Money fee) {
        if(phone == null) throw new IllegalArgumentException();
        if(fee.isLessThan(Money.ZERO)) throw new IllegalArgumentException();
        this.phone = phone;
        this.money = fee;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "phone=" + phone +
                ", money=" + money +
                '}';
    }
}
