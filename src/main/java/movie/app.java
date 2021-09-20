package movie;

import common.Money;
import movie.condition.SequenceCondition;
import movie.policy.PercentDiscountPolicy;

import java.time.Duration;
import java.time.LocalDateTime;

public class app {

    public static void main(String[] args) {
        Customer customer = new Customer("kim", "1");
        Movie avatar = new Movie("avatar", Duration.ofHours(2), Money.wons(15000L), new PercentDiscountPolicy(0.3, new SequenceCondition(10)));
        Screening screening = new Screening(avatar, 10, LocalDateTime.now());
        Reservation reserve = screening.reserve(customer, 5);
        Money money = screening.calculateFee(5);
        System.out.println("money = " + money);
        System.out.println("reserve = " + reserve);
    }
}
