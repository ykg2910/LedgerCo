package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Payment {

    private final String bankName;
    private final String borrowerName;
    private final Integer lumpsumpAmount;
    private final Integer emiNumber;
}
