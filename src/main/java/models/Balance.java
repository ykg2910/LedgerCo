package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Balance {

    private final String bankName;
    private final String borrowerName;
    private final Integer amountPaid;
    private final Integer numEmiLeft;

    @Override
    public String toString() {
        return bankName + " " + borrowerName + " " + amountPaid + " " + numEmiLeft;
    }
}
