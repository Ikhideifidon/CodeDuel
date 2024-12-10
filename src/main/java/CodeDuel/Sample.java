package CodeDuel;

import java.math.BigDecimal;

public class Sample {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String cardNumber;
    private final String securityNumber;

    public Sample(String firstName, String lastName, String email, String cardNumber, String securityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cardNumber = cardNumber;
        this.securityNumber = securityNumber;
    }

    public String getName() {
        return this.firstName + this.lastName;
    }

    @Override
    public String toString() {
        return this.cardNumber + "\n" +
                this.securityNumber + "\n" +
                this.firstName + " " + this.lastName + "\n" +
                (this.email);
    }
}

class LinkedSample extends Sample {
    private final BigDecimal balance;
    public LinkedSample(String firstName, String lastName, String email, String cardNumber, String securityNumber, BigDecimal balance) {
        super(firstName, lastName, email, cardNumber, securityNumber);
        this.balance = balance;
    }
}
