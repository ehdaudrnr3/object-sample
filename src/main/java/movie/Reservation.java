package movie;

import common.Money;

public class Reservation {

    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", screening=" + screening +
                ", fee=" + fee +
                ", audienceCount=" + audienceCount +
                '}';
    }
}
